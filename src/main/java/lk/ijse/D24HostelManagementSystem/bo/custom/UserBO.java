package lk.ijse.D24HostelManagementSystem.bo.custom;

import lk.ijse.D24HostelManagementSystem.bo.SuperBO;
import lk.ijse.D24HostelManagementSystem.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {

    public boolean checkUser(String name, String password, String password1);

    public String generateNextUserId();

    public boolean saveUser(UserDTO userDTO);

    public List<UserDTO> getAllUser();

    public boolean updateUser(UserDTO userDTO);

    public boolean deleteUser(UserDTO userDTO);
}
