package ru.ikolpakoff.logic.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.ikolpakoff.base.HibernateUtil;
import ru.ikolpakoff.controllers.MainWindowController;
import ru.ikolpakoff.logic.CameraType;
import ru.ikolpakoff.logic.ProtectionDevice;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProtectionDeviceDAO implements ElementsDAO {

    private ProtectionDevice protectionDevice;

    public ProtectionDeviceDAO() {
    }

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

    @Override
    public void fill(MainWindowController controller) {
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<ProtectionDevice> protectionDevices;
        Query query = session.createQuery("select p from ProtectionDevice p");
        protectionDevices = query.list();
        Collections.sort(protectionDevices, (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));

        controller.getProtectionDeviceComboBox().getItems().addAll(protectionDevices);

        transaction.commit();
        session.close();

    }
}
