package BLL;

import DLL.DBConnect;
import javafx.scene.control.Alert;

import java.security.AlgorithmConstraints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdministratorDB {
    /*
     * Purpose: Handles the communications to the database for logging in
     * Author: Brent Ward
     * Module: PROJ-207-OSD
     * Date May 24, 2019
     * */

    //checks the admin is in the database
    public static Administrator checkAdmin(String username, String password){
        Administrator admin = null;

        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "select adminFirstName, adminLastName, adminMode, img_dir from administrators where " +
                    "adminUsername = ? and adminPassword = ?";

            //makes a sql statement
            PreparedStatement stmt = connect.prepareStatement(selectQuery);
            stmt.setString(1, username);
            stmt.setString(2, password);

            //assigns & executes statement
            ResultSet rs = stmt.executeQuery();

            //grabs admin
            if(rs.next()){
                String imgTrimed = rs.getString("img_dir").replace("file:", "");
                admin = new Administrator(rs.getString("adminFirstName"), rs.getString("adminLastName"),
                        rs.getString("adminMode"), imgTrimed);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Username or Password.");
                alert.show();
            }

            connect.close();

        }catch(Exception e) { e.printStackTrace(); }

        return admin;
    }

    //updates the admins password
    public static void changePassword(Administrator admin, String newPassword){
        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "update Administrators set adminPassword=? where adminFirstName=? and adminLastName=?";

            //makes a sql statement
            PreparedStatement stmt = connect.prepareStatement(selectQuery);
            stmt.setString(1, newPassword);
            stmt.setString(2, admin.getFirstName());
            stmt.setString(3, admin.getLastName());

            //checks if the data was inserted
            int numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Password failed to update. Contact Tech Support.");
                alert.showAndWait();
            }
            connect.close();

        }catch(Exception e) { e.printStackTrace(); }
    }
    //updates the admins password
    public static void changeColorMode(Administrator admin, String colorMode){
        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "update Administrators set adminMode=? where adminFirstName=? and adminLastName=?";

            //makes a sql statement
            PreparedStatement stmt = connect.prepareStatement(selectQuery);
            stmt.setString(1, colorMode);
            stmt.setString(2, admin.getFirstName());
            stmt.setString(3, admin.getLastName());

            //checks if the data was inserted
            int numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Color mode failed to update. Contact Tech Support.");
                alert.showAndWait();
            }
            connect.close();

        }catch(Exception e) { e.printStackTrace(); }
    }

    //updates the admins profile picture
    public static void changePicture(Administrator admin, String img_dir){
        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "update Administrators set img_dir=? where adminFirstName=? and adminLastName=?";

            //makes a sql statement
            PreparedStatement stmt = connect.prepareStatement(selectQuery);
            stmt.setString(1, img_dir);
            stmt.setString(2, admin.getFirstName());
            stmt.setString(3, admin.getLastName());

            //checks if the data was inserted
            int numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Image failed to update. Contact Tech Support.");
                alert.showAndWait();
            }
            connect.close();

        }catch(Exception e) { e.printStackTrace(); }
    }
}
