package org.jpcl.resview.excel;

/**
 * @author Administrator
 */
public class Student {

    @ExcelAnnotation(columnIndex = 0, columnName = "姓名")
    private String name;

    @ExcelAnnotation(columnIndex = 1, columnName = "年龄")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
