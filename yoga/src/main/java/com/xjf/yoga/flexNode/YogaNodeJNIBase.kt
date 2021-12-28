package com.xjf.yoga.flexNode

import com.xjf.yoga.YogaNative
import com.xjf.yoga.config.YogaConfig
import com.xjf.yoga.config.YogaConfigJNIBase
import kotlin.experimental.inv

/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/27 4:36 下午
 */
open class YogaNodeJNIBase: FlexNode,Cloneable {

    companion object {
        /* Those flags needs be in sync with YGJNI.h */
        private val MARGIN: Byte = 1
        private val PADDING: Byte = 2
        private val BORDER: Byte = 4
        private val HAS_NEW_LAYOUT: Byte = 16

        private val LAYOUT_EDGE_SET_FLAG_INDEX: Byte = 0
        private val LAYOUT_WIDTH_INDEX: Byte = 1
        private val LAYOUT_HEIGHT_INDEX: Byte = 2
        private val LAYOUT_LEFT_INDEX: Byte = 3
        private val LAYOUT_TOP_INDEX: Byte = 4
        private val LAYOUT_DIRECTION_INDEX: Byte = 5
        private val LAYOUT_MARGIN_START_INDEX: Byte = 6
        private val LAYOUT_PADDING_START_INDEX: Byte = 10
        private val LAYOUT_BORDER_START_INDEX: Byte = 14
    }

    private var mOwner: YogaNodeJNIBase? = null

    private var mChildren:ArrayList<YogaNodeJNIBase>? = null

    private var mMeasureFunction: YogaMeasureFunction? =null

    private var mBaselineFunction: YogaBaselineFunction? =null

    protected var mNativePointer:Long = 0L

    private var mData: Any? =null

    private var arr:Array<Float>?=null

    private var mLayoutDirection:Int = 0
    private var mHasNewLayout = true

    constructor():this(YogaNative.jni_YGNodeNewJNI())

    constructor(config: YogaConfig):this(YogaNative.jni_YGNodeNewWithConfigJNI((config as YogaConfigJNIBase).mNativePointer))

    constructor(nativePointer:Long) {
        check(nativePointer != 0L) { "Failed to allocate native memory" }
        this.mNativePointer = nativePointer
    }



    override fun reset() {
        mMeasureFunction = null
        mBaselineFunction = null
        mData = null
        arr = null
        mHasNewLayout  = true
        mLayoutDirection = 0

        YogaNative.jni_YGNodeResetJNI(nativePointer = mNativePointer)
    }

    override fun addChildAt(child: FlexNode, i: Int) {
        val child = child as YogaNodeJNIBase
        check(child.mOwner !=null) {"Child already has a parent, it must be removed first." }

        if (mChildren ==null) {
            mChildren = ArrayList(4)
        }
        mChildren?.add(i,child)
        child.mOwner = this
        YogaNative.jni_YGNodeInsertChildJNI(mNativePointer,child.mNativePointer,i)
    }

    override fun setIsReferenceBaseline(isReferenceBaseline: Boolean) {
       YogaNative.jni_YGNodeSetIsReferenceBaselineJNI(mNativePointer, isReferenceBaseline)
    }

    override fun isReferenceBaseline(): Boolean {
       return YogaNative.jni_YGNodeIsReferenceBaselineJNI(mNativePointer)
    }

    override fun indexOf(child: FlexNode): Int {
        return if(mChildren ==null) -1 else mChildren!!.indexOf(child)
    }

    override fun hasNewLayout(): Boolean {
        return if (arr !=null) {
            arr!![LAYOUT_EDGE_SET_FLAG_INDEX.toInt()].toInt() and HAS_NEW_LAYOUT.toInt() == HAS_NEW_LAYOUT.toInt()
        } else {
            mHasNewLayout
        }
    }

    override fun copyStyle(srcNode: FlexNode) {
        YogaNative.jni_YGNodeCopyStyleJNI(mNativePointer,(srcNode as YogaNodeJNIBase).mNativePointer)
    }

    override fun markLayoutSeen() {
        if (arr !=null) {
            arr!![LAYOUT_EDGE_SET_FLAG_INDEX.toInt()] = (arr!![LAYOUT_EDGE_SET_FLAG_INDEX.toInt()].toInt() and HAS_NEW_LAYOUT.inv().toInt()).toFloat()
        }
        mHasNewLayout = false
    }

    override fun getStyleDirection(): YogaDirection {
       return YogaDirection.fromInt(YogaNative.jni_YGNodeStyleGetDirectionJNI(mNativePointer))
    }

    override fun setDirection(direction: YogaDirection) {
        YogaNative.jni_YGNodeStyleSetDirectionJNI(mNativePointer,direction.mIntValue)
    }

    override fun getFlexDirection(): YogaFlexDirection {
        return YogaFlexDirection.fromInt(YogaNative.jni_YGNodeStyleGetFlexDirectionJNI(mNativePointer))
    }

    override fun setFlexDirection(flexDirection: YogaFlexDirection) {
         YogaNative.jni_YGNodeStyleSetFlexDirectionJNI(mNativePointer,flexDirection.mIntValue)
    }

    override fun getJustifyContent(): YogaJustify {
       return YogaJustify.fromInt(YogaNative.jni_YGNodeStyleGetJustifyContentJNI(mNativePointer))
    }

    override fun setJustifyContent(yogaJustify: YogaJustify) {
        YogaNative.jni_YGNodeStyleSetJustifyContentJNI(mNativePointer,yogaJustify.mIntValue)
    }

    override fun getAlignItems(): YogaAlign {
       return YogaAlign.fromInt(YogaNative.jni_YGNodeStyleGetAlignItemsJNI(mNativePointer))
    }

    override fun setAlignItems(alignItems: YogaAlign) {
        YogaNative.jni_YGNodeStyleSetAlignItemsJNI(mNativePointer,alignItems.mIntValue)
    }

    override fun getAlignSelf(): YogaAlign {
       return YogaAlign.fromInt(YogaNative.jni_YGNodeStyleGetAlignSelfJNI(mNativePointer))
    }

    override fun setAlignSelf(alignSelf: YogaAlign) {
        YogaNative.jni_YGNodeStyleSetAlignSelfJNI(mNativePointer,alignSelf.mIntValue)
    }

    override fun getAlignContent(): YogaAlign {
        return YogaAlign.fromInt(YogaNative.jni_YGNodeStyleGetAlignContentJNI(mNativePointer))
    }

    override fun setAlignContent(alignContent: YogaAlign) {
      YogaNative.jni_YGNodeStyleSetAlignContentJNI(mNativePointer,alignContent.mIntValue)
    }

    override fun getPositionType(): YogaPositionType {
       return  YogaPositionType.fromInt(YogaNative.jni_YGNodeStyleGetPositionTypeJNI(mNativePointer))
    }

    override fun setPositionType(positionType: YogaPositionType) {
        YogaNative.jni_YGNodeStyleSetPositionTypeJNI(mNativePointer,positionType.intValue())
    }

    override fun getWrap(): YogaWrap {
        return  YogaWrap.fromInt(YogaNative.jni_YGNodeStyleGetFlexWrapJNI(mNativePointer))
    }

    override fun setWrap(flexWrap: YogaWrap) {
        YogaNative.jni_YGNodeStyleSetFlexWrapJNI(mNativePointer,flexWrap.intValue())
    }

    override fun getOverflow(): YogaOverflow {
        return YogaOverflow.fromInt(YogaNative.jni_YGNodeStyleGetOverflowJNI(mNativePointer))
    }

    override fun setOverflow(overflow: YogaOverflow) {
        YogaNative.jni_YGNodeStyleSetOverflowJNI(mNativePointer,overflow.intValue())
    }

    override fun getDisplay(): YogaDisplay {
       return YogaDisplay.fromInt(YogaNative.jni_YGNodeStyleGetDisplayJNI(mNativePointer))
    }

    override fun setDisplay(display: YogaDisplay) {
        YogaNative.jni_YGNodeStyleSetDisplayJNI(mNativePointer,display.intValue())
    }

    override fun getFlex(): Float {
       return YogaNative.jni_YGNodeStyleGetFlexJNI(mNativePointer)
    }

    override fun setFlex(flex: Float) {
        YogaNative.jni_YGNodeStyleSetFlexJNI(mNativePointer,flex = flex)
    }

    override fun getFlexGrow(): Float {
       return YogaNative.jni_YGNodeStyleGetFlexGrowJNI(mNativePointer)
    }

    override fun setFlexGrow(flexGrow: Float) {
       YogaNative.jni_YGNodeStyleSetFlexGrowJNI(mNativePointer,flexGrow)
    }

    override fun getFlexShrink(): Float {
        return YogaNative.jni_YGNodeStyleGetFlexShrinkJNI(mNativePointer)
    }

    override fun setFlexShrink(flexShrink: Float) {
        YogaNative.jni_YGNodeStyleSetFlexShrinkJNI(mNativePointer,flexShrink)
    }

    override fun getFlexBasis(): YogaValue {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetFlexBasisJNI(mNativePointer))
    }

    private fun valueFromLong(raw: Long): YogaValue {
        return YogaValue(raw.toFloat(), (raw shr 32).toInt())
    }

    override fun setFlexBasis(flexBasis: Float) {
        YogaNative.jni_YGNodeStyleSetFlexBasisJNI(mNativePointer,flexBasis)
    }

    override fun setFlexBasisPercent(percent: Float) {
        YogaNative.jni_YGNodeStyleSetFlexBasisPercentJNI(mNativePointer,percent)
    }

    override fun setFlexBasisAuto() {
       YogaNative.jni_YGNodeStyleSetFlexBasisAutoJNI(mNativePointer)
    }

    override fun getMargin(edge: YogaEdge): YogaValue {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMarginJNI(mNativePointer, edge.intValue()))
    }

    override fun setMargin(edge: YogaEdge, margin: Float) {
        YogaNative.jni_YGNodeStyleSetMarginJNI(mNativePointer, edge.intValue(), margin)
    }

    override fun setMarginPercent(edge: YogaEdge, percent: Float) {
        YogaNative.jni_YGNodeStyleSetMarginPercentJNI(mNativePointer, edge.intValue(), percent)
    }

    override fun setMarginAuto(edge: YogaEdge) {
        YogaNative.jni_YGNodeStyleSetMarginAutoJNI(mNativePointer, edge.intValue())
    }

    override fun getPadding(edge: YogaEdge): YogaValue {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetPaddingJNI(mNativePointer, edge.intValue()))
    }

    override fun setPadding(edge: YogaEdge, padding: Float) {
        YogaNative.jni_YGNodeStyleSetPaddingJNI(mNativePointer, edge.intValue(), padding)
    }

    override fun setPaddingPercent(edge: YogaEdge, percent: Float) {
        YogaNative.jni_YGNodeStyleSetPaddingPercentJNI(mNativePointer, edge.intValue(), percent)
    }

    override fun getBorder(edge: YogaEdge): Float {
        return YogaNative.jni_YGNodeStyleGetBorderJNI(mNativePointer, edge.intValue())
    }

    override fun setBorder(edge: YogaEdge, border: Float) {
        YogaNative.jni_YGNodeStyleSetBorderJNI(mNativePointer, edge.intValue(), border)
    }

    override fun getPosition(edge: YogaEdge): YogaValue {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetPositionJNI(mNativePointer, edge.intValue()))
    }

    override fun setPosition(edge: YogaEdge, position: Float) {
        YogaNative.jni_YGNodeStyleSetPositionJNI(mNativePointer, edge.intValue(), position)
    }

    override fun setPositionPercent(edge: YogaEdge, percent: Float) {
        YogaNative.jni_YGNodeStyleSetPositionPercentJNI(mNativePointer, edge.intValue(), percent)
    }

    override fun getWidth(): YogaValue {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetWidthJNI(mNativePointer))
    }

    override fun setWidthPercent(percent: Float) {
        YogaNative.jni_YGNodeStyleSetWidthPercentJNI(mNativePointer, percent)
    }

    override fun setWidthAuto() {
        YogaNative.jni_YGNodeStyleSetWidthAutoJNI(mNativePointer)
    }

    override fun getHeight(): YogaValue {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetHeightJNI(mNativePointer))
    }

    override fun setHeightPercent(percent: Float) {
        YogaNative.jni_YGNodeStyleSetHeightPercentJNI(mNativePointer, percent)
    }

    override fun setHeightAuto() {
        YogaNative.jni_YGNodeStyleSetHeightAutoJNI(mNativePointer)
    }

    override fun getMinWidth(): YogaValue {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMinWidthJNI(mNativePointer))
    }

    override fun setMinWidth(minWidth: Float) {
        YogaNative.jni_YGNodeStyleSetMinWidthJNI(mNativePointer, minWidth)
    }

    override fun setMinWidthPercent(percent: Float) {
        YogaNative.jni_YGNodeStyleSetMinWidthPercentJNI(mNativePointer, percent)
    }

    override fun getMinHeight(): YogaValue {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMinHeightJNI(mNativePointer))
    }

    override fun setMinHeight(minHeight: Float) {
        YogaNative.jni_YGNodeStyleSetMinHeightJNI(mNativePointer, minHeight)
    }

    override fun setMinHeightPercent(percent: Float) {
        YogaNative.jni_YGNodeStyleSetMinHeightPercentJNI(mNativePointer, percent)
    }

    override fun getMaxWidth(): YogaValue {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMaxWidthJNI(mNativePointer))
    }

    override fun setMaxWidth(maxWidth: Float) {
        YogaNative.jni_YGNodeStyleSetMaxWidthJNI(mNativePointer, maxWidth)
    }

    override fun setMaxWidthPercent(percent: Float) {
        YogaNative.jni_YGNodeStyleSetMaxWidthPercentJNI(mNativePointer, percent)
    }

    override fun getMaxHeight(): YogaValue {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMaxHeightJNI(mNativePointer))
    }

    override fun setMaxHeight(maxheight: Float) {
        YogaNative.jni_YGNodeStyleSetMaxHeightJNI(mNativePointer, maxheight)
    }

    override fun setMaxHeightPercent(percent: Float) {
        YogaNative.jni_YGNodeStyleSetMaxHeightPercentJNI(mNativePointer, percent)
    }

    override fun getAspectRatio(): Float {
        return YogaNative.jni_YGNodeStyleGetAspectRatioJNI(mNativePointer)
    }

    override fun setAspectRatio(aspectRatio: Float) {
        YogaNative.jni_YGNodeStyleSetAspectRatioJNI(mNativePointer, aspectRatio)
    }

    override fun getLayoutMargin(edge: YogaEdge): Float {
        return if (arr != null && arr!![LAYOUT_EDGE_SET_FLAG_INDEX.toInt()].toInt() and MARGIN.toInt() == MARGIN.toInt()) {
            when (edge) {
                YogaEdge.LEFT -> arr!![LAYOUT_MARGIN_START_INDEX.toInt()]
                YogaEdge.TOP -> arr!![LAYOUT_MARGIN_START_INDEX + 1]
                YogaEdge.RIGHT -> arr!![LAYOUT_MARGIN_START_INDEX + 2]
                YogaEdge.BOTTOM -> arr!![LAYOUT_MARGIN_START_INDEX + 3]
                YogaEdge.START -> if (getLayoutDirection() === YogaDirection.RTL) arr!![LAYOUT_MARGIN_START_INDEX + 2] else arr!![LAYOUT_MARGIN_START_INDEX.toInt()]
                YogaEdge.END -> if (getLayoutDirection() === YogaDirection.RTL) arr!![LAYOUT_MARGIN_START_INDEX.toInt()] else arr!![LAYOUT_MARGIN_START_INDEX + 2]
                else -> throw IllegalArgumentException("Cannot get layout margins of multi-edge shorthands")
            }
        } else {
            0f
        }
    }

    override fun getLayoutPadding(edge: YogaEdge): Float {
        return if (arr != null && arr!![LAYOUT_EDGE_SET_FLAG_INDEX.toInt()].toInt() and PADDING.toInt() == PADDING.toInt()) {
            val paddingStartIndex = (LAYOUT_PADDING_START_INDEX
                    - if (arr!![LAYOUT_EDGE_SET_FLAG_INDEX.toInt()].toInt() and MARGIN.toInt() == MARGIN.toInt()) 0 else 4)
            when (edge) {
                YogaEdge.LEFT -> arr!![paddingStartIndex]
                YogaEdge.TOP -> arr!![paddingStartIndex + 1]
                YogaEdge.RIGHT -> arr!![paddingStartIndex + 2]
                YogaEdge.BOTTOM -> arr!![paddingStartIndex + 3]
                YogaEdge.START -> if (getLayoutDirection() === YogaDirection.RTL) arr!![paddingStartIndex + 2] else arr!![paddingStartIndex]
                YogaEdge.END -> if (getLayoutDirection() === YogaDirection.RTL) arr!![paddingStartIndex] else arr!![paddingStartIndex + 2]
                else -> throw java.lang.IllegalArgumentException("Cannot get layout paddings of multi-edge shorthands")
            }
        } else {
            0f
        }
    }

    override fun getLayoutBorder(edge: YogaEdge): Float {
        return if (arr != null && arr!![LAYOUT_EDGE_SET_FLAG_INDEX.toInt()].toInt() and BORDER.toInt() == BORDER.toInt()) {
            val borderStartIndex = (LAYOUT_BORDER_START_INDEX
                    - (if (arr!![LAYOUT_EDGE_SET_FLAG_INDEX.toInt()].toInt() and MARGIN.toInt() == MARGIN.toInt()) 0 else 4)
                    - if (arr!![LAYOUT_EDGE_SET_FLAG_INDEX.toInt()].toInt() and PADDING.toInt() == PADDING.toInt()) 0 else 4)
            when (edge) {
                YogaEdge.LEFT -> arr!![borderStartIndex]
                YogaEdge.TOP -> arr!![borderStartIndex + 1]
                YogaEdge.RIGHT -> arr!![borderStartIndex + 2]
                YogaEdge.BOTTOM -> arr!![borderStartIndex + 3]
                YogaEdge.START -> if (getLayoutDirection() === YogaDirection.RTL) arr!![borderStartIndex + 2] else arr!![borderStartIndex]
                YogaEdge.END -> if (getLayoutDirection() === YogaDirection.RTL) arr!![borderStartIndex] else arr!![borderStartIndex + 2]
                else -> throw java.lang.IllegalArgumentException("Cannot get layout border of multi-edge shorthands")
            }
        } else {
            0f
        }
    }

    override fun getLayoutDirection(): YogaDirection {
        return YogaDirection.fromInt(
                if (arr != null) arr!![LAYOUT_DIRECTION_INDEX.toInt()].toInt() else mLayoutDirection)
    }

    override fun setBaselineFunction(baselineFunction: YogaBaselineFunction?) {
        mBaselineFunction = baselineFunction
        YogaNative.jni_YGNodeSetHasBaselineFuncJNI(mNativePointer, baselineFunction != null)
    }

    override fun isMeasureDefined(): Boolean {
        return mMeasureFunction != null
    }

    override fun isBaselineDefined(): Boolean {
        return mBaselineFunction != null
    }


    override fun getChildCount(): Int {
        return if (mChildren == null) 0 else mChildren!!.size
    }

    override fun getChildAt(i: Int): FlexNode {
        checkNotNull(mChildren) { "FlexNode does not have children" }
        return mChildren!![i]
    }

    override fun removeChildAt(i: Int): FlexNode {
        checkNotNull(mChildren) { "Trying to remove a child of a FlexNode that does not have children" }
        val child = mChildren!!.removeAt(i)
        child.mOwner = null
        YogaNative.jni_YGNodeRemoveChildJNI(mNativePointer, child.mNativePointer)
        return child
    }

    override fun getOwner(): FlexNode {
        return mOwner!!
    }

    override fun calculateLayout(width: Float, height: Float) {
        var nativePointers: LongArray?
        var nodes: Array<YogaNodeJNIBase>?

        val n = java.util.ArrayList<YogaNodeJNIBase>()
        n.add(this)
        for (i in n.indices) {
            val children: List<YogaNodeJNIBase>? = n[i].mChildren
            if (children != null) {
                n.addAll(children)
            }
        }

        nodes = n.toTypedArray()
        nativePointers = LongArray(nodes.size)
        for (i in nodes.indices) {
            nativePointers[i] = nodes[i].mNativePointer
        }

        YogaNative.jni_YGNodeCalculateLayoutJNI(mNativePointer, width, height, nativePointers, nodes)
    }

    override fun dirty() {
        YogaNative.jni_YGNodeMarkDirtyJNI(mNativePointer)
    }

    override fun isDirty(): Boolean {
        return YogaNative.jni_YGNodeIsDirtyJNI(mNativePointer)
    }

    override fun setWidth(width: Float) {
        YogaNative.jni_YGNodeStyleSetWidthJNI(mNativePointer, width)
    }

    override fun setHeight(height: Float) {
        YogaNative.jni_YGNodeStyleSetHeightJNI(mNativePointer, height)
    }

    override fun getLayoutX(): Float {
        return if (arr != null) arr!![LAYOUT_LEFT_INDEX.toInt()] else 0f
    }

    override fun getLayoutY(): Float {
        return if (arr != null) arr!![LAYOUT_TOP_INDEX.toInt()] else 0f
    }

    override fun getLayoutWidth(): Float {
        return if (arr != null) arr!![LAYOUT_WIDTH_INDEX.toInt()] else 0f
    }

    override fun getLayoutHeight(): Float {
        return if (arr != null) arr!![LAYOUT_HEIGHT_INDEX.toInt()] else 0f
    }

    override fun setMeasureFunction(measureFunction: YogaMeasureFunction?) {
        mMeasureFunction = measureFunction
        YogaNative.jni_YGNodeSetHasMeasureFuncJNI(mNativePointer, measureFunction != null)
    }

    override fun setData(data: Any) {
        mData = data
    }

    override fun getData(): Any? {
        return mData
    }

    override fun print() {
        YogaNative.jni_YGNodePrintJNI(mNativePointer)
    }

    override fun cloneWithoutChildren(): FlexNode {
        return try {
            val clonedYogaNode = super.clone() as YogaNodeJNIBase
            val clonedNativePointer = YogaNative.jni_YGNodeCloneJNI(mNativePointer)
            clonedYogaNode.mOwner = null
            clonedYogaNode.mNativePointer = clonedNativePointer
            clonedYogaNode.clearChildren()
            clonedYogaNode
        } catch (ex: CloneNotSupportedException) {
            // This class implements Cloneable, this should not happen
            throw RuntimeException(ex)
        }
    }


    private fun clearChildren() {
        mChildren = null
        YogaNative.jni_YGNodeClearChildrenJNI(mNativePointer)
    }

    override fun cloneWithChildren(): FlexNode {
        return try {
            val clonedYogaNode = super.clone() as YogaNodeJNIBase
            val clonedNativePointer = YogaNative.jni_YGNodeCloneJNI(mNativePointer)
            clonedYogaNode.mOwner = null
            clonedYogaNode.mNativePointer = clonedNativePointer
            for (i in 0 until clonedYogaNode.getChildCount()) {
                clonedYogaNode.swapChildAt(clonedYogaNode.getChildAt(i).cloneWithChildren(), i)
            }
            clonedYogaNode
        } catch (ex: CloneNotSupportedException) {
            // This class implements Cloneable, this should not happen
            throw java.lang.RuntimeException(ex)
        }
    }


    private fun swapChildAt(newChild: FlexNode, position: Int) {
        val child = newChild as YogaNodeJNIBase
        mChildren!!.removeAt(position)
        mChildren!!.add(position, child)
        child.mOwner = this
        YogaNative.jni_YGNodeSwapChildJNI(mNativePointer, child.mNativePointer, position)
    }

}