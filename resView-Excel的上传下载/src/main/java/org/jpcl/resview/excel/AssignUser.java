package org.jpcl.resview.excel;

/**
 * @author Administrator
 */
public class AssignUser {
    @ExcelAnnotation(columnIndex = 0, columnName = "通行证")
    private String passport;

    @ExcelAnnotation(columnIndex = 1, columnName = "用户的yyuid")
    private long yyuid;

    @ExcelAnnotation(columnIndex = 2, columnName = "客服名")
    private String customer;

    @ExcelAnnotation(columnIndex = 3, columnName = "分配时间")
    private String time;

    @ExcelAnnotation(columnIndex = 4, columnName = "是否好友")
    private String isFriend;

    @ExcelAnnotation(columnIndex = 5, columnName = "是否微信好友")
    private String isWFriend;

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public long getYyuid() {
        return yyuid;
    }

    public void setYyuid(long yyuid) {
        this.yyuid = yyuid;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(String isFriend) {
        this.isFriend = isFriend;
    }

    public String getIsWFriend() {
        return isWFriend;
    }

    public void setIsWFriend(String isWFriend) {
        this.isWFriend = isWFriend;
    }
}
