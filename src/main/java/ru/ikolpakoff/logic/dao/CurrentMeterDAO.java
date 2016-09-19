package ru.ikolpakoff.logic.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.ikolpakoff.base.HibernateUtil;
import ru.ikolpakoff.controllers.MainWindowController;
import ru.ikolpakoff.logic.CurrentMeter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CurrentMeterDAO implements ElementsDAO {

    CurrentMeter currentMeter;

    public CurrentMeterDAO() {
    }

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

    @Override
    public void fill(MainWindowController controller) {
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<CurrentMeter> currentMeters;
        Query query = session.createQuery("select p from CurrentMeter p");
        currentMeters = query.list();
        Collections.sort(currentMeters, (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));

        controller.getCurrentMeterComboBox().getItems().addAll(currentMeters);

        transaction.commit();
        session.close();

    }
}
