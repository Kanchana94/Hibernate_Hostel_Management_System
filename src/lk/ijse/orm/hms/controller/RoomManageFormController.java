package lk.ijse.orm.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm.hms.bo.BOFactory;
import lk.ijse.orm.hms.bo.BOTypes;
import lk.ijse.orm.hms.bo.customer.RoomBO;
import lk.ijse.orm.hms.dto.RoomDTO;
import lk.ijse.orm.hms.view.tdm.RoomTM;

import java.io.IOException;
import java.util.List;

public class RoomManageFormController {
    public AnchorPane RoomManageForm;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomQty;
    public TableView<RoomTM> tblRoom;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colKeymny;
    public TableColumn colRoomQty;
    public Button btnsave;
    public Button btndelete;
    public Button btnAddNewroom;
    public ComboBox<String> cmbRoomId;
    public ComboBox<String> cmbRoomtype;

    RoomBO roomBO = BOFactory.getInstance().getBO(BOTypes.ROOM);

    public void initialize(){
        cmbRoomId.getItems().addAll("RM-01","RM-02","RM-03","RM-04");
        cmbRoomtype.getItems().addAll("Non-AC","Non-AC/Food","AC","AC/Food");

        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomID"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("roomType"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("roomQty"));

        initUI();

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnsave.setText(newValue != null ? "Update" : "Save");
            btndelete.setDisable(false);

            if(newValue != null){
                cmbRoomId.setValue(newValue.getRoomID());
                cmbRoomtype.setValue(newValue.getRoomType());
                txtKeyMoney.setText(newValue.getKeyMoney());
                txtRoomQty.setText(String.valueOf(newValue.getRoomQty()));


                cmbRoomId.setDisable(false);
                cmbRoomtype.setDisable(false);
                txtKeyMoney.setDisable(false);
                txtRoomQty.setDisable(false);


                btnsave.setDisable(false);
            }
        });

        loadAllRooms();
    }
    private void loadAllRooms() {
        tblRoom.getItems().clear();
        try {
            List<RoomDTO> allrooms = roomBO.getAllRooms();
            for(RoomDTO r : allrooms) {
                tblRoom.getItems().add(new RoomTM(
                        r.getRoomID(),
                        r.getRoomType(),
                        r.getKeyMoney(),
                        r.getRoomQty()
                ));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }
    private void clearFields(){
        cmbRoomId.setValue(null);
        cmbRoomtype.setValue(null);
        txtKeyMoney.clear();
        txtRoomQty.clear();

    }
    private void initUI() {
        cmbRoomId.setValue(null);
        cmbRoomtype.setValue(null);
        txtKeyMoney.clear();
        txtRoomQty.clear();
        cmbRoomId.setDisable(true);
        cmbRoomtype.setDisable(true);
        txtKeyMoney.setDisable(true);
        txtRoomQty.setDisable(true);
        cmbRoomId.setEditable(true);
        btnsave.setDisable(true);
        btndelete.setDisable(true);
    }
    public void SaveOnAction(ActionEvent actionEvent) throws IOException {
        String id =cmbRoomId.getValue();
        String type = cmbRoomtype.getValue();
        String key_mny = txtKeyMoney.getText();
        int qty = Integer.valueOf(txtRoomQty.getText());

        if (!key_mny.matches("^[0-9]{3,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Key_Money").show();
            txtKeyMoney.requestFocus();
            return;
        } else if (!txtRoomQty.getText().matches("^[0-9]{1,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty ").show();
            txtRoomQty.requestFocus();
            return;
        }
        if (btnsave.getText().equalsIgnoreCase("Save")) {

            if (roomBO.saveRoom(new RoomDTO(id, type, key_mny, qty)))
            {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                tblRoom.getItems().add(new RoomTM(id, type, key_mny, qty));
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

            }

        } else {
            try {
                if (roomBO.updateRoom(new RoomDTO(id, type, key_mny, qty))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                    loadAllRooms();
                    clearFields();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            }
        }
    }
    public void DeleteOnAction(ActionEvent actionEvent) {
        String id=tblRoom.getSelectionModel().getSelectedItem().getRoomID();
        try {
            roomBO.deleteRoom(id);
            tblRoom.getItems().remove(tblRoom.getSelectionModel().getSelectedItem());
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted..!").show();

            tblRoom.getSelectionModel().clearSelection();
            clearFields();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something Happened.Try again Carefully..!").show();
        }
    }
    public void AddNewRoomOnAction(ActionEvent actionEvent) {
        cmbRoomId.setDisable(false);
        cmbRoomtype.setDisable(false);
        txtKeyMoney.setDisable(false);
        txtRoomQty.setDisable(false);
        cmbRoomId.setValue(null);
        cmbRoomtype.setValue(null);
        txtKeyMoney.clear();
        txtRoomQty.clear();
        cmbRoomId.requestFocus();
        btnsave.setDisable(false);
        btnsave.setText("Save");
        tblRoom.getSelectionModel().clearSelection();
    }
}
