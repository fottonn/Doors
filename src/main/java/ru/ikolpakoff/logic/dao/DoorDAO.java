package ru.ikolpakoff.logic.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.ikolpakoff.base.HibernateUtil;
import ru.ikolpakoff.logic.CameraType;
import ru.ikolpakoff.logic.Door;

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
        return 0;
    }
}
