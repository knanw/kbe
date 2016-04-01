//package com.bluelotussoftware.example.cdi;
// 
//import java.util.UUID;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Named;
//
//import org.apache.derby.iapi.services.uuid.UUIDFactory;
// import com.bluelotussoftware.example.cdi.*;
///**
// * A page backing bean for the
// * <code>index.xhtml</code> page.
// *
// * @author John Yeary <jyeary@bluelotussoftware.com>
// * @version 1.0
// */
//@Named
//@RequestScoped
//@Interceptable
//public class IndexBean implements Loggable {
// 
//    private UUIDFactory factory;
//    private Logger log;
// 
//    /**
//     * Default no-argument constructor.
//     */
//    public IndexBean() {
//    }
// 
//    @PostConstruct
//    private void initialize() {
//        // This should cause an NPE since logger is not initialized, but we will do our magic here.
//        log.info("I am an injected logger");
//    }
// 
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void setLogger(final Logger logger) {
//        this.log = logger;
//    }
// 
//    /**
//     * This sets the UUID factory for the bean.
//     *
//     * @param uuidFactory the factory to be set.
//     */
//    public void setFactory(final UUIDFactory uuidFactory) {
//        log.log(Level.INFO, "Factory being set to {0}", uuidFactory);
//        this.factory = uuidFactory;
//    }
// 
//    /**
//     * This returns a randomly generated {@link UUID#randomUUID()#toString()}
//     *
//     * @return a randomly generated UUID value, or {@code null} if the factory
//     * is not initialized.
//     */
//    public String getUUID() {
//        if (null != factory) {
//           // return factory.generateUUID().toString();
//            return factory.createUUID().toString();
//
//        } else {
//            return null;
//        }
//    }
//}