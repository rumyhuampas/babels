package babelsObjects;

import java.sql.*;

public class Client {

    private final String TABLENAME = "clients";
    private final String FIELD_ID = "Id";
    private final String FIELD_NAME = "Name";
    private final String FIELD_PHONE1 = "Phone1";
    private final String FIELD_PHONE2 = "Phone2";
    private final String FIELD_ADRESS = "Adress";
    private final String FIELD_CUIT = "Cuit";
    private final String FIELD_IVATYPE = "IvaType";
    private Connection Conn;
    private int Id;
    public String Name;
    public String Phone1;
    public String Phone2;
    public String Adress;
    public String Cuit;
    public int IdIvaType;
    
      public int getId() {
        return this.Id;
    }
       public Client(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Name = "";
        this.Adress = "";
        this.Cuit = "";
        this.IdIvaType = 0;
        this.Phone1 = "";
        this.Phone2 = "";
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectClient(qry);
        } finally {
            qry.close();
        }
    }

    public boolean Load(String name) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_NAME + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, name);
            return SelectClient(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectClient(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.Name = results.getString(this.FIELD_NAME);
                this.Adress = results.getString(this.FIELD_ADRESS);
                this.Phone1 = results.getString(this.FIELD_PHONE1);
                this.Phone2 = results.getString(this.FIELD_PHONE2);
                this.Cuit = results.getString(this.FIELD_CUIT);
                this.IdIvaType = results.getInt(this.FIELD_IVATYPE);               
                return true;
            } else {
                return false;
            }
        } finally {
            results.close();
        }
    }

    public boolean Save() throws SQLException {
        if (!Exists()) {
            if (this.Id == -1) {
                return InsertClient();
            } else {
                return UpdateClient();
            }
        } else {
            return false;
        }
    }

    private boolean InsertClient() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_NAME + ", " + this.FIELD_PHONE1 + ", "
                + this.FIELD_PHONE2 + ", " + this.FIELD_ADRESS + ", "
                + this.FIELD_CUIT + ", " + this.FIELD_IVATYPE 
                + ") VALUES (?,?,?,?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setString(1, this.Name);
            qry.setString(2, this.Phone1);
            qry.setString(3, this.Phone2);
            qry.setString(4, this.Adress);
            qry.setString(5, this.Cuit);
            qry.setInt(6, this.IdIvaType);
                   
           if (qry.executeUpdate() > 0) {
                ResultSet result = qry.getGeneratedKeys();
                result.next();
                this.Id = result.getInt("GENERATED_KEY");
                return true;
            } else {
                return false;
            }
        } finally {
            qry.close();
        }
    }

    private boolean UpdateClient() throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_NAME + " = ?,"
                + this.FIELD_PHONE1 + " = ?,"
                + this.FIELD_PHONE2 + " = ?,"
                + this.FIELD_ADRESS + " = ?,"
                + this.FIELD_CUIT + " = ?,"
                + this.FIELD_IVATYPE + " = ?"               
                + " WHERE " + this.FIELD_ID + " = ?";   
                PreparedStatement qry = this.Conn.prepareStatement(sql);
            try {
                qry.setString(1, this.Name);
                qry.setString(2, this.Phone1);
                qry.setString(3, this.Phone2);
                qry.setString(4, this.Adress);
                qry.setString(5, this.Cuit);
                qry.setInt(6, this.IdIvaType);                
                qry.setInt(7, this.Id);
                return qry.executeUpdate() > 0;
            } finally {
                qry.close();
            }    
         }

    public boolean Exists() throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_NAME + " = ? AND "
                + this.FIELD_ID + " <> ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Name);
            qry.setInt(2, this.Id);
            ResultSet results = qry.executeQuery();
            try {
                if (results.next()) {
                    return true;
                } else {
                    return false;
                }
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }

    public boolean Delete() throws SQLException {
        String sql = "DELETE FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, this.Id);
            if (qry.executeUpdate() > 0) {
                this.Id = -1;
                return true;
            } else {
                return false;
            }
        } finally {
            qry.close();
        }
    }
}
