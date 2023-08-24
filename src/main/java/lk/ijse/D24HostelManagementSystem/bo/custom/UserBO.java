package lk.ijse.D24HostelManagementSystem.bo.custom;

import lk.ijse.D24HostelManagementSystem.bo.SuperBO;

public interface UserBO extends SuperBO {
    public boolean checkUser(String name, String password, String password1);
}
