package lk.ijse.D24HostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.D24HostelManagementSystem.bo.BOFactory;
import lk.ijse.D24HostelManagementSystem.bo.custom.RoomBO;
import lk.ijse.D24HostelManagementSystem.bo.custom.StudentBO;
import lk.ijse.D24HostelManagementSystem.dto.RoomDTO;
import lk.ijse.D24HostelManagementSystem.dto.StudentDTO;
import lk.ijse.D24HostelManagementSystem.tm.RoomTM;
import lk.ijse.D24HostelManagementSystem.tm.StudentTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomFormController implements Initializable {
    @FXML
    private JFXTextField txtRoomId;

    @FXML
    private JFXTextField txtKeyMoneyId;

    @FXML
    private JFXTextField txtQtyId;

    @FXML
    private ComboBox<String> comboTypeId;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableView<RoomTM> tblRoomId;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private JFXTextField txtSearch;

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
//        RoomTM roomTM = tblRoomId.getSelectionModel().getSelectedItem();
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomTypeId(txtRoomId.getText());
        roomDTO.setType(comboTypeId.getValue());
        roomDTO.setKeyMoney(Double.valueOf(txtKeyMoneyId.getText()));
        roomDTO.setQty(txtQtyId.getLength());
//        if (roomTM != null){
            roomBO.deleteRoom(roomDTO);
            new Alert(Alert.AlertType.INFORMATION, "Room Deleted!").show();
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
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomTypeId(txtRoomId.getText());
        roomDTO.setType(comboTypeId.getValue());
        roomDTO.setKeyMoney(Double.valueOf(txtKeyMoneyId.getText()));
        roomDTO.setQty(txtQtyId.getLength());

        if (btnSave.getText().equals("Save")){
            boolean isSaved = roomBO.saveRoom(roomDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room saved!").show();
                getAll();
                clearAll();
                generateNextId();
            } else {
                new Alert(Alert.AlertType.ERROR, "Room not saved!").show();
            }
        } else if (btnSave.getText().equals("Update")) {
            btnSave.setStyle("-fx-background-color: #379237; -fx-background-radius: 10;");
            boolean isUpdated = roomBO.updateRoom(roomDTO);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Room updated!").show();
                getAll();
                clearAll();
                refreshTable();
                btnSave.setText("Save");
                btnSave.setStyle("-fx-background-color:  #765827; -fx-background-radius: 10;");
            }else {
                new Alert(Alert.AlertType.ERROR,"Room not updated!").show();
            }
        }
    }

    private void refreshTable() {
        try {
            generateNextId();
            List<RoomDTO> all = roomBO.getAllRoom();
            ObservableList<RoomTM> roomDTOObservableList = FXCollections.observableArrayList();
            all.forEach(dto -> roomDTOObservableList.add(new RoomTM(dto.getRoomTypeId(), dto.getType(), dto.getKeyMoney(), dto.getQty())));
            tblRoomId.setItems(roomDTOObservableList);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
            tblRoomId.getItems().clear();
        }
    }

    @FXML
    void roomTblOnMouseClicked(MouseEvent event) {
        RoomTM selectedItem = tblRoomId.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                btnSave.setDisable(false);
                txtRoomId.setText(selectedItem.getRoomTypeId());
                comboTypeId.setValue(selectedItem.getType());
                txtKeyMoneyId.setText(String.valueOf(selectedItem.getKeyMoney()));
                txtQtyId.setText(String.valueOf(selectedItem.getQty()));
                btnSave.setText("Update");
                btnSave.setStyle("-fx-background-color: #379237; -fx-background-radius: 10;");
            }

        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearAll() {
        txtRoomId.clear();
        comboTypeId.setValue(null);
        txtKeyMoneyId.clear();
        txtQtyId.clear();
    }

    @FXML
    void comboTypeOnAction(ActionEvent event) {
        txtKeyMoneyId.requestFocus();

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
        ObservableList<String> list = FXCollections.observableArrayList("Non-AC","Non-Ac/ Food","AC","AC / Food");
        comboTypeId.setItems(list);

        getAll();
        generateNextId();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void generateNextId() {
        String nextId = roomBO.generateNextRoomId();
        txtRoomId.setText(nextId);
    }

    private void getAll() {
        ObservableList<RoomTM> observableList = FXCollections.observableArrayList();
        List<RoomDTO> roomDTOList = roomBO.getAllRoom();
        for (RoomDTO roomDTO : roomDTOList) {
            observableList.add(new RoomTM(
                            roomDTO.getRoomTypeId(),
                            roomDTO.getType(),
                            roomDTO.getKeyMoney(),
                            roomDTO.getQty()
                    )
            );
        }
        tblRoomId.setItems(observableList);
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String roomId = txtSearch.getText();
        RoomDTO roomDTO = roomBO.searchRoom(roomId);
        if (roomDTO != null){
            fillDate(roomDTO);
            btnSave.setText("Update");
            btnSave.setStyle("-fx-background-color: #379237; -fx-background-radius: 10;");
        }else {
            new Alert(Alert.AlertType.WARNING,"Don't have this id").show();
        }
        txtSearch.setText("");
    }

    private void fillDate(RoomDTO roomDTO) {
        txtRoomId.setText(roomDTO.getRoomTypeId());
        comboTypeId.setValue(roomDTO.getRoomTypeId());
        txtKeyMoneyId.setText(String.valueOf(roomDTO.getKeyMoney()));
        txtQtyId.setText(String.valueOf(roomDTO.getQty()));
    }
}
