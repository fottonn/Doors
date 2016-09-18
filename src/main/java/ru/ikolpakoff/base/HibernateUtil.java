package ru.ikolpakoff.base;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static HibernateUtil hibernateUtil;

    public static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    private HibernateUtil() {

    }

    public static HibernateUtil getHibernateUtil() {
        if(hibernateUtil == null) {
            return new HibernateUtil();
        } else {
            return hibernateUtil;
        }
    }

    public static void sessionFactoryClose() {
        sessionFactory.close();
    }
}
