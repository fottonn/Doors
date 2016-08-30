package ru.ikolpakoff.base;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class HSQLBaseHelper {

    public static Connection con;
    public static Statement st;

    public static void baseInit() {
//        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        baseDriverLoad();
        baseConnect();
        statementCreate();

        try {
            st.executeUpdate("CREATE CACHED TABLE IF NOT EXISTS cameraType (id int IDENTITY, title VARCHAR(255))");
            st.executeUpdate("CREATE CACHED TABLE IF NOT EXISTS protectionDevice (id int IDENTITY, title VARCHAR(255))");
            st.executeUpdate("CREATE CACHED TABLE IF NOT EXISTS currentMeter (id int IDENTITY, title VARCHAR(255))");

//            st.executeUpdate("INSERT INTO cameraType (title) VALUES ('Онега')");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void baseDriverLoad() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            e.printStackTrace();
        }
    }

    public static void baseConnect() {

        try {
            con = DriverManager.getConnection("jdbc:hsqldb:file:bd/doorsdb");
        } catch (SQLException e) {
            System.out.println("base connection exception");
            e.printStackTrace();
        }

    }

    public static void statementCreate() {
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println("Statement creating exception");
            e.printStackTrace();
        }
    }

}
