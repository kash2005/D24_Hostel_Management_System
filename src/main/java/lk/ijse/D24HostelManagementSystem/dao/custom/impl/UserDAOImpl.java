package lk.ijse.D24HostelManagementSystem.dao.custom.impl;

import lk.ijse.D24HostelManagementSystem.dao.custom.UserDAO;
import lk.ijse.D24HostelManagementSystem.entity.User;
import lk.ijse.D24HostelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean check(String name, String password, String password1) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createNativeQuery("SELECT * FROM User WHERE userName = ?",User.class).setParameter(1,name).getResultList();
        for (User user: userList) {
            transaction.commit();
            session.close();
            if (user.getUserPassword().equals(password) || user.getUserPassword().equals(password1)){
                return true;
            }
        }
        return false;
    }
}
