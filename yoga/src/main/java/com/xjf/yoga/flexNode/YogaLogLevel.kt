package com.xjf.yoga.flexNode

/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/27 5:22 下午
 */
enum class YogaLogLevel(mIntValue:Int) {
    ERROR(0),
    WARN(1),
    INFO(2),
    DEBUG(3),
    VERBOSE(4),
    FATAL(5);

    companion object {
        fun fromInt(value :Int): YogaLogLevel {
           return when(value) {
                0 -> ERROR
                1 -> WARN
                2 -> INFO
                3 -> DEBUG
                4 -> VERBOSE
                5 -> FATAL
                else -> throw IllegalArgumentException("Unknown enum value: $value")
           }
        }
    }
}