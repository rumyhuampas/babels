package andro.babels.models;

import andro.babels.controllers.Welcome;
import babelsObjects.Combo;
import java.io.InputStream;
import java.sql.SQLException;

public class Combos extends andro.babels.models.Base{
    public InputStream GetComboImage(Integer id) throws SQLException {
        Combo combo = new Combo(Welcome.mysql.Conn);
        combo.Load(id);
        return combo.ImageStream;
    }
}
