package com.xjf.yoga.flexNode

import com.xjf.yoga.YogaNative
import com.xjf.yoga.config.YogaConfig

/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/28 3:08 下午
 */
class YogaNodeJNIFinalizer:YogaNodeJNIBase {

    constructor():super()

    constructor(config:YogaConfig):super(config)

    @Throws(Throwable::class)
    protected fun finalize() {
        freeNatives()
    }

    private fun freeNatives() {
        if (this.mNativePointer != 0L) {
            val nativePointer = mNativePointer
            mNativePointer = 0
            YogaNative.jni_YGNodeFreeJNI(nativePointer)
        }
    }
}