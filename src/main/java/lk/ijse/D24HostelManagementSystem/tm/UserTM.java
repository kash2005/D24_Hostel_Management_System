package lk.ijse.D24HostelManagementSystem.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserTM {
    private String userId;
    private String userName;
    private String userPassword;
    private String userPasswordHint;
    private String userEmail;
}
