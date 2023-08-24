package lk.ijse.D24HostelManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardLoadFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imgSettingId;

    @FXML
    void unpaidViewBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/unpaidStudentForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void imgSettingOnMouseClicked(MouseEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/userForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("D24 Hostel Management System - Setting Page");
        stage.show();
    }
}
