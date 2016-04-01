package interceptor3;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author happyFace
 */
@Logged
@Interceptor
public class LoggedInterceptor implements Serializable {

    public static final Logger LOGGER = Logger.getLogger(LoggedInterceptor.class.getName());
    public LoggedInterceptor() {
    }

    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext)
            throws Exception {
        LOGGER.log(Level.INFO, "Entering method: {0} in class {1} at Time {2}.",
                new Object[]{invocationContext.getMethod().getName(),
                    invocationContext.getMethod().getDeclaringClass().getName(),
                    new Date()});
        return invocationContext.proceed();
    }
//}  
}