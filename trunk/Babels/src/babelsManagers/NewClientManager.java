package babelsManagers;

import babelsObjects.Client;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import babels.Babels;

public class NewClientManager {

    public boolean CheckFields(JTextField txtName, JTextField txtCuit, JTextField txtAdress) {
        if (!txtName.getText().equals("") && !txtCuit.getText().equals("") && !txtAdress.getText().equals("")) {
            //VALIDAR TODOS LOS CAMPOS!!!!!!!!!
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Debe completar Nombre o Razon Social, CUIT y Direccion o Domicilio Fiscal",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public boolean SaveClient(int IdClient, String Name, String Phone1, String Phone2, String Adress,
             String Cuit, int IvaType) throws SQLException {
        Babels.mysql.Open();
        try {
            Client client = new Client(Babels.mysql.Conn);
            if (IdClient != -1) {
                client.Load(IdClient);
            }
            client.Name = Name;
            client.Phone1 = Phone1;
            client.Phone2 = Phone2;
            client.Adress = Adress;
            client.Cuit = Cuit;
            client.IdIvaType = IvaType;
          
            if (client.Exists() == false) {
                if (client.Save() == true) {
                    JOptionPane.showMessageDialog(null, "Cliente guardado",
                            "Exito", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar el Cliente",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "El Cliente ya existe",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } finally {
            Babels.mysql.Close();
        }
    }

}
