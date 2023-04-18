package lk.ijse.orm.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.orm.hms.bo.customer.impl.UserBOImpl;
import lk.ijse.orm.hms.entity.UserLogin;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane LoginForm;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXButton btnlogIn;
    public CheckBox CheckBoxPassword;

    public JFXTextField txtPasswordHidden;

    UserBOImpl userBO = new UserBOImpl();

    public void changeVisibility(MouseEvent mouseEvent) {
        if (CheckBoxPassword.isSelected()) {
            txtPasswordHidden.setText(txtPassword.getText());
            txtPasswordHidden.setVisible(true);
            txtPassword.setVisible(false);
            return;
        }
        txtPasswordHidden.setText(txtPassword.getText());
        txtPassword.setVisible(true);
        txtPasswordHidden.setVisible(false);
    }

    public void LogInOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        String name = txtUserName.getText();
        String password = txtPassword.getText();

        UserLogin user = userBO.searchUser(name);

        if (user != null) {
            if (txtPassword.getText().equals(user.getPassword())) {
                setUI("DashBoardForm");
            } else {
                new Alert(Alert.AlertType.ERROR, "Incorrect Password..!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Incorrect User ID!").show();
        }
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) LoginForm.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }
}