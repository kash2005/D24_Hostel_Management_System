package lk.ijse.D24HostelManagementSystem.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.D24HostelManagementSystem.bo.BOFactory;
import lk.ijse.D24HostelManagementSystem.bo.custom.UserBO;
import lk.ijse.D24HostelManagementSystem.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtpasswordId;

    @FXML
    private JFXPasswordField txtrepeatPasswordId;

    @FXML
    private JFXTextField txtpasswordHintId;

    @FXML
    private JFXTextField txtUserId;

    @FXML
    private JFXTextField txtEmailId;

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
    void txtpasswordHintOnAction(ActionEvent event) {

    }

    @FXML
    void txtpasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtrepeatPasswordOnAction(ActionEvent event) {

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


    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void registerBtnOnAction(ActionEvent event) {
        String id = txtUserId.getText();
        String name = txtUserName.getText();
        String email = txtEmailId.getText();
        String password = txtpasswordId.getText();
        String passwordHint = txtpasswordHintId.getText();

        UserDTO userDTO = new UserDTO(id,name,email,password,passwordHint);
        boolean isSaved = userBO.saveUser(userDTO);

        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Registarion Successful !").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"User Not Saved !").show();
        }
    }
}
