package sample;


public class Clothing
{

    private ClothingType cT;
    private String name;
    private double price;
    private int qty;


    public Clothing(ClothingType cT, String name, double price, int qty){
        this.cT = cT;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public ClothingType getcT() {
        return cT;
    }

    public void setcT(ClothingType cT) {
        this.cT = cT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
