//
// Created by BJ3376 on 2021/12/28.
//

#ifndef YOGA_YOGAJNIEXCEPTION_H
#define YOGA_YOGAJNIEXCEPTION_H
#include <stdexcept>
#include <string>
#include "common.h"
namespace facebook {
namespace yoga {
namespace vanillajni {
class YogaJniException : public std::exception {
public:
    YogaJniException();
    ~YogaJniException() override;

    //显示调用
    explicit YogaJniException(jthrowable throwable);

    YogaJniException(YogaJniException&& rhs);

    YogaJniException(const YogaJniException& other);

    ScopedLocalRef<jthrowable> getThrowable() const noexcept;


private:
    ScopedGlobalRef<jthrowable> throwable_;

};
}
}
}


#endif //YOGA_YOGAJNIEXCEPTION_H
