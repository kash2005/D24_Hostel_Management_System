package lk.ijse.D24HostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.D24HostelManagementSystem.bo.BOFactory;
import lk.ijse.D24HostelManagementSystem.bo.custom.ReservationBO;
import lk.ijse.D24HostelManagementSystem.bo.custom.RoomBO;
import lk.ijse.D24HostelManagementSystem.bo.custom.StudentBO;
import lk.ijse.D24HostelManagementSystem.dto.ReservsionDTO;
import lk.ijse.D24HostelManagementSystem.dto.RoomDTO;
import lk.ijse.D24HostelManagementSystem.dto.StudentDTO;
import lk.ijse.D24HostelManagementSystem.tm.ReservationTM;
import lk.ijse.D24HostelManagementSystem.tm.RoomTM;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationFormController implements Initializable {

    @FXML
    private TableView<ReservationTM> tblReservationId;

    @FXML
    private TableColumn<?, ?> colResId;

    @FXML
    private TableColumn<?, ?> colDateId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colStatusid;

    @FXML
    private JFXTextField reservationId;

    @FXML
    private ComboBox<String> studentId;

    @FXML
    private DatePicker booking;

    @FXML
    private JFXTextField roomTypeId;

    @FXML
    private JFXTextField pricePerRoomId;

    @FXML
    private ComboBox<String> roomId;

    @FXML
    private JFXTextField AvailableQtyId;

    @FXML
    private JFXButton btnPaid;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnBooking;


    @FXML
    private TableView<RoomTM> tblRoomId;

    @FXML
    private TableColumn<?, ?> coltblRoomId;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private TableColumn<?, ?> colRoomKeyMoney;

    @FXML
    private TableColumn<?, ?> colRoomQty;

    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    @FXML
    void btnBookingOnAction(ActionEvent event) {
        saveReservation("un-paid");
    }

    private void saveReservation(String s) {
        try {
            /*if (validateData()) {*/
            ReservsionDTO reservationDto = new ReservsionDTO();
            reservationDto.setResId(reservationId.getText());
            reservationDto.setStatus(s);
            reservationDto.setDate(Date.valueOf(booking.getValue()));

            StudentDTO studentDto = studentBO.searchStudent(studentId.getValue());
            reservationDto.setStudent(studentDto);

            RoomDTO dto = roomBO.searchRoom(roomId.getValue());
            dto.setQty(dto.getQty() - 1);
            reservationDto.setRoom(dto);

            reservationBO.saveReservation(reservationDto);
            reservationGetAll();

            /*Stage stage = (Stage) root.getScene().getWindow();
            stage.setAlwaysOnTop(false);*/
            new Alert(Alert.AlertType.INFORMATION, "Added!").showAndWait();
            /*stage.setAlwaysOnTop(true);
            stage.close();*/
            /* }*/
        } catch (RuntimeException exception) {
            /*Stage stage = (Stage) root.getScene().getWindow();
            stage.setAlwaysOnTop(false);
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
            stage.setAlwaysOnTop(false);*/
            exception.printStackTrace();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            ReservationTM reservationTm = tblReservationId.getSelectionModel().getSelectedItem();
            if (reservationTm != null) {
                btnDelete.setDisable(false);
                reservationBO.deleteReservation(reservationTm.getRes_id());
                new Alert(Alert.AlertType.ERROR, "Reservation Deleted : " + reservationTm.getRes_id()).show();
                refreshTable();
                reservationGetAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Select Item First").show();
            }
            btnDelete.setDisable(true);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }

    private void clearAll() {
        reservationId.clear();
        booking.setValue(null);
        studentId.setValue(null);
        AvailableQtyId.clear();
        roomTypeId.clear();
        roomId.setValue(null);
        pricePerRoomId.clear();
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
    void btnPaidOnAction(ActionEvent event) {
        saveReservation("paid");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadStudentID();
        loadRoomTypeId();
        roomGetAll();
        reservationGetAll();
        generateNextId();
        roomSetCellValueFactory();
        reservationSetCellValueFactory();
    }

    private void loadRoomTypeId() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<String> roomBOCodes = roomBO.getCodes();
        for (String reservationId : roomBOCodes) {
            observableList.add(reservationId);
        }
        roomId.setItems(observableList);
    }

    private void loadStudentID() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<String> reservation = studentBO.getCodes();
        for (String reservationId : reservation) {
            observableList.add(reservationId);
        }
        studentId.setItems(observableList);
    }

    private void reservationSetCellValueFactory() {
        colResId.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        colDateId.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatusid.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room_id"));
    }

    private void roomSetCellValueFactory() {
        coltblRoomId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colRoomKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colRoomQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void generateNextId() {
        String nextId = reservationBO.generateReservationNextId();
        reservationId.setText(nextId);
    }

    private void reservationGetAll() {
        ObservableList<ReservationTM> observableList = FXCollections.observableArrayList();
        List<ReservsionDTO> reservationDTOList = reservationBO.getAllReservation();
        for (ReservsionDTO reservationDTO : reservationDTOList) {
            observableList.add(new ReservationTM(
                            reservationDTO.getResId(),
                            reservationDTO.getDate(),
                            reservationDTO.getStatus(),
                            reservationDTO.getStudent().getStudentId(),
                            reservationDTO.getRoom().getRoomTypeId()
                    )
            );
        }
        tblReservationId.setItems(observableList);

    }

    private void roomGetAll() {
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
    void roomIdOnAction(ActionEvent event) {
        try {
            String selectedItem = roomId.getSelectionModel().getSelectedItem();
            if (!(selectedItem == null || selectedItem.equals("") || selectedItem.equals(" "))) {
                RoomDTO room = roomBO.searchRoom(selectedItem);
                pricePerRoomId.setText(String.valueOf(room.getKeyMoney()));
                AvailableQtyId.setText(String.valueOf(room.getQty()));
                roomTypeId.setText(room.getType());

                if (room.getQty() != 0) {
                    btnPaid.setDisable(false);
                    btnBooking.setDisable(false);
                } else {
                    btnPaid.setDisable(true);
                    btnBooking.setDisable(true);
                    throw new RuntimeException("Room not available at the moment!");
                }
            } else {
                pricePerRoomId.setText("0");
                AvailableQtyId.setText("0");
            }
        } catch (RuntimeException exception) {
            exception.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
        }
    }
}
