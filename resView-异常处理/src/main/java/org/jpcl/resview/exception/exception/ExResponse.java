package org.jpcl.resview.exception.exception;

/**
 * @author Administrator
 */
public interface ExResponse {
    /**
     * 取得错误码
     * @return
     */
    int getCode();

    /**
     * 取得错误信息
     * @return
     */
    String getMessage();

}
