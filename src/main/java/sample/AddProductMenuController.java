package sample;

import couchdb.DB;
import couchdb.DBNames;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.dataSource.DBKeys;
import sample.dataSource.Data;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AddProductMenuController {

    InventoryListController inventoryListController;


    @FXML
    private RadioButton shirtType;

    @FXML
    private RadioButton pantType;

    @FXML
    private TextField productName;

    @FXML
    private TextField productPrice;

    @FXML
    private TextField productQuantity;

    @FXML
    private Button addButton;


    public void addProduct(Event event)
    {

        //get values from fields, check for null values

        //a product
        HashMap<String, String> productOne = new HashMap<>();


//        Data.db.put("Products", );





        if(productName != null && productPrice != null && productQuantity != null) {
            Clothing c;

            if(shirtType.isSelected()){

                //check for decimal in field
                String quantity = null;
                if(productQuantity.getText().contains("."))
                {
                    int endIndex = productQuantity.getText().indexOf(".");
                    quantity = productQuantity.getText().substring(0, endIndex);
                }else{
                    quantity = productQuantity.getText();
                }
                c = new Clothing(ClothingType.SHIRT, productName.getText(), Double.parseDouble(productPrice.getText()), Integer.parseInt(quantity));

                System.out.println(ClothingType.SHIRT + " " + productName.getText() + " "+productPrice.getText() + " " + productQuantity.getText());

                //create one shirt in hashMap
                HashMap<String, Object> oneShirtMap = new HashMap<>();
//                       "name" : "Ralph Lauren",
//                        "quantity" : "5",
//                        "price" : "$30.00"

                oneShirtMap.put(DBKeys.SHIRT_NAME_KEY, c.getName());
                oneShirtMap.put(DBKeys.SHIRT_PRICE_KEY, c.getPrice()+"");
                oneShirtMap.put(DBKeys.SHIRT_QUANTITY_KEY,c.getQty()+"");



                //get productsDB from mainDB instance
                DB db = new DB();
                Map<String, Object> productsDB = db.readDocInDB(DBNames.PRODUCTS_ENTIRE_DB);

                //get shirtsArrayMap from productsDB Map
                ArrayList<HashMap<String, Object>> shirtsAryMap = (ArrayList<HashMap<String, Object>>)productsDB.get(DBNames.SHIRTS_DB);

                //add oneShirtMap to shirtsArrayMap
                shirtsAryMap.add(oneShirtMap);

                //put shirtAryMap back into productsDB Map
                productsDB.put(DBNames.SHIRTS_DB, shirtsAryMap);


                //save updated productsDB Map
                Map<String, Object> updatedDoc = db.updateDocInDB(DBNames.PRODUCTS_ENTIRE_DB, productsDB);

                //print shirts in aryMap
                db.printDocAsJSON(updatedDoc, 3);


















            }else{
                c = new Clothing(ClothingType.PANTS, productName.getText(), Double.parseDouble(productPrice.getText()), Integer.parseInt(productQuantity.getText()));



                //one PANT
                HashMap<String, Object> onePantMap = new HashMap<>();
                onePantMap.put(DBKeys.PANT_NAME_KEY, c.getName());
                onePantMap.put(DBKeys.PANT_PRICE_KEY, c.getPrice()+"");
                onePantMap.put(DBKeys.PANT_QUANTITY_KEY,c.getQty()+"");

                //get productsDB from mainDB instance
                DB db = new DB();
                Map<String, Object> productsDB = db.readDocInDB(DBNames.PRODUCTS_ENTIRE_DB);

                //get shirtsArrayMap from productsDB Map
                ArrayList<HashMap<String, Object>> pantsAryMap = (ArrayList<HashMap<String, Object>>)productsDB.get(DBNames.SHIRTS_DB);

                //add oneShirtMap to shirtsArrayMap
                pantsAryMap.add(onePantMap);

                //put shirtAryMap back into productsDB Map
                productsDB.put(DBNames.PANTS_DB, pantsAryMap);

                //save updated productsDB Map
                Map<String, Object> updatedDoc = db.updateDocInDB(DBNames.PRODUCTS_ENTIRE_DB, productsDB);

                //print pants in aryMap
                db.printDocAsJSON(updatedDoc, 3);

            }
                System.out.println( ClothingType.PANTS + " " + productName.getText() + " "+productPrice.getText() + " " + productQuantity.getText());
            try {

                //display confirmation screen
                InventoryMainMenuController.insertClothing(c);
                ConfirmationPopUpController.inputName(c.getName());
                Parent list = FXMLLoader.load(getClass().getResource("/ConfirmationPopUp.fxml"));
                Scene addProductScene = new Scene(list);
                Stage addProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                addProductStage.hide();
                addProductStage.setScene(addProductScene);
                //adminStage.setTitle("Admin Panel");
                addProductStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            productName.setText("No input");
            productName.setStyle("-fx-text-inner-color: red;");
            productPrice.setText("0");
            productQuantity.setText("0");
        }

        //read local DB
        new Data().readDB();
    }
    public void cancelEntry(Event event){
        try {
            Parent list = FXMLLoader.load(getClass().getResource("/InventoryMainMenu.fxml"));
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

    public TextField getProductName(){
        return productName;
    }
    public TextField getProductPrice() {
        return productPrice;
    }
    public TextField getProductQuantity(){
        return productQuantity;
    }
    public void setProductName(TextField productName){
        this.productName = productName;
    }
    public void setProductPrice(TextField productPrice){
        this.productPrice = productPrice;
    }
    public void setProductQuantity(TextField productQuantity){
        this.productQuantity = productQuantity;
    }



}
