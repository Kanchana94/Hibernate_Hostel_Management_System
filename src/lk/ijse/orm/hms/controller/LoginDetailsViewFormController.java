package lk.ijse.orm.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm.hms.bo.customer.UserBO;
import lk.ijse.orm.hms.bo.customer.impl.UserBOImpl;
import lk.ijse.orm.hms.dto.UserLoginDTO;
import lk.ijse.orm.hms.view.tdm.UserTM;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginDetailsViewFormController {
    public AnchorPane LoginDetailsViewForm;
    public JFXTextField txtUserID;
    //public JFXTextField txtPassword;
    public TableView<UserTM> tblLogInDetail;
    public TableColumn colUserID;
    public TableColumn colUserName;
    public TableColumn colPassward;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    //public JFXTextField txtUserName;
    public javafx.scene.control.TextField txtUserId;
    public javafx.scene.control.Button txtSave;
    public javafx.scene.control.Button txtDelete;
    public javafx.scene.control.TextField txtUsername;
    public javafx.scene.control.TextField txtpassword;
    public javafx.scene.control.Button btnAddnew;

    UserBO userBO=new UserBOImpl();

    public void initialize() throws IOException {

        tblLogInDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userID"));
        tblLogInDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblLogInDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("password"));

        initUI();

        tblLogInDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnDelete.setDisable(false);

            if(newValue != null){
                txtUserID.setText(newValue.getUserID());
                txtUsername.setText(newValue.getUserName());
                txtpassword.setText(newValue.getPassword());


                txtUserID.setDisable(false);
                txtUsername.setDisable(false);
                txtpassword.setDisable(false);


                btnSave.setDisable(false);
            }
        });

        loadAllUsers();
    }

    private void loadAllUsers() {
        tblLogInDetail.getItems().clear();
        try {
            List<UserLoginDTO> userLoginDTOS = userBO.getAllUser();
            for(UserLoginDTO u : userLoginDTOS) {
                tblLogInDetail.getItems().add(new UserTM(
                        u.getUserID(),
                        u.getUserName(),
                        u.getPassword()
                ));
            }
        } catch (Exception e) {
            // System.out.println(e);
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    private void clearFields(){
        txtUserID.clear();
        txtUsername.clear();
        txtpassword.clear();

    }

    private void initUI() {
        txtUserID.clear();
        txtUsername.clear();
        txtpassword.clear();
        txtUserID.setDisable(true);
        txtUsername.setDisable(true);
        txtpassword.setDisable(true);
        txtUserID.setEditable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void AddNewOnAction(ActionEvent actionEvent) {
        txtUserID.setDisable(false);
        txtUsername.setDisable(false);
        txtpassword.setDisable(false);
        txtUserID.clear();
        txtUsername.clear();
        txtpassword.clear();
        txtUserID.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblLogInDetail.getSelectionModel().clearSelection();
    }

    public void SaveOnAction(ActionEvent actionEvent) throws IOException {
        String id = txtUserID.getText();
        String name = txtUsername.getText();
        String password = txtpassword.getText();

        if (!id.matches("^(U00)[0-9]{1,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
            txtUserID.requestFocus();
            return;

        }else if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtUsername.requestFocus();
            return;
        } else if (!password.matches("^[A-z]{3,8}[0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtpassword.requestFocus();
            return;
        }
        if (btnSave.getText().equalsIgnoreCase("Save")) {

            if (userBO.saveUser(new UserLoginDTO(id, name, password)))
            {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                tblLogInDetail.getItems().add(new UserTM(id, name, password));
                loadAllUsers();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

            }

        } else {
            try {
                if (userBO.updateUser(new UserLoginDTO(id, name, password))){
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                    loadAllUsers();
                    clearFields();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            }
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        String id=tblLogInDetail.getSelectionModel().getSelectedItem().getUserID();
        try {
            userBO.deleteUser(id);
            tblLogInDetail.getItems().remove(tblLogInDetail.getSelectionModel().getSelectedItem());
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted..!").show();

            tblLogInDetail.getSelectionModel().clearSelection();
            clearFields();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something Happened.Try again Carefully..!").show();
        }
    }
}
