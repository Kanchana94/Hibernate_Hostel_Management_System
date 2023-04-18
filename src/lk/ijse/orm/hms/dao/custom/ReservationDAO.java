package lk.ijse.orm.hms.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.orm.hms.dao.CrudDAO;
import lk.ijse.orm.hms.entity.Reservation;
import lk.ijse.orm.hms.view.tdm.PaymentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationDAO extends CrudDAO<Reservation,String> {

    public ArrayList<Reservation> searchReservation(String enteredText) throws SQLException, ClassNotFoundException;

    public ObservableList<PaymentTM> getPayment() throws SQLException, ClassNotFoundException, IOException;

}
