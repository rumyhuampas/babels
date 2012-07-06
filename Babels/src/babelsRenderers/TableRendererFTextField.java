package babelsRenderers;

import babelsObjects.MovementTypes;
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
        fillColor(table, txtFormated, isSelected);
        txtFormated.setText("" + value);

        txtFormated.setHorizontalAlignment(SwingConstants.CENTER);
        /*
         * if (((Float) table.getValueAt(row, table.getColumnCount() -
         * 3)).compareTo(new Float(0)) == -1) { txtFormated.setBackground(new
         * Color(0xFE899B)); txtFormated.setOpaque(true); if (isSelected) {
         * txtFormated.setBackground(Color.lightGray); } }
         */
        if (table.getValueAt(row, 1).equals(MovementTypes.MT_APER)) {
            txtFormated.setBackground(new Color(0xFFFF00));
            txtFormated.setOpaque(true);
            if (isSelected) {
                txtFormated.setBackground(Color.lightGray);
            }
        }
        if (table.getValueAt(row, 1).equals(MovementTypes.MT_CIERREPARC)) {
            txtFormated.setBackground(new Color(0xFF8000));
             txtFormated.setOpaque(true);
            if (isSelected) {
                txtFormated.setBackground(Color.lightGray);
            }
        }
        if (table.getValueAt(row, 1).equals(MovementTypes.MT_CIERRE)) {
            txtFormated.setBackground(new Color(0xFF4000));
             txtFormated.setOpaque(true);
            if (isSelected) {
                txtFormated.setBackground(Color.lightGray);
            }
        }
        if (table.getValueAt(row, 1).equals(MovementTypes.MT_CANCELATION)) {
            txtFormated.setBackground(new Color(0xFF0040));
             txtFormated.setOpaque(true);
            if (isSelected) {
                txtFormated.setBackground(Color.lightGray);
            }
        }
        if (table.getValueAt(row, 1).equals(MovementTypes.MT_EXTRACCION)) {
            txtFormated.setBackground(new Color(0x2EFE2E));
             txtFormated.setOpaque(true);
            if (isSelected) {
                txtFormated.setBackground(Color.lightGray);
            }
        }
         if (table.getValueAt(row, 1).equals(MovementTypes.MT_DEPOSITO)) {
            txtFormated.setBackground(new Color(0x2E64FE));
             txtFormated.setOpaque(true);
            if (isSelected) {
                txtFormated.setBackground(Color.lightGray);
            }
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
