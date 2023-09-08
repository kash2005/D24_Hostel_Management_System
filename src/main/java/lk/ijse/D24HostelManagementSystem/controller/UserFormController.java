package lk.ijse.D24HostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.D24HostelManagementSystem.bo.BOFactory;
import lk.ijse.D24HostelManagementSystem.bo.custom.UserBO;
import lk.ijse.D24HostelManagementSystem.dto.RoomDTO;
import lk.ijse.D24HostelManagementSystem.dto.UserDTO;
import lk.ijse.D24HostelManagementSystem.entity.User;
import lk.ijse.D24HostelManagementSystem.tm.RoomTM;
import lk.ijse.D24HostelManagementSystem.tm.UserTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colPasswordHint;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private JFXTextField userId;

    @FXML
    private JFXTextField passwordId;

    @FXML
    private JFXTextField passwordHint;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXTextField emaillId;

    @FXML
    private JFXTextField userNameId;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void emailOnAction(ActionEvent event) {
        btnSave.fire();
    }

    @FXML
    void passwordHintOnAction(ActionEvent event) {
        emaillId.requestFocus();
    }

    @FXML
    void passwordOnAction(ActionEvent event) {
        passwordHint.requestFocus();
    }

    @FXML
    void tblUserOnMouseClick(MouseEvent event) {

    }

    @FXML
    void userIdOnAction(ActionEvent event) {
        userNameId.requestFocus();
    }

    @FXML
    void userNameOnAction(ActionEvent event) {
        passwordId.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAll();
        generateNextId();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
        colPasswordHint.setCellValueFactory(new PropertyValueFactory<>("userPasswordHint"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));

    }

    private void generateNextId() {
        String nextId = userBO.generateNextUserId();
        userId.setText(nextId);
    }

    private void getAll() {
        ObservableList<UserTM> observableList = FXCollections.observableArrayList();
        List<UserDTO> userDTOList = userBO.getAllUser();
        for (UserDTO userDTO : userDTOList) {
            observableList.add(new UserTM(
                            userDTO.getUserId(),
                            userDTO.getUserName(),
                            userDTO.getUserPassword(),
                            userDTO.getUserPasswordHint(),
                            userDTO.getUserEmail()
                    )
            );
        }
        tblUser.setItems(observableList);
    }
}
