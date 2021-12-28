package com.xjf.yoga.flexNode

/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/27 3:17 下午
 */
enum class YogaMeasureMode(val mIntValue:Int) {
    UNDEFINED(0),
    EXACTLY(1),
    AT_MOST(2);

    companion object {
        fun fromInt(value: Int): YogaMeasureMode {
            return when (value) {
                0 -> UNDEFINED
                1 -> EXACTLY
                2 -> AT_MOST
                else -> throw IllegalArgumentException("Unknown enum value: $value")
            }
        }

    }
}
