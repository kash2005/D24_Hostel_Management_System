package lk.ijse.D24HostelManagementSystem.dao.custom.impl;

import lk.ijse.D24HostelManagementSystem.dao.SupperDAO;
import lk.ijse.D24HostelManagementSystem.dao.custom.ReservationDAO;
import lk.ijse.D24HostelManagementSystem.entity.Reservation;
import lk.ijse.D24HostelManagementSystem.entity.Room;
import lk.ijse.D24HostelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createNamedQuery("Reservation.findLatestReservationId",String.class);
        query.setMaxResults(1);
        String latesetReservationId =  query.uniqueResult();

        if (latesetReservationId != null){
            transaction.commit();
            session.close();
            int newReservationId = Integer.parseInt(latesetReservationId.replace("REC-",""))+1;
            return  String.format("REC-%06d",newReservationId);
        }
        return "REC-000001";
    }

    @Override
    public boolean save(Reservation entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<Reservation> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Reservation> reservationList = session.createNativeQuery("SELECT * FROM Reservation").addEntity(Reservation.class).list();

        transaction.commit();
        session.close();
        return reservationList;
    }

    @Override
    public boolean delete(Reservation entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Reservation search(String id) {
        return null;
    }

    @Override
    public boolean update(Reservation entity) {
        return false;
    }
}
