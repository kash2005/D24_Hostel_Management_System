package lk.ijse.D24HostelManagementSystem.bo.custom;

import lk.ijse.D24HostelManagementSystem.bo.SuperBO;
import lk.ijse.D24HostelManagementSystem.dto.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBO {
    public String generateNextRoomId();

    public boolean saveRoom(RoomDTO roomDTO);

    public boolean updateRoom(RoomDTO roomDTO);

    public List<RoomDTO> getAllRoom();

    public boolean deleteRoom(RoomDTO roomDTO);

    public RoomDTO searchRoom(String roomId);

    public List<String> getCodes();
}
