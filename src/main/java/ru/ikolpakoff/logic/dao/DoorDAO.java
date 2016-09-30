package ru.ikolpakoff.logic.dao;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.ikolpakoff.base.HibernateUtil;
import ru.ikolpakoff.logic.CameraType;
import ru.ikolpakoff.logic.Component;
import ru.ikolpakoff.logic.Door;

import java.util.List;

public class DoorDAO {

    private Door door;

    public DoorDAO() {
    }

    public DoorDAO(Door door) {
        this.door = door;
    }

    public void save() {
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(door);
        transaction.commit();
        session.close();
    }

    public int getLastDoorNumber(CameraType cameraType) {

        Object objectNumber = null;
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select max(p.number) from Door p");
        try {
            objectNumber = query.uniqueResult();
        } catch (NonUniqueResultException e) {
            System.out.println("Non unique doors number in table of door");
            e.printStackTrace();
        }

        transaction.commit();
        session.close();

        if (objectNumber == null) {
            return 0;
        } else {
            return (int) objectNumber;
        }

    }

    public Component getComponent(String componentName) {
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Component> query = session.createQuery("from Component where name = :n");
        query.setParameter("n", componentName);
        Component component = query.uniqueResult();
        transaction.commit();
        session.close();

        return component;
    }

    public List<Door> getDoorByHash(Integer hash) {
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Door> query = session.createQuery("from Door where hash = :h");
        query.setParameter("h", hash);
        List<Door> doorList = query.list();
        transaction.commit();
        session.close();
        return doorList;
    }
}
