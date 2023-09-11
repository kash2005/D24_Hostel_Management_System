package lk.ijse.D24HostelManagementSystem.dao.custom.impl;

import lk.ijse.D24HostelManagementSystem.dao.SupperDAO;
import lk.ijse.D24HostelManagementSystem.dao.custom.RoomDAO;
import lk.ijse.D24HostelManagementSystem.entity.Room;
import lk.ijse.D24HostelManagementSystem.entity.Student;
import lk.ijse.D24HostelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
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
            int newRoomId = Integer.parseInt(latestRoomId.replace("RM-",""))+1;
            return  String.format("RM-%04d",newRoomId);
        }
        return "RM-0001";
    }

    @Override
    public boolean save(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<Room> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Room> roomArrayList = session.createNativeQuery("SELECT * FROM Room").addEntity(Room.class).list();

        transaction.commit();
        session.close();
        return roomArrayList;
    }


    @Override
    public boolean delete(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public Room search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM Room WHERE roomTypeId = :roomTypeId");
        nativeQuery.setParameter("roomTypeId",id);

        nativeQuery.addEntity(Room.class);
        Room room = (Room) nativeQuery.uniqueResult();
        transaction.commit();
        session.close();
        return room;
    }

    @Override
    public boolean update(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);
        transaction.commit();
        session.close();

        return true;
    }



}
