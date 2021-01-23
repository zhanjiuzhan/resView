package org.jpcl.resview.事件;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 * @author Administrator
 */
public class MyDefineEvent extends ApplicationEvent {

    private String message;

    /**
     * Create a new ApplicationEvent.
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyDefineEvent(Object source) {
        super(source);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
