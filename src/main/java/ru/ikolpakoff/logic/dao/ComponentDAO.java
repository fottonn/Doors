package ru.ikolpakoff.logic.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.ikolpakoff.base.HibernateUtil;
import ru.ikolpakoff.logic.Component;

public class ComponentDAO implements ElementsDAO {

    private Component component;

    public ComponentDAO(Component component) {
        this.component = component;
    }

    @Override
    public void save() {
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(component);
        transaction.commit();
        session.close();

    }
}
