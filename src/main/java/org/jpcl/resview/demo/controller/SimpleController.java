package org.jpcl.resview.demo.controller;

import org.jpcl.resview.demo.model.User;
import org.jpcl.resview.view.JcJsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/sample")
@CrossOrigin(origins = "*")
public class SimpleController {

    Logger logger = LoggerFactory.getLogger(SimpleController.class);
    @RequestMapping("/getUser")
    public JcJsonView getUser() {
        logger.info("getUser {} {}", "aa", "s");

        User user = new User();
        user.setAge(11);
        user.setId("12331");
        user.setName("dw_chenglei");
        JcJsonView jc = new JcJsonView("5001");
        return jc;
    }
}
