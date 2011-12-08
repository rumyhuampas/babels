package babelsObjects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class User {
    
    private final String TABLENAME = "Users";
    private final String FIELD_ID = "Id";
    private final String FIELD_NAME = "Name";
    private final String FIELD_PASS = "Pass";
    private final String FIELD_ISADMIN = "IsAdmin";
    private final String FIELD_ACTIVE = "Active";
    private Connection Conn;
    private Integer Id;
    public String Name;
    public String Pass;
    public Boolean IsAdmin;
    public Boolean Active;
    
    public User(Connection conn) throws SQLException {
        this.Conn = conn;
        Clear();
    }
    
    public final void Clear() {
        this.Id = -1;
        this.Name = "";
        this.Pass = "";
        this.IsAdmin = false;
        this.Active = true;
    }
    
    public Boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectUser(qry);
        } finally {
            qry.close();
        }
    }
    
    public Boolean Load(String name) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_NAME + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, name);
            return SelectUser(qry);
        } finally {
            qry.close();
        }
    }
    
    private Boolean SelectUser(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.Name = results.getString(this.FIELD_NAME);
                this.Pass = results.getString(this.FIELD_PASS);
                this.IsAdmin = results.getInt(this.FIELD_ISADMIN) == 1;
                this.Active = results.getInt(this.FIELD_ACTIVE) == 1;
                return true;
            } else {
                return false;
            }
        } finally {
            results.close();
        }
    }
    
    public Boolean Save() throws SQLException {
        if (this.Id == -1) {
            if (!Exists()) {
                return InsertUser();
            } else {
                return false;
            }
        } else {
            return UpdateUser();
        }
    }
    
    private Boolean InsertUser() throws SQLException {
        Integer isAdmin = this.IsAdmin == false ? 0 : 1;
        Integer isActive = this.Active == false ? 0 : 1;
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_NAME + ", " + this.FIELD_PASS + ", "
                + this.FIELD_ISADMIN + ", " + this.FIELD_ACTIVE
                + ") VALUES (?,?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Name);
            qry.setString(2, this.Pass);
            qry.setInt(3, isAdmin);
            qry.setInt(4, isActive);
            return qry.execute();
        } finally {
            qry.close();
        }
    }
    
    private Boolean UpdateUser() throws SQLException {
        Integer isAdmin = this.IsAdmin == false ? 0 : 1;
        Integer isActive = this.Active == false ? 0 : 1;
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_NAME + " = ?,"
                + this.FIELD_PASS + " = ?,"
                + this.FIELD_ISADMIN + " = ?,"
                + this.FIELD_ACTIVE + " = ? "
                + "WHERE " + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Name);
            qry.setString(2, this.Pass);
            qry.setInt(3, isAdmin);
            qry.setInt(4, isActive);
            qry.setInt(5, this.Id);
            return qry.execute();
        } finally {
            qry.close();
        }
    }
    
    public Boolean Exists() throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_NAME + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Name);
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
    
    public Boolean Delete(Integer id) throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_ACTIVE + " = ? "
                + "WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, 0);
            qry.setInt(2, id);
            return qry.execute();
        } finally {
            qry.close();
            this.Active = false;
        }
    }
    
    public Boolean Delete(String name) throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_ACTIVE + " = ? "
                + "WHERE "
                + this.FIELD_NAME + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, 0);
            qry.setString(2, name);
            return qry.execute();
        } finally {
            qry.close();
            this.Active = false;
        }
    }
}
