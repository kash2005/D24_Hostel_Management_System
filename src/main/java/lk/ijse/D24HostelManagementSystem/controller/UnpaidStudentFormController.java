package lk.ijse.D24HostelManagementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.D24HostelManagementSystem.bo.BOFactory;
import lk.ijse.D24HostelManagementSystem.bo.custom.StudentBO;
import lk.ijse.D24HostelManagementSystem.dto.StudentDTO;
import lk.ijse.D24HostelManagementSystem.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UnpaidStudentFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<StudentTM> tblUnPaidStudents;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    @FXML
    void BackToDashboardBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/dashboardLoadForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("studentAddress"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("studentContact"));


    }

//    private void refreshUnpaidStudentTable() {
//        try {
//            List<StudentDTO> studentDTOS= studentBO.getUnpaidStudents();
//            ObservableList<StudentTM> studentTm = FXCollections.observableArrayList();
//            for (StudentDTO studentDTO : studentDTOS) {
//                StudentTM studentTM = new StudentTM();
//                studentTM.setStudentId(studentDTO.getStudentId());
//                studentTM.setStudentName(studentDTO.getStudentName());
//                studentDTO.setStudentAddress(studentDTO.getStudentAddress());
//                studentDTO.setStudentContact(studentDTO.getStudentContact());
//                studentTm.add(studentTM);
//            }
//            tblUnPaidStudents.setItems(studentTm);
//        } catch (RuntimeException exception) {
//            new Alert(Alert.AlertType.INFORMATION, exception.getMessage()).show();
//        }
//    }
}
