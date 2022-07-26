package me.jaeseong.javatest.tag;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Test
@Tag("local")
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalTest {
}
