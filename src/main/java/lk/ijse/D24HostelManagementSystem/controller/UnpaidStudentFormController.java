package lk.ijse.D24HostelManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UnpaidStudentFormController {
    @FXML
    private AnchorPane root;

    @FXML
    void BackToDashboardBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelManagementSystem/view/dashboardLoadForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }
}
