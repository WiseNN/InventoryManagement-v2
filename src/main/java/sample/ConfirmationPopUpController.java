package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmationPopUpController implements Initializable {

    @FXML
    private Text confirmationText;

    private static String name;

    public static void inputName(String n){
        name = n;
    }

    public void setupConfirmation(){
        Clothing c = InventoryMainMenuController.getClothingList(name);
        confirmationText.setText(c.getQty() + " " + this.name + " at $" + c.getPrice() + " have been added to the inventory");
    }


    public void okPressed(Event event){
        try {
            Parent list = FXMLLoader.load(getClass().getResource("/InventoryMainMenu.fxml"));
            Scene okScene = new Scene(list);
            Stage okStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            okStage.hide();
            okStage.setScene(okScene);
            //adminStage.setTitle("Admin Panel");
            okStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void cancelPressed(Event event){
        try {
            Parent list = FXMLLoader.load(getClass().getResource("/AddProductMenu.fxml"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupConfirmation();
    }
}
