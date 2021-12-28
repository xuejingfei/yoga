package com.xjf.yoga.flexNode

/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/27 3:46 下午
 */
enum class YogaFlexDirection(var mIntValue:Int) {
    COLUMN(0),
    COLUMN_REVERSE(1),
    ROW(2),
    ROW_REVERSE(3);

    companion object {
        fun fromInt(value: Int): YogaFlexDirection {
            return when (value) {
                0 -> COLUMN
                1 -> COLUMN_REVERSE
                2 -> ROW
                3 -> ROW_REVERSE
                else -> throw IllegalArgumentException("Unknown enum value: $value")
            }
        }
    }
}