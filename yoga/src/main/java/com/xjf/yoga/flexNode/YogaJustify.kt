package com.xjf.yoga.flexNode

/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/27 3:58 下午
 */
enum class YogaJustify(var mIntValue:Int) {
    FLEX_START(0),
    CENTER(1),
    FLEX_END(2),
    SPACE_BETWEEN(3),
    SPACE_AROUND(4),
    SPACE_EVENLY(5);


    companion object {
        fun fromInt(value: Int): YogaJustify {
            return when (value) {
                0 -> FLEX_START
                1 -> CENTER
                2 -> FLEX_END
                3 -> SPACE_BETWEEN
                4 -> SPACE_AROUND
                5 -> SPACE_EVENLY
                else -> throw IllegalArgumentException("Unknown enum value: $value")
            }
        }
    }

}