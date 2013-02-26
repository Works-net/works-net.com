package com.worksnet.utils;

import com.worksnet.system.Conf;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;

import java.io.Serializable;

/**
 * @author maxim.levicky
 *         Date: 2/19/13
 *         Time: 11:48 AM
 */
public class DB {
    private static final SessionFactory factory = buildSessionFactory();
    private static final Session session = createSession();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            System.out.print(Conf.get("webinfPath"));
            return new AnnotationConfiguration()
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session createSession() {
        return getSessionFactory().openSession();
    }

    private static SessionFactory getSessionFactory() {
        return factory;
    }

    private static Session getSession() {
        return session;
    }

    public static void save(Object object) {
        getSession().save(object);
    }

    public static void saveOrUpdate(Object object) {
        getSession().saveOrUpdate(object);
    }

    public static void delete(Object object) {
        getSession().delete(object);
    }

    public static <T> T get(Class<T> clazz, Serializable id) {
        return (T) getSession().get(clazz, id);
    }

    public static <T> T find(String query) {
        return (T) getSession().createQuery(query).iterate().next();
    }
}
