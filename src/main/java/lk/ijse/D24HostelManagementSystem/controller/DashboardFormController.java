package lk.ijse.D24HostelManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    void dashboardBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/dashboardLoadForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadDashboardUi();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDashboardUi() throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/dashboardLoadForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void signoutBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/loginForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        Stage stage1 = (Stage) root.getScene().getWindow();
        stage1.close();
        stage.setTitle("D24 Hostel Management System - Login Page");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void studentBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/studentForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void roomBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/roomForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void reservationBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/reservationForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void settingBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/userForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("D24 Hostel Management System - Setting Page");
        stage.show();
    }

}

