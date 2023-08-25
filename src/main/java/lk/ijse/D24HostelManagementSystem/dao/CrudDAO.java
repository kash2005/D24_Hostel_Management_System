package lk.ijse.D24HostelManagementSystem.dao;

import lk.ijse.D24HostelManagementSystem.entity.User;

public interface CrudDAO extends SupperDAO{
    public String generateNextId();

     public boolean save(User user);
}
