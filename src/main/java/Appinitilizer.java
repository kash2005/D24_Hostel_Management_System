import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Appinitilizer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/loadingForm.fxml"));
        stage.setScene(new Scene(load));
        stage.setTitle("D24 Hostel Management System - Loding Page");
        stage.centerOnScreen();
        stage.show();
    }
}
