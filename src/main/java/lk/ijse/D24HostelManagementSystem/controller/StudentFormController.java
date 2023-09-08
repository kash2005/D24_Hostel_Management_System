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
import lk.ijse.D24HostelManagementSystem.bo.BOFactory;
import lk.ijse.D24HostelManagementSystem.bo.custom.StudentBO;
import lk.ijse.D24HostelManagementSystem.dto.StudentDTO;
import lk.ijse.D24HostelManagementSystem.tm.StudentTM;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {
    @FXML
    private JFXTextField txtStudentId;

    @FXML
    private JFXTextField txtStudentNameId;

    @FXML
    private JFXTextField txtStudentAddressId;

    @FXML
    private JFXTextField txtStudentContactId;

    @FXML
    private DatePicker dob;

    @FXML
    private RadioButton maleBtnId;

    @FXML
    private RadioButton femaleBtnId;


    @FXML
    private TableView<StudentTM> studentTbl;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;


    @FXML
    private JFXTextField txtSearchId;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    @FXML
    private void deleteBtnOnAction(ActionEvent event) {
//       StudentTM studentTM =studentTbl.getSelectionModel().getSelectedItem();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(txtStudentId.getText());
        studentDTO.setStudentName(txtStudentNameId.getText());
        studentDTO.setStudentAddress(txtStudentAddressId.getText());
        studentDTO.setStudentContact(txtStudentContactId.getText());
        studentDTO.setDate(Date.valueOf(dob.getValue()));
        studentDTO.setGender(maleBtnId.isSelected() ? "Male" : "Female");
//        if (studentTM != null){
            studentBO.deleteStudent(studentDTO);
            new Alert(Alert.AlertType.INFORMATION, "Student Deleted!").show();
            clearAll();
            refreshTable();
            btnSave.setText("Save");
            btnSave.setStyle("-fx-background-color:  #765827; -fx-background-radius: 10;");
//        }else {
//            new Alert(Alert.AlertType.ERROR, "Select Student first!");
//        }

//        btnSave.setDisable(false);
//        btnDelete.setDisable(true);
    }

    @FXML
    void studentTblOnMouseClicked(MouseEvent event) {
        StudentTM selectedItem = studentTbl.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                btnSave.setDisable(false);
                txtStudentId.setText(selectedItem.getStudentId());
                txtStudentNameId.setText(selectedItem.getStudentName());
                txtStudentAddressId.setText(selectedItem.getStudentAddress());
                if (selectedItem.getGender().equals("Male")) {
                    maleBtnId.setSelected(true);
                } else {
                    femaleBtnId.setSelected(true);
                }
                txtStudentContactId.setText(selectedItem.getStudentContact());
                dob.setValue(selectedItem.getDate().toLocalDate());
                btnSave.setText("Update");
                btnSave.setStyle("-fx-background-color:  #379237; -fx-background-radius: 10;");
            }

        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void refreshTable() {
        try {
            generateNextId();
            List<StudentDTO> all = studentBO.getAllStudent();
            ObservableList<StudentTM> studentDtoObservableList = FXCollections.observableArrayList();
            all.forEach(dto -> studentDtoObservableList.add(new StudentTM(dto.getStudentId(), dto.getStudentName(), dto.getStudentAddress(), dto.getStudentContact(), dto.getDate(), dto.getGender())));
            studentTbl.setItems(studentDtoObservableList);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
            studentTbl.getItems().clear();
        }
    }

    private void clearAll() {
        txtStudentId.clear();
        txtStudentNameId.clear();
        txtStudentAddressId.clear();
        txtStudentContactId.clear();
        dob.setValue(null);
        maleBtnId.setSelected(false);
        femaleBtnId.setSelected(false);
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentId(txtStudentId.getText());
            studentDTO.setStudentName(txtStudentNameId.getText());
            studentDTO.setStudentAddress(txtStudentAddressId.getText());
            studentDTO.setStudentContact(txtStudentContactId.getText());
            studentDTO.setDate(Date.valueOf(dob.getValue()));
            studentDTO.setGender(maleBtnId.isSelected() ? "Male" : "Female");
        if (btnSave.getText().equals("Save")) {
            boolean isSaved = studentBO.saveStudent(studentDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student saved!").show();
                getAll();
                clearAll();
                generateNextId();
            } else {
                new Alert(Alert.AlertType.ERROR, "student not saved!").show();
            }
        }else if (btnSave.getText().equals("Update")){
            boolean isUpdated = studentBO.updateStudent(studentDTO);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Student updated!").show();
                getAll();
                clearAll();
                generateNextId();
                btnSave.setText("Save");
                btnSave.setStyle("-fx-background-color:  #765827; -fx-background-radius: 10;");
            }else {
                new Alert(Alert.AlertType.ERROR,"Student not updated!").show();
            }
        }
    }

    @FXML
    void txtStudentAddressOnAction(ActionEvent event) {
        txtStudentContactId.requestFocus();
    }

    @FXML
    void txtStudentContactOnAction(ActionEvent event) {
        dob.requestFocus();
    }

    @FXML
    void txtStudentDOBOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {
        txtStudentNameId.requestFocus();
    }

    @FXML
    void txtStudentNameOnAction(ActionEvent event) {
        txtStudentAddressId.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAll();
        generateNextId();
        setCellValueFactory();
    }

    private void generateNextId() {
        String nextId = studentBO.generateStudentNextId();
        txtStudentId.setText(nextId);
    }

    private void getAll() {
        ObservableList<StudentTM> observableList = FXCollections.observableArrayList();
        List<StudentDTO> customerDTOList = studentBO.getAllStudent();
        for (StudentDTO studentDTO : customerDTOList) {
            observableList.add(new StudentTM(
                            studentDTO.getStudentId(),
                            studentDTO.getStudentName(),
                            studentDTO.getStudentAddress(),
                            studentDTO.getStudentContact(),
                            studentDTO.getDate(),
                            studentDTO.getGender()
                    )
            );
        }
        studentTbl.setItems(observableList);
    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("studentAddress"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("studentContact"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("date"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }


    @FXML
    public void txtSearchOnAction(ActionEvent event) {
        String studentId = txtSearchId.getText();
        StudentDTO studentDTO = studentBO.searchStudent(studentId);
        if (studentDTO != null){
            fillDate(studentDTO);
            btnSave.setText("Update");
            btnSave.setStyle("-fx-background-color: #379237; -fx-background-radius: 10;");
        }else {
            new Alert(Alert.AlertType.WARNING,"Don't have this id").show();
        }
        txtSearchId.setText("");
    }

    private void fillDate(StudentDTO studentDTO) {
        txtStudentId.setText(studentDTO.getStudentId());
        txtStudentNameId.setText(studentDTO.getStudentName());
        txtStudentAddressId.setText(studentDTO.getStudentAddress());
        txtStudentContactId.setText(studentDTO.getStudentContact());
        dob.setValue(studentDTO.getDate().toLocalDate());
        if ("Male".equals(studentDTO.getGender())) {
            maleBtnId.setSelected(true);
            femaleBtnId.setSelected(false);
        } else if ("Female".equals(studentDTO.getGender())) {
            femaleBtnId.setSelected(true);
            maleBtnId.setSelected(false);
        }
    }
}
