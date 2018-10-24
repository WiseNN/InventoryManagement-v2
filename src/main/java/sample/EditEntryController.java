package sample;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EditEntryController {
    public void confirmEdit(Event event){

        }

    public void cancelEdit(Event event){
        try {
            Parent list = FXMLLoader.load(getClass().getResource("/InventoryList.fxml"));
            Scene invScene = new Scene(list);
            Stage invStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            invStage.hide();
            invStage.setScene(invScene);
            //adminStage.setTitle("Admin Panel");
            invStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
