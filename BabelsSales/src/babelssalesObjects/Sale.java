package babelssalesObjects;

import babelssalesObjects.Client;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Statement;

public class Sale {

    private final String TABLENAME = "Sales";
    private final String FIELD_ID = "Id";
    private final String FIELD_CODE = "Code";
    private final String FIELD_DATE = "Date";
    private final String FIELD_IDCLIENT = "IdClient";
    private final String FIELD_TOTAL = "Total";
    private Connection Conn;
    private int Id;
    private String Code;
    private Date Date;
    public Client Client;
    public ArrayList SaleDtl;
    public float Total;

    public int getId() {
        return this.Id;
    }

    public Sale(Connection conn) throws SQLException {
        this.Conn = conn;
        this.SaleDtl = new ArrayList();
        Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Code = "";
        this.Date = null;
        this.Client = null;
        this.SaleDtl.clear();
        this.Total = Float.parseFloat("0");
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectSale(qry);
        } finally {
            qry.close();
        }
    }

    public boolean Load(String code) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_CODE + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, code);
            return SelectSale(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectSale(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.Code = results.getString(this.FIELD_CODE);
                this.Date = results.getDate(this.FIELD_DATE);
                this.Client = new Client(this.Conn);
                this.Client.Load(results.getInt(this.FIELD_IDCLIENT));
                this.Total = results.getFloat(this.FIELD_TOTAL);
                this.SaleDtl = SaleDetail.GetSaleDetails(this.Conn, this);

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
                if (InsertSale() == true) {
                    if (SaveSaleDetails() == true) {
                        return true;
                    } else {
                        Delete();
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (SaveSaleDetails() == true) {
                    return UpdateSale();
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    private boolean SaveSaleDetails() throws SQLException {
        boolean result = false;
        this.Conn.setAutoCommit(false);
        try {
            PreparedStatement qryIns = null;
            PreparedStatement qryUpd = null;
            for (int i = 0; i > this.SaleDtl.size(); i++) {
                SaleDetail sd = ((SaleDetail) this.SaleDtl.get(i));
                if (sd.getId() == -1) {
                    qryIns = this.Conn.prepareStatement(SaleDetail.GetInsertSql());
                    qryIns.setInt(1, sd.getIdSale());
                    qryIns.setInt(2, sd.Amount);
                    qryIns.setInt(3, sd.Product.getId());
                    qryIns.setFloat(4, sd.SubTotal);

                    qryIns.addBatch();
                } else {
                    qryUpd = this.Conn.prepareStatement(SaleDetail.GetUpdateSql());
                    qryUpd.setInt(1, sd.getIdSale());
                    qryUpd.setInt(2, sd.Amount);
                    qryUpd.setInt(3, sd.Product.getId());
                    qryUpd.setFloat(4, sd.SubTotal);
                    qryUpd.setInt(5, sd.getId());

                    qryUpd.addBatch();
                }
            }
            qryIns.executeBatch();
            qryUpd.executeBatch();
            this.Conn.commit();

            result = true;
        } catch (Exception ex) {
            this.Conn.rollback();
            result = false;
        } finally {
            this.Conn.setAutoCommit(true);
        }
        return result;
    }

    private boolean InsertSale() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_CODE + "," + this.FIELD_DATE + ","
                + this.FIELD_IDCLIENT + "," + this.FIELD_TOTAL
                + ") VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setString(1, this.Code);
            qry.setDate(2, this.Date);
            qry.setInt(3, this.Client.getId());
            qry.setFloat(4, this.Total);
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

    private boolean UpdateSale() throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_CODE + " = ?," + this.FIELD_DATE + " = ?,"
                + this.FIELD_IDCLIENT + " = ?," + this.FIELD_TOTAL + " = ? "
                + "WHERE " + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Code);
            qry.setDate(2, this.Date);
            qry.setInt(3, this.Client.getId());
            qry.setFloat(4, this.Total);
            qry.setInt(5, this.Id);
            return qry.executeUpdate() > 0;
        } finally {
            qry.close();
        }
    }

    public boolean Exists() throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_CODE + " = ? AND "
                + this.FIELD_ID + " <> ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Code);
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
        DeleteSaleDetails();
        if (DeleteSale() == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean DeleteSale() throws SQLException {
        String sql = "DELETE FROM " + this.TABLENAME + " "
                + "WHERE "
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

    private boolean DeleteSaleDetails() throws SQLException {
        PreparedStatement qry = this.Conn.prepareStatement(SaleDetail.GetDeleteFromSaleSql());
        try {
            qry.setInt(1, this.Id);
            if (qry.executeUpdate() > 0) {
                for (int i = 0; i < this.SaleDtl.size(); i++) {
                    SaleDetail sd = ((SaleDetail) this.SaleDtl.get(i));
                    sd.ResetId();
                }
                return true;
            } else {
                return false;
            }
        } finally {
            qry.close();
        }
    }
}
