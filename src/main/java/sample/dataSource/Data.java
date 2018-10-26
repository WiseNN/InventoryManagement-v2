package sample.dataSource;

import java.sql.Array;
import java.util.HashMap;

public class Data {

    /*
    *
    * DOCS
    *   - To store objects in database conform to this protocol.
    *       - ALL keys being stored in this map MUST be of type String
    *       - ALL top level values in this map must be of type HashMap<String, String>()
    *
    * */

    //database for entire app
    public static HashMap<String, HashMap<String, String>> db = new HashMap<>();

    public void readDB()
    {
        db.get("shirts");
        db.get("pants");
        System.out.println("shirts");
        System.out.println("pants");

        db.entrySet().forEach((it) -> {

            System.out.println("---- DB ----");
            System.out.println(it.getKey()+ ": "+ it.getValue()+"\n");
        });
    }
}


