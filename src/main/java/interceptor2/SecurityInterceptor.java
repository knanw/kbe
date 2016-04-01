package interceptor2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

import javax.el.ELContext;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;


@Interceptor
@Secure("")
public class SecurityInterceptor {
    private String getArgName(int index) { return "arg" + index; }
 
    @AroundInvoke
    public Object invoke(InvocationContext ctx) throws Exception {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        ELContext elCtx = facesCtx.getELContext();
 
        Secure secure = getSecureAnnotation(ctx.getMethod());
        String expression = secure.value();
 
        // Populating the request map so that parameters are available (arg0, ...)
        Map<String, Object> requestMap = facesCtx.getExternalContext()
                .getRequestMap();
        for (int i = 0; i < ctx.getParameters().length; i++) {
            Object parameter = ctx.getParameters()[i];
            requestMap.put(getArgName(i), parameter);
        }
 
        Boolean expressionValue = (Boolean) facesCtx.getApplication()
                .getExpressionFactory()
                .createValueExpression(elCtx, expression, Boolean.class)
                .getValue(elCtx);
 
        // Removing the parameters (arg0, arg1, ...)
        for (int i = 0; i < ctx.getParameters().length; i++) {
            requestMap.remove(getArgName(i));
        }
 
        if (expressionValue == null || !expressionValue) {
            throw new SecurityException();
        }
 
        return ctx.proceed();
    }
 
    private Secure getSecureAnnotation(Method m) {
        for (Annotation a: m.getAnnotations()) {
            if (a instanceof Secure) { return (Secure) a; }
        }
        for (Annotation a: m.getDeclaringClass().getAnnotations()) {
            if (a instanceof Secure) { return (Secure) a; }
        }
 
        throw new RuntimeException("@Secure not found on method " + m.getName() +
                " or its class " + m.getClass().getName());
    }
}