//
// Created by BJ3376 on 2021/12/28.
//

#pragma once

#include <jni.h>
#include <cstddef>
#include <type_traits>
#include "corefunctions.h"


namespace facebook {
namespace yoga {
namespace vanillajni {
template <typename  T>
class ScopedGlobalRef{
    static_assert( std::is_same<T, jclass>() || std::is_same<T, jobject>() ||
                   std::is_same<T, jstring>() || std::is_same<T, jthrowable>() ||
                   std::is_same<T, jbyteArray>() || std::is_same<T, jintArray>() ||
                   std::is_same<T, jshortArray>() || std::is_same<T, jcharArray>() ||
                   std::is_same<T, jlongArray>() || std::is_same<T, jfloatArray>() ||
                   std::is_same<T, jdoubleArray>() || std::is_same<T, jobjectArray>() ||
                   std::is_same<T, jbooleanArray>(), "ScopedGlobalRef instantiated for invalid type");
public:
    ScopedGlobalRef(T globalRef):mGlobalRef(globalRef) {};

    explicit ScopedGlobalRef(): mGlobalRef(NULL){};

    ScopedGlobalRef(ScopedGlobalRef&& s):mGlobalRef(s.release()) {}

    ScopedGlobalRef& operator=(ScopedGlobalRef&& s) {
        reset(s.release())
    }

    ~ScopedGlobalRef() {
        reset(NULL)
    }

    T release() {
        T globalRef = mGlobalRef;
        mGlobalRef = NULL;
        return globalRef;
    }

    void reset(T ptr = NULL) {
        if (ptr != mGlobalRef) {
            if (mGlobalRef != NULL) {
                vanillajni::getCurrentEnv()->DeleteGlobalRef(mGlobalRef)
            }
            mGlobalRef = ptr;
        }
    }

    T get() const { return mGlobalRef; }

    operator bool() const { return mGlobalRef !=NULL; }

    ScopedGlobalRef(const ScopedGlobalRef& ref) = delete;
    ScopedGlobalRef& operator=(const ScopedGlobalRef& other) = delete;

private:
    T mGlobalRef;
}

template <typename T>
ScopedGlobalRef<T> make_global_ref(T globalRef) {
    return ScopedGlobalRef<T>(globalRef);
}

}
}
}
