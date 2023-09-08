package lk.ijse.D24HostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId.getText());
        userDTO.setUserName(userNameId.getText());
        userDTO.setUserPassword(passwordId.getText());
        userDTO.setUserPasswordHint(passwordHint.getText());
        userDTO.setUserEmail(emaillId.getText());
//        if (roomTM != null){
        userBO.deleteUser(userDTO);
        new Alert(Alert.AlertType.INFORMATION, "User Deleted!").show();
        clearAll();
        refreshTable();
        btnSave.setText("Save");
        btnSave.setStyle("-fx-background-color:  #765827; -fx-background-radius: 10;");
//        }else {
//            new Alert(Alert.AlertType.ERROR, "Select room first!");
//        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId.getText());
        userDTO.setUserName(userNameId.getText());
        userDTO.setUserPassword(passwordId.getText());
        userDTO.setUserPasswordHint(passwordHint.getText());
        userDTO.setUserEmail(emaillId.getText());

        if (btnSave.getText().equals("Save")){
            boolean isSaved = userBO.saveUser(userDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "User saved!").show();
                getAll();
                clearAll();
                generateNextId();
            } else {
                new Alert(Alert.AlertType.ERROR, "Room not saved!").show();
            }
        } else if (btnSave.getText().equals("Update")) {
            btnSave.setStyle("-fx-background-color: #379237; -fx-background-radius: 10;");
            boolean isUpdated = userBO.updateUser(userDTO);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"User updated!").show();
                getAll();
                clearAll();
                refreshTable();
                btnSave.setText("Save");
                btnSave.setStyle("-fx-background-color:  #765827; -fx-background-radius: 10;");
            }else {
                new Alert(Alert.AlertType.ERROR,"User not updated!").show();
            }
        }
    }

    private void refreshTable() {
        try {
            generateNextId();
            List<UserDTO> all = userBO.getAllUser();
            ObservableList<UserTM> userDTOObservableList = FXCollections.observableArrayList();
            all.forEach(dto -> userDTOObservableList.add(new UserTM(dto.getUserId(), dto.getUserName(), dto.getUserPassword(), dto.getUserPasswordHint(),dto.getUserEmail())));
            tblUser.setItems(userDTOObservableList);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
            tblUser.getItems().clear();
        }
    }

    private void clearAll() {
        userId.clear();
        userNameId.clear();
        passwordId.clear();
        passwordHint.clear();
        emaillId.clear();
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
        UserTM selectedItem = tblUser.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                btnSave.setDisable(false);
                userId.setText(selectedItem.getUserId());
                userNameId.setText(selectedItem.getUserName());
                passwordId.setText(selectedItem.getUserPassword());
                passwordHint.setText(selectedItem.getUserPasswordHint());
                emaillId.setText(selectedItem.getUserEmail());
                btnSave.setText("Update");
                btnSave.setStyle("-fx-background-color: #379237; -fx-background-radius: 10;");
            }

        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
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
