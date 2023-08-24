package lk.ijse.D24HostelManagementSystem.bo.custom.impl;

import lk.ijse.D24HostelManagementSystem.bo.custom.UserBO;
import lk.ijse.D24HostelManagementSystem.dao.DAOFactory;
import lk.ijse.D24HostelManagementSystem.dao.custom.UserDAO;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean checkUser(String name, String password, String password1) {
        userDAO.check(name,password,password1);
        return true;
    }
}
