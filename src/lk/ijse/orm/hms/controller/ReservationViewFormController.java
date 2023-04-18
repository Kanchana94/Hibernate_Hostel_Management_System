package lk.ijse.orm.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.orm.hms.bo.BOFactory;
import lk.ijse.orm.hms.bo.BOTypes;
import lk.ijse.orm.hms.bo.customer.RoomBO;
import lk.ijse.orm.hms.bo.customer.impl.PurchaseReserveBOImpl;
import lk.ijse.orm.hms.dto.ReservationDTO;
import lk.ijse.orm.hms.dto.RoomDTO;
import lk.ijse.orm.hms.dto.StudentDTO;
import lk.ijse.orm.hms.entity.Room;
import lk.ijse.orm.hms.entity.Student;
import lk.ijse.orm.hms.view.tdm.ReservationTM;

import javax.persistence.Id;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationViewFormController {

    public AnchorPane ReservationDetails;
    //public JFXTextField txtStudentName;
    //public JFXComboBox cmbStudentID;
    public TableView<ReservationTM> tblReservation;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colStudentQty;
    public TableColumn colKeyMny;
    public TableColumn colStatus;
    public TableColumn colDelete;
    //public JFXComboBox cmbRoomID;
    //public JFXTextField txtRoomType;
    //public JFXTextField txtKeyMoney;
    //public JFXTextField txtQtyOfRoom;
    public TextField txtStatus;
    public TextField txtStudentQty;
    public TableColumn colReservationID;
    public JFXButton btnAddToRemain;
    public Label lblReserveID;
    public Text lblRoomId;
    public Text lblRoomType;
    public Label lblRoomQty;
    public TextField txtStudentname;
    public TextField txtRoomtype;
    public TextField txtQtyOfroom;
    public TextField txtKeymoney;
    public ComboBox cmbStudentId;
    public ComboBox cmbRoomId;
    String reservationId;
    int preQty;
    ArrayList<RoomDTO> allrooms;

    PurchaseReserveBOImpl purchaseReserveBO = BOFactory.getInstance().getBO(BOTypes.PERCHASE_RESERVE);
    RoomBO roomBO = BOFactory.getInstance().getBO(BOTypes.ROOM);

    public void initialize() {

        RF();

        tblReservation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("reserveID"));
        tblReservation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("roomID"));
        tblReservation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("roomType"));
        tblReservation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("studentQty"));
        tblReservation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblReservation.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<ReservationTM, Button> lastCol = (TableColumn<ReservationTM, Button>) tblReservation.getColumns().get(6);
        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(event -> {
                if (tblReservation.getSelectionModel().getSelectedItem() != null) {
                    try {
                        if (purchaseReserveBO.deleteReservation(reservationId)) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted.....").show();
                            System.out.println("qry room: " + preQty);

                            RoomDTO roomDTO1 = purchaseReserveBO.searchRooms((String) cmbRoomId.getValue());

                            roomDTO1.setRoomQty(preQty);

                            roomBO.updateRoom(roomDTO1);

                            tblReservation.getItems().remove(param.getValue());
                            tblReservation.getSelectionModel().clearSelection();
                            clearFields();

                        } else {

                            new Alert(Alert.AlertType.ERROR, "Try Again.....").show();
                            ;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Select Row....").show();
                    ;
                }
            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        try {
            loadAllReservation();
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadStudentIds();
        loadRoomIds();

        cmbStudentId.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                StudentDTO studentDTO = purchaseReserveBO.searchStudent((String) newValue);
                txtStudentname.setText(studentDTO.getStudentName());
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        cmbRoomId.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                RoomDTO roomDTO = purchaseReserveBO.searchRooms((String) newValue);
                lblRoomId.setText(roomDTO.getRoomID());
                lblRoomType.setText(roomDTO.getRoomType());
                lblRoomQty.setText(String.valueOf(roomDTO.getRoomQty()));

                txtRoomtype.setText(roomDTO.getRoomType());
                txtQtyOfroom.setText((String.valueOf(roomDTO.getRoomQty())));
                txtKeymoney.setText(roomDTO.getKeyMoney());
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    private void loadRoomIds() {
        try {
            cmbRoomId.getItems().addAll(purchaseReserveBO.getRoomIds());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStudentIds() {
        try {
            cmbStudentId.getItems().addAll(purchaseReserveBO.getStudentIds());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAllReservation() throws Exception {
        ObservableList<ReservationTM> observableList = FXCollections.observableArrayList();
        List<ReservationDTO> list = purchaseReserveBO.getAllReservation();

        for (ReservationDTO r : list) {
            String reserveID = r.getRes_id();
            Room room = r.getRoomID();
            String roomID = room.getRoom_type_id();
            String roomType = room.getType();
            int studentQty = r.getQty();
            double keyMoney = r.getKey_money();
            String status = r.getStatus();

            ReservationTM reservationTM = new ReservationTM(reserveID, roomID, roomType, studentQty, keyMoney, status);
            observableList.add(reservationTM);
            tblReservation.setItems(observableList);
        }
    }

    private void clearFields() {
        cmbStudentId.setValue(null);
        txtStudentname.clear();
        cmbRoomId.setValue(null);
        txtRoomtype.clear();
        txtKeymoney.clear();
        txtQtyOfroom.clear();
        txtStatus.clear();
        txtStudentQty.clear();
    }

    private void RF() {
        reservationId = generateNewOrderId();
        lblReserveID.setText(reservationId);
    }

    private String generateNewOrderId() {

        try {
            return purchaseReserveBO.generateNewOrderID();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();

        return "R001";
    }

    public void ReserveOnAction(ActionEvent actionEvent) throws Exception {
        String res_id = lblReserveID.getText();
        LocalDate date = MainFormController.date;
        StudentDTO studentDTO = purchaseReserveBO.searchStudent((String) cmbStudentId.getValue());
        Student student = new Student(studentDTO.getStudentID(), studentDTO.getStudentName(), studentDTO.getAddress(), studentDTO.getContactNo(), studentDTO.getDob(), studentDTO.getGender());
        RoomDTO roomDTO = purchaseReserveBO.searchRooms((String) cmbRoomId.getValue());
        Room room = new Room(roomDTO.getRoomID(), roomDTO.getRoomType(), roomDTO.getKeyMoney(), roomDTO.getRoomQty());
        double key_money = Double.parseDouble(txtKeymoney.getText());
        String status = txtStatus.getText();
        int qty = Integer.parseInt(txtStudentQty.getText());

        ReservationDTO reservationDTO = new ReservationDTO(res_id, date, student, room, key_money, status, qty);
        if (purchaseReserveBO.purchaseReserveSave(reservationDTO)) {
            updateRoomQty((String) cmbRoomId.getValue());
            loadAllReservation();
            RF();
            new Alert(Alert.AlertType.CONFIRMATION, "Saved.......").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again.......").show();
        }
    }

    private void updateRoomQty(String id) throws IOException, SQLException, ClassNotFoundException {
        RoomDTO roomDTO = purchaseReserveBO.searchRooms(id);
        int newqty = roomDTO.getRoomQty() - Integer.parseInt(txtStudentQty.getText());

        roomDTO.setRoomQty(newqty);
        roomBO.updateRoom(roomDTO);
    }

    public void updateOnAction(ActionEvent actionEvent) throws Exception {
        String res_id = lblReserveID.getText();
        LocalDate date = MainFormController.date;
        StudentDTO studentDTO = purchaseReserveBO.searchStudent((String) cmbStudentId.getValue());
        Student student = new Student(studentDTO.getStudentID(), studentDTO.getStudentName(), studentDTO.getAddress(), studentDTO.getContactNo(), studentDTO.getDob(), studentDTO.getGender());
        RoomDTO roomDTO = purchaseReserveBO.searchRooms((String) cmbRoomId.getValue());
        Room room = new Room(roomDTO.getRoomID(), roomDTO.getRoomType(), roomDTO.getKeyMoney(), roomDTO.getRoomQty());
        double key_money = Double.parseDouble(txtKeymoney.getText());
        String status = txtStatus.getText();
        int qty = Integer.parseInt(txtStudentQty.getText());

        ReservationDTO reservationDTO = new ReservationDTO(res_id, date, student, room, key_money, status, qty);

        if (purchaseReserveBO.UpdateReservation(reservationDTO)) {
            loadAllReservation();

            int b = preQty - Integer.parseInt(txtStudentQty.getText());

            RoomDTO roomDTO1 = new RoomDTO(room.getRoom_type_id(), room.getType(), room.getKey_money(), b);

            roomBO.updateRoom(roomDTO1);

            new Alert(Alert.AlertType.CONFIRMATION, "Updated.......").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again.......").show();
        }
    }

    public void AddToRemainOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        ReservationDetails.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/PaymentManageForm.fxml"));
        ReservationDetails.getChildren().add(parent);
        if (tblReservation.getSelectionModel().getSelectedItem() != null) {
            ReservationTM selectedItem = tblReservation.getSelectionModel().getSelectedItem();
            reservationId = selectedItem.getReservationID();

            ReservationDTO reservationDTO = purchaseReserveBO.searchReservation(reservationId);
            Student student = reservationDTO.getStudentID();
            Room roomID = reservationDTO.getRoomID();
            preQty = roomID.getQty() + selectedItem.getStudentQty();
            cmbStudentId.setValue(student.getStudent_id());
            txtStudentname.setText(student.getStudentName());

            lblReserveID.setText(selectedItem.getReservationID());

            cmbRoomId.setValue(selectedItem.getRoomID());
            txtRoomtype.setText(selectedItem.getRoomType());
            txtKeymoney.setText(String.valueOf(selectedItem.getKeyMoney()));
            txtQtyOfroom.setText(String.valueOf(roomID.getQty()));

            txtStatus.setText(selectedItem.getStatus());
            txtStudentQty.setText(String.valueOf(selectedItem.getStudentQty()));
        }
    }

    public void reservationTableClicked(MouseEvent mouseEvent) throws SQLException, IOException, ClassNotFoundException {
        if (tblReservation.getSelectionModel().getSelectedItem() != null) {
            ReservationTM selectedItem = tblReservation.getSelectionModel().getSelectedItem();
            reservationId = selectedItem.getReservationID();

            ReservationDTO reservationDTO = purchaseReserveBO.searchReservation(reservationId);
            Student student = reservationDTO.getStudentID();
            Room roomID = reservationDTO.getRoomID();
            preQty = roomID.getQty() + selectedItem.getStudentQty();
            cmbStudentId.setValue(student.getStudent_id());
            txtStudentname.setText(student.getStudentName());

            lblReserveID.setText(selectedItem.getReservationID());

            cmbRoomId.setValue(selectedItem.getRoomID());
            txtRoomtype.setText(selectedItem.getRoomType());
            txtKeymoney.setText(String.valueOf(selectedItem.getKeyMoney()));
            txtQtyOfroom.setText(String.valueOf(roomID.getQty()));

            txtStatus.setText(selectedItem.getStatus());
            txtStudentQty.setText(String.valueOf(selectedItem.getStudentQty()));
        }
    }
}

