package lk.ijse.D24HostelManagementSystem.bo.custom.impl;

import lk.ijse.D24HostelManagementSystem.bo.custom.UserBO;
import lk.ijse.D24HostelManagementSystem.dao.DAOFactory;
import lk.ijse.D24HostelManagementSystem.dao.custom.UserDAO;
import lk.ijse.D24HostelManagementSystem.dto.RoomDTO;
import lk.ijse.D24HostelManagementSystem.dto.UserDTO;
import lk.ijse.D24HostelManagementSystem.entity.Room;
import lk.ijse.D24HostelManagementSystem.entity.User;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<UserDTO> getAllUser() {
        List<UserDTO> userDTOArrayList = new ArrayList<>();
        List<User> userArrayList = userDAO.getAll();
        for (User user:userArrayList) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setUserName(user.getUserName());
            userDTO.setUserPassword(user.getUserPassword());
            userDTO.setUserPasswordHint(user.getUserPasswordHint());
            userDTO.setUserEmail(user.getUserEmail());
            userDTOArrayList.add(userDTO);
        }
        return userDTOArrayList;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return userDAO.update(new User(userDTO.getUserId(),userDTO.getUserName(),userDTO.getUserPassword(),userDTO.getUserPasswordHint(),userDTO.getUserEmail()));
    }

    @Override
    public boolean deleteUser(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setUserPassword(userDTO.getUserPassword());
        user.setUserPasswordHint(userDTO.getUserPasswordHint());
        user.setUserEmail(userDTO.getUserEmail());
        return userDAO.delete(user);
    }
}
