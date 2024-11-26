package com.example.demo12;


import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private TableView<?> roomTable;
    @FXML
    private TableColumn<?, ?> roomIdColumn;
    @FXML
    private TableColumn<?, ?> roomTypeColumn;
    @FXML
    private TableColumn<?, ?> roomPriceColumn;
    @FXML
    private TableColumn<?, ?> roomAvailabilityColumn;
    @FXML
    private Button reserveButton;

    @FXML
    public void handleReserveRoom() {
        // Add logic for reserving a room
        System.out.println("Reserve button clicked!");
    }
}
