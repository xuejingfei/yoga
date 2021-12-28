//
// Created by BJ3376 on 2021/12/28.
//

#pragma once

#include <jni.h>
#include <cstddef>
#include <type_traits>
namespace facebook {
namespace yoga {
namespace vanillajni {


template <typename T>
class ScopedLocalRef {
    static_assert(
            std::is_same<T, jclass>() || std::is_same<T, jobject>() ||
            std::is_same<T, jstring>() || std::is_same<T, jthrowable>() ||
            std::is_same<T, jbyteArray>() || std::is_same<T, jintArray>() ||
            std::is_same<T, jshortArray>() || std::is_same<T, jcharArray>() ||
            std::is_same<T, jlongArray>() || std::is_same<T, jfloatArray>() ||
            std::is_same<T, jdoubleArray>() || std::is_same<T, jobjectArray>() ||
            std::is_same<T, jbooleanArray>(),
            "ScopedLocalRef instantiated for invalid type");
public:

    ScopedLocalRef(JNIEnv* env, T localRef) : mEnv(env),mLocalRef(localRef){}

    explicit ScopedLocalRef(JNIEnv* env) : mEnv(env), mLocalRef(NULL){}

    ScopedLocalRef(ScopedLocaRef&& s):mEnv(s.mEnv), mLocalRef(s.release()) {}

    ~ScopedLocalRef() { reset(); }

    void reset(T ptr = NULL) {
        if (ptr != mLocalRef) {
            if (mLocalRef != NULL) {
                mEnv->DeleteLocalRef(mLocalRef);
            }
            mLocalRef = ptr;
        }
    }

    T release() {
        T localRef = mLocalRef;
        mLocalRef = NULL;
        return localRef;
    }

    T get() const {return mLocalRef}

    operator bool() const {return mLocalRef !=NULL;}

    ScopedLocalRef(const ScopedLocalRef& other) = delete;

    ScopedLocalRef& operator=(const  ScopedLocalRef& other) = delete;

private:
    JNIEnv* mEnv;
    T mLocalRef;

};

template<typename T>
ScopedLocalRef<T> make_local_ref(JNIEnv* env, T localRef) {
    return ScopedLocalRef<T>(env,localRef);
}

}
}
}