package com.example.mylibrary.util

import android.util.Log

object LogUtil {

    private var isDebug = true

    private var mTag = "LogUtils"

    //for error log
    fun error(msg: String) {
        if (isDebug) {
            Log.e(mTag, msg)
        }
    }

    //for warming log
    fun warn(msg: String) {
        if (isDebug) {
            Log.w(mTag, msg)
        }
    }

    //for info log
    fun info(msg: String) {
        if (isDebug) {
            Log.i(mTag, msg)
        }
    }

    //for debug log
    fun debug(msg: String) {
        if (isDebug) {
            Log.d(mTag, msg)
        }
    }

    //for verbose log
    fun verbose(msg: String) {
        if (isDebug) {
            Log.v(mTag, msg)
        }
    }

    //for error log
    fun e(msg: String) {
        if (isDebug) {
            Log.e(mTag, msg)
        }
    }

    //for warming log
    fun w(msg: String) {
        if (isDebug) {
            Log.w(mTag, msg)
        }
    }

    //for info log
    fun i(msg: String) {
        if (isDebug) {
            Log.i(mTag, msg)
        }
    }

    //for debug log
    fun d(msg: String) {
        if (isDebug) {
            Log.d(mTag, msg)
        }
    }

    //for verbose log
    fun v(msg: String) {
        if (isDebug) {
            Log.v(mTag, msg)
        }
    }


    //for warming log
    fun w(tag: String?, msg: String) {
        var tag = tag
        if (isDebug) {
            if (tag == null || "".equals(tag!!.trim { it <= ' ' }, ignoreCase = true)) {
                tag = mTag
            }
            Log.w(tag, msg)
        }
    }

    //for info log
    fun i(tag: String?, msg: String) {
        var tag = tag
        if (isDebug) {
            if (tag == null || "".equals(tag!!.trim { it <= ' ' }, ignoreCase = true)) {
                tag = mTag
            }
            Log.i(tag, msg)
        }
    }

    //for debug log
    fun d(tag: String?, msg: String) {
        var tag = tag
        if (isDebug) {
            if (tag == null || "".equals(tag!!.trim { it <= ' ' }, ignoreCase = true)) {
                tag = mTag
            }
            Log.d(tag, msg)
        }
    }

    //for verbose log
    fun v(tag: String?, msg: String) {
        var tag = tag
        if (isDebug) {
            if (tag == null || "".equals(tag!!.trim { it <= ' ' }, ignoreCase = true)) {
                tag = mTag
            }
            Log.v(tag, msg)
        }
    }

    //for verbose log
    fun e(tag: String?, msg: String) {
        var tag = tag
        if (isDebug) {
            if (tag == null || "".equals(tag!!.trim { it <= ' ' }, ignoreCase = true)) {
                tag = mTag
            }
            Log.e(tag, msg)
        }
    }

    fun setDebug(isDebug: Boolean) {
        LogUtil.isDebug = isDebug
    }

    fun isDebug(): Boolean {
        return isDebug
    }

    /**
     * 点击Log跳转到指定源码位置
     *
     * @param tag
     * @param msg
     */
    fun showLog(tag: String?, msg: String) {
        var tag = tag
        if (isDebug) {
            if (tag == null || "".equals(tag!!.trim { it <= ' ' }, ignoreCase = true)) {
                tag = mTag
            }
            val stackTraceElement = Thread.currentThread()
                .stackTrace
            var currentIndex = -1
            for (i in stackTraceElement.indices) {
                if (stackTraceElement[i].methodName.compareTo("showLog") == 0) {
                    currentIndex = i + 1
                    break
                }
            }
            if (currentIndex >= 0) {
                val fullClassName = stackTraceElement[currentIndex].className
                val className = fullClassName.substring(
                    fullClassName
                        .lastIndexOf(".") + 1
                )
                val methodName = stackTraceElement[currentIndex].methodName
                val lineNumber = (stackTraceElement[currentIndex].lineNumber).toString()

                Log.i(
                    tag, (msg + "\n  ---->at " + className + "." + methodName + "("
                            + className + ".java:" + lineNumber + ")")
                )
            } else {
                Log.i(tag, msg)
            }
        }
    }
}