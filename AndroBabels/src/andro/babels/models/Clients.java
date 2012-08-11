package andro.babels.models;

import android.database.SQLException;
import babelsObjects.Client;

public class Clients extends andro.babels.models.Base {
    public Client currentClient;
    
    public boolean Search(String search) throws SQLException, java.sql.SQLException {
        Client client = new Client(andro.babels.controllers.Welcome.mysql.Conn);
        if(client.Load(search) == true){
            currentClient = client;
            return true;
        }
        currentClient = null;
        return false;
    }
}
