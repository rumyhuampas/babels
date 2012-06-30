package babelsManagers;

import babels.Babels;
import babelsObjects.Sale;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CashRegisterSaleDetailManager {
    private JTable tableDetail;
    private JTextField txtAmount;
    private JTextField txtDateTime;
    private JTextField txtMovementId;
    private JTextField txtMovementType;
    
    public CashRegisterSaleDetailManager(JTable tblSaleDetail, JTextField txtAmount, JTextField txtDateTime, JTextField txtMovementId, JTextField txtMovementType) {
       this.tableDetail = tblSaleDetail;
       this.txtAmount = txtAmount;
       this.txtDateTime = txtDateTime;
       this.txtMovementId = txtMovementId;
       this.txtMovementType = txtMovementType;
    }
    
    
    public void LoadMovement(int prodId) throws SQLException {
        Babels.mysql.Open();
        try {
            Sale sale = new Sale (Babels.mysql.Conn);
            sale.Load(prodId);
            
                    
            
            
        } finally {
            Babels.mysql.Close();
        }
    }
}
