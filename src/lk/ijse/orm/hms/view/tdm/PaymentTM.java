package lk.ijse.orm.hms.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTM {
    private String studentID;
    private String StudentName;
    private String status;
}
