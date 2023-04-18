package lk.ijse.orm.hms.dto;


import lk.ijse.orm.hms.entity.Room;
import lk.ijse.orm.hms.entity.Student;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDTO {
    private String res_id;
    private LocalDate date;
    private Student studentID;
    private Room roomID;
    private double key_money;
    private String status;
    private int qty;

}
