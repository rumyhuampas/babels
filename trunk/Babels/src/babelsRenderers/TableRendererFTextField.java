package babelsRenderers;

import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class TableRendererFTextField implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JFormattedTextField txtFormated = new JFormattedTextField();
        txtFormated.setBorder(BorderFactory.createEmptyBorder());
        fillColor(table,txtFormated,isSelected);
        txtFormated.setText(""+ value);

        txtFormated.setHorizontalAlignment(SwingConstants.CENTER);
        if(((Float)table.getValueAt(row, table.getColumnCount()-2)).compareTo(new Float(0))==-1) {
            txtFormated.setBackground(new Color(0xFE899B));
            txtFormated.setOpaque(true);
            
        }

        return (txtFormated);
    }

    public void fillColor(JTable t, JFormattedTextField JFTextField, boolean isSelected) {
        if (isSelected) {
            JFTextField.setBackground(t.getSelectionBackground());
            JFTextField.setForeground(t.getSelectionForeground());
        } else {
            JFTextField.setBackground(t.getBackground());
            JFTextField.setForeground(t.getForeground());

        }

    }

}
