package com.xjf.yoga.node

import com.xjf.yoga.flexNode.YogaMeasureFunction


/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/27 2:44 下午
 */
public interface INode<T> {
    fun reset()

    fun getChildCount():Int

    fun getChildAt(i:Int):T

    fun addChildAt(child:T,i:Int)

    fun removeChildAt(i:Int):T

    fun getOwner():T

    fun indexOf(child:T):Int

    fun calculateLayout(width:Float,height:Float)

    fun dirty()

    fun isDirty():Boolean

    fun setWidth(width:Float)

    fun setHeight(height:Float)

    fun getLayoutX():Float

    fun getLayoutY():Float

    fun getLayoutWidth():Float

    fun getLayoutHeight():Float

    fun setMeasureFunction(measureFunction: YogaMeasureFunction?)

    fun setData(data:Any)

    fun getData():Any?

    fun print()

    fun cloneWithoutChildren():T

    fun cloneWithChildren():T

}