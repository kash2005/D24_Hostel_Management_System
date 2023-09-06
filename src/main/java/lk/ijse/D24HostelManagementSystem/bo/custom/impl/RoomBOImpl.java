package lk.ijse.D24HostelManagementSystem.bo.custom.impl;

import lk.ijse.D24HostelManagementSystem.bo.SuperBO;
import lk.ijse.D24HostelManagementSystem.bo.custom.RoomBO;
import lk.ijse.D24HostelManagementSystem.dao.DAOFactory;
import lk.ijse.D24HostelManagementSystem.dao.custom.RoomDAO;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    @Override
    public String generateNextRoomId() {
        return roomDAO.generateNextId();
    }
}
