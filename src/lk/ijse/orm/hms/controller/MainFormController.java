package lk.ijse.orm.hms.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class MainFormController {
    public AnchorPane DashBoardForm;
    public AnchorPane DashBoard;
    public Label lblDate;
    public Label lblTime;

    public Button btnLogout;
    static LocalDate date;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadDateAndTime();

    }

    private void loadDateAndTime() {
        /* set Date*/
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        date= LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        /* set Date*/
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateFormat dateFormat = new SimpleDateFormat("hh : mm : ss aa");
            String dateString = dateFormat.format(new Date()).toString();
            lblTime.setText(dateString);
        }),
                new KeyFrame(Duration.seconds(1))

        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void ManageStudentOnAction(ActionEvent actionEvent) throws IOException {
        DashBoard.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ManageStudentForm.fxml"));
        DashBoard.getChildren().add(parent);
    }

    public void PaymentOnAction(ActionEvent actionEvent) throws IOException {
        DashBoard.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/PaymentManageForm.fxml"));
        DashBoard.getChildren().add(parent);
    }

    public void ReservationOnAction(ActionEvent actionEvent) throws IOException {
        DashBoard.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ReservationViewForm.fxml"));
        DashBoard.getChildren().add(parent);
    }

    public void ManageRoomOnAction(ActionEvent actionEvent) throws IOException {
        DashBoard.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/RoomManageForm.fxml"));
        DashBoard.getChildren().add(parent);
    }

    public void LogOutOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage=(Stage) DashBoardForm.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

    public void LoginDetailsOnAction(ActionEvent actionEvent) throws IOException {
        DashBoard.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/orm/hms/view/LoginDetailsViewForm.fxml"));
        DashBoard.getChildren().add(parent);
    }
}
