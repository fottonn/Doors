package ru.ikolpakoff.logic.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.ikolpakoff.base.HibernateUtil;
import ru.ikolpakoff.logic.CurrentMeter;

public class CurrentMeterDAO implements ElementsDAO {

    CurrentMeter currentMeter;

    public CurrentMeterDAO(CurrentMeter currentMeter) {
        this.currentMeter = currentMeter;
    }

    @Override
    public void save() {
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(currentMeter);
        transaction.commit();
        session.close();
    }
}
