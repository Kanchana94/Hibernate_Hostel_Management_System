package lk.ijse.orm.hms.view.tdm;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserTM {
    private String userID;
    private String userName;
    private String password;
}
