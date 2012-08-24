package babelsObjects;

import java.sql.*;

public class Client {

    public static final String TABLENAME = "Clients";
    public static final String FIELD_ID = "Id";
    public static final String FIELD_NAME = "Name";
    public static final String FIELD_DOCNUM = "DocNum";
    public static final String FIELD_DOCTYPE = "DocType";
    public static final String FIELD_RESP = "Resp";
    public static final String FIELD_ADDRESS = "Address";
    public static final String FIELD_PHONE1 = "Phone1";
    public static final String FIELD_PHONE2 = "Phone2";
    
    private Connection Conn;
    private int Id;
    public String Name;
    public String DocNum;
    public char DocType;
    public char Resp;
    public String Address;
    public String Phone1;
    public String Phone2;

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
        this.DocNum = "";
        this.DocType = ' ';
        this.Resp = ' ';
        this.Address = "";
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
                this.Name = results.getString(Client.FIELD_NAME);
                this.DocNum = results.getString(Client.FIELD_DOCNUM);
                this.DocType = results.getString(Client.FIELD_DOCTYPE).charAt(0);
                this.Resp = results.getString(Client.FIELD_RESP).charAt(0);
                this.Address = results.getString(Client.FIELD_ADDRESS);
                this.Phone1 = results.getString(Client.FIELD_PHONE1);
                this.Phone2 = results.getString(Client.FIELD_PHONE2);
                return true;
            } else {
                return false;
            }
        } finally {
            results.close();
        }
    }

    public boolean Save() throws SQLException {
        if (this.Id == -1) {
            return InsertClient();
        } else {
            return UpdateClient();
        }
    }

    private boolean InsertClient() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_NAME + ", " + this.FIELD_DOCNUM + ", "
                + this.FIELD_DOCTYPE + ", " + this.FIELD_RESP + ", "
                + this.FIELD_ADDRESS + this.FIELD_PHONE1
                + this.FIELD_PHONE2
                + ") VALUES (?,?,?,?,?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setString(1, this.Name);
            qry.setString(2, this.DocNum);
            qry.setString(3, String.valueOf(this.DocType));
            qry.setString(4, String.valueOf(this.Resp));
            qry.setString(5, this.Address);
            qry.setString(6, this.Phone1);
            qry.setString(7, this.Phone2);
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
                + this.FIELD_DOCNUM + " = ?, "
                + this.FIELD_DOCTYPE + " = ?, "
                + this.FIELD_RESP + " = ?, "
                + this.FIELD_ADDRESS + " = ?, "
                + this.FIELD_PHONE1 + " = ?, "
                + this.FIELD_PHONE2 + " = ? "
                + "WHERE " + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Name);
            qry.setString(2, this.DocNum);
            qry.setString(3, String.valueOf(this.DocType));
            qry.setString(4, String.valueOf(this.Resp));
            qry.setString(5, this.Address);
            qry.setString(6, this.Phone1);
            qry.setString(7, this.Phone2);
            qry.setInt(8, this.Id);
            return qry.executeUpdate() > 0;
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
