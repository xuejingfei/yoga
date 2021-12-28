//
// Created by BJ3376 on 2021/12/28.
//

#pragma once
#include <jni.h>
#include <cstddef>

namespace facebook {
namespace yoga  {
namespace vanillajni {
jint ensureInitialized(JNIEnv** env, JavaVM* vm);

JNIEnv* getCurrentEnv();

void logErrorMessageAndDie(const  char* message);


void assertNoPendingJniException(JNIEnv* env);

void assertNoPendingJniExceptionIf(JNIEnv* env,bool condition)
} // namespace vanillajni
} // namespace yoga
} // namespace facebook
