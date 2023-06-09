package lk.ijse.orm.hms.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDTO {
    private String roomID;
    private String roomType;
    private String keyMoney;
    private int roomQty;
}
