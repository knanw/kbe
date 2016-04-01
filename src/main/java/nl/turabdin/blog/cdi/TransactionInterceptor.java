package nl.turabdin.blog.cdi;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import javax.persistence.*;

import org.slf4j.LoggerFactory;
import org.slf4j.*;



/**
 * JTA Transaction interceptor.
 *
 * @author marcus
 */
@UseTransaction @Interceptor
public class TransactionInterceptor {

  private static final Logger LOG = LoggerFactory.getLogger(TransactionInterceptor.class);
  private static final String TRANSACTION_JNDI = "java:comp/UserTransaction";

 /**
  * Wrap method
  * @param ctx InvocationContext from Interceptor.
  * @return result
  * @throws Exception if something goes wrong.
  */
  @AroundInvoke
  public Object wrapTransaction(InvocationContext ctx) throws Exception {

    LOG.trace("Intercepting method");
    UserTransaction tx = getTransaction();
    if (tx == null) {
      // We are in a EJB transaction or no UserTransaction available. Don't wrap
      return ctx.proceed();
    }
    // Check nested transaction
    if (tx.getStatus() != Status.STATUS_ACTIVE) {
      // Start transaction
      LOG.trace("Starting UserTransaction");
      tx.begin();
      // Join EntityManager in this transaction
      joinEntityManagerTransaction();
      // Proceed intercepted method and store result.
      Object result = ctx.proceed();
      // Commit transaction
      tx.commit();
      LOG.trace("UserTransaction committed");
      // Return result
      return result;
    } else {
      return ctx.proceed();
    }
  }

 /**
  * @return Current active entity manager or null if none is found.
  */
  protected EntityManager getEntityManager() {

    try {
      // Get EntityManager by CDI producer (you have to define a Producer for EntityManager.
      BeanManager manager = CDI.current().getBeanManager();
      Bean bean; bean = manager.resolve(manager.getBeans(EntityManager.class));
      Object obj = manager.getReference(bean, EntityManager.class, manager.createCreationalContext(bean));

      return (EntityManager)obj;
    } catch (Exception e) {
      // Error while retrieving EntityManager.
      return null;
    }
  }

  protected UserTransaction getTransaction() {
    try {
      Context context = new InitialContext();
      Object obj = context.lookup(TRANSACTION_JNDI);
      if (obj instanceof UserTransaction) {
        return (UserTransaction)obj;
      }
    } catch (Exception e) {
      // Ignore and return null
    }
    return null;
  }

  private void joinEntityManagerTransaction() {
    EntityManager em = getEntityManager();
    if (em != null) {
      em.joinTransaction();
    }
  }

}