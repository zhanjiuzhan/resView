package org.spring.core.service;

import org.spring.core.annotation.MyAutowired;
import org.spring.core.annotation.MyComponent;
import org.spring.core.dao.MyDao;

/**
 * @author Administrator
 */
@MyComponent
public class MyService {

    @MyAutowired
    public MyDao myDao;

    public String say() {
        return myDao.say();
    }
}
