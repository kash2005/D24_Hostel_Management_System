package lk.ijse.D24HostelManagementSystem.bo;

import lk.ijse.D24HostelManagementSystem.bo.custom.StudentBO;
import lk.ijse.D24HostelManagementSystem.bo.custom.impl.StudentBOImpl;
import lk.ijse.D24HostelManagementSystem.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? new BOFactory() : boFactory;
    }

    public enum BOTypes{
        USER,STUDENT
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            default:
                return null;
        }
    }
}
