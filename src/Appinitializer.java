import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.orm.hms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class Appinitializer extends Application{
    /*public static void main(String[] args) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        transaction.commit();
        session.close();
    }*/
    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setResizable(false);

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/orm/hms/view/MainForm.fxml"))));
        primaryStage.show();
        primaryStage.centerOnScreen();
        //primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/orm/hms/view/LoginDetailsViewForm.fxml"))));
    }
}

