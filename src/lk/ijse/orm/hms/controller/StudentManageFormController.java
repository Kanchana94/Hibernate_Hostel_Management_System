package lk.ijse.orm.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm.hms.bo.BOFactory;
import lk.ijse.orm.hms.bo.BOTypes;
import lk.ijse.orm.hms.bo.customer.StudentBO;
import lk.ijse.orm.hms.dto.StudentDTO;
import lk.ijse.orm.hms.view.tdm.StudentTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class StudentManageFormController {

    public AnchorPane ManageStudentForm;
    public TextField txtStdAddress;
    public TextField txtStdContactNo;
    public TextField txtStdId;
    public TextField txtStdName;
    public DatePicker txtDOB;
    //public ComboBox cmbGender;
    //public JFXComboBox<String> cmbGender;
    public TableView<StudentTM> tblStudent;
    public JFXButton btnAddNewStudent;
    public JFXButton btnSave;
    //public TableView tblStudent;
    public TableColumn colStudentId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colConNo;
    public TableColumn colDOB;
    public TableColumn colGender;
    public JFXButton btnDelete;
    public ComboBox<String> cmbgender;

    StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

    public void initialize() throws IOException {

        cmbgender.getItems().addAll("Male","Female");

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentName"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        initUI();

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnDelete.setDisable(false);

            if(newValue != null){
                txtStdId.setText(newValue.getStudentID());
                txtStdName.setText(newValue.getStudentName());
                txtStdAddress.setText(newValue.getAddress());
                txtStdContactNo.setText(newValue.getContactNo());
                txtDOB.setValue(newValue.getDob());
                cmbgender.setValue(newValue.getGender());

                txtStdId.setDisable(false);
                txtStdName.setDisable(false);
                txtStdAddress.setDisable(false);
                txtStdContactNo.setDisable(false);
                txtDOB.setDisable(false);
                cmbgender.setDisable(false);

                btnSave.setDisable(false);
            }
        });

        loadAllStudents();
    }

    private void loadAllStudents() {
        tblStudent.getItems().clear();
        try {
            List<StudentDTO> allStudents = studentBO.getAllStudent();
            for(StudentDTO s : allStudents) {
                tblStudent.getItems().add(new StudentTM(
                        s.getStudentID(),
                        s.getStudentName(),
                        s.getAddress(),
                        s.getContactNo(),
                        s.getDob(),
                        s.getGender()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    private void clearFields(){
        txtStdId.clear();
        txtStdName.clear();
        txtStdAddress.clear();
        txtStdContactNo.clear();
        txtDOB.setValue(null);
        cmbgender.setValue(null);
    }

    private void initUI() {
        txtStdId.clear();
        txtStdName.clear();
        txtStdAddress.clear();
        txtStdContactNo.clear();
        txtStdId.setDisable(true);
        txtStdName.setDisable(true);
        txtStdAddress.setDisable(true);
        txtStdContactNo.setDisable(true);
        txtDOB.setDisable(true);
        cmbgender.setDisable(true);
        txtStdId.setEditable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        txtStdId.setDisable(false);
        txtStdName.setDisable(false);
        txtStdAddress.setDisable(false);
        txtStdContactNo.setDisable(false);
        txtDOB.setDisable(false);
        cmbgender.setDisable(false);
        txtStdId.clear();
        txtStdName.clear();
        txtStdAddress.clear();
        txtStdContactNo.clear();
        txtStdId.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblStudent.getSelectionModel().clearSelection();
    }

    public void AddNewStudentOnAction(ActionEvent actionEvent) {
        txtStdId.setDisable(false);
        txtStdName.setDisable(false);
        txtStdAddress.setDisable(false);
        txtStdContactNo.setDisable(false);
        txtDOB.setDisable(false);
        cmbgender.setDisable(false);
        txtStdId.clear();
        txtStdName.clear();
        txtStdAddress.clear();
        txtStdContactNo.clear();
        txtStdId.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblStudent.getSelectionModel().clearSelection();
    }

    public void SaveStudentOnAction(ActionEvent actionEvent) throws IOException {
        String id = txtStdId.getText();
        String name = txtStdName.getText();
        String address = txtStdAddress.getText();
        String contact_no = txtStdContactNo.getText();
        LocalDate dob = txtDOB.getValue();
        String gender = cmbgender.getValue();

        if (!id.matches("^(S00)[0-9]{1,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
            txtStdId.requestFocus();
            return;

        }else if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtStdName.requestFocus();
            return;
        } else if (!address.matches("^[A-z0-9 ,/]{4,20}$")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtStdAddress.requestFocus();
            return;
        }else if (!contact_no.matches("[A-Za-z ]+")) {
            //"^07(7|6|8|1|2|5|0|4)-[0-9]{7}$"
            new Alert(Alert.AlertType.ERROR, "Invalid city").show();
            txtStdContactNo.requestFocus();
            return;
        }
        if (btnSave.getText().equalsIgnoreCase("Save")) {


            if (studentBO.saveStudent(new StudentDTO(id, name, address, contact_no, dob, gender)))
            {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                tblStudent.getItems().add(new StudentTM(id, name, address, contact_no, dob, gender));
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

            }


        } else {
            try {
                if (studentBO.updateStudent(new StudentDTO(id, name, address, contact_no, dob, gender))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                    loadAllStudents();
                    clearFields();
                }
            } catch (Exception e) {
                // System.out.println("Exception 2");
                //System.out.println(e);
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            }
        }
    }
}
