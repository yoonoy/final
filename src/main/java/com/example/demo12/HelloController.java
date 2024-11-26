package com.example.demo12;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class HelloController {

    // List to store reservations
    private List<Reservation> reservations = new ArrayList<>();

    // ListView to display reservations in the FXML (we'll add this later in the FXML file)
    @FXML
    private ListView<String> reservationListView;

    // Variable to store the selected room
    private Room selectedRoom;

    @FXML
    private void handleViewRooms() {
        // Example data for demonstration
        Room room1 = new Room(1, 1, "Single", 100.0, true);
        Room room2 = new Room(2, 1, "Double", 150.0, true);

        StringBuilder roomDetails = new StringBuilder("Available Rooms:\n");
        roomDetails.append(room1.toString()).append("\n");
        roomDetails.append(room2.toString()).append("\n");

        showMessage("View Rooms", roomDetails.toString());
    }

    @FXML
    private void handleMakeReservation() {
        // For simplicity, we use hardcoded data. Normally this would navigate to a new screen.
        showMessage("Make Reservation", "Please select a room and fill out your details to make a reservation.");
    }

    @FXML
    private void handleViewReservations() {
        // Show all reservations in the ListView (if any)
        StringBuilder reservationDetails = new StringBuilder("Reservations:\n");

        if (reservations.isEmpty()) {
            reservationDetails.append("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                reservationDetails.append(reservation.toString()).append("\n");
            }
        }

        showMessage("View Reservations", reservationDetails.toString());
    }

    @FXML
    private void handleViewHotels() {
        // Showing hotel information
        Hotel hotel = new Hotel(1, "Alga", "Osh");
        HotelController hotelController = new HotelController();
        hotelController.displayHotelDetails(hotel);
    }

    @FXML
    private void handleSelectRoom() {
        // Example rooms to select from
        Room room1 = new Room(1, 1, "Single", 100.0, true);
        Room room2 = new Room(2, 1, "Double", 150.0, true);

        // Simulate room selection (could be a dropdown or a list of rooms)
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Room Selection");
        dialog.setHeaderText("Select Room");
        dialog.setContentText("Enter room ID to select (1 for Single, 2 for Double):");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(roomId -> {
            int selectedId = Integer.parseInt(roomId);

            // Find and store the selected room based on the ID
            if (selectedId == 1) {
                selectedRoom = room1;
            } else if (selectedId == 2) {
                selectedRoom = room2;
            }

            // Show the room selected
            if (selectedRoom != null) {
                showMessage("Room Selected", "You have selected Room ID: " + selectedRoom.getRoomId());
            } else {
                showMessage("Invalid Selection", "Invalid room ID. Please select either 1 or 2.");
            }
        });
    }

    @FXML
    private void handleConfirmReservation() {
        if (selectedRoom != null) {
            // Prompt for the user's name
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Confirm Reservation");
            dialog.setHeaderText("Confirm Reservation");
            dialog.setContentText("Enter your name:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> {
                // Create a reservation based on the selected room
                Reservation reservation = new Reservation(1, selectedRoom.getRoomId(), name, new Date(), new Date());
                reservations.add(reservation);  // Add reservation to the list

                showMessage("Reservation Confirmed", "Reservation confirmed for: " + name);
            });
        } else {
            showMessage("No Room Selected", "Please select a room before confirming the reservation.");
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    private void showMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
