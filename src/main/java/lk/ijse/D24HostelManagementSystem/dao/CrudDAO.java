package lk.ijse.D24HostelManagementSystem.dao;

import lk.ijse.D24HostelManagementSystem.dto.StudentDTO;
import lk.ijse.D24HostelManagementSystem.entity.Student;
import lk.ijse.D24HostelManagementSystem.entity.User;

import java.util.List;

public interface CrudDAO<T> extends SupperDAO{
    public String generateNextId();

     public boolean save(T entity);



    public List<T> getAll();

    public boolean delete(String id);


    public T search(String id);
}
