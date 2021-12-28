package com.xjf.yoga.config

import com.xjf.yoga.flexNode.YogaLogLevel

/**
 * Description:
 * Author: xuejingfei
 * E-mail: xue.jingfei@immomo.com
 * Date: 2021/12/27 5:21 下午
 */
public interface YogaLogger {
    fun log(level: YogaLogLevel, message: String)
}