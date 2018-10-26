package sample;

import javafx.beans.property.SimpleStringProperty;

public class ClothingT {

    private SimpleStringProperty cT = new SimpleStringProperty("");
    private SimpleStringProperty name = new SimpleStringProperty("");;
    private SimpleStringProperty price = new SimpleStringProperty("");;
    private SimpleStringProperty qty = new SimpleStringProperty("");;


    public ClothingT(String cT, String name, String price, String qty){

        setcT(cT);
        setName(name);
        setPrice(price);
        setQty(qty);
    }


    public String getcT() {
        return cT.get();
    }

    public SimpleStringProperty cTProperty() {
        return cT;
    }

    public void setcT(String cT) {
        this.cT.set(cT);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getQty() {
        return qty.get();
    }

    public SimpleStringProperty qtyProperty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty.set(qty);
    }
}
