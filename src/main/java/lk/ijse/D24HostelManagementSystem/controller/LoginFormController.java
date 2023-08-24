package lk.ijse.D24HostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.D24HostelManagementSystem.bo.BOFactory;
import lk.ijse.D24HostelManagementSystem.bo.custom.UserBO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXTextField userNameTextId;

    @FXML
    private JFXTextField txtPasswordId;

    @FXML
    private JFXPasswordField passwordTextId;

    @FXML
    private ImageView imgShowId;

    @FXML
    private ImageView imgHideId;

    @FXML
    private AnchorPane root;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException {
        String name = userNameTextId.getText();
        String password = passwordTextId.getText();
        String password1 = txtPasswordId.getText();

        boolean isValid = userBO.checkUser(name,password,password1);
        if (isValid){
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/dashboardForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(anchorPane));
            Stage stage1 = (Stage) root.getScene().getWindow();
            stage1.close();
            stage.setTitle("D24 Hostel Management System - Dashboard");
            stage.centerOnScreen();
            stage.show();
        }
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        loginBtn.fire();
    }

    @FXML
    void passwordTextOnAction(ActionEvent event) {
        loginBtn.fire();
    }

    @FXML
    void imgHidePasswordOnMouseClick(MouseEvent event) {
        String password = passwordTextId.getText();
        txtPasswordId.setText(password);
        passwordTextId.setVisible(false);
        txtPasswordId.setVisible(true);
        imgHideId.setVisible(false);
        imgShowId.setVisible(true);
    }

    @FXML
    void showPasswordOnMouseClick(MouseEvent event) {
        String password = txtPasswordId.getText();
        passwordTextId.setText(password);
        txtPasswordId.setVisible(false);
        passwordTextId.setVisible(true);
        imgShowId.setVisible(false);
        imgHideId.setVisible(true);
    }

    @FXML
    void userNameTextOnAction(ActionEvent event) {
        passwordTextId.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgShowId.setVisible(false);
        txtPasswordId.setVisible(false);
    }

    @FXML
    void signUpBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/signUpForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        Stage stage1 = (Stage) root.getScene().getWindow();
        stage1.close();
        stage.setTitle("D24 Hostel Management System - Sign Up Page");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void forgetPasswordOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/forgetPasswordForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        Stage stage1 = (Stage) root.getScene().getWindow();
        stage1.close();
        stage.setTitle("D24 Hostel Management System - Forget password Page");
        stage.centerOnScreen();
        stage.show();
    }

}
