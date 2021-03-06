package net.kiberion.swampmachine.gui.annotations;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RUNTIME)
public @interface InjectChild {

    String id();
    String[] methodProperties() default {};
    
}
