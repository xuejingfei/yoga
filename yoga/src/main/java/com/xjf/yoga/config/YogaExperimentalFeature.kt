package com.xjf.yoga.config


/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/27 5:30 下午
 */
enum class YogaExperimentalFeature(val mIntValue:Int) {
    WEB_FLEX_BASIS(0);

    companion object {
        fun fromInt(value: Int): YogaExperimentalFeature {
            return when (value) {
                0 -> WEB_FLEX_BASIS
                else -> throw IllegalArgumentException("Unknown enum value: $value")
            }
        }
    }

}