package org.jpcl.resview.access.token;

/**
 * @author Administrator
 */
final public class TokenConst {
    private TokenConst() {}

    /**
     * 加密的key
     */
    public static String SECRET = "9f6d5c6b4519c4bba50862006a18f493";

    /**
     * 过期时间 秒
     */
    public static int EXPIRE = 600;

    public static String SUB_USER = "jpcl";

    public static String BEARER = "jpcl_cl ";
}
