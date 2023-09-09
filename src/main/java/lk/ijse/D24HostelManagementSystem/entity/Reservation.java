package lk.ijse.D24HostelManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Data;
import java.sql.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@NamedQuery(
        name = "Reservation.findLatestReservationId",
        query = "SELECT r.resId FROM Reservation r ORDER BY r.resId DESC"
)

public class Reservation {
    @Id
    private String resId;
    private Date date;
    private String status;
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Student student;
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Room room;
}
