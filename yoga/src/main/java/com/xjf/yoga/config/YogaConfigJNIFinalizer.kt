package com.xjf.yoga.config

import com.xjf.yoga.YogaNative

/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/28 3:18 下午
 */
class YogaConfigJNIFinalizer:YogaConfigJNIBase {
    constructor():super()

    @Throws(Throwable::class)
    protected fun finalize() {
        if (mNativePointer != 0L) {
            val nativePointer = mNativePointer
            mNativePointer = 0
            YogaNative.jni_YGConfigFreeJNI(nativePointer)
        }
    }
}