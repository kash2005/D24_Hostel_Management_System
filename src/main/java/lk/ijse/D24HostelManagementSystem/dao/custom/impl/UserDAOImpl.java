package lk.ijse.D24HostelManagementSystem.dao.custom.impl;

import lk.ijse.D24HostelManagementSystem.dao.custom.UserDAO;
import lk.ijse.D24HostelManagementSystem.entity.Room;
import lk.ijse.D24HostelManagementSystem.entity.Student;
import lk.ijse.D24HostelManagementSystem.entity.User;
import lk.ijse.D24HostelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Internal;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean check(String name, String password, String password1) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<User> userList = session.createNativeQuery("SELECT * FROM User WHERE userName = ?", User.class).setParameter(1, name).getResultList();
        for (User user:userList) {
            transaction.commit();
            session.close();
            if (user.getUserPassword().equals(password) || user.getUserPassword().equals(password1)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createNamedQuery("User.findLatestUserId", String.class);
        query.setMaxResults(1);
        String latestUserId = query.uniqueResult();

        if (latestUserId != null){
            transaction.commit();
            session.close();

            int newUserID = Integer.parseInt(latestUserId.replace("U00-", "")) + 1;
            return String.format("U00-%03d",newUserID);
        }else {
            return "U00-001";
        }
    }

    @Override
    public boolean save(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(user);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<User> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<User> userArrayList = session.createNativeQuery("SELECT * FROM User").addEntity(User.class).list();

        transaction.commit();
        session.close();
        return userArrayList;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public User search(String id) {
        return null;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }


}
