package lk.ijse.D24HostelManagementSystem.bo.custom.impl;

import lk.ijse.D24HostelManagementSystem.bo.SuperBO;
import lk.ijse.D24HostelManagementSystem.bo.custom.ReservationBO;
import lk.ijse.D24HostelManagementSystem.dao.DAOFactory;
import lk.ijse.D24HostelManagementSystem.dao.custom.ReservationDAO;
import lk.ijse.D24HostelManagementSystem.dto.ReservsionDTO;
import lk.ijse.D24HostelManagementSystem.dto.RoomDTO;
import lk.ijse.D24HostelManagementSystem.dto.StudentDTO;
import lk.ijse.D24HostelManagementSystem.entity.Reservation;
import lk.ijse.D24HostelManagementSystem.entity.Room;
import lk.ijse.D24HostelManagementSystem.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);

    @Override
    public String generateReservationNextId() {
        return reservationDAO.generateNextId();
    }

    @Override
    public List<ReservsionDTO> getAllReservation() {
        List<ReservsionDTO> reservsionDTOArrayList = new ArrayList<>();
        List<Reservation> reservationList = reservationDAO.getAll();
        for (Reservation reservation:reservationList) {
            ReservsionDTO reservsionDTO = new ReservsionDTO();
            reservsionDTO.setResId(reservation.getResId());
            reservsionDTO.setDate(reservation.getDate());
            reservsionDTO.setStatus(reservation.getStatus());

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentId(reservation.getStudent().getStudentId());
            studentDTO.setStudentName(reservation.getStudent().getStudentName());
            studentDTO.setStudentAddress(reservation.getStudent().getStudentAddress());
            studentDTO.setStudentContact(reservation.getStudent().getStudentAddress());
            studentDTO.setDate(reservation.getStudent().getDate());
            studentDTO.setGender(reservation.getStudent().getGender());

            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoomTypeId(reservation.getRoom().getRoomTypeId());
            roomDTO.setType(reservation.getRoom().getType());
            roomDTO.setKeyMoney(reservation.getRoom().getKeyMoney());
            roomDTO.setQty(reservation.getRoom().getQty());
            reservsionDTOArrayList.add(new ReservsionDTO(reservation.getResId(),reservation.getDate(),reservation.getStatus(),studentDTO,roomDTO));
        }
        return reservsionDTOArrayList;
    }

    @Override
    public boolean deleteReservation(String reservationId) {
        Reservation reservation = new Reservation();
        reservation.setResId(reservationId);
        return reservationDAO.delete(reservation);
    }

    @Override
    public boolean saveReservation(ReservsionDTO reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setResId(reservationDto.getResId());
        reservation.setDate(reservationDto.getDate());
        reservation.setStatus(reservationDto.getStatus());

        Room room = new Room();
        room.setRoomTypeId(reservationDto.getRoom().getRoomTypeId());
        room.setType(reservationDto.getRoom().getType());
        room.setKeyMoney(reservationDto.getRoom().getKeyMoney());
        room.setQty(reservationDto.getRoom().getQty());
        reservation.setRoom(room);

        Student student = new Student();
        student.setStudentId(reservationDto.getStudent().getStudentId());
        student.setStudentName(reservationDto.getStudent().getStudentName());
        student.setStudentAddress(reservationDto.getStudent().getStudentAddress());
        student.setStudentContact(reservationDto.getStudent().getStudentContact());
        student.setDate(reservationDto.getStudent().getDate());
        student.setGender(reservationDto.getStudent().getGender());

        reservation.setStudent(student);
        return reservationDAO.save(reservation);
    }


}
