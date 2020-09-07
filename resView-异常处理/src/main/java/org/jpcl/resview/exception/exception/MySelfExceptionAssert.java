package org.jpcl.resview.exception.exception;

import java.text.MessageFormat;

/**
 * @author Administrator
 */
public interface MySelfExceptionAssert extends MyExResponseEnum, MyExAssert {

    @Override
    default MySelfException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new MySelfException(this, args, msg);
    }

    @Override
    default MySelfException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new MySelfException(this, args, msg, t);
    }
}
