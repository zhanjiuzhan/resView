package org.jpcl.resview.demo.cors;

import org.jpcl.resview.demo.model.User;
import org.jpcl.resview.view.JcJsonView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 跨域请求的demo
 * @author Administrator
 */
@Controller
public class CorsController {

    @CrossOrigin
    @GetMapping("/cors")
    public JcJsonView test() {
        User user = new User();
        user.setName("cors");
        user.setId("1111");
        user.setAge(23);
        return new JcJsonView(user);
    }

    @CrossOrigin
    @GetMapping("/login")
    public JcJsonView login() {
        User user = new User();
        user.setName("cors");
        user.setId("1111");
        user.setAge(23);
        return new JcJsonView(user);
    }
}
