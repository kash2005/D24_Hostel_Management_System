package lk.ijse.D24HostelManagementSystem.dao.custom.impl;

import lk.ijse.D24HostelManagementSystem.dao.SupperDAO;
import lk.ijse.D24HostelManagementSystem.dao.custom.StudentDAO;
import lk.ijse.D24HostelManagementSystem.entity.Student;
import lk.ijse.D24HostelManagementSystem.entity.User;
import lk.ijse.D24HostelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createNamedQuery("Student.findLatestUserId",String.class);
        query.setMaxResults(1);
        String latestUserId = query.uniqueResult();

        if (latestUserId != null){
            transaction.commit();
            session.close();
            int newUserId = Integer.parseInt(latestUserId.replace("S00-",""))+1;
            return  String.format("S00-%03d",newUserId);
        }else {
            return "S00-001";
        }
    }

    @Override
    public boolean save(Student entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);
        transaction.commit();
        session.close();

        return true;
    }



    @Override
    public List<Student> getAll(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Student> studentArrayList = session.createNativeQuery("SELECT * FROM Student").addEntity(Student.class).list();

        transaction.commit();
        session.close();
        return studentArrayList;

    }

    @Override
    public boolean delete(Student entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(entity);
        transaction.commit();
        session.close();

        return true;
    }

    public Student search(String id){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM student WHERE studentId = :studentId");
        nativeQuery.setParameter("studentId",id);

        nativeQuery.addEntity(Student.class);
        Student student = (Student) nativeQuery.uniqueResult();
        transaction.commit();
        session.close();
        return student;

    }

    @Override
    public boolean update(Student entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);
        transaction.commit();
        session.close();

        return true;
    }
}
