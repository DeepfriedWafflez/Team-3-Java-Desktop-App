package app;

import BLL.Administrator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardController {
    /*
     * Purpose: Controller for dashboard/home page for the desktop app.
     * Author: Brent Ward - starting template found at k33ptoo.
     * Module: PROJ-207-OSD
     * Date: May 15, 2019
     * */

    //buttons
    @FXML private Button btnDashboard;
    @FXML private Button btnAgents;
    @FXML private Button btnCustomers;
    @FXML private Button btnPackages;
    @FXML private Button btnProducts;
    @FXML private Button btnSuppliers;
    @FXML private Button btnSettings;
    @FXML private Button btnSignout;


    //Other properties
    @FXML private Label lblUserName;
    @FXML private Label lblClock;


    //handles all button clocks
    @FXML void handleButtonClicks(ActionEvent event) throws IOException {
        //dashboard button
        if(event.getSource() == btnDashboard){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/dashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //agent button
        if(event.getSource() == btnAgents){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/agent.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //customer button
        if(event.getSource() == btnCustomers){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/customer.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //package button
        if(event.getSource() == btnPackages){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/package.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //product button
        if(event.getSource() == btnProducts){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/product.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //supplier button
        if(event.getSource() == btnSuppliers){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/supplier.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //settings button
        if(event.getSource() == btnSettings){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/settings.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //log out
        if(event.getSource() == btnSignout){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
    }

    //Widget Code
    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a   -   dd/MM/YYYY");
            lblClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    //Startup Sequence
    @FXML void initialize() {
        startClock(); //runs the clock
        //builds the welcome label
        Administrator user = LoginController.userLoggedIn();
        lblUserName.setText(user.getLastName() + ", " + user.getFirstName());
        lblUserName.setWrapText(true);
    }
}
