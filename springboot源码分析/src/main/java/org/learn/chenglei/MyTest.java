package org.learn.chenglei;

import org.springframework.util.Assert;

public class MyTest {

    public static void main(String[] args) {
        Test11 test11 = new Test11();
        Assert.isInstanceOf(Test.class, test11);
        ((Test)test11).test();
    }
}
