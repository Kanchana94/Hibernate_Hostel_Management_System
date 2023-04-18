package lk.ijse.orm.hms.bo.customer.impl;

import javafx.collections.ObservableList;
import lk.ijse.orm.hms.bo.customer.ReservationBO;
import lk.ijse.orm.hms.dao.DAOFactory;
import lk.ijse.orm.hms.dao.DAOTypes;
import lk.ijse.orm.hms.dao.custom.ReservationDAO;
import lk.ijse.orm.hms.dto.ReservationDTO;
import lk.ijse.orm.hms.view.tdm.PaymentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO= DAOFactory.getInstance().getDAO(DAOTypes.RESERVATION);

    @Override
    public ArrayList<ReservationDTO> getAllReserveDetails() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ReservationDTO> searchReserveDetails(String enteredText) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<PaymentTM> getPayment() throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.getPayment();
    }
}
