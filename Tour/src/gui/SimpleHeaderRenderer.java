package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
 
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
 
public class SimpleHeaderRenderer extends JLabel implements TableCellRenderer {
 
    public SimpleHeaderRenderer() {
    	setFont(new Font(MainScreen.FONT_TEXT, Font.BOLD, 14));
        setOpaque(true);
        setForeground(Color.WHITE);
        setBackground(MainScreen.COLOR_MAIN);
    }
     
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }
}
