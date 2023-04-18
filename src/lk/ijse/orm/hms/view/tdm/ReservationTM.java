package lk.ijse.orm.hms.view.tdm;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationTM {
    private String reservationID;
    private String roomID;
    private String roomType;
    private int studentQty;
    private double keyMoney;
    private String status;
}
