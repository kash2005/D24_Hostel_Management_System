package lk.ijse.D24HostelManagementSystem.bo.custom.impl;

import lk.ijse.D24HostelManagementSystem.bo.custom.UserBO;
import lk.ijse.D24HostelManagementSystem.dao.DAOFactory;
import lk.ijse.D24HostelManagementSystem.dao.custom.UserDAO;
import lk.ijse.D24HostelManagementSystem.dto.UserDTO;
import lk.ijse.D24HostelManagementSystem.entity.User;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean checkUser(String name, String password, String password1) {
       return userDAO.check(name,password,password1);

    }

    @Override
    public String generateNextUserId() {
        return userDAO.generateNextId();
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getUserId(),userDTO.getUserName(),userDTO.getUserPassword(),userDTO.getUserPasswordHint(),userDTO.getUserEmail()));
    }
}
