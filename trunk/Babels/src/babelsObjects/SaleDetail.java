package babelsObjects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class SaleDetail {

    private static final String TABLENAME = "SaleDetails";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_IDSALE = "IdSale";
    private static final String FIELD_AMOUNT = "Amount";
    private static final String FIELD_IDPRODUCT = "IdProduct";
    private static final String FIELD_SUBTOTAL = "SubTotal";
    private Connection Conn;
    private int Id;
    private int IdSale;
    public int Amount;
    public Product Product;
    public float SubTotal;
    
    public int getId(){
        return this.Id;
    }
    
    public Integer getIdSale(){
        return this.IdSale;
    }

    public SaleDetail(Connection conn) throws SQLException {
        this.Conn = conn;
        Clear();
    }
    
    public void Clear(){
        this.Id = -1;
        this.IdSale = -1;
        this.Amount = 0;;
        this.Product = null;
        this.SubTotal = Float.parseFloat("0");
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            ResultSet results = qry.executeQuery();
            try {
                if (results.next()) {
                    this.Id = results.getInt(this.FIELD_ID);
                    this.IdSale = results.getInt(this.FIELD_IDSALE);
                    this.Amount = results.getInt(this.FIELD_AMOUNT);
                    this.Product = new Product(this.Conn);
                    this.Product.Load(results.getInt(this.FIELD_IDPRODUCT));
                    this.SubTotal = results.getFloat(this.FIELD_SUBTOTAL);

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

    public boolean Save() throws SQLException {
        if (this.Id == -1) {
            return InsertSaleDetail();
        } else {
            return UpdateSaleDetail();
        }
    }

    private boolean InsertSaleDetail() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_IDSALE + "," + this.FIELD_AMOUNT + ","
                + this.FIELD_IDPRODUCT + "," + this.FIELD_SUBTOTAL
                + ") VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, this.IdSale);
            qry.setInt(2, this.Amount);
            qry.setInt(3, this.Product.getId());
            qry.setFloat(4, this.SubTotal);
            return qry.executeUpdate() > 0;
        } finally {
            qry.close();
        }
    }

    private boolean UpdateSaleDetail() throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_IDSALE + " = ?," + this.FIELD_AMOUNT + " = ?,"
                + this.FIELD_IDPRODUCT + " = ?," + this.FIELD_SUBTOTAL + " = ?"
                + "WHERE " + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, this.IdSale);
            qry.setInt(2, this.Amount);
            qry.setInt(3, this.Product.getId());
            qry.setFloat(4, this.SubTotal);
            qry.setInt(5, this.Id);
            return qry.executeUpdate() > 0;
        } finally {
            qry.close();
        }
    }

    static ArrayList GetSaleDetails(Connection conn, Sale sale) throws SQLException {
        String sql = "SELECT * FROM " + TABLENAME + " WHERE "
                + FIELD_IDSALE + " = ?";
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            qry.setInt(1, sale.getId());
            ArrayList result = new ArrayList();
            ResultSet results = qry.executeQuery(sql);
            try {
                while (results.next()) {
                    SaleDetail detail = new SaleDetail(conn);
                    detail.Load(results.getInt(FIELD_ID));
                    result.add(detail);
                }
                return result;
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }
    
    static String GetInsertSql(){
        return "INSERT INTO " + TABLENAME + " ("
                + FIELD_IDSALE + "," + FIELD_AMOUNT + ","
                + FIELD_IDPRODUCT + "," + FIELD_SUBTOTAL
                + ") VALUES (?,?,?,?,?,?,?,?,?)";
    }
    
    static String GetUpdateSql(){
        return "UPDATE " + TABLENAME + " SET "
                + FIELD_IDSALE + " = ?," + FIELD_AMOUNT + " = ?,"
                + FIELD_IDPRODUCT + " = ?," + FIELD_SUBTOTAL + " = ?"
                + "WHERE " + FIELD_ID + " = ?";
    }
}
