package interceptor2;

import java.lang.annotation.ElementType;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
 
/**
 * @author Adam Warski (adam at warski dot org)
 */
@Retention(RUNTIME)
@Target({TYPE,ElementType.METHOD})
@InterceptorBinding
public @interface Secure {
    /**
     * @return The EL expression that should be evaluated. If it evaluates to 
     * {@code true}, access will be granted. The EL expression may reference 
     * any objects that are in any context, as well as the arguments of the method,
     * under the names {@code arg0, arg1, arg2, ...}.
     */
    @Nonbinding
    String  value();
}

//usage
//@Secure("#{loggedInUser.name == arg0.name}") 