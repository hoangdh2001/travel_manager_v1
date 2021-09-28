package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import connect.ConnectDB;
import dao.HuongDanVien_DAO;
import entity.HuongDanVien;

public class HuongDanVien_Pane extends JPanel implements ActionListener {
	private ArrayList<HuongDanVien> dsHuongDanVien;
	private JButton buttonDauTrang;
	private JButton buttonTruoc;
	private JButton buttonKeTiep;
	private JLabel soTrang;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton buttonCuoiTrang;
	private JButton lamMoiButton;
	private JButton themHuongDanVienButton;
	private JButton xoaButton;
	private JButton suaButton;
	private JComboBox<String> gioiTinhComboBox;
	private JTextField timKiemSDT;
	private JTextField timKiemTen;
	private HuongDanVien_DAO hdv_DAO;
	private JComboBox<String> trangThaiComboBox;
	private JScrollPane spTableDiaDanh;
	
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
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}
	/**
	 * @return the buttonCuoiTrang
	 */
	public JButton getButtonCuoiTrang() {
		return buttonCuoiTrang;
	}
	/**
	 * @return the gioiTinhComboBox
	 */
	public JComboBox<String> getGioiTinhComboBox() {
		return gioiTinhComboBox;
	}
	
	/**
	 * @return the timKiemSDT
	 */
	public JTextField getTimKiemSDT() {
		return timKiemSDT;
	}
	/**
	 * @return the timKiemTen
	 */
	public JTextField getTimKiemTen() {
		return timKiemTen;
	}
	/**
	 * @return the trangThaiComboBox
	 */
	public JComboBox<String> getTrangThaiComboBox() {
		return trangThaiComboBox;
	}
	/**
	 * @return the spTableDiaDanh
	 */
	public JScrollPane getSpTableDiaDanh() {
		return spTableDiaDanh;
	}
	
	public HuongDanVien_Pane() {
		setBackground(MainScreen.BACKGROUND_COLOR);
		setLayout(null);
		buidHuongDanVien_Pane();
	}
	private void buidHuongDanVien_Pane() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		hdv_DAO = new HuongDanVien_DAO();
		
		JPanel box1 = new JPanel();
		box1.setBackground(MainScreen.SUBPANE_COLOR);
		box1.setBounds(30, 0, 1180, 100);
		box1.setLayout(null);
		box1.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(142,170,215)));
		add(box1);
		
		timKiemTen = new JTextField() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(getText().length() > 0) {
					return;
				}
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setColor(Color.GRAY);
				g2d.setFont(new Font("Segoe ui", Font.ITALIC, 16));
				g.drawString("Nhập tên", getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
			}
		};
		timKiemTen.requestFocus(false);
		timKiemTen.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		timKiemTen.setBounds(10, 10, 200, 30);
		timKiemTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableModel.setRowCount(0);
				soTrang.setText("1");
				themVaoBangHuongDanVien();
			}
		});
		box1.add(timKiemTen);
		
		timKiemSDT = new JTextField() {
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
				g.drawString("Nhập số điện thoại", getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
			}
		};
		timKiemSDT.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		timKiemSDT.setBounds(230, 10, 200, 30);
		timKiemSDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableModel.setRowCount(0);
				soTrang.setText("1");
				themVaoBangHuongDanVien();
			}
		});
		box1.add(timKiemSDT);
		
		gioiTinhComboBox = new JComboBox<String>();
		gioiTinhComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		gioiTinhComboBox.setBounds(450, 10, 200, 30);
		gioiTinhComboBox.addItem("<Tất cả>");
		gioiTinhComboBox.addItem("Nam");
		gioiTinhComboBox.addItem("Nữ");
		box1.add(gioiTinhComboBox);
		
		trangThaiComboBox = new JComboBox<String>();
		trangThaiComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		trangThaiComboBox.setBounds(670, 10, 200, 30);
		trangThaiComboBox.addItem("<Tất cả>");
		trangThaiComboBox.addItem("Chưa có lịch đi");
		trangThaiComboBox.addItem("Đã có lịch đi");
		trangThaiComboBox.addItem("Đang đi tour");
		box1.add(trangThaiComboBox);
		
		lamMoiButton = new JButton("Làm mới");
		lamMoiButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/refresh_15px.png")));
		lamMoiButton.setBounds(10, 60, 100, 30);
		lamMoiButton.setBackground(new Color(51,122,183));
		lamMoiButton.setForeground(Color.WHITE);
		lamMoiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(lamMoiButton);
		
		themHuongDanVienButton = new JButton("Thêm");
		themHuongDanVienButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/add_15px.png")));
		themHuongDanVienButton.setBackground(new Color(63,201,213));
		themHuongDanVienButton.setBounds(120, 60, 100, 30);
		themHuongDanVienButton.setForeground(Color.WHITE);
		themHuongDanVienButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(themHuongDanVienButton);
		
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
		
		JPanel box2 = new JPanel();
		box2.setLayout(null);
		box2.setBackground(Color.WHITE);
		box2.setBounds(30, 130, 1180, 550);
		add(box2);
		
		JLabel titleTable = new JLabel("Danh sách hướng dẫn viên");
		titleTable.setForeground(new Color(231,31,18));
		titleTable.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 20));
		titleTable.setIcon(new ImageIcon(getClass().getResource("/gui/icon/list_30px.png")));
		titleTable.setBounds(10, 0, 300, 40);
		box2.add(titleTable);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 40, 1140, 10);
		box2.add(separator);
		
		int[] columnWidth = {110, 320, 110, 140, 170, 290};
		String[] columnTitle = {"ID", "Tên nhân viên", "Giới tính", "Trạng thái", "Số điện thoại", "Địa chỉ"};
		
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
		table.setShowGrid(true);
		table.setGridColor(MainScreen.BACKGROUND_COLOR);
		table.setRowHeight(30);
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		for (int i = 0; i < columnWidth.length; i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setMinWidth(columnWidth[i]);
			column.setMaxWidth(columnWidth[i]);
			column.setPreferredWidth(columnWidth[i]);
		}
		spTableDiaDanh = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		spTableDiaDanh.setBounds(20, 60, 1140, 440);
		box2.add(spTableDiaDanh);
		
		buttonDauTrang = new JButton();
		buttonDauTrang.setIcon(new ImageIcon(getClass().getResource("/gui/icon/skip_to_start_24px.png")));
		buttonDauTrang.setBounds(440, 505, 40, 30);
		buttonDauTrang.setBackground(MainScreen.COLOR_MAIN);
		buttonDauTrang.setToolTipText("Đầu trang");
		buttonDauTrang.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box2.add(buttonDauTrang);
		
		buttonTruoc = new JButton();
		buttonTruoc.setIcon(new ImageIcon(getClass().getResource("/gui/icon/rewind_24px.png")));
		buttonTruoc.setBounds(505, 505, 40, 30);
		buttonTruoc.setBackground(MainScreen.COLOR_MAIN);
		buttonTruoc.setToolTipText("Đầu trang");
		buttonTruoc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box2.add(buttonTruoc);
		
		soTrang = new JLabel("1", JLabel.CENTER);
		soTrang.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		soTrang.setBounds(575, 505, 30, 30);
		box2.add(soTrang);
		
		buttonKeTiep = new JButton();
		buttonKeTiep.setIcon(new ImageIcon(getClass().getResource("/gui/icon/fast_forward_24px.png")));
		buttonKeTiep.setToolTipText("Kế tiếp");
		buttonKeTiep.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonKeTiep.setBounds(630, 505, 40, 30);
		buttonKeTiep.setBackground(MainScreen.COLOR_MAIN);
		box2.add(buttonKeTiep);
		
		buttonCuoiTrang = new JButton();
		buttonCuoiTrang.setIcon(new ImageIcon(getClass().getResource("/gui/icon/end_24px.png")));
		buttonCuoiTrang.setToolTipText("Cuối trang");
		buttonCuoiTrang.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonCuoiTrang.setBounds(690, 505, 40, 30);
		buttonCuoiTrang.setBackground(MainScreen.COLOR_MAIN);
		box2.add(buttonCuoiTrang);
		
		themVaoBangHuongDanVien();
		themHuongDanVienButton.addActionListener(this);
		gioiTinhComboBox.addActionListener(this);
		trangThaiComboBox.addActionListener(this);
		lamMoiButton.addActionListener(this);
		xoaButton.addActionListener(this);
		suaButton.addActionListener(this);
		buttonDauTrang.addActionListener(this);
		buttonTruoc.addActionListener(this);
		buttonKeTiep.addActionListener(this);
		buttonCuoiTrang.addActionListener(this);
		
		TableCellRenderer tableCellRenderer = new TableCellRenderer() {
			JLabel lbLabel = new JLabel();
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				lbLabel.setOpaque(true);
				lbLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
				lbLabel.setForeground(Color.WHITE);
				lbLabel.setHorizontalAlignment(JLabel.HORIZONTAL);
				if(value.equals("Đã có lịch")) {
					lbLabel.setBackground(new Color(218, 127, 110));
					lbLabel.setText(value.toString());
				}
				else  if(value.equals("Chưa có lịch đi")){
					lbLabel.setBackground(MainScreen.HEADER_COLOR);
					lbLabel.setText(value.toString());
				}
				else {
					lbLabel.setBackground(new Color(78, 136, 229));
					lbLabel.setText(value.toString());
				}
				if(isSelected) {
					lbLabel.setBackground(table.getSelectionBackground());
				}
				return lbLabel;
			}
		};
		table.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int tongTrang = 1;
		String gioiTinh = "";
		int index = gioiTinhComboBox.getSelectedIndex();
		if(index == 1) {
			gioiTinh = "0";
		}
		else if(index == 2) {
			gioiTinh = "1";
		}
		String trangThai = trangThaiComboBox.getSelectedItem().toString();
		if(trangThai.equals("<Tất cả>"))
			trangThai = "";
		int soLuongHuongDanVien = hdv_DAO.soLuongHuongDanVien(timKiemSDT.getText().trim(), timKiemTen.getText().trim(), gioiTinh, trangThai);
		tongTrang = soLuongHuongDanVien % 20 == 0 ? soLuongHuongDanVien / 20 : (soLuongHuongDanVien / 20) + 1;
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(gioiTinhComboBox)) {
			tableModel.getDataVector().removeAllElements();
			soTrang.setText("1");
			table.clearSelection();
			themVaoBangHuongDanVien();
		}
		else if(o.equals(trangThaiComboBox)) {
			tableModel.setRowCount(0);
			soTrang.setText("1");
			table.clearSelection();
			themVaoBangHuongDanVien();
		}
		else if(o.equals(lamMoiButton)) {
			timKiemSDT.setText("");
			timKiemTen.setText("");
			gioiTinhComboBox.setSelectedIndex(0);
		}
		else if (o.equals(themHuongDanVienButton)) {
			DialogThemHuongDanVien dialogThemHuongDanVien = new DialogThemHuongDanVien();
			dialogThemHuongDanVien.setVisible(true);
			dialogThemHuongDanVien.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					tableModel.getDataVector().removeAllElements();
					themVaoBangHuongDanVien();
				}
			});
			
		}
		else if(o.equals(xoaButton)) {
			if(table.getSelectedRow()  != -1) {
				String maHuongDanVien = table.getValueAt(table.getSelectedRow(), 0).toString();
				String trangThai1 = table.getValueAt(table.getSelectedRow(), 3).toString();
				if(trangThai1.equals("Đã có lịch đi")) {
					JOptionPane.showMessageDialog(this, "Hướng dẫn viên đang có tour");
					return;
				}
				else if(trangThai1.equals("Đang đi tour")) {
					JOptionPane.showMessageDialog(this, "Hướng dẫn viên đang đi tour!");
					return;
				}
				if(JOptionPane.showConfirmDialog(this, "Bạn có đồng ý xóa?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if(hdv_DAO.deleteHuongDanVien(maHuongDanVien)) {
						JOptionPane.showMessageDialog(this, "Xóa thành công!");
						tableModel.setRowCount(0);
						themVaoBangHuongDanVien();
					}
					else
						JOptionPane.showMessageDialog(this, "Xóa thất bại!");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Chưa chọn hướng dẫn viên để xóa!");
			}
		}
		else if(o.equals(suaButton)) {
			if(table.getSelectedRow() != -1) {
				String maHuongDanVien = table.getValueAt(table.getSelectedRow(), 0).toString();
				DialogSuaHuongDanVien dialogSuaHuongDanVien = new DialogSuaHuongDanVien(maHuongDanVien);
				dialogSuaHuongDanVien.setVisible(true);
				dialogSuaHuongDanVien.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						tableModel.getDataVector().removeAllElements();
						themVaoBangHuongDanVien();
					}
				});
			} else {
				JOptionPane.showMessageDialog(this, "Chưa chọn hướng dẫn viên để sửa!");
			}
		}
		else if(o.equals(buttonDauTrang)) {
			int page = Integer.parseInt(soTrang.getText());
			if(page != 1) {
				soTrang.setText("1");
				tableModel.getDataVector().removeAllElements();
				table.clearSelection();
				themVaoBangHuongDanVien();
			}
		}
		else if(o.equals(buttonTruoc)) {
			int page = Integer.parseInt(soTrang.getText()) - 1;
			if(page > 0) {
				tableModel.getDataVector().removeAllElements();
				soTrang.setText(page + "");
				table.clearSelection();
				themVaoBangHuongDanVien();
			}
		}
		else if(o.equals(buttonKeTiep)) {
			int page = Integer.parseInt(soTrang.getText()) + 1;
			if(page <= tongTrang) {
				tableModel.getDataVector().removeAllElements();
				soTrang.setText(page + "");
				table.clearSelection();
				themVaoBangHuongDanVien();
				
			}
		}
		else if(o.equals(buttonCuoiTrang)) {
			int page = Integer.parseInt(soTrang.getText());
			if(page != tongTrang) {
				tableModel.getDataVector().removeAllElements();
				soTrang.setText(Integer.toString(tongTrang));
				table.clearSelection();
				themVaoBangHuongDanVien();
			}
		}
		
	}
	private void themVaoBangHuongDanVien() {
		int page = Integer.parseInt(soTrang.getText());
		String gioiTinh = "";
		int index = gioiTinhComboBox.getSelectedIndex();
		if(index == 1) {
			gioiTinh = "0";
		} else if (index == 2) {
			gioiTinh = "1";
		}
		String trangThai = trangThaiComboBox.getSelectedItem().toString();
		if(trangThai.equals("<Tất cả>"))
			trangThai = "";
		dsHuongDanVien = hdv_DAO.themVaoBangHuongDanVien(page - 1, timKiemTen.getText().trim(), timKiemSDT.getText().trim(), gioiTinh, trangThai);
		for (HuongDanVien huongDanVien : dsHuongDanVien) {
			tableModel.addRow(new Object[] {huongDanVien.getMaHuongDanVien(), huongDanVien.getTenHuongDanVien(), huongDanVien.isGioiTinh()? "Nữ":"Nam", huongDanVien.getTrangThai(), huongDanVien.getSoDienThoai(), huongDanVien.getDiaChi().getTinhThanh()});
		}
	}
}

