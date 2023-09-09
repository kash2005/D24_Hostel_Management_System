package lk.ijse.D24HostelManagementSystem.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lk.ijse.D24HostelManagementSystem.entity.Room;
import lk.ijse.D24HostelManagementSystem.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ReservsionDTO {
    private String resId;
    private Date date;
    private String status;
    @ToString.Exclude
    private StudentDTO student;
    @ToString.Exclude
    private RoomDTO room;
}
