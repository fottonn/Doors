package ru.ikolpakoff.logic.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.ikolpakoff.base.HibernateUtil;
import ru.ikolpakoff.logic.CameraType;

public class CameraTypeDAO implements ElementsDAO {

    private CameraType cameraType;

    public CameraTypeDAO(CameraType cameraType) {
        this.cameraType = cameraType;
    }


    @Override
    public void save() {
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(cameraType);
        transaction.commit();
        session.close();
    }
}
