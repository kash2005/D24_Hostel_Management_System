package lk.ijse.D24HostelManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class User {
    @Id
    private String userId;
    private String userName;
    private String userPassword;
}
