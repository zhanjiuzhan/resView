package org.jpcl.resview.exception;

import org.jpcl.resview.view.JcJsonView;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Administrator
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public JcJsonView exception(Exception e) {
        return new JcJsonView(500);
    }
}
