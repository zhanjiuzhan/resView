package org.jpcl.resview.view;

import org.jpcl.resview.view.model.TextRes;
import org.jpcl.resview.view.resolver.impl.JcTextViewResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 */
public class JcTextView  extends ModelAndView {
    private TextRes textRes;

    public JcTextView(String message) {
        this.textRes = new TextRes();
        textRes.setMessage(message);
        super.setView(new JcTextViewResolver(this.textRes));
    }
}
