package lk.ijse.orm.hms.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm.hms.bo.BOFactory;
import lk.ijse.orm.hms.bo.BOTypes;
import lk.ijse.orm.hms.bo.customer.ReservationBO;
import lk.ijse.orm.hms.view.tdm.PaymentTM;

import javax.swing.text.TabableView;
import java.io.IOException;
import java.sql.SQLException;

public class PaymentManageFormController {
    public AnchorPane PaymentDetails;
    public TableView <PaymentTM> tblPaymentDetails;

    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colStatus;

    ReservationBO reservationBO = BOFactory.getInstance().getBO(BOTypes.RESERVATION);

    public void initialize() throws SQLException, IOException, ClassNotFoundException {

        tblPaymentDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tblPaymentDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        tblPaymentDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("status"));

        loadRemainKeyMoneyStudent();
    }
    private void loadRemainKeyMoneyStudent() throws SQLException, IOException, ClassNotFoundException {

        ObservableList<PaymentTM> remainKeyMnyStudent = reservationBO.getPayment();
        tblPaymentDetails.setItems(remainKeyMnyStudent);

    }

}
