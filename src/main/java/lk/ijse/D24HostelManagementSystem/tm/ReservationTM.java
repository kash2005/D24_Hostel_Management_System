package lk.ijse.D24HostelManagementSystem.tm;

import lk.ijse.D24HostelManagementSystem.dto.RoomDTO;
import lk.ijse.D24HostelManagementSystem.dto.StudentDTO;
import lk.ijse.D24HostelManagementSystem.entity.Room;
import lk.ijse.D24HostelManagementSystem.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ReservationTM {
    private String reservationId;
    private Date booking;
    private String status;
    private StudentDTO student;
    private RoomDTO room;
}
