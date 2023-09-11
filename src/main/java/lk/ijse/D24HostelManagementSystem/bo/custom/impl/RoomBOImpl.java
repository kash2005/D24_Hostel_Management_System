package lk.ijse.D24HostelManagementSystem.bo.custom.impl;

import lk.ijse.D24HostelManagementSystem.bo.custom.RoomBO;
import lk.ijse.D24HostelManagementSystem.dao.DAOFactory;
import lk.ijse.D24HostelManagementSystem.dao.custom.RoomDAO;
import lk.ijse.D24HostelManagementSystem.dto.RoomDTO;
import lk.ijse.D24HostelManagementSystem.dto.StudentDTO;
import lk.ijse.D24HostelManagementSystem.entity.Room;
import lk.ijse.D24HostelManagementSystem.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    @Override
    public String generateNextRoomId() {
        return roomDAO.generateNextId();
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomTypeId(roomDTO.getRoomTypeId());
        room.setType(roomDTO.getType());
        room.setKeyMoney(roomDTO.getKeyMoney());
        room.setQty(roomDTO.getQty());
        return roomDAO.save(room);
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomTypeId(roomDTO.getRoomTypeId());
        room.setType(roomDTO.getType());
        room.setKeyMoney(roomDTO.getKeyMoney());
        room.setQty(roomDTO.getQty());
        return roomDAO.update(room);
    }

    @Override
    public List<RoomDTO> getAllRoom() {
        List<RoomDTO> roomsDTOArrayList = new ArrayList<>();
        List<Room> roomsArrayList = roomDAO.getAll();
        for (Room room:roomsArrayList) {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoomTypeId(room.getRoomTypeId());
            roomDTO.setType(room.getType());
            roomDTO.setKeyMoney(room.getKeyMoney());
            roomDTO.setQty(room.getQty());
            roomsDTOArrayList.add(roomDTO);
        }
        return roomsDTOArrayList;
    }

    @Override
    public boolean deleteRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomTypeId(roomDTO.getRoomTypeId());
        room.setType(roomDTO.getType());
        room.setKeyMoney(roomDTO.getKeyMoney());
        room.setQty(roomDTO.getQty());
        return roomDAO.delete(room);
    }

    @Override
    public RoomDTO searchRoom(String roomId) {
        Room room = roomDAO.search(roomId);
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomTypeId(room.getRoomTypeId());
        roomDTO.setType(room.getType());
        roomDTO.setKeyMoney(room.getKeyMoney());
        roomDTO.setQty(room.getQty());

        return roomDTO;
    }

    @Override
    public List<String> getCodes() {
        List<Room> reservations = roomDAO.getAll();
        List<String> roomId = new ArrayList<>();
        for (Room room: reservations) {
            roomId.add(room.getRoomTypeId());
        }
        return roomId;
    }
}
