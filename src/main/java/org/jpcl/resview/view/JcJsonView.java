package org.jpcl.resview.view;

import org.jpcl.resview.view.model.JsonRes;
import org.jpcl.resview.view.resolver.impl.JcJsonViewResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class JcJsonView extends ModelAndView {

    private JsonRes jsonRes;

    /**
     * 返回的code
     */
    private static Map<String, String> code = new HashMap() {
        {
            put("500", "服务器繁忙， 请稍后再试。");
        }
    };

    {
        jsonRes = new JsonRes();
        super.setView(new JcJsonViewResolver(jsonRes));
    }

    public JcJsonView() {}

    /*
    public JcJsonView(String code) {
        if (code == null || code.trim().length() < 3) {
            return;
        }
        int retCode = Integer.valueOf(code.substring(0, 3));
        if (retCode != 200) {
            this.jsonRes.setStatus(retCode);
            this.jsonRes.setMsg(JcJsonView.code.getOrDefault(code, "500"));
        }
    }*/

    public JcJsonView(Object data) {
        if (data == null) {
            return;
        }
        this.jsonRes.setData(data);
    }
}
