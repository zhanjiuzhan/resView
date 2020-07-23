package org.jpcl.resview.excel;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.FIELD)
public @interface ExcelAnnotation {
    int columnIndex() default 0;
    String columnName() default "";
}
