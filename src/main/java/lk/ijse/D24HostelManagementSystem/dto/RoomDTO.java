package lk.ijse.D24HostelManagementSystem.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RoomDTO {
    private String roomTypeId;
    private String type;
    private Double keyMoney;
    private Integer qty;
}
