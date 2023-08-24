package lk.ijse.D24HostelManagementSystem.bo;

import lk.ijse.D24HostelManagementSystem.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? new BOFactory() : boFactory;
    }

    public enum BOTypes{
        USER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
