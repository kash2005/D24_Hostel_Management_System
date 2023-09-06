package lk.ijse.D24HostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import lk.ijse.D24HostelManagementSystem.bo.BOFactory;
import lk.ijse.D24HostelManagementSystem.bo.custom.RoomBO;
import lk.ijse.D24HostelManagementSystem.bo.custom.StudentBO;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomFormController implements Initializable {
    @FXML
    private JFXTextField txtRoomId;

    @FXML
    private JFXTextField txtKeyMoneyId;

    @FXML
    private JFXTextField txtQtyId;

    @FXML
    private ComboBox<?> comboTypeId;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void comboTypeOnAction(ActionEvent event) {
        txtKeyMoneyId.requestFocus();
//        ComboBox<String> comboBox = new ComboBox<>();
//
//// Create a list of items to add to the ComboBox
//        ObservableList<String> items = FXCollections.observableArrayList(
//                "Item 1",
//                "Item 2",
//                "Item 3"
//        );
//
//// Add the items to the ComboBox
//        comboBox.setItems(items);
//
//// Optionally, set a default selection
//        comboBox.getSelectionModel().select(0);
    }

    @FXML
    void txtKeyMoneyOnAction(ActionEvent event) {
        txtQtyId.requestFocus();
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

    @FXML
    void txtRoomOnAction(ActionEvent event) {
        comboTypeId.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAll();
        generateNextId();
        setCellValueFactory();
    }

    private void setCellValueFactory() {

    }

    private void generateNextId() {
        String nextId = roomBO.generateNextRoomId();
        txtRoomId.setText(nextId);
    }

    private void getAll() {

    }
}
