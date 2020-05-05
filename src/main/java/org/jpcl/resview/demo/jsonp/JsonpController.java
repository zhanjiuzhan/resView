package org.jpcl.resview.demo.jsonp;

import org.jpcl.resview.demo.model.User;
import org.jpcl.resview.jsonp.Jsonp;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 */
@Controller
@ComponentScan({"org.jpcl.resview.jsonp", "org.jpcl.resview.view.init"})
@Jsonp
public class JsonpController {

    @RequestMapping("/jsonp")
    @ResponseBody
    public User jsonp() {
        User user = new User();
        user.setName("dw_chenglei");
        return user;
    }
}
