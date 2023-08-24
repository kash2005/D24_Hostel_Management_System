package lk.ijse.D24HostelManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgetPasswordFormController {
    @FXML
    private AnchorPane root;

    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
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
    void continueBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/setNewPasswordForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        Stage stage1 = (Stage) root.getScene().getWindow();
        stage1.close();
        stage.setTitle("D24 Hostel Management System - Set New Password Page");
        stage.centerOnScreen();
        stage.show();
    }
}
