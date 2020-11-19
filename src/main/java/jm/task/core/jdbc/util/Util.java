package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;


public class Util {
    private String url = "jdbc:mysql://localhost:3306/db_test1?serverTimezone=Europe/Moscow&useSSL=false";
    private String username = "root";
    private String password = "Rjhjdf89njxrf!3";
    private String DB_Driver = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DB_Driver); //register jdbc driver
            connection = DriverManager.getConnection(url, username, password); //creating connection to DB
            System.out.println("Connection is OK!!!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Connection is ERROR!!!");
        }
        return connection;
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/db_test1?serverTimezone=Europe/Moscow&useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Rjhjdf89njxrf!3");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("SessionFactory creation failed");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
//        dbSetting.put(Environment.URL, "jdbc:mysql://localhost:3306/db_test1?serverTimezone=Europe/Moscow&useSSL=false");
//        dbSetting.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
//        dbSetting.put(Environment.USER, "root");
//        dbSetting.put(Environment.PASS, "Rjhjdf89njxrf!3");
//        dbSetting.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");