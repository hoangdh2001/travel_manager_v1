package gui;

import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import connect.ConnectDB;
import dao.NhanVien_DAO;
import entity.NhanVien;

public class NhanVien_Pane extends JPanel implements ActionListener {
	private ArrayList<NhanVien> dsNhanVien;
	private JButton buttonDauTrang;
	private JButton buttonTruoc;
	private JButton buttonKeTiep;
	private JLabel soTrang;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton buttonCuoiTrang;
	private JButton lamMoiButton;
	private JButton themNhanVienButoon;
	private JButton xoaButton;
	private JButton suaButton;
	private JComboBox<String> gioiTinhComboBox;
	private NhanVien_DAO nv_DAO;
	private JTextField timKiemSDT;
	private JTextField timKiemTen;
	
	public NhanVien_Pane() {
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
		
		nv_DAO = new NhanVien_DAO();
		
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
				themVaoBangNhanVien();
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
				themVaoBangNhanVien();
			}
		});
		box1.add(timKiemSDT);
		
		gioiTinhComboBox = new JComboBox<String>();
		gioiTinhComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		gioiTinhComboBox.setBounds(450, 10, 200, 30);
		gioiTinhComboBox.addItem("<Giới tính>");
		gioiTinhComboBox.addItem("Nam");
		gioiTinhComboBox.addItem("Nữ");
		box1.add(gioiTinhComboBox);
		
		lamMoiButton = new JButton("Làm mới");
		lamMoiButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/refresh_15px.png")));
		lamMoiButton.setBounds(10, 60, 100, 30);
		lamMoiButton.setBackground(new Color(51,122,183));
		lamMoiButton.setForeground(Color.WHITE);
		lamMoiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(lamMoiButton);
		
		themNhanVienButoon = new JButton("Thêm");
		themNhanVienButoon.setIcon(new ImageIcon(getClass().getResource("/gui/icon/add_15px.png")));
		themNhanVienButoon.setBackground(new Color(63,201,213));
		themNhanVienButoon.setBounds(120, 60, 100, 30);
		themNhanVienButoon.setForeground(Color.WHITE);
		themNhanVienButoon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(themNhanVienButoon);
		
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
		
		JLabel titleTable = new JLabel("Danh sách nhân viên");
		titleTable.setForeground(new Color(231,31,18));
		titleTable.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 20));
		titleTable.setIcon(new ImageIcon(getClass().getResource("/gui/icon/list_30px.png")));
		titleTable.setBounds(10, 0, 300, 40);
		box2.add(titleTable);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 40, 1140, 10);
		box2.add(separator);
		
		int[] columnWidth = {100, 220, 110, 110, 145, 250, 200};
		String[] columnTitle = {"ID", "Tên nhân viên", "Giới tính", "Trạng thái", "Số điện thoại", "Email", "Địa chỉ"};
		
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
		for (int i = 0; i < columnWidth.length; i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setMinWidth(columnWidth[i]);
			column.setMaxWidth(columnWidth[i]);
			column.setPreferredWidth(columnWidth[i]);
		}
		JScrollPane spTableDiaDanh = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
		
		themVaoBangNhanVien();
		themNhanVienButoon.addActionListener(this);
		gioiTinhComboBox.addActionListener(this);
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
		int tongTrang = 1;
		String gioiTinh = "";
		int index = gioiTinhComboBox.getSelectedIndex();
		if(index == 1) {
			gioiTinh = "0";
		}
		else if(index == 2) {
			gioiTinh = "1";
		}
		int soLuongNhanVien = nv_DAO.soLuongNhanVien(timKiemSDT.getText().trim(), timKiemTen.getText().trim(), gioiTinh);
		tongTrang = soLuongNhanVien % 20 == 0 ? soLuongNhanVien / 20 : (soLuongNhanVien / 20) + 1;
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(gioiTinhComboBox)) {
			tableModel.setRowCount(0);
			soTrang.setText("1");
			table.clearSelection();
			themVaoBangNhanVien();
		}
		else if(o.equals(lamMoiButton)) {
			timKiemSDT.setText("");
			timKiemTen.setText("");
			gioiTinhComboBox.setSelectedIndex(0);
		}
		else if (o.equals(themNhanVienButoon)) {
			DialogThemNhanVien dialogThemNhanVien= new DialogThemNhanVien();
			dialogThemNhanVien.setVisible(true);
			dialogThemNhanVien.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					tableModel.setRowCount(0);
					themVaoBangNhanVien();
				}
			});
		}
		else if(o.equals(xoaButton)) {
			if(table.getSelectedRow()  != -1) {
				String maNhanVien = table.getValueAt(table.getSelectedRow(), 0).toString();
				String soDienThoai = table.getValueAt(table.getSelectedRow(), 4).toString();
				String trangThai = table.getValueAt(table.getSelectedRow(), 3).toString();
				if(trangThai.equals("Đang làm")) {
					JOptionPane.showMessageDialog(this, "Nhân viên đang làm việc");
					return;
				}
				if(JOptionPane.showConfirmDialog(this, "Bạn có đồng ý xóa?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if(nv_DAO.deleteNhanVien(maNhanVien, soDienThoai)) {
						JOptionPane.showMessageDialog(this, "Xóa thành công!");
						tableModel.setRowCount(0);
						themVaoBangNhanVien();
					}
					else
						JOptionPane.showMessageDialog(this, "Xóa thất bại!");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên để xóa!");
			}
		}
		else if(o.equals(suaButton)) {
			if(table.getSelectedRow() != -1) {
				String maNhanVien = table.getValueAt(table.getSelectedRow(), 0).toString();
				DialogSuaNhanVien dialogSuaNhanVien = new DialogSuaNhanVien(maNhanVien);
				dialogSuaNhanVien.setVisible(true);
				dialogSuaNhanVien.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						tableModel.setRowCount(0);
						themVaoBangNhanVien();
					}
				});
			}
			else
				JOptionPane.showMessageDialog(this, "Chưa chọn khách hàng để sửa!");
		}
		else if(o.equals(buttonDauTrang)) {
			int page = Integer.parseInt(soTrang.getText());
			if(page != 1) {
				tableModel.setRowCount(0);
				soTrang.setText("1");
				table.clearSelection();
				themVaoBangNhanVien();
			}
		}
		else if(o.equals(buttonTruoc)) {
			int page = Integer.parseInt(soTrang.getText()) - 1;
			if(page > 0) {
				tableModel.setRowCount(0);
				soTrang.setText(page + "");
				table.clearSelection();
				themVaoBangNhanVien();
			}
		}
		else if(o.equals(buttonKeTiep)) {
			int page = Integer.parseInt(soTrang.getText()) + 1;
			if(page <= tongTrang) {
				tableModel.setRowCount(0);
				soTrang.setText(page + "");
				table.clearSelection();
				themVaoBangNhanVien();
				
			}
		}
		else if(o.equals(buttonCuoiTrang)) {
			int page = Integer.parseInt(soTrang.getText());
			if(page != tongTrang) {
				tableModel.setRowCount(0);
				soTrang.setText(Integer.toString(tongTrang));
				table.clearSelection();
				themVaoBangNhanVien();
			}
		}
		
	}
	private void themVaoBangNhanVien() {
		int page = Integer.parseInt(soTrang.getText());
		String gioiTinh = "";
		int index = gioiTinhComboBox.getSelectedIndex();
		if(index == 1) {
			gioiTinh = "0";
		} else if (index == 2) {
			gioiTinh = "1";
		}
		dsNhanVien = nv_DAO.themVaoBangNhanVien(page - 1, timKiemSDT.getText().trim(), timKiemTen.getText().trim(), gioiTinh);
		for (NhanVien nhanVien : dsNhanVien) {
			tableModel.addRow(new Object[] {nhanVien.getMaNhanVien(), nhanVien.getTenNhanVien(), nhanVien.isGioiTinh() ? "Nữ":"Nam", nhanVien.isTrangThai() ? "Đang làm":"Nghỉ việc", nhanVien.getSoDienThoai(), nhanVien.getEmail(), nhanVien.getDiaChi().getTinhThanh()});
		}
	}
}

