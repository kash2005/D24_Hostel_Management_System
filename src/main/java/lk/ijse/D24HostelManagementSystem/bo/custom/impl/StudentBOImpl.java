package lk.ijse.D24HostelManagementSystem.bo.custom.impl;

import lk.ijse.D24HostelManagementSystem.bo.BOFactory;
import lk.ijse.D24HostelManagementSystem.bo.custom.StudentBO;
import lk.ijse.D24HostelManagementSystem.dao.DAOFactory;
import lk.ijse.D24HostelManagementSystem.dao.custom.StudentDAO;
import lk.ijse.D24HostelManagementSystem.dto.StudentDTO;
import lk.ijse.D24HostelManagementSystem.entity.Room;
import lk.ijse.D24HostelManagementSystem.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public String generateStudentNextId() {
        return studentDAO.generateNextId();
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        return studentDAO.save(new Student(studentDTO.getStudentId(), studentDTO.getStudentName(), studentDTO.getStudentAddress(), studentDTO.getStudentContact(),studentDTO.getDate() ,studentDTO.getGender() ));
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        List<StudentDTO> customerDTOArrayList = new ArrayList<>();
        List<Student> customerArrayList = studentDAO.getAll();
        for (Student student:customerArrayList) {
            customerDTOArrayList.add(new StudentDTO(student.getStudentId(),student.getStudentName(), student.getStudentAddress(), student.getStudentContact(),student.getDate(),student.getGender()));
        }
        return customerDTOArrayList;

    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setStudentName(studentDTO.getStudentName());
        student.setStudentAddress(studentDTO.getStudentAddress());
        student.setStudentContact(studentDTO.getStudentContact());
        student.setDate(studentDTO.getDate());
        student.setGender(studentDTO.getGender());
        return studentDAO.delete(student);
    }

    @Override
    public StudentDTO searchStudent(String studentId) {
        Student student = studentDAO.search(studentId);
        return new StudentDTO(student.getStudentId(),student.getStudentName(),student.getStudentAddress(),student.getStudentContact(),student.getDate(),student.getGender()) ;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        return studentDAO.update(new Student(studentDTO.getStudentId(),studentDTO.getStudentName(),studentDTO.getStudentAddress(),studentDTO.getStudentContact(),studentDTO.getDate(),studentDTO.getGender()));
    }

    @Override
    public List<StudentDTO> getUnpaidStudents() {
//        List<StudentDTO> studentDTOArrayList = new ArrayList<>();
//        List<Student> studentArrayList = studentDAO.unPaidStudents();
//        for (Student student:studentArrayList) {
//            studentDTOArrayList.add(new StudentDTO(student.getStudentId(),student.getStudentName(), student.getStudentAddress(), student.getStudentContact(),student.getDate(),student.getGender()));
//        }
//        return studentDTOArrayList;
        return null;
    }

    @Override
    public List<String> getCodes() {
        List<Student> students = studentDAO.getAll();
        List<String> studentId = new ArrayList<>();
        for (Student student: students) {
            studentId.add(student.getStudentId());
        }
        return studentId;
    }


}
