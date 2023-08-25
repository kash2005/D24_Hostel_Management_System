package lk.ijse.D24HostelManagementSystem.bo.custom;

import lk.ijse.D24HostelManagementSystem.bo.SuperBO;
import lk.ijse.D24HostelManagementSystem.dto.UserDTO;

public interface UserBO extends SuperBO {
    public boolean checkUser(String name, String password, String password1);

    public String generateNextUserId();

    public boolean saveUser(UserDTO userDTO);
}
