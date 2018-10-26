package sample;

import couchdb.DB;
import couchdb.DBNames;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.dataSource.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception{



        instantiateDB();

        Parent root = FXMLLoader.load(getClass().getResource("/InventoryMainMenu.fxml"));

        //primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 670));
        primaryStage.show();


    }

    private void instantiateDB()
    {

        //create productsDB if does not exist (err if it does)
        HashMap<String, Object> productsDB = new HashMap<>();


        //create an arrayList full of hashMaps (for shirtsList)
        ArrayList<HashMap<String, String>> shirtsAryMap = new ArrayList<>();
        productsDB.put(DBNames.SHIRTS_DB, shirtsAryMap);

        //create an arrayList full of hashMaps (for pantsList)
        ArrayList<HashMap<String, String>> pantsAryMap = new ArrayList<>();
        productsDB.put(DBNames.PANTS_DB, pantsAryMap);

        new DB().createDoc(DBNames.PRODUCTS_ENTIRE_DB, productsDB);
    }

    public static void main(String[] args)
    {

        launch(args);
        Data data = new Data();
        data.readDB();


    }
}
