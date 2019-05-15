package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button btnAgent;

    @FXML
    void btnAgentAction(ActionEvent event) throws IOException {
        Parent agentParent = FXMLLoader.load(getClass().getResource("../views/agent.fxml"));
        Scene agentScene = new Scene(agentParent);

        //gets the stage  -- gets the window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(agentScene);
    }

}
