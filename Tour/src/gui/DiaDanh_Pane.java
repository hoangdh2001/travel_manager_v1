package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import connect.ConnectDB;
import dao.DiaDanh_DAO;
import entity.DiaDanh;

public class DiaDanh_Pane extends JPanel implements ActionListener {
	private ArrayList<DiaDanh> dsDiaDanh;
	private JButton buttonDauTrang;
	private JButton buttonTruoc;
	private JButton buttonKeTiep;
	private JLabel soTrang;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton buttonCuoiTrang;
	private JTextField timKiemTextField;
	private JComboBox<String> thuocTinhComboBox;
	private JButton lamMoiButton;
	private JButton themDiaDanhButton;
	private JButton xoaButton;
	private JButton suaButton;
	private JPanel box2;
	private JScrollPane spTableDiaDanh;
	
	/**
	 * @return the tableModel
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @return the timKiemTextField
	 */
	public JTextField getTimKiemTextField() {
		return timKiemTextField;
	}

	/**
	 * @return the thuocTinhComboBox
	 */
	public JComboBox<String> getThuocTinhComboBox() {
		return thuocTinhComboBox;
	}

	/**
	 * @return the themDiaDanhButton
	 */
	public JButton getThemDiaDanhButton() {
		return themDiaDanhButton;
	}
	/**
	 * @return the buttonDauTrang
	 */
	public JButton getButtonDauTrang() {
		return buttonDauTrang;
	}

	/**
	 * @return the buttonTruoc
	 */
	public JButton getButtonTruoc() {
		return buttonTruoc;
	}

	/**
	 * @return the buttonKeTiep
	 */
	public JButton getButtonKeTiep() {
		return buttonKeTiep;
	}

	/**
	 * @return the soTrang
	 */
	public JLabel getSoTrang() {
		return soTrang;
	}

	/**
	 * @return the buttonCuoiTrang
	 */
	public JButton getButtonCuoiTrang() {
		return buttonCuoiTrang;
	}
	
	/**
	 * @return the spTableDiaDanh
	 */
	public JScrollPane getSpTableDiaDanh() {
		return spTableDiaDanh;
	}

	/**
	 * @param spTableDiaDanh the spTableDiaDanh to set
	 */
	public void setSpTableDiaDanh(JScrollPane spTableDiaDanh) {
		this.spTableDiaDanh = spTableDiaDanh;
	}

	public DiaDanh_Pane() {
		setBackground(MainScreen.BACKGROUND_COLOR);
		setLayout(null);
		buidDiaDanh_Pane();
	}
	private void buidDiaDanh_Pane() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		JPanel box1 = new JPanel();
		box1.setBackground(MainScreen.SUBPANE_COLOR);
		box1.setBounds(30, 0, 1180, 100);
		box1.setLayout(null);
		box1.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(142,170,215)));
		add(box1);
		
		timKiemTextField = new JTextField() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (getText().length() > 0) {
					return;
				}
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setColor(Color.GRAY);
				g2d.setFont(new Font("Segoe ui", Font.ITALIC, 16));
				g.drawString("Tìm kiếm...", getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
			}
		};
		timKiemTextField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		timKiemTextField.setBounds(10, 10, 250, 30);
		timKiemTextField.setToolTipText("Nhập mã hoặc tên địa danh để tìm kiếm");
		timKiemTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableModel.setRowCount(0);
				soTrang.setText("1");
				themVaoBangDiaDanh();
			}
		});
		box1.add(timKiemTextField);
		
		DefaultComboBoxModel<String> thuocTinhBoxModel = new DefaultComboBoxModel<String>();
		thuocTinhComboBox = new JComboBox<String>(thuocTinhBoxModel);
		thuocTinhComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		thuocTinhComboBox.setBounds(280, 10, 200, 30);
		thuocTinhComboBox.addItem("<Tỉnh/TP>");
		thuocTinhBoxModel.addAll(DiaDanh_DAO.getAllTinh());
		box1.add(thuocTinhComboBox);
		
		lamMoiButton = new JButton("Làm mới");
		lamMoiButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/refresh_15px.png")));
		lamMoiButton.setBounds(10, 60, 100, 30);
		lamMoiButton.setBackground(new Color(51,122,183));
		lamMoiButton.setForeground(Color.WHITE);
		lamMoiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(lamMoiButton);
		
		themDiaDanhButton = new JButton("Thêm");
		themDiaDanhButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/add_15px.png")));
		themDiaDanhButton.setBackground(new Color(63,201,213));
		themDiaDanhButton.setBounds(120, 60, 100, 30);
		themDiaDanhButton.setForeground(Color.WHITE);
		themDiaDanhButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(themDiaDanhButton);
		
		xoaButton = new JButton("Xóa");
		xoaButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/delete_trash_15px.png")));
		xoaButton.setBounds(240, 60, 100, 30);
		xoaButton.setForeground(Color.WHITE);
		xoaButton.setBackground(new Color(51,122,183));
		xoaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(xoaButton);
		
		suaButton = new JButton("Sửa");
		suaButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/wrench_15px.png")));
		suaButton.setBounds(360, 60, 100, 30);
		suaButton.setForeground(Color.WHITE);
		suaButton.setBackground(new Color(63,201,213));
		suaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(suaButton);
		
		box2 = new JPanel();
		box2.setLayout(null);
		box2.setBackground(Color.WHITE);
		box2.setBounds(30, 130, 1180, 550);
		add(box2);
		
		JLabel titleTable = new JLabel("Danh sách địa danh");
		titleTable.setForeground(new Color(231,31,18));
		titleTable.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 20));
		titleTable.setIcon(new ImageIcon(getClass().getResource("/gui/icon/list_30px.png")));
		titleTable.setBounds(10, 0, 300, 40);
		box2.add(titleTable);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(box2.getWidth() - 1160, 40, 1140, 10);
		box2.add(separator);
		
		
		String[] columnTitle = {"ID", "Tên địa danh", "Tỉnh", "Hình ảnh"};
		
		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer() {
			JLabel lbl = new JLabel();
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				lbl.setOpaque(true);
				lbl.setHorizontalTextPosition(JLabel.CENTER);
				if(row % 2 == 0) {
					lbl.setBackground(new Color(255, 255, 255));
					lbl.setForeground(Color.BLACK);
				}
				else {
					lbl.setBackground(new Color(242, 242, 242));
					lbl.setForeground(Color.BLACK);
				}
				if(isSelected)  {
					lbl.setBackground(new Color(57, 105, 138));
					lbl.setForeground(Color.WHITE);
				}
				
				
				lbl.setText("Hình ảnh");
//				lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lbl.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
				Font font = lbl.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				lbl.setFont(font.deriveFont(attributes));
				return lbl;
			}
		};
		
		tableModel = new DefaultTableModel(columnTitle, 0);
		table = new JTable(tableModel) {
			@Override
			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			}
		};
		table.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
		table.getTableHeader().setBackground(MainScreen.COLOR_MAIN);
		table.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		table.getTableHeader().setPreferredSize(new Dimension(1220, 30));
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setFillsViewportHeight(true);
		table.setGridColor(MainScreen.BACKGROUND_COLOR);
		table.setShowGrid(true);
		table.setRowHeight(30);
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		table.getColumnModel().getColumn(3).setCellRenderer(defaultTableCellRenderer);
		table.setToolTipText("Click 2 lần để xem ảnh");
		spTableDiaDanh = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		spTableDiaDanh.setBounds(box2.getWidth() - 1160, box2.getHeight() - 490, box2.getWidth() - 40, box2.getHeight() - 110);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					JDialog xemAnhDialog = new JDialog();
					xemAnhDialog.setSize(800, 500);
					xemAnhDialog.setModal(true);
					xemAnhDialog.setLocationRelativeTo(null);
					JLabel anh = new JLabel();
					String maDiaDanh = table.getValueAt(table.getSelectedRow(), 0).toString();
					ImageIcon icon = new ImageIcon(DiaDanh_DAO.getAnh(maDiaDanh));
					Image img = icon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
					ImageIcon newIcon = new ImageIcon(img);
					anh.setIcon(newIcon);
					xemAnhDialog.add(anh);
					xemAnhDialog.setVisible(true);
				}
			}
		});
		box2.add(spTableDiaDanh);
		
		buttonDauTrang = new JButton();
		buttonDauTrang.setIcon(new ImageIcon(getClass().getResource("/gui/icon/skip_to_start_24px.png")));
		buttonDauTrang.setBounds(box2.getWidth() - 740, box2.getHeight() - 45, 40, 30);
		buttonDauTrang.setBackground(MainScreen.COLOR_MAIN);
		buttonDauTrang.setToolTipText("Đầu trang");
		buttonDauTrang.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box2.add(buttonDauTrang);
		
		buttonTruoc = new JButton();
		buttonTruoc.setIcon(new ImageIcon(getClass().getResource("/gui/icon/rewind_24px.png")));
		buttonTruoc.setBounds(box2.getWidth() - 675, box2.getHeight() - 45, 40, 30);
		buttonTruoc.setBackground(MainScreen.COLOR_MAIN);
		buttonTruoc.setToolTipText("Đầu trang");
		buttonTruoc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box2.add(buttonTruoc);
		
		soTrang = new JLabel("1", JLabel.CENTER);
		soTrang.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		soTrang.setBounds(box2.getWidth() - 605, box2.getHeight() - 45, 30, 30);
		box2.add(soTrang);
		
		buttonKeTiep = new JButton();
		buttonKeTiep.setIcon(new ImageIcon(getClass().getResource("/gui/icon/fast_forward_24px.png")));
		buttonKeTiep.setToolTipText("Kế tiếp");
		buttonKeTiep.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonKeTiep.setBounds(box2.getWidth() - 550, box2.getHeight() - 45, 40, 30);
		buttonKeTiep.setBackground(MainScreen.COLOR_MAIN);
		box2.add(buttonKeTiep);
		
		buttonCuoiTrang = new JButton();
		buttonCuoiTrang.setIcon(new ImageIcon(getClass().getResource("/gui/icon/end_24px.png")));
		buttonCuoiTrang.setToolTipText("Cuối trang");
		buttonCuoiTrang.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonCuoiTrang.setBounds(box2.getWidth() - 490, box2.getHeight() - 45, 40, 30);
		buttonCuoiTrang.setBackground(MainScreen.COLOR_MAIN);
		box2.add(buttonCuoiTrang);
		
		themVaoBangDiaDanh();
		themDiaDanhButton.addActionListener(this);
		thuocTinhComboBox.addActionListener(this);
		lamMoiButton.addActionListener(this);
		xoaButton.addActionListener(this);
		suaButton.addActionListener(this);
		buttonDauTrang.addActionListener(this);
		buttonTruoc.addActionListener(this);
		buttonKeTiep.addActionListener(this);
		buttonCuoiTrang.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String thuocTinh = thuocTinhComboBox.getSelectedItem().toString().trim();
		if(thuocTinh.equals("<Tỉnh/TP>"))
			thuocTinh = "";
		int tongTrang = 1;
		int tongDiaDanh = DiaDanh_DAO.soLuongDiaDanh(timKiemTextField.getText().trim(), thuocTinh);
		tongTrang = tongDiaDanh % 20 == 0 ? tongDiaDanh / 20 : (tongDiaDanh / 20) + 1;
		Object o = e.getSource();
		if(o.equals(themDiaDanhButton)) {
			try {
				for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						javax.swing.UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
			} catch (ClassNotFoundException ex) {
				java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			} catch (InstantiationException ex) {
				java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			} catch (IllegalAccessException ex) {
				java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			} catch (javax.swing.UnsupportedLookAndFeelException ex) {
				java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			}
			DialogThemDiaDanh dialogThemDiaDanh = new DialogThemDiaDanh();
			dialogThemDiaDanh.setVisible(true);
			dialogThemDiaDanh.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					tableModel.setRowCount(0);
					themVaoBangDiaDanh();
				}
			});
		}
		else if(o.equals(thuocTinhComboBox)) {
			tableModel.setRowCount(0);
			soTrang.setText("1");
			table.clearSelection();
			themVaoBangDiaDanh();
		}
		else if(o.equals(lamMoiButton)) {
			thuocTinhComboBox.setSelectedIndex(0);
			timKiemTextField.setText("");
		}
		else if(o.equals(xoaButton)) {
			if(table.getSelectedRow()  != -1) {
				String maDiaDanh = table.getValueAt(table.getSelectedRow(), 0).toString();
				if(JOptionPane.showConfirmDialog(this, "Bạn có đồng ý xóa?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if(DiaDanh_DAO.deleteDiaDanh(maDiaDanh)) {
						JOptionPane.showMessageDialog(this, "Xóa thành công");
						tableModel.setRowCount(0);
						themVaoBangDiaDanh();
					}
					else
						JOptionPane.showMessageDialog(this, "Địa danh vẫn còn trong chuyến đi");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Chưa chọn địa danh để xóa!");
			}
		}
		else if(o.equals(suaButton)) {
			if(table.getSelectedRow() != -1) {
				try {
					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							javax.swing.UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (ClassNotFoundException ex) {
					java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				} catch (InstantiationException ex) {
					java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				} catch (IllegalAccessException ex) {
					java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				} catch (javax.swing.UnsupportedLookAndFeelException ex) {
					java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				}
				String maDiaDanh = table.getValueAt(table.getSelectedRow(), 0).toString();
				DialogSuaDiaDanh dialogSuaDiaDanh = new DialogSuaDiaDanh(maDiaDanh);
				dialogSuaDiaDanh.setVisible(true);
				dialogSuaDiaDanh.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						tableModel.setRowCount(0);
						themVaoBangDiaDanh();
					}
				});
			}
			else 
				JOptionPane.showMessageDialog(this, "Chưa chọn địa danh để sửa!");
		}
		else if(o.equals(buttonDauTrang)) {
			int page = Integer.parseInt(soTrang.getText());
			if(page != 1) {
				soTrang.setText("1");
				tableModel.setRowCount(0);
				table.clearSelection();
				themVaoBangDiaDanh();
			}
		}
		else if(o.equals(buttonTruoc)) {
			int page = Integer.parseInt(soTrang.getText()) - 1;
			if(page > 0) {
				tableModel.setRowCount(0);
				soTrang.setText(page + "");
				table.clearSelection();
				themVaoBangDiaDanh();
			}
		}
		else if(o.equals(buttonKeTiep)) {
			int page = Integer.parseInt(soTrang.getText()) + 1;
			if(page <= tongTrang) {
				tableModel.getDataVector().removeAllElements();
				soTrang.setText(page + "");
				table.clearSelection();
				themVaoBangDiaDanh();
				
			}
		}
		else if(o.equals(buttonCuoiTrang)) {
			int page = Integer.parseInt(soTrang.getText());
			if(page != tongTrang) {
				tableModel.getDataVector().removeAllElements();
				soTrang.setText(Integer.toString(tongTrang));
				table.clearSelection();
				themVaoBangDiaDanh();
			}
		}
		
	}
	private void themVaoBangDiaDanh() {
		int page = Integer.parseInt(soTrang.getText());
		String thuocTinh = thuocTinhComboBox.getSelectedItem().toString().trim();
		if(thuocTinh.equals("<Tỉnh/TP>"))
			thuocTinh = "";
		dsDiaDanh = DiaDanh_DAO.themVaoBangDiaDanh(page - 1, timKiemTextField.getText().trim(), thuocTinh);
		if(dsDiaDanh.size() == 0) {
			tableModel.fireTableDataChanged();
		}
		for (DiaDanh diaDanh : dsDiaDanh) {
			tableModel.addRow(new Object[] {diaDanh.getMaDiaDanh(), diaDanh.getTenDiaDanh(), diaDanh.getThuocTinh(), diaDanh.getAnhDiaDanh()});
		}
	}
}

