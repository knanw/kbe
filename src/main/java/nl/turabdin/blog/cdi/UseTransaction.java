package nl.turabdin.blog.cdi;


import java.lang.annotation.ElementType;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import javax.interceptor.InterceptorBinding;

/**
* Annotation to use {@link TransactionInterceptor}
*
* @author marcus
*/
@Inherited
@InterceptorBinding
@Retention(RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface UseTransaction {

}