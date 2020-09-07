package org.jpcl.resview.exception;

import org.jpcl.resview.exception.exception.MySelfException;
import org.jpcl.resview.view.JcJsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Administrator
 */
@Component
@ControllerAdvice
@ConditionalOnWebApplication
@ConditionalOnMissingBean(UnifiedExceptionHandler.class)
public class UnifiedExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(UnifiedExceptionHandler.class);

    /**
     * 业务异常
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = MySelfException.class)
    public JcJsonView handleMySelfException(MySelfException e) {
        logger.error(e.getMessage(), e);
        return e.getExMessage();
    }
}
