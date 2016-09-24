package ru.ikolpakoff.logic.dao;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.ikolpakoff.base.HibernateUtil;
import ru.ikolpakoff.controllers.MainWindowController;
import ru.ikolpakoff.logic.CameraType;

import javax.persistence.PersistenceException;
import java.util.Collections;
import java.util.List;

public class CameraTypeDAO implements ElementsDAO {

    private CameraType cameraType;

    public CameraTypeDAO() {
    }

    public CameraTypeDAO(CameraType cameraType) {
        this.cameraType = cameraType;
    }


    @Override
    public void save() {
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(cameraType);
        try {
            transaction.commit();
        } catch (PersistenceException e) {
            new Alert(Alert.AlertType.WARNING, String.format("%s уже содержится в базе", cameraType.getDecimalNumber()), ButtonType.OK).showAndWait();
        } finally {
            session.close();
        }
    }

    @Override
    public void fill(MainWindowController controller) {
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<CameraType> cameraTypes;
        Query query = session.createQuery("select p from CameraType p");
        cameraTypes = query.list();
        Collections.sort(cameraTypes, (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));

        controller.getCameraTypeComboBox().getItems().addAll(cameraTypes);

        transaction.commit();
        session.close();

    }
}
