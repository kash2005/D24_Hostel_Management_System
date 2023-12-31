package lk.ijse.D24HostelManagementSystem.util;

import lk.ijse.D24HostelManagementSystem.entity.Reservation;
import lk.ijse.D24HostelManagementSystem.entity.Room;
import lk.ijse.D24HostelManagementSystem.entity.Student;
import lk.ijse.D24HostelManagementSystem.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;


public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        //add configuration file
        Configuration configuration = new Configuration();

        //add properties
        Properties properties = new Properties();

        //add already created hibernate file to properties in current thread
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //add properties to configure
        configuration.setProperties(properties);

        //add anotated class to configure
        configuration.addAnnotatedClass(User.class).addAnnotatedClass(Student.class).addAnnotatedClass(Room.class).addAnnotatedClass(Reservation.class);

        //build session factory
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration==null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
