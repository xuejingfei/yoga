package com.xjf.yoga.flexNode

/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/27 3:14 下午
 */
interface YogaMeasureFunction {


   fun measure(node: FlexNode, width:Float, widthMode: YogaMeasureMode, height:Float, heightMode: YogaMeasureMode)

}