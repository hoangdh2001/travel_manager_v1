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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
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
import dao.DiaChi_DAO;
import dao.KhachHang_DAO;
import entity.KhachHang;


public class KhachHang_Pane extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField timKiemTen;
	private JTable table;
	private DefaultTableModel tableModel;
	private KhachHang_DAO kh_DAO;
	private ArrayList<KhachHang> dsKhachHang;
	private JButton buttonDauTrang;
	private JButton buttonLui;
	private JLabel pageNumber;
	private JButton buttonTien;
	private JButton buttonCuoiTrang;
	private JTextField timKiemSDT;
	private JComboBox<String> cbGioiTinh;
	private JComboBox<String> cbDiaChi;
	private JButton xoaButton;
	private JButton suaButton;
	private JButton lamMoiButton;
	
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
	public KhachHang_Pane() {
		setBackground(MainScreen.BACKGROUND_COLOR);
		setLayout(null);
		buidKhachHang();
	}
	/**
	 * 
	 */
	private void buidKhachHang() {
		try {
			ConnectDB.getInstance().connect();;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		kh_DAO = new KhachHang_DAO();
		
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
				pageNumber.setText("1");
				themVaoBang();
			}
		});
		box1.add(timKiemTen);
		
		timKiemSDT = new JTextField() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(getText().length() > 0) {
					return;
				}
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.GRAY);
				g2d.setFont(new Font(MainScreen.FONT_TEXT, Font.ITALIC, 16));
				g2d.drawString("Nhập số điện thoại", getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
			}
		};
		timKiemSDT.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		timKiemSDT.setBounds(230, 10, 200, 30);
		timKiemSDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableModel.setRowCount(0);
				pageNumber.setText("1");
				themVaoBang();
			}
		});
		box1.add(timKiemSDT);
		
		cbGioiTinh = new JComboBox<String>();
		cbGioiTinh.setFont(new Font(MainScreen.FONT_TEXT, Font.BOLD, 16));
		cbGioiTinh.addItem("<Tất cả>");
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");
		cbGioiTinh.setBounds(450, 10, 200, 30);
		box1.add(cbGioiTinh);
		
		DefaultComboBoxModel<String> cbDiaChiModel = new DefaultComboBoxModel<String>();
		cbDiaChiModel.addElement("<Tỉnh/TP>");
		cbDiaChiModel.addAll(DiaChi_DAO.getAllTinh());
		cbDiaChi = new JComboBox<String>();
		cbDiaChi.setFont(new Font(MainScreen.FONT_TEXT, Font.BOLD, 16));
		cbDiaChi.setModel(cbDiaChiModel);
		cbDiaChi.setBounds(670, 10, 200, 30);
		box1.add(cbDiaChi);
		
		lamMoiButton = new JButton("Làm mới");
		lamMoiButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/refresh_15px.png")));
		lamMoiButton.setBounds(10, 60, 100, 30);
		lamMoiButton.setBackground(new Color(51,122,183));
		lamMoiButton.setForeground(Color.WHITE);
		lamMoiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(lamMoiButton);

		xoaButton = new JButton("Xóa");
		xoaButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/delete_trash_15px.png")));
		xoaButton.setBounds(120, 60, 100, 30);
		xoaButton.setForeground(Color.WHITE);
		xoaButton.setBackground(new Color(51,122,183));
		xoaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(xoaButton);

		suaButton = new JButton("Sửa");
		suaButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/wrench_15px.png")));
		suaButton.setBounds(240, 60, 100, 30);
		suaButton.setForeground(Color.WHITE);
		suaButton.setBackground(new Color(51,122,183));
		suaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(suaButton);
		
		JPanel box2 = new JPanel();
		box2.setBackground(Color.WHITE);
		box2.setLayout(null);
		box2.setBounds(30, 130, 1180, 550);
		add(box2);
		
		JLabel titleTable = new JLabel("Danh sách khách hàng");
		titleTable.setForeground(new Color(231,31,18));
		titleTable.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 20));
		titleTable.setIcon(new ImageIcon(getClass().getResource("/gui/icon/list_30px.png")));
		titleTable.setBounds(10, 0, 300, 40);
		box2.add(titleTable);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 40, 1140, 10);
		box2.add(separator);
		
		int[] columnWidth = {100, 210, 100, 100, 150, 240, 240};
        String[] columnNames = new String[] {"ID", "Tên KH", "Giới Tính", "Ngày sinh", "SDT", "Email", "DiaChi"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel) {
        	@Override
        	public boolean editCellAt(int row, int column, EventObject e) {
        		return false;
        	}
        };
        table.getTableHeader().setBackground(MainScreen.COLOR_MAIN);
        table.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFillsViewportHeight(true);
        table.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
        table.setGridColor(MainScreen.BACKGROUND_COLOR);
        table.setShowGrid(true);
        table.setRowHeight(30);
        table.setCursor(new Cursor(Cursor.HAND_CURSOR));
        table.getTableHeader().setPreferredSize(new Dimension(1240, 30));
        
		for (int i = 0; i < columnWidth.length; i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setMinWidth(columnWidth[i]);
			column.setMaxWidth(columnWidth[i]);
			column.setPreferredWidth(columnWidth[i]);
		}
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBounds(20, 60, 1140, 440);
        box2.add(sp);
        
        buttonDauTrang = new JButton();
        buttonDauTrang.setIcon(new ImageIcon(getClass().getResource("/gui/icon/skip_to_start_24px.png")));
        buttonDauTrang.setBounds(440, 505, 40, 30);
        buttonDauTrang.setBackground(MainScreen.COLOR_MAIN);
        buttonDauTrang.setToolTipText("Đầu trang");
        buttonDauTrang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        box2.add(buttonDauTrang);
        
        
        buttonLui = new JButton();
        buttonLui.setIcon(new ImageIcon(getClass().getResource("/gui/icon/rewind_24px.png")));
        buttonLui.setToolTipText("Trước");
        buttonLui.setBounds(505, 505, 40, 30);
        buttonLui.setBackground(MainScreen.COLOR_MAIN);
        buttonLui.setCursor(new Cursor(Cursor.HAND_CURSOR));
        box2.add(buttonLui);
        
        pageNumber = new JLabel("1", JLabel.CENTER);
        pageNumber.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
        pageNumber.setBounds(575, 505, 30, 30);
        box2.add(pageNumber);
        
        buttonTien = new JButton();
        buttonTien.setToolTipText("Kế tiếp");
        buttonTien.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonTien.setIcon(new ImageIcon(getClass().getResource("/gui/icon/fast_forward_24px.png")));
        buttonTien.setBounds(630, 505, 40, 30);
        buttonTien.setBackground(MainScreen.COLOR_MAIN);
        box2.add(buttonTien);
        
        buttonCuoiTrang = new JButton();
        buttonCuoiTrang.setIcon(new ImageIcon(getClass().getResource("/gui/icon/end_24px.png")));
        buttonCuoiTrang.setBounds(690, 505, 40, 30);
        buttonCuoiTrang.setToolTipText("Cuối trang");
        buttonCuoiTrang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonCuoiTrang.setBackground(MainScreen.COLOR_MAIN);
        box2.add(buttonCuoiTrang);
        
        themVaoBang();
        
        cbGioiTinh.addActionListener(this);
        cbDiaChi.addActionListener(this);
        lamMoiButton.addActionListener(this);
        xoaButton.addActionListener(this);
        suaButton.addActionListener(this);
        buttonDauTrang.addActionListener(this);
        buttonLui.addActionListener(this);
        buttonTien.addActionListener(this);
        buttonCuoiTrang.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int tongTrang = 1;
		String gioiTinh = "";
		int index = cbGioiTinh.getSelectedIndex();
		if(index == 1) {
			gioiTinh = "0";
		}
		else if(index == 2) {
			gioiTinh = "1";
		}
		String tinhThanh = cbDiaChi.getSelectedItem().toString();
		if(tinhThanh.equals("<Tỉnh/TP>"))
			tinhThanh = "";
		int tongKhachHang = kh_DAO.tongKhachHang(timKiemSDT.getText().trim(), timKiemTen.getText().trim(), gioiTinh, tinhThanh);
		tongTrang = tongKhachHang % 20 == 0 ? tongKhachHang / 20 : (tongKhachHang / 20) + 1;
		Object o = e.getSource();
		if(o.equals(cbGioiTinh)) {
			tableModel.getDataVector().removeAllElements();
			pageNumber.setText("1");
			themVaoBang();
		}
		else if(o.equals(cbDiaChi)) {
			tableModel.getDataVector().removeAllElements();
			pageNumber.setText("1");
			themVaoBang();
		}
		else if(o.equals(lamMoiButton)) {
			timKiemTen.setText("");
			timKiemSDT.setText("");
			cbGioiTinh.setSelectedIndex(0);
			cbDiaChi.setSelectedIndex(0);
		}
		else if(o.equals(xoaButton)) {
			if(table.getSelectedRow()  != -1) {
				String maKhachHang = table.getValueAt(table.getSelectedRow(), 0).toString();
				String soDienThoai = table.getValueAt(table.getSelectedRow(), 4).toString();
				if(JOptionPane.showConfirmDialog(this, "Bạn có đồng ý xóa?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if(kh_DAO.xoaKhachHang(maKhachHang, soDienThoai)) {
						JOptionPane.showMessageDialog(this, "Xóa thành công");
						tableModel.getDataVector().removeAllElements();
						themVaoBang();
					}
					else
						JOptionPane.showMessageDialog(this, "Địa danh vẫn còn trong chuyến đi");
				}
			}
			else
				JOptionPane.showMessageDialog(this, "Chưa chọn khách hàng để xóa");
		}
		else if(o.equals(suaButton)) {
			if(table.getSelectedRow() != -1) {
				String maKhachHang = table.getValueAt(table.getSelectedRow(), 0).toString();
				DialogSuaKhachHang dialogSuaKhachHang = new DialogSuaKhachHang(maKhachHang);
				dialogSuaKhachHang.setVisible(true);
				dialogSuaKhachHang.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						tableModel.getDataVector().removeAllElements();
						themVaoBang();
					}
				});
			}
			else
				JOptionPane.showMessageDialog(this, "Chưa chọn khách hàng để sửa!");
		}
		else if(o.equals(buttonTien)) {
			
			int page = Integer.parseInt(pageNumber.getText()) + 1;
	        if(page <= tongTrang) {
	        	tableModel.getDataVector().removeAllElements();
	        	pageNumber.setText(Integer.toString(page));
	        	table.clearSelection();
	        	themVaoBang();
	        }
		}
		else if(o.equals(buttonLui)) {
			int page = Integer.parseInt(pageNumber.getText()) - 1;
			if(page > 0) {
				tableModel.getDataVector().removeAllElements();
				pageNumber.setText(Integer.toString(page));
				table.clearSelection();
				themVaoBang();
			}
		}
		else if(o.equals(buttonCuoiTrang)) {
			int page = Integer.parseInt(pageNumber.getText());
			if(page != tongTrang) {
				tableModel.getDataVector().removeAllElements();
				pageNumber.setText(Integer.toString(tongTrang));
				table.clearSelection();
				themVaoBang();
			}
		}
		else if(o.equals(buttonDauTrang)) {
			int page = Integer.parseInt(pageNumber.getText());
			if(page != 1) {
				tableModel.getDataVector().removeAllElements();
				pageNumber.setText("1");
				table.clearSelection();
				themVaoBang();
			}
		}
	}
	private void themVaoBang() {
		int page = Integer.parseInt(pageNumber.getText());
		String gioiTinh = "";
		int index = cbGioiTinh.getSelectedIndex();
		if(index == 1) {
			gioiTinh = "0";
		}
		else if(index == 2) {
			gioiTinh = "1";
		}
		String tinhThanh = cbDiaChi.getSelectedItem().toString();
		if(tinhThanh.equals("<Tỉnh/TP>"))
			tinhThanh = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dsKhachHang = kh_DAO.themVaoBang(page - 1, timKiemSDT.getText().trim(), timKiemTen.getText().trim(), gioiTinh, tinhThanh);
		if(dsKhachHang.size() == 0) {
			tableModel.fireTableDataChanged();
		}
		for (KhachHang khachHang : dsKhachHang) {
			tableModel.addRow(new Object[] {khachHang.getMaKhachHang(), khachHang.getTenKhachHang(), khachHang.isGioiTinh() ? "Nữ":"Nam", sdf.format(khachHang.getNgaySinh()), khachHang.getSoDienThoai(), khachHang.getEmail(), khachHang.getDiaChi().getTinhThanh()});
		}
	}
}
