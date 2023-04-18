package lk.ijse.orm.hms.bo.customer;

import javafx.collections.ObservableList;
import lk.ijse.orm.hms.bo.SuperBO;
import lk.ijse.orm.hms.dto.ReservationDTO;
import lk.ijse.orm.hms.view.tdm.PaymentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO extends SuperBO {
    ArrayList<ReservationDTO> getAllReserveDetails() throws SQLException, ClassNotFoundException;

    public ArrayList<ReservationDTO> searchReserveDetails(String enteredText) throws SQLException, ClassNotFoundException;

    ObservableList<PaymentTM> getPayment() throws SQLException, ClassNotFoundException, IOException;

}
