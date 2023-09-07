package lk.ijse.D24HostelManagementSystem.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RoomTM {
    private String roomTypeId;
    private String type;
    private Double keyMoney;
    private Integer qty;
}