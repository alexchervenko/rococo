package io.student.rococo.jupiter.annotation;

import io.student.rococo.jupiter.extension.CreateUserExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static io.student.rococo.test_data.Defaults.DEFAULT_USER_PASSWORD;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ExtendWith(CreateUserExtension.class)
public @interface User {
    String password() default DEFAULT_USER_PASSWORD;
}
