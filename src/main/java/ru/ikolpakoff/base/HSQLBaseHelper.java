package ru.ikolpakoff.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HSQLBaseHelper {
    public static SessionFactory sf = new Configuration().configure().buildSessionFactory();
    public Session session;
    public Transaction ts;


}
