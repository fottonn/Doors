package ru.ikolpakoff.logic.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.ikolpakoff.base.HibernateUtil;
import ru.ikolpakoff.logic.ProtectionDevice;

public class ProtectionDeviceDAO implements ElementsDAO {

    private ProtectionDevice protectionDevice;

    public ProtectionDeviceDAO(ProtectionDevice protectionDevice) {
        this.protectionDevice = protectionDevice;
    }

    @Override
    public void save() {
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(protectionDevice);
        transaction.commit();
        session.close();

    }
}
