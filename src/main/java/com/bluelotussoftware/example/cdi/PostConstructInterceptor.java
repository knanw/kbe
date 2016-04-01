package com.bluelotussoftware.example.cdi;
// 
//import java.util.Map;
//import java.util.UUID;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import javax.interceptor.Interceptor;
//import javax.interceptor.InvocationContext;
//
//import org.apache.derby.iapi.services.uuid.UUIDFactory;
// 
/**
 *
 * @author John Yeary
 * @version 1.0
 */
/*
@Interceptable
@Interceptor
public class PostConstructInterceptor {
 
    private static final Logger log = Logger.getLogger(PostConstructInterceptor.class.getName());
 */
    /**
     * This is the method that handles the {@link @PostConstruct} event. The
     * method name can be called any legal Java method name. I chose
     * <code>intercept</code> since it is an action describing what we are
     * doing.
     *
     * @param context The current invocation context.
     * @throws Exception If any {@link Exception} occurs during processing.
     */
  /*  @PostConstruct
    public void intercept(InvocationContext context) throws Exception {
 
        log.setLevel(Level.FINE);
        log.info("Before call to @PostConstuct.");
 
        Map<String, Object> map = context.getContextData();
        for (String key : map.keySet()) {
            log.log(Level.INFO, "Context data key --> {0} data --> {1}", new Object[]{key, map.get(key)});
        }
 
        log.log(Level.INFO, "Examining object: {0}", context.getTarget().getClass().getName());
 
        // Best practice is to check if the object is an instanceof an interface.
        if (context.getTarget() instanceof Loggable) {
            Loggable loggable = (Loggable) context.getTarget();
            loggable.setLogger(Logger.getLogger(context.getTarget().getClass().getName()));
        }
 
        /*
         * This checks for an instanceof a specific bean. This is not the preferred approach
         * unless you are really expecting to only check for a specific class.
         */
     /*   if (context.getTarget() instanceof IndexBean) {
            IndexBean indexBean = (IndexBean) context.getTarget();
            indexBean.setFactory(new UUIDFactory() {
 
        
				@Override
				public UUID createUUID() {
				     return UUID.randomUUID();
				}

				@Override
				public org.apache.derby.catalog.UUID recreateUUID(String arg0) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public org.apache.derby.catalog.UUID recreateUUID(byte[] arg0) {
					// TODO Auto-generated method stub
					return null;
				}
            });
        }
 
        context.proceed();
        log.info("After call to  to @PostConstuct.");
    }
}*/