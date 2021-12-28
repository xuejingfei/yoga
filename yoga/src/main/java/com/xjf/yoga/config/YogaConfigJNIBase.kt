package com.xjf.yoga.config

import com.xjf.yoga.YogaNative

/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/27 5:39 下午
 */
abstract class YogaConfigJNIBase :YogaConfig{

    var mNativePointer:Long = 0

    private var mLogger:YogaLogger? =null

    constructor():this(YogaNative.jni_YGConfigNewJNI())

    constructor(nativePointer:Long) {
        check(nativePointer != 0L) { "Failed to allocate native memory" }
        mNativePointer = nativePointer
    }

    override fun setExperimentalFeatureEnabled(feature: YogaExperimentalFeature, enabled: Boolean) {
        YogaNative.jni_YGConfigSetExperimentalFeatureEnabledJNI(mNativePointer, feature.mIntValue, enabled)
    }

    override fun setUseWebDefaults(useWebDefaults: Boolean) {
        YogaNative.jni_YGConfigSetUseWebDefaultsJNI(mNativePointer, useWebDefaults)
    }

    override fun setPrintTreeFlag(enable: Boolean) {
        YogaNative.jni_YGConfigSetPrintTreeFlagJNI(mNativePointer, enable)
    }

    override fun setPointScaleFactor(pixelsInPoint: Float) {
        YogaNative.jni_YGConfigSetPointScaleFactorJNI(mNativePointer, pixelsInPoint)
    }

    /**
     * Yoga previously had an error where containers would take the maximum space possible instead of the minimum
     * like they are supposed to. In practice this resulted in implicit behaviour similar to align-self: stretch;
     * Because this was such a long-standing bug we must allow legacy users to switch back to this behaviour.
     */
    override fun setUseLegacyStretchBehaviour(useLegacyStretchBehaviour: Boolean) {
        YogaNative.jni_YGConfigSetUseLegacyStretchBehaviourJNI(mNativePointer, useLegacyStretchBehaviour)
    }

    /**
     * If this flag is set then yoga would diff the layout without legacy flag and would set a bool in
     * FlexNode(mDoesLegacyStretchFlagAffectsLayout) with true if the layouts were different and false
     * if not
     */
    override fun setShouldDiffLayoutWithoutLegacyStretchBehaviour(
            shouldDiffLayoutWithoutLegacyStretchBehaviour: Boolean) {
        YogaNative.jni_YGConfigSetShouldDiffLayoutWithoutLegacyStretchBehaviourJNI(
                mNativePointer, shouldDiffLayoutWithoutLegacyStretchBehaviour)
    }

    override fun setLogger(logger: YogaLogger) {
        this.mLogger = logger
        YogaNative.jni_YGConfigSetLoggerJNI(mNativePointer, logger)
    }

    override fun getLogger(): YogaLogger? {
        return mLogger
    }

    override fun getNativePointer(): Long {
        return mNativePointer
    }


}