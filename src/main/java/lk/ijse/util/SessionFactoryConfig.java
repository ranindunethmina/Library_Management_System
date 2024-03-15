package lk.ijse.util;

import lk.ijse.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfiguration;
    private final SessionFactory sessionFactory;
    private SessionFactoryConfig() {
            sessionFactory = new Configuration()
                    .mergeProperties(Utility.getProperties())
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Branch.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(BorrowDetails.class)
                    .buildSessionFactory();
    }
    public static SessionFactoryConfig getInstance() {
        return (null == factoryConfiguration)
                ? factoryConfiguration = new SessionFactoryConfig()
                : factoryConfiguration;
    }

    public Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}