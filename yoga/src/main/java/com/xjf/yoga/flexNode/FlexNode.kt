package com.xjf.yoga.flexNode

import com.xjf.yoga.node.INode

/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/27 2:55 下午
 */
abstract class FlexNode : INode<FlexNode> {

    abstract override fun addChildAt(child: FlexNode, i:Int)

    abstract fun setIsReferenceBaseline(isReferenceBaseline:Boolean)

    abstract  fun isReferenceBaseline():Boolean

    abstract override fun indexOf(child: FlexNode):Int

    abstract fun hasNewLayout():Boolean

    abstract fun copyStyle(srcNode: FlexNode)

    abstract fun markLayoutSeen()

    abstract fun getStyleDirection(): YogaDirection

    abstract fun setDirection(direction: YogaDirection)

    abstract fun getFlexDirection(): YogaFlexDirection

    abstract fun setFlexDirection(flexDirection: YogaFlexDirection)

    abstract fun getJustifyContent(): YogaJustify

    abstract fun setJustifyContent(yogaJustify: YogaJustify)

    abstract fun getAlignItems(): YogaAlign

    abstract fun setAlignItems(alignItems: YogaAlign)

    abstract fun getAlignSelf(): YogaAlign

    abstract fun setAlignSelf(alignSelf: YogaAlign)

    abstract fun getAlignContent(): YogaAlign

    abstract fun setAlignContent(alignContent: YogaAlign)

    abstract fun getPositionType(): YogaPositionType

    abstract fun setPositionType(positionType: YogaPositionType)

    abstract fun getWrap(): YogaWrap

    abstract fun setWrap(flexWrap: YogaWrap)

    abstract fun getOverflow(): YogaOverflow

    abstract fun setOverflow(overflow: YogaOverflow)

    abstract fun getDisplay(): YogaDisplay

    abstract fun setDisplay(display: YogaDisplay)

    abstract fun getFlex(): Float

    abstract fun setFlex(flex: Float)

    abstract fun getFlexGrow(): Float

    abstract fun setFlexGrow(flexGrow: Float)

    abstract fun getFlexShrink(): Float

    abstract fun setFlexShrink(flexShrink: Float)

    abstract fun getFlexBasis(): YogaValue

    abstract fun setFlexBasis(flexBasis: Float)

    abstract fun setFlexBasisPercent(percent: Float)

    abstract fun setFlexBasisAuto()

    abstract fun getMargin(edge: YogaEdge): YogaValue

    abstract fun setMargin(edge: YogaEdge, margin: Float)

    abstract fun setMarginPercent(edge: YogaEdge, percent: Float)

    abstract fun setMarginAuto(edge: YogaEdge)

    abstract fun getPadding(edge: YogaEdge): YogaValue?

    abstract fun setPadding(edge: YogaEdge, padding: Float)

    abstract fun setPaddingPercent(edge: YogaEdge, percent: Float)

    abstract fun getBorder(edge: YogaEdge): Float

    abstract fun setBorder(edge: YogaEdge, border: Float)

    abstract fun getPosition(edge: YogaEdge): YogaValue

    abstract fun setPosition(edge: YogaEdge, position: Float)

    abstract fun setPositionPercent(edge: YogaEdge, percent: Float)

    abstract fun getWidth(): YogaValue


    abstract fun setWidthPercent(percent: Float)

    abstract fun setWidthAuto()

    abstract fun getHeight(): YogaValue


    abstract fun setHeightPercent(percent: Float)

    abstract fun setHeightAuto()

    abstract fun getMinWidth(): YogaValue

    abstract fun setMinWidth(minWidth: Float)

    abstract fun setMinWidthPercent(percent: Float)

    abstract fun getMinHeight(): YogaValue

    abstract fun setMinHeight(minHeight: Float)

    abstract fun setMinHeightPercent(percent: Float)

    abstract fun getMaxWidth(): YogaValue

    abstract fun setMaxWidth(maxWidth: Float)

    abstract fun setMaxWidthPercent(percent: Float)

    abstract fun getMaxHeight(): YogaValue?

    abstract fun setMaxHeight(maxheight: Float)

    abstract fun setMaxHeightPercent(percent: Float)

    abstract fun getAspectRatio(): Float

    abstract fun setAspectRatio(aspectRatio: Float)


    abstract fun getLayoutMargin(edge: YogaEdge): Float

    abstract fun getLayoutPadding(edge: YogaEdge): Float

    abstract fun getLayoutBorder(edge: YogaEdge): Float

    abstract fun getLayoutDirection(): YogaDirection?


    abstract fun setBaselineFunction(baselineFunction: YogaBaselineFunction?)

    abstract fun isMeasureDefined(): Boolean

    abstract fun isBaselineDefined(): Boolean



}