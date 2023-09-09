package lk.ijse.D24HostelManagementSystem.dao;

import lk.ijse.D24HostelManagementSystem.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.D24HostelManagementSystem.dao.custom.impl.RoomDAOImpl;
import lk.ijse.D24HostelManagementSystem.dao.custom.impl.StudentDAOImpl;
import lk.ijse.D24HostelManagementSystem.dao.custom.impl.UserDAOImpl;
import lk.ijse.D24HostelManagementSystem.entity.Reservation;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null) ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        USER,STUDENT,ROOM,RESERVATION
    }

    public SupperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            default:
                return null;
        }
    }
}
