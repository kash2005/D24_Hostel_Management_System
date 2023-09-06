package lk.ijse.D24HostelManagementSystem.dao.custom;

import lk.ijse.D24HostelManagementSystem.dao.CrudDAO;
import lk.ijse.D24HostelManagementSystem.entity.User;

public interface UserDAO extends CrudDAO<User> {
    public boolean check(String name, String password, String password1);
}
