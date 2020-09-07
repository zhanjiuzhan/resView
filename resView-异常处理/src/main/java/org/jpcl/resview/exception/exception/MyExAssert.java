package org.jpcl.resview.exception.exception;

/**
 * 一个专门处理自定义异常的断言
 * @author Administrator
 */
public interface MyExAssert {

    /**
     * 创建异常
     * @param args
     * @return
     */
    MySelfException newException(Object... args);

    /**
     * 创建异常
     * @param t
     * @param args
     * @return
     */
    MySelfException newException(Throwable t, Object... args);

    /**
     * 断言该对象不能为空对象
     * 特别的对于string 不能是空字符串
     * @param obj 判断的对象
     */
    default void assertNotNull(Object obj) {
        if (obj == null) {
            throw newException();
        }
        if (obj instanceof String) {
            if (((String) obj).trim().length() == 0) {
                throw newException();
            }
        }
    }

    /**
     * 断言该对象不能为空对象
     * 特别的对于string 不能是空字符串
     * 带参数
     * @param obj 判断的对象
     * @param args message占位符对应的参数列表
     */
    default void assertNotNull(Object obj, Object... args) {
        if (obj == null) {
            throw newException(args);
        }
        if (obj instanceof String) {
            if (((String) obj).trim().length() == 0) {
                throw newException(args);
            }
        }
    }
}
