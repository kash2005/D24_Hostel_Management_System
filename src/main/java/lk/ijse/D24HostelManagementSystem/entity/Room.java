package lk.ijse.D24HostelManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

@NamedQuery(
        name = "Room.findLatestUserId",
        query = "SELECT r.roomTypeId FROM Room r ORDER BY r.roomTypeId DESC"
)

public class Room {
    @Id
    private String roomTypeId;
    private String type;
    private Double keyMoney;
    private Integer qty;
    @ToString.Exclude
    @OneToMany(mappedBy = "room" , targetEntity = Reservation.class)
    List<Reservation> reservationList = new ArrayList<>();
}
