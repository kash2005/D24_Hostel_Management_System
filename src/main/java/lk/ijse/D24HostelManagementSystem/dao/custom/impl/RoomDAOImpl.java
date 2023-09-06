package lk.ijse.D24HostelManagementSystem.dao.custom.impl;

import lk.ijse.D24HostelManagementSystem.dao.SupperDAO;
import lk.ijse.D24HostelManagementSystem.dao.custom.RoomDAO;
import lk.ijse.D24HostelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createNamedQuery("Room.findLatestUserId",String.class);
        query.setMaxResults(1);
        String latestRoomId = query.uniqueResult();

        if (latestRoomId != null){
            transaction.commit();
            session.close();
            int newRoomId = Integer.parseInt(latestRoomId.replace("R00-",""))+1;
            return  String.format("R00-%03d",newRoomId);
        }
        return "R00-001";
    }

    @Override
    public boolean save(Object entity) {
        return false;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Object search(String id) {
        return null;
    }

    @Override
    public boolean update(Object entity) {
        return false;
    }
}
