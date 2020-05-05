package org.jpcl.resview.jsonp;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * @author Administrator
 */
@ControllerAdvice(annotations = {Jsonp.class})
public class JsonpControllerAdvice extends AbstractJsonpResponseBodyAdvice {
    /**
     * AbstractJsonpResponseBodyAdvice 用来支持jsonp
     */
    public JsonpControllerAdvice() {
        super("callback", "jsonp");
    }
}
