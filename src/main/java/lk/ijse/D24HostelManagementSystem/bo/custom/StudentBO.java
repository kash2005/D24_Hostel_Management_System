package lk.ijse.D24HostelManagementSystem.bo.custom;

import lk.ijse.D24HostelManagementSystem.bo.SuperBO;
import lk.ijse.D24HostelManagementSystem.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    public String generateStudentNextId();

    public boolean saveStudent(StudentDTO studentDTO);


    public List<StudentDTO> getAllStudent();

    public boolean deleteStudent(String studentId);

    StudentDTO searchStudent(String studentId);
}
