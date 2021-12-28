package com.xjf.yoga

import com.xjf.yoga.config.YogaLogger
import com.xjf.yoga.flexNode.YogaNodeJNIBase

/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/27 4:58 下午
 */
object YogaNative {

    // JNI methods that use Vanilla JNI
    // YGConfig related
    external fun jni_YGConfigNewJNI(): Long
    external fun jni_YGConfigFreeJNI(nativePointer: Long)
    external fun jni_YGConfigSetExperimentalFeatureEnabledJNI(nativePointer: Long, feature: Int, enabled: Boolean)
    external fun jni_YGConfigSetUseWebDefaultsJNI(nativePointer: Long, useWebDefaults: Boolean)
    external fun jni_YGConfigSetPrintTreeFlagJNI(nativePointer: Long, enable: Boolean)
    external fun jni_YGConfigSetPointScaleFactorJNI(nativePointer: Long, pixelsInPoint: Float)
    external fun jni_YGConfigSetUseLegacyStretchBehaviourJNI(nativePointer: Long, useLegacyStretchBehaviour: Boolean)
    external fun jni_YGConfigSetShouldDiffLayoutWithoutLegacyStretchBehaviourJNI(nativePointer: Long, shouldDiffLayoutWithoutLegacyStretchBehaviour: Boolean)
    external fun jni_YGConfigSetLoggerJNI(nativePointer: Long, logger: YogaLogger)

    // YGNode related
    open external fun jni_YGNodeNewJNI(): Long
    external fun jni_YGNodeNewWithConfigJNI(configPointer: Long): Long
    external fun jni_YGNodeFreeJNI(nativePointer: Long)
    external fun jni_YGNodeResetJNI(nativePointer: Long)
    external fun jni_YGNodeInsertChildJNI(nativePointer: Long, childPointer: Long, index: Int)
    external fun jni_YGNodeSwapChildJNI(nativePointer: Long, childPointer: Long, index: Int)
    external fun jni_YGNodeSetIsReferenceBaselineJNI(nativePointer: Long, isReferenceBaseline: Boolean)
    external fun jni_YGNodeIsReferenceBaselineJNI(nativePointer: Long): Boolean
    external fun jni_YGNodeClearChildrenJNI(nativePointer: Long)
    external fun jni_YGNodeRemoveChildJNI(nativePointer: Long, childPointer: Long)
    external fun jni_YGNodeCalculateLayoutJNI(nativePointer: Long, width: Float, height: Float, nativePointers: LongArray?, nodes: Array<YogaNodeJNIBase>)
    external fun jni_YGNodeMarkDirtyJNI(nativePointer: Long)
    external fun jni_YGNodeIsDirtyJNI(nativePointer: Long): Boolean
    external fun jni_YGNodeCopyStyleJNI(dstNativePointer: Long, srcNativePointer: Long)
    external fun jni_YGNodeStyleGetDirectionJNI(nativePointer: Long): Int
    external fun jni_YGNodeStyleSetDirectionJNI(nativePointer: Long, direction: Int)
    external fun jni_YGNodeStyleGetFlexDirectionJNI(nativePointer: Long): Int
    external fun jni_YGNodeStyleSetFlexDirectionJNI(nativePointer: Long, flexDirection: Int)
    external fun jni_YGNodeStyleGetJustifyContentJNI(nativePointer: Long): Int
    external fun jni_YGNodeStyleSetJustifyContentJNI(nativePointer: Long, justifyContent: Int)
    external fun jni_YGNodeStyleGetAlignItemsJNI(nativePointer: Long): Int
    external fun jni_YGNodeStyleSetAlignItemsJNI(nativePointer: Long, alignItems: Int)
    external fun jni_YGNodeStyleGetAlignSelfJNI(nativePointer: Long): Int
    external fun jni_YGNodeStyleSetAlignSelfJNI(nativePointer: Long, alignSelf: Int)
    external fun jni_YGNodeStyleGetAlignContentJNI(nativePointer: Long): Int
    external fun jni_YGNodeStyleSetAlignContentJNI(nativePointer: Long, alignContent: Int)
    external fun jni_YGNodeStyleGetPositionTypeJNI(nativePointer: Long): Int
    external fun jni_YGNodeStyleSetPositionTypeJNI(nativePointer: Long, positionType: Int)
    external fun jni_YGNodeStyleGetFlexWrapJNI(nativePointer: Long): Int
    external fun jni_YGNodeStyleSetFlexWrapJNI(nativePointer: Long, wrapType: Int)
    external fun jni_YGNodeStyleGetOverflowJNI(nativePointer: Long): Int
    external fun jni_YGNodeStyleSetOverflowJNI(nativePointer: Long, overflow: Int)
    external fun jni_YGNodeStyleGetDisplayJNI(nativePointer: Long): Int
    external fun jni_YGNodeStyleSetDisplayJNI(nativePointer: Long, display: Int)
    external fun jni_YGNodeStyleGetFlexJNI(nativePointer: Long): Float
    external fun jni_YGNodeStyleSetFlexJNI(nativePointer: Long, flex: Float)
    external fun jni_YGNodeStyleGetFlexGrowJNI(nativePointer: Long): Float
    external fun jni_YGNodeStyleSetFlexGrowJNI(nativePointer: Long, flexGrow: Float)
    external fun jni_YGNodeStyleGetFlexShrinkJNI(nativePointer: Long): Float
    external fun jni_YGNodeStyleSetFlexShrinkJNI(nativePointer: Long, flexShrink: Float)
    external fun jni_YGNodeStyleGetFlexBasisJNI(nativePointer: Long): Long
    external fun jni_YGNodeStyleSetFlexBasisJNI(nativePointer: Long, flexBasis: Float)
    external fun jni_YGNodeStyleSetFlexBasisPercentJNI(nativePointer: Long, percent: Float)
    external fun jni_YGNodeStyleSetFlexBasisAutoJNI(nativePointer: Long)
    external fun jni_YGNodeStyleGetMarginJNI(nativePointer: Long, edge: Int): Long
    external fun jni_YGNodeStyleSetMarginJNI(nativePointer: Long, edge: Int, margin: Float)
    external fun jni_YGNodeStyleSetMarginPercentJNI(nativePointer: Long, edge: Int, percent: Float)
    external fun jni_YGNodeStyleSetMarginAutoJNI(nativePointer: Long, edge: Int)
    external fun jni_YGNodeStyleGetPaddingJNI(nativePointer: Long, edge: Int): Long
    external fun jni_YGNodeStyleSetPaddingJNI(nativePointer: Long, edge: Int, padding: Float)
    external fun jni_YGNodeStyleSetPaddingPercentJNI(nativePointer: Long, edge: Int, percent: Float)
    external fun jni_YGNodeStyleGetBorderJNI(nativePointer: Long, edge: Int): Float
    external fun jni_YGNodeStyleSetBorderJNI(nativePointer: Long, edge: Int, border: Float)
    external fun jni_YGNodeStyleGetPositionJNI(nativePointer: Long, edge: Int): Long
    external fun jni_YGNodeStyleSetPositionJNI(nativePointer: Long, edge: Int, position: Float)
    external fun jni_YGNodeStyleSetPositionPercentJNI(nativePointer: Long, edge: Int, percent: Float)
    external fun jni_YGNodeStyleGetWidthJNI(nativePointer: Long): Long
    external fun jni_YGNodeStyleSetWidthJNI(nativePointer: Long, width: Float)
    external fun jni_YGNodeStyleSetWidthPercentJNI(nativePointer: Long, percent: Float)
    external fun jni_YGNodeStyleSetWidthAutoJNI(nativePointer: Long)
    external fun jni_YGNodeStyleGetHeightJNI(nativePointer: Long): Long
    external fun jni_YGNodeStyleSetHeightJNI(nativePointer: Long, height: Float)
    external fun jni_YGNodeStyleSetHeightPercentJNI(nativePointer: Long, percent: Float)
    external fun jni_YGNodeStyleSetHeightAutoJNI(nativePointer: Long)
    external fun jni_YGNodeStyleGetMinWidthJNI(nativePointer: Long): Long
    external fun jni_YGNodeStyleSetMinWidthJNI(nativePointer: Long, minWidth: Float)
    external fun jni_YGNodeStyleSetMinWidthPercentJNI(nativePointer: Long, percent: Float)
    external fun jni_YGNodeStyleGetMinHeightJNI(nativePointer: Long): Long
    external fun jni_YGNodeStyleSetMinHeightJNI(nativePointer: Long, minHeight: Float)
    external fun jni_YGNodeStyleSetMinHeightPercentJNI(nativePointer: Long, percent: Float)
    external fun jni_YGNodeStyleGetMaxWidthJNI(nativePointer: Long): Long
    external fun jni_YGNodeStyleSetMaxWidthJNI(nativePointer: Long, maxWidth: Float)
    external fun jni_YGNodeStyleSetMaxWidthPercentJNI(nativePointer: Long, percent: Float)
    external fun jni_YGNodeStyleGetMaxHeightJNI(nativePointer: Long): Long
    external fun jni_YGNodeStyleSetMaxHeightJNI(nativePointer: Long, maxheight: Float)
    external fun jni_YGNodeStyleSetMaxHeightPercentJNI(nativePointer: Long, percent: Float)
    external fun jni_YGNodeStyleGetAspectRatioJNI(nativePointer: Long): Float
    external fun jni_YGNodeStyleSetAspectRatioJNI(nativePointer: Long, aspectRatio: Float)
    external fun jni_YGNodeSetHasMeasureFuncJNI(nativePointer: Long, hasMeasureFunc: Boolean)
    external fun jni_YGNodeSetHasBaselineFuncJNI(nativePointer: Long, hasMeasureFunc: Boolean)
    external fun jni_YGNodePrintJNI(nativePointer: Long)
    external fun jni_YGNodeCloneJNI(nativePointer: Long): Long


}