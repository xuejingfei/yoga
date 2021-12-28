package com.xjf.yoga.flexNode

/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/27 3:35 下午
 */
enum class YogaDirection(val mIntValue:Int) {
    INHERIT(0),
    LTR(1),
    RTL(2);

    companion object {
        fun fromInt(value:Int): YogaDirection {
            return when(value) {
                0 -> INHERIT
                1 -> LTR
                2 -> RTL
                else -> throw IllegalArgumentException("Unknown enum value: $value")
            }
        }
    }
}