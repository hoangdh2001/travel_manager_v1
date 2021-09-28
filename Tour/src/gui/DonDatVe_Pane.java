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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.toedter.calendar.JDateChooser;

import connect.ConnectDB;
import dao.DonDatVe_DAO;
import dao.HuongDanVien_DAO;
import entity.DonDatVe;
import entity.HuongDanVien;

public class DonDatVe_Pane extends JPanel implements ActionListener, PropertyChangeListener {
	private ArrayList<DonDatVe> dsDonDatVe;
	private JButton buttonDauTrang;
	private JButton buttonTruoc;
	private JButton buttonKeTiep;
	private JLabel soTrang;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton buttonCuoiTrang;
	private JButton lamMoiButton;
	private JButton xoaButton;
	private JTextField timKiem;
	private JScrollPane spTableDiaDanh;
	private JDateChooser ngayDatVeDateChooser;
	private DonDatVe_DAO ddv_DAO;
	
	public DonDatVe_Pane() {
		setBackground(MainScreen.BACKGROUND_COLOR);
		setLayout(null);
		buidDonDatVe_Pane();
	}
	private void buidDonDatVe_Pane() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ddv_DAO = new DonDatVe_DAO();
		
		JPanel box1 = new JPanel();
		box1.setBackground(MainScreen.SUBPANE_COLOR);
		box1.setBounds(30, 0, 1180, 100);
		box1.setLayout(null);
		box1.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(142,170,215)));
		add(box1);
		
		timKiem = new JTextField() {
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
				g.drawString("Tìm kiếm...", getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
			}
		};
		timKiem.requestFocus(false);
		timKiem.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		timKiem.setBounds(10, 10, 200, 30);
		timKiem.setToolTipText("Nhập mã đơn, mã chuyến, mã khách hàng, tên khách hàng, mã nhân viên, tên nhân viên");
		timKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableModel.setRowCount(0);
				soTrang.setText("1");
				themVaoBangDonDatVe();
			}
		});
		box1.add(timKiem);
		
		ngayDatVeDateChooser = new JDateChooser();
		ngayDatVeDateChooser = new JDateChooser();
		ngayDatVeDateChooser.setBounds(230, 10, 200, 30);
		ngayDatVeDateChooser.setDateFormatString("dd/MM/yyyy");
		ngayDatVeDateChooser.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		ngayDatVeDateChooser.setBackground(box1.getBackground());
		box1.add(ngayDatVeDateChooser);
		
		lamMoiButton = new JButton("Làm mới");
		lamMoiButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/refresh_15px.png")));
		lamMoiButton.setBounds(10, 60, 100, 30);
		lamMoiButton.setBackground(new Color(51,122,183));
		lamMoiButton.setForeground(Color.WHITE);
		lamMoiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(lamMoiButton);
		
		xoaButton = new JButton("Xóa");
		xoaButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/delete_trash_15px.png")));
		xoaButton.setBounds(130, 60, 100, 30);
		xoaButton.setForeground(Color.WHITE);
		xoaButton.setBackground(new Color(51,122,183));
		xoaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(xoaButton);
		
		JPanel box2 = new JPanel();
		box2.setLayout(null);
		box2.setBackground(Color.WHITE);
		box2.setBounds(30, 130, 1180, 550);
		add(box2);
		
		JLabel titleTable = new JLabel("Danh sách đơn đặt vé");
		titleTable.setForeground(new Color(231,31,18));
		titleTable.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 20));
		titleTable.setIcon(new ImageIcon(getClass().getResource("/gui/icon/list_30px.png")));
		titleTable.setBounds(10, 0, 300, 40);
		box2.add(titleTable);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 40, 1140, 10);
		box2.add(separator);
		
		int[] columnWidth = {100, 130, 260, 260, 170, 90, 120};
		String[] columnTitle = {"ID", "Ngày đặt vé", "Khách hàng", "Nhân viên", "Chuyến đi", "Số Lượng", "Tổng tiền"};
		
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
		
		themVaoBangDonDatVe();
		lamMoiButton.addActionListener(this);
		xoaButton.addActionListener(this);
		ngayDatVeDateChooser.addPropertyChangeListener(this);
		buttonDauTrang.addActionListener(this);
		buttonTruoc.addActionListener(this);
		buttonKeTiep.addActionListener(this);
		buttonCuoiTrang.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int tongTrang = 1;
		String timKiemValues = timKiem.getText();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String ngayDatVe = "";
		if(ngayDatVeDateChooser.getDate() != null)
			ngayDatVe = df.format(ngayDatVeDateChooser.getDate());
		int soLuongDonDatVe = ddv_DAO.soLuongDonDatVe(timKiemValues, ngayDatVe);
		tongTrang = soLuongDonDatVe % 20 == 0 ? soLuongDonDatVe / 20 : (soLuongDonDatVe / 20) + 1;
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(lamMoiButton)) {
			timKiem.setText("");
			ngayDatVeDateChooser.setDate(null);
		}
		else if(o.equals(xoaButton)) {
			if(table.getSelectedRow()  != -1) {
				String maDonDatVe = table.getValueAt(table.getSelectedRow(), 0).toString();
				if(JOptionPane.showConfirmDialog(this, "Bạn có đồng ý xóa?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if(ddv_DAO.deleteDonDatVe(maDonDatVe)) {
						JOptionPane.showMessageDialog(this, "Xóa thành công!");
						tableModel.setRowCount(0);
						themVaoBangDonDatVe();
					}
					else
						JOptionPane.showMessageDialog(this, "Xóa thất bại!");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Chưa chọn hướng dẫn viên để xóa!");
			}
		}
		else if(o.equals(buttonDauTrang)) {
			int page = Integer.parseInt(soTrang.getText());
			if(page != 1) {
				soTrang.setText("1");
				tableModel.getDataVector().removeAllElements();
				table.clearSelection();
				themVaoBangDonDatVe();
			}
		}
		else if(o.equals(buttonTruoc)) {
			int page = Integer.parseInt(soTrang.getText()) - 1;
			if(page > 0) {
				tableModel.getDataVector().removeAllElements();
				soTrang.setText(page + "");
				table.clearSelection();
				themVaoBangDonDatVe();
			}
		}
		else if(o.equals(buttonKeTiep)) {
			int page = Integer.parseInt(soTrang.getText()) + 1;
			if(page <= tongTrang) {
				tableModel.getDataVector().removeAllElements();
				soTrang.setText(page + "");
				table.clearSelection();
				themVaoBangDonDatVe();
			}
		}
		else if(o.equals(buttonCuoiTrang)) {
			int page = Integer.parseInt(soTrang.getText());
			if(page != tongTrang) {
				tableModel.getDataVector().removeAllElements();
				soTrang.setText(Integer.toString(tongTrang));
				table.clearSelection();
				themVaoBangDonDatVe();
			}
		}
		
	}
	private void themVaoBangDonDatVe() {
		int page = Integer.parseInt(soTrang.getText());
		String timKiemValues = timKiem.getText();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String ngayDatVe = "";
		DecimalFormat d = new DecimalFormat("#,##0 VND");
		if(ngayDatVeDateChooser.getDate() != null)
			ngayDatVe = df.format(ngayDatVeDateChooser.getDate());
			
		dsDonDatVe = ddv_DAO.themDonDatVeVaoBang(page - 1, timKiemValues, ngayDatVe);
		for (DonDatVe donDatVe : dsDonDatVe) {
			tableModel.addRow(new Object[] {donDatVe.getMaDonDatVe(), donDatVe.getNgayDatVe(), donDatVe.getKhachHang().getTenKhachHang(), donDatVe.getNhanVien().getTenNhanVien(), donDatVe.getChuyenDi().getMaChuyenDi(), donDatVe.getSoLuong() + " vé", d.format(donDatVe.getTongTien())});
		}
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		Object o = evt.getSource();
		if(o.equals(ngayDatVeDateChooser)) {
			tableModel.setRowCount(0);
			soTrang.setText("1");
			themVaoBangDonDatVe();
		}
	}
}

