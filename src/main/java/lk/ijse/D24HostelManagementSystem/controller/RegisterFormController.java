package lk.ijse.D24HostelManagementSystem.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.D24HostelManagementSystem.bo.BOFactory;
import lk.ijse.D24HostelManagementSystem.bo.custom.UserBO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField passwordId;

    @FXML
    private JFXPasswordField repeatPasswordId;

    @FXML
    private JFXTextField passwordHintId;

    @FXML
    private JFXTextField txtUserId;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void backToLoginBtnOnAction(ActionEvent event) throws IOException {
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
    void passwordHintOnAction(ActionEvent event) {

    }

    @FXML
    void passwordOnAction(ActionEvent event) {

    }

    @FXML
    void repeatPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void userIdOnAction(ActionEvent event) {

    }

    @FXML
    void userNameOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateId();
    }

    private void generateId() {
        String nextId = userBO.generateNextUserId();
        txtUserId.setText(nextId);
    }
}
