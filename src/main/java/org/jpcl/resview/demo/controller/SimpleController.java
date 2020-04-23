package org.jpcl.resview.demo.controller;

import com.alibaba.fastjson.JSON;
import org.jpcl.resview.demo.model.User;
import org.jpcl.resview.view.JcResView;
import org.jpcl.resview.view.model.Status;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/sample")
public class SimpleController {

    @RequestMapping("/getUser")
    public JcResView getUser() {
        JcResView jc = new JcResView();
        User user = new User();
        user.setAge(11);
        user.setId("12331");
        user.setName("dw_chenglei");
        jc.setResObject(Status.R500);
        jc.setData(JSON.toJSONString(user));
        return jc;
    }
}
