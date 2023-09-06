package lk.ijse.D24HostelManagementSystem.dao;

import lk.ijse.D24HostelManagementSystem.dao.custom.impl.RoomDAOImpl;
import lk.ijse.D24HostelManagementSystem.dao.custom.impl.StudentDAOImpl;
import lk.ijse.D24HostelManagementSystem.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null) ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        USER,STUDENT,ROOM
    }

    public SupperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            default:
                return null;
        }
    }
}
