package lk.ijse.D24HostelManagementSystem.dao;

import lk.ijse.D24HostelManagementSystem.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null) ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        USER
    }

    public SupperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
