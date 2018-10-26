package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InventoryMainMenuController{

    private static Map<String, Clothing> clothesList = new HashMap<String, Clothing>();

    @FXML
    public void deleteItem()
    {

    }

    public void viewInventory(Event event){
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

    public void addProduct(Event event){
        try {
            Parent list = FXMLLoader.load(getClass().getResource("/AddProductMenu.fxml"));
            Scene addProductScene = new Scene(list);
            Stage addProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            addProductStage.hide();
            addProductStage.setScene(addProductScene);
            //adminStage.setTitle("Admin Panel");
            addProductStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchProduct(Event event){
        try {
            Parent list = FXMLLoader.load(getClass().getResource("/SearchForm.fxml"));
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

    public static void insertClothing(Clothing c){
        clothesList.put(c.getName(), c);
    }

    public static Map<String, Clothing> getClothingList(){
        return clothesList;
    }

    public static Clothing getClothingList(String nameKey){
        Clothing c = clothesList.get(nameKey);
        return c;
    }



}
