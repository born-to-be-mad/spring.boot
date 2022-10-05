package by.dma.aop.aspect;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.10
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.METHOD })
public @interface LogMethod {

  boolean logArguments() default true;

}
