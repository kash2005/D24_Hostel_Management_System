package lk.ijse.D24HostelManagementSystem.dao.custom;

import lk.ijse.D24HostelManagementSystem.dao.CrudDAO;

public interface UserDAO extends CrudDAO {
    public boolean check(String name, String password, String password1);
}
