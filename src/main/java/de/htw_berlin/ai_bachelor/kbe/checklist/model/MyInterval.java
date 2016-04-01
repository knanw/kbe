package de.htw_berlin.ai_bachelor.kbe.checklist.model;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MyIntervalValidator.class)
@Target( { FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyInterval {
	String message() default "Der Wert liegt nicht im erlaubten Bereich.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
