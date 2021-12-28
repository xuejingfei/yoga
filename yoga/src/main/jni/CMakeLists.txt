# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# 把.so文件生成到libs目录下
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../libs/${ANDROID_ABI})

set(CMAKE_C_FLAGS_DEBUG "${CMAKE_C_FLAGS_DEBUG} -s" )
set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -s" )

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

#GLOB会遍历匹配目录的所有文件以及子目录下面的文件，产生一个由文件组成的列表，并将其保存到变量中
file(GLOB_RECURSE jni_SRC jni/*.cpp)
add_library( # Sets the name of the library.
             yoga

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             ${jni_SRC} )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

file(GLOB yogajni_version_script
        yogajni.version)
# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                        yoga
                       # Links the target library to the log library
                       # included in the NDK.
                        -Wl,
                        --gc-sections,
                        --version-script=${yogajni_version_script} yogacore )