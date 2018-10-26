package main.java.sample;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchFormController {
    public void search(Event event){
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
    public void cancelSearch(Event event){
        try {
            Parent list = FXMLLoader.load(getClass().getResource("/InventoryList.fxml"));
            Scene cancelScene = new Scene(list);
            Stage cancelStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            cancelStage.hide();
            cancelStage.setScene(cancelScene);
            //adminStage.setTitle("Admin Panel");
            cancelStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
