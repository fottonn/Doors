package ru.ikolpakoff.logic.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.ikolpakoff.addLogic.Adder;
import ru.ikolpakoff.addLogic.ComponentAdder;
import ru.ikolpakoff.base.HibernateUtil;
import ru.ikolpakoff.controllers.MainWindowController;
import ru.ikolpakoff.logic.CameraType;
import ru.ikolpakoff.logic.Component;
import ru.ikolpakoff.logic.ComponentsComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class ComponentDAO implements ElementsDAO {

    private Component component;

    public ComponentDAO() {
    }

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

    @Override
    public void fill(MainWindowController controller) {

        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Component> components;
        Query query = session.createQuery("select p from Component p");
        components = query.list();

        TreeSet<Component> treeComponents = new TreeSet<>(new ComponentsComparator());

        for(Component c : components) {
            treeComponents.add(new Component(c.getName()));
        }

        ComponentAdder adder = new ComponentAdder();
        adder.setMainWindowController(controller);
        adder.addRows(controller.getComponentsGridPain(), treeComponents);

        transaction.commit();
        session.close();

    }
}
