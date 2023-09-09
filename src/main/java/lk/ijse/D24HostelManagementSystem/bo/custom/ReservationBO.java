package lk.ijse.D24HostelManagementSystem.bo.custom;

import lk.ijse.D24HostelManagementSystem.bo.SuperBO;
import lk.ijse.D24HostelManagementSystem.dto.ReservsionDTO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    public String generateReservationNextId();

    public List<ReservsionDTO> getAllReservation();

    public boolean deleteReservation(String reservationId);

    public boolean saveReservation(ReservsionDTO reservationDto);
}
