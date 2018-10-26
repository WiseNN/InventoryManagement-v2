package sample;
import com.sun.javafx.scene.control.SelectedCellsMap;
import couchdb.DB;
import couchdb.DBNames;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class InventoryListController implements Initializable {


    @FXML
    private TableView<ClothingT> myTableView;

    @FXML
    private TableColumn<ClothingT, String> itemNumCol;

    @FXML
    private TableColumn<ClothingT, String> prodNumCol;

    @FXML
    private TableColumn<ClothingT, String> prodNameCol;

    @FXML
    private TableColumn<ClothingT, String> prodPriceCol;

    @FXML
    private TableColumn<ClothingT, String> prodQtyCol;

    @FXML
    private TableColumn<ClothingT, String> prodTypeCol;

    int rowIndex = -1;



    AddProductMenuController addProductMenuController;

    Clothing c;





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        itemNumCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClothingT, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClothingT, String> param) {
                //increment row index on each call
                rowIndex++;
                return new SimpleStringProperty(rowIndex+"");
            }
        });
        prodNumCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClothingT, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClothingT, String> param) {
                return new SimpleStringProperty(skuGenerator());
            }
        });
        prodNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClothingT, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClothingT, String> param) {
                return param.getValue().nameProperty();
            }
        });
        prodPriceCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClothingT, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClothingT, String> param) {
                return param.getValue().priceProperty();
            }
        });
        prodQtyCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClothingT, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClothingT, String> param) {
                return param.getValue().qtyProperty();
            }
        });
        prodTypeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClothingT, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClothingT, String> param) {
                return param.getValue().cTProperty();
            }
        });


        myTableView.getItems().setAll(parseClothingList());
    }


    //generate random product numbers
    private String skuGenerator()
    {
        String skuBuilder = "";


        for(int i=0;i<7;i++)
        {
            int randomNum = getRandomNumberInRange(65, 90);
            char letter = (char)randomNum;
            skuBuilder+= Character.toString(letter);

        }

        return  skuBuilder;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private ObservableList<ClothingT> parseClothingList()
    {

        ArrayList<ClothingT> clothingList = new ArrayList<>();


        Map<String, Object> productsMap = new DB().readDocInDB(DBNames.PRODUCTS_ENTIRE_DB);

        //get shirts map
        ArrayList<Map<String, String>> shirtsAryMap = (ArrayList<Map<String, String>>) productsMap.get(DBNames.SHIRTS_DB);

        //get pants map
        ArrayList<Map<String, String>> pantsAryMap = (ArrayList<Map<String, String>>) productsMap.get(DBNames.PANTS_DB);


        if(shirtsAryMap.size() != 0)
        {
            System.out.println("printing shirts");
            shirtsAryMap.forEach(oneShirtMap -> {

                String clothingName = oneShirtMap.get(DBNames.SHIRT_NAME_KEY);
                String clothingPrice = oneShirtMap.get(DBNames.SHIRT_PRICE_KEY);
                String clothingQty = oneShirtMap.get(DBNames.SHIRT_QUANTITY_KEY);

                ClothingT clothingShirtItem = new ClothingT(ClothingType.SHIRT.toString(), clothingName,clothingPrice,clothingQty);
                clothingList.add(clothingShirtItem);

            });
        }

        if(pantsAryMap.size() != 0)
        {

            System.out.println("printing pants");
            pantsAryMap.forEach(onePantMap -> {

                String clothingName = onePantMap.get(DBNames.SHIRT_NAME_KEY);
                String clothingPrice = onePantMap.get(DBNames.SHIRT_PRICE_KEY);
                String clothingQty = onePantMap.get(DBNames.SHIRT_QUANTITY_KEY);

                ClothingT clothingPantItem = new ClothingT(ClothingType.PANTS.toString(), clothingName, clothingPrice, clothingQty);

                clothingList.add(clothingPantItem);

            });
        }

        return FXCollections.observableArrayList(clothingList);
    }

    public void backToMainMenu(Event event){
        try {
            Parent list = FXMLLoader.load(getClass().getResource("/InventoryMainMenu.fxml"));
            Scene mainScene = new Scene(list);
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainStage.hide();
            mainStage.setScene(mainScene);
            //adminStage.setTitle("Admin Panel");
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void editEntry(Event event){
        try {
            Parent list = FXMLLoader.load(getClass().getResource("/EditEntry.fxml"));
            Scene editScene = new Scene(list);
            Stage editStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            editStage.hide();
            editStage.setScene(editScene);
            //adminStage.setTitle("Admin Panel");
            editStage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public void deleteItem(Event event) {

        ClothingT clothingT_selected = myTableView.getSelectionModel().getSelectedItem();

        //get productsDB from mainDB instance
        DB db = new DB();
        Map<String, Object> productsDB = db.readDocInDB(DBNames.PRODUCTS_ENTIRE_DB);

        if(clothingT_selected.cTProperty().get() == ClothingType.SHIRT.toString())
        {

            //get shirtsArrayMap from productsDB Map
            ArrayList<HashMap<String, Object>> shirtsAryMap = (ArrayList<HashMap<String, Object>>)productsDB.get(DBNames.SHIRTS_DB);

            int index = 0;
            //search and remove product from list by productName
            for (Map<String, Object> oneShirtMap : shirtsAryMap)
            {

                String selectedProductName = clothingT_selected.getName();
                String comparatorProductName = (String) oneShirtMap.get(DBNames.SHIRT_NAME_KEY);

                if (comparatorProductName.equals(selectedProductName)) {
                    shirtsAryMap.remove(oneShirtMap);
                    myTableView.getItems().remove(index);

                    break;
                }
                index++;
            }
        }
        else
        {
            //get pantsArrayMap from productsDB Map
            ArrayList<HashMap<String, Object>> pantsAryMap = (ArrayList<HashMap<String, Object>>)productsDB.get(DBNames.PANTS_DB);

            int index = 0;
            //search and remove product from list by productName
            for (HashMap<String, Object> onePantMap : pantsAryMap)
            {
                String selectedProductName = clothingT_selected.getName();
                String comparatorProductName = (String) onePantMap.get(DBNames.PANT_NAME_KEY);

                if (comparatorProductName.equals(selectedProductName)) {
                    pantsAryMap.remove(onePantMap);
                    myTableView.getItems().remove(index);
                    break;
                }
            }
        }



    }

    private void searchAndRemoveHelper(ClothingT clothingT_selected, ArrayList<HashMap<String, Object>> aryMap)
    {

    }
}




