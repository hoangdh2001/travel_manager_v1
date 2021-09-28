package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventObject;

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
import javax.swing.JTextArea;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.DefaultCaret;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import connect.ConnectDB;
import dao.CT_ThamQuan_DAO;
import dao.ChuyenDi_DAO;
import dao.DiaChi_DAO;
import dao.DiaDanh_DAO;
import dao.LoaiChuyenDi_DAO;
import dohuyhoang.animation.slideshow.SlideShowPane;
import entity.CT_ThamQuan;
import entity.ChuyenDi;
import entity.DiaDanh;

public class ChuyenDi_Pane extends JPanel implements ActionListener, PropertyChangeListener {
	private ChuyenDi_DAO cd_DAO;
	private ArrayList<ChuyenDi> dsChuyenDi;
	private JTextArea tenChuyenDi;
	private SlideShowPane slideShowAnh;
	private JTextArea moTa;
	private JScrollPane ct_ThamQuanScrollPane;
	private JButton buttonKeTiep;
	private JButton buttonDautrang;
	private JButton buttonTruoc;
	private JButton buttonCuoiTrang;
	private JLabel soTrang;
	private DefaultTableModel tableModel;
	private JTable table;
	private JComboBox<String> noiKhoiHanhComboBox;
	private JComboBox<String> giaComboBox;
	private JDateChooser ngayKhoiHanh;
	private JDateChooser ngayKetThuc;
	private JButton lamMoiButton;
	private JButton themChuyenDiButton;
	private JButton xoaButton;
	private JButton suaButton;
	private JPanel themDiaDanh_Pane;
	private JPanel CT_ThamQuanPane;
	private JButton themDiaDanhButton;
	private JButton lenLichButton;
	private JButton huyLichButton;
	private JButton suaDiaDanhButton;
	private JComboBox<String> loaiChuyenComboBox;
	private JComboBox<String> trangThaiComboBox;

	public ChuyenDi_Pane() {
		setBackground(MainScreen.BACKGROUND_COLOR);
		setLayout(null);
		buidChuyenDi();
	}

	private void buidChuyenDi() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		cd_DAO = new ChuyenDi_DAO();
		cd_DAO.capNhatTrangThaiChuyenDi();
		
		JPanel box1 = new JPanel();
		box1.setBackground(MainScreen.SUBPANE_COLOR);
		box1.setBounds(30, 0, 1180, 100);
		box1.setLayout(null);
		box1.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(142, 170, 215)));
		add(box1);

		noiKhoiHanhComboBox = new JComboBox<String>();
		noiKhoiHanhComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		noiKhoiHanhComboBox.addItem("Nơi khởi hành");
		noiKhoiHanhComboBox.addItem("Thủ đô Hà Nội");
		noiKhoiHanhComboBox.addItem("Thành phố Hồ Chí Minh");
		noiKhoiHanhComboBox.addItem("Thành phố Đà Nẵng");
		noiKhoiHanhComboBox.setBounds(10, 10, 200, 30);
		box1.add(noiKhoiHanhComboBox);

		String[] gia = { "Giá", "Dưới 1 triệu", "1-4 triệu", "4-6 triệu", "6-10 triệu", "Trên 10 triệu" };
		giaComboBox = new JComboBox<String>(gia);
		giaComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		giaComboBox.setBounds(230, 10, 200, 30);
		box1.add(giaComboBox);

		JLabel ngayKhoiHanhLabel = new JLabel("Ngày khởi hành:");
		ngayKhoiHanhLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		ngayKhoiHanhLabel.setBounds(450, 10, 130, 30);
		box1.add(ngayKhoiHanhLabel);

		Date date;
		date = Date.valueOf(LocalDate.now());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		ngayKhoiHanh = new JDateChooser();
		ngayKhoiHanh.setBounds(580, 10, 200, 30);
		ngayKhoiHanh.setDate(date);
		ngayKhoiHanh.setDateFormatString("dd/MM/yyyy");
		ngayKhoiHanh.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		ngayKhoiHanh.setBackground(box1.getBackground());
		Calendar maxDate = Calendar.getInstance();
		Calendar minDate = Calendar.getInstance();
		maxDate.setTime(date);
		minDate.setTime(date);
		maxDate.roll(Calendar.YEAR, 1);
		minDate.roll(Calendar.YEAR, -1);
		ngayKhoiHanh.setMinSelectableDate(Date.valueOf(df.format(minDate.getTime())));
		ngayKhoiHanh.setMaxSelectableDate(Date.valueOf(df.format(maxDate.getTime())));
		box1.add(ngayKhoiHanh);

		JLabel ngayKetThucLabel = new JLabel("Ngày kết thúc:");
		ngayKetThucLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		ngayKetThucLabel.setBounds(800, 10, 130, 30);
		box1.add(ngayKetThucLabel);

		ngayKetThuc = new JDateChooser();
		ngayKetThuc.setBounds(930, 10, 200, 30);
//		ngayKetThuc.setDate(date);
		ngayKetThuc.setDateFormatString("dd/MM/yyyy");
		ngayKetThuc.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		ngayKetThuc.setBackground(box1.getBackground());
		Calendar maxDate2 = Calendar.getInstance();
		Calendar minDate2 = Calendar.getInstance();
		maxDate2.setTime(ngayKhoiHanh.getDate());
		minDate2.setTime(ngayKhoiHanh.getDate());
		maxDate2.roll(Calendar.MONTH, 1);
		ngayKetThuc.setMinSelectableDate(Date.valueOf(df.format(minDate2.getTime())));
		box1.add(ngayKetThuc);

		lamMoiButton = new JButton("Làm mới");
		lamMoiButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/refresh_15px.png")));
		lamMoiButton.setBounds(10, 60, 100, 30);
		lamMoiButton.setBackground(new Color(51, 122, 183));
		lamMoiButton.setForeground(Color.WHITE);
		lamMoiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(lamMoiButton);

		themChuyenDiButton = new JButton("Thêm");
		themChuyenDiButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/add_15px.png")));
		themChuyenDiButton.setBackground(new Color(63, 201, 213));
		themChuyenDiButton.setBounds(120, 60, 100, 30);
		themChuyenDiButton.setForeground(Color.WHITE);
		themChuyenDiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(themChuyenDiButton);

		xoaButton = new JButton("Xóa");
		xoaButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/delete_trash_15px.png")));
		xoaButton.setBounds(240, 60, 100, 30);
		xoaButton.setForeground(Color.WHITE);
		xoaButton.setBackground(new Color(51, 122, 183));
		xoaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(xoaButton);

		suaButton = new JButton("Sửa");
		suaButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/wrench_15px.png")));
		suaButton.setBounds(360, 60, 100, 30);
		suaButton.setForeground(Color.WHITE);
		suaButton.setBackground(new Color(63, 201, 213));
		suaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(suaButton);
		
		lenLichButton = new JButton("Lên lịch");
		lenLichButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/schedule_15px.png")));
		lenLichButton.setBounds(480, 60, 100, 30);
		lenLichButton.setForeground(Color.WHITE);
		lenLichButton.setBackground(new Color(51, 122, 183));
		lenLichButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(lenLichButton);
		
		huyLichButton = new JButton("Hủy lịch");
		huyLichButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/calendar_delete_15px.png")));
		huyLichButton.setBounds(600, 60, 100, 30);
		huyLichButton.setForeground(Color.WHITE);
		huyLichButton.setBackground(new Color(63, 201, 213));
		huyLichButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box1.add(huyLichButton);
		
		DefaultComboBoxModel<String> loaiChuyenModel = new DefaultComboBoxModel<String>();
		loaiChuyenComboBox = new JComboBox<String>(loaiChuyenModel);
		loaiChuyenComboBox.addItem("<Loại chuyến>");
		loaiChuyenModel.addAll(LoaiChuyenDi_DAO.getAllLoaiChuyenDi());
		loaiChuyenComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		loaiChuyenComboBox.setBounds(720, 60, 200, 30);
		box1.add(loaiChuyenComboBox);
		
		DefaultComboBoxModel<String> trangThaiModel = new DefaultComboBoxModel<String>(new String[] {"<Trạng thái>", "Đang xử lý", "Chưa bắt đầu", "Đang khởi hành", "Kết thúc"});
		trangThaiComboBox = new JComboBox<String>(trangThaiModel);
		trangThaiComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		trangThaiComboBox.setBounds(940, 60, 200, 30);
		box1.add(trangThaiComboBox);
		
		JPanel box2 = new JPanel();
		box2.setLayout(null);
		box2.setBackground(Color.WHITE);
		box2.setBounds(30, 130, 1180, 550);
		add(box2);

		JLabel titleTable = new JLabel("Danh sách chuyến đi");
		titleTable.setForeground(new Color(231, 31, 18));
		titleTable.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 20));
		titleTable.setIcon(new ImageIcon(getClass().getResource("/gui/icon/list_30px.png")));
		titleTable.setBounds(10, 0, 300, 40);
		box2.add(titleTable);

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 40, 1140, 10);
		box2.add(separator);

		int[] columnWidth = { 90, 120, 110, 60, 145, 110 };
		String[] columnTitle = { "Mã chuyến", "Ngày khởi hành", "Ngày kết thúc", "Số chỗ", "Loại chuyến đi",
				"Trạng thái" };
		tableModel = new DefaultTableModel(columnTitle, 0);
		table = new JTable(tableModel) {
			@Override
			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			}
		};
		table.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
		table.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		table.getTableHeader().setBackground(MainScreen.COLOR_MAIN);
		table.getTableHeader().setPreferredSize(new Dimension(1220, 30));
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setFillsViewportHeight(true);
		table.setShowGrid(true);
		table.setGridColor(MainScreen.BACKGROUND_COLOR);
		table.setRowHeight(30);
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String maChuyenDi = table.getValueAt(table.getSelectedRow(), 0).toString();
				ktTrangThai(maChuyenDi);
			}
		});
		for (int i = 0; i < columnWidth.length; i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setMinWidth(columnWidth[i]);
			column.setMaxWidth(columnWidth[i]);
			column.setPreferredWidth(columnWidth[i]);
		}
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBounds(10, 60, 655, 450);

		box2.add(sp);

		CT_ThamQuanPane = new JPanel();
		CT_ThamQuanPane.setLayout(null);
		CT_ThamQuanPane.setBackground(Color.WHITE);
		CT_ThamQuanPane.setPreferredSize(new Dimension(510, 800));

		themDiaDanh_Pane = new JPanel();
		themDiaDanh_Pane.setLayout(null);
		themDiaDanh_Pane.setBackground(MainScreen.SUBPANE_COLOR);
		JLabel themLabel = new JLabel("Chuyến đi này chưa có địa danh phải thêm địa danh mới lên lịch");
		themLabel.setBounds(20, 40, 470, 40);
		themLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.ITALIC, 16));
		themLabel.setForeground(Color.RED);
		themDiaDanh_Pane.add(themLabel);
		themDiaDanhButton = new JButton("Thêm");
		themDiaDanhButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		themDiaDanhButton.setBackground(MainScreen.HEADER_COLOR);
		themDiaDanhButton.setForeground(Color.WHITE);
		themDiaDanhButton.setBounds(200, 100, 100, 40);
		themDiaDanh_Pane.add(themDiaDanhButton);

		ct_ThamQuanScrollPane = new JScrollPane(CT_ThamQuanPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ct_ThamQuanScrollPane.setAutoscrolls(false);
		ct_ThamQuanScrollPane.setBounds(660, 60, 510, 450);
		ct_ThamQuanScrollPane.getVerticalScrollBar().setUnitIncrement(16);

		tenChuyenDi = new JTextArea();
		tenChuyenDi.setBounds(10, 300, 470, 80);
		tenChuyenDi.setFont(new Font(MainScreen.FONT_TEXT, Font.BOLD, 18));
		tenChuyenDi.setLineWrap(true);
		tenChuyenDi.setWrapStyleWord(true);
		tenChuyenDi.setEditable(false);
		tenChuyenDi.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		tenChuyenDi.setBorder(BorderFactory.createEmptyBorder());
		CT_ThamQuanPane.add(tenChuyenDi);

		moTa = new JTextArea();
		moTa.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		moTa.setBounds(10, 380, 470, 350);
		moTa.setLineWrap(true);
		moTa.setEditable(false);
		moTa.setWrapStyleWord(true);
		moTa.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		moTa.setBorder(BorderFactory.createEmptyBorder());
		DefaultCaret caret = (DefaultCaret) moTa.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		CT_ThamQuanPane.add(moTa);
		
		suaDiaDanhButton = new JButton("Sửa địa danh");
		suaDiaDanhButton.setBounds(10, 750, 130, 40);
		suaDiaDanhButton.setIcon(new ImageIcon(getClass().getResource("/gui/icon/wrench_15px.png")));
		suaDiaDanhButton.setForeground(Color.WHITE);
		suaDiaDanhButton.setBackground(MainScreen.HEADER_COLOR);
		CT_ThamQuanPane.add(suaDiaDanhButton);

		buttonDautrang = new JButton();
		buttonDautrang.setIcon(new ImageIcon(getClass().getResource("/gui/icon/skip_to_start_24px.png")));
		buttonDautrang.setBounds(440, 515, 40, 30);
		buttonDautrang.setBackground(MainScreen.COLOR_MAIN);
		buttonDautrang.setToolTipText("Đầu trang");
		buttonDautrang.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box2.add(buttonDautrang);

		buttonTruoc = new JButton();
		buttonTruoc.setIcon(new ImageIcon(getClass().getResource("/gui/icon/rewind_24px.png")));
		buttonTruoc.setToolTipText("Trước");
		buttonTruoc.setBounds(505, 515, 40, 30);
		buttonTruoc.setBackground(MainScreen.COLOR_MAIN);
		buttonTruoc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		box2.add(buttonTruoc);

		soTrang = new JLabel("1", JLabel.CENTER);
		soTrang.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		soTrang.setBounds(575, 515, 30, 30);
		box2.add(soTrang);

		buttonKeTiep = new JButton();
		buttonKeTiep.setToolTipText("Kế tiếp");
		buttonKeTiep.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonKeTiep.setIcon(new ImageIcon(getClass().getResource("/gui/icon/fast_forward_24px.png")));
		buttonKeTiep.setBounds(630, 515, 40, 30);
		buttonKeTiep.setBackground(MainScreen.COLOR_MAIN);
		box2.add(buttonKeTiep);

		buttonCuoiTrang = new JButton();
		buttonCuoiTrang.setToolTipText("Cuối trang");
		buttonCuoiTrang.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonCuoiTrang.setIcon(new ImageIcon(getClass().getResource("/gui/icon/end_24px.png")));
		buttonCuoiTrang.setBackground(MainScreen.COLOR_MAIN);
		buttonCuoiTrang.setBounds(690, 515, 40, 30);
		box2.add(buttonCuoiTrang);
		
		
		themVaoBangChuyenDi();

		slideShowAnh = new SlideShowPane(CT_ThamQuan_DAO.getAnhChuyenDiTheoChuyenDi(dsChuyenDi.get(0).getMaChuyenDi()),
				new Dimension(490, 300), 2000);
		slideShowAnh.setBounds(0, 0, 490, 300);
		CT_ThamQuanPane.add(slideShowAnh);
		box2.add(ct_ThamQuanScrollPane);

		setChiTiet(dsChuyenDi.get(0).getMaChuyenDi());

		noiKhoiHanhComboBox.addActionListener(this);
		giaComboBox.addActionListener(this);
		lamMoiButton.addActionListener(this);
		themChuyenDiButton.addActionListener(this);
		xoaButton.addActionListener(this);
		suaButton.addActionListener(this);
		loaiChuyenComboBox.addActionListener(this);
		trangThaiComboBox.addActionListener(this);
		lenLichButton.addActionListener(this);
		suaDiaDanhButton.addActionListener(this);
		huyLichButton.addActionListener(this);
		themDiaDanhButton.addActionListener(this);
		ngayKetThuc.addPropertyChangeListener(this);
		buttonKeTiep.addActionListener(this);
		buttonDautrang.addActionListener(this);
		buttonCuoiTrang.addActionListener(this);
		buttonTruoc.addActionListener(this);

		TableCellRenderer tableCellRenderer = new TableCellRenderer() {
			JLabel lbLabel = new JLabel();

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				lbLabel.setOpaque(true);
				lbLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
				lbLabel.setForeground(Color.WHITE);
				lbLabel.setHorizontalAlignment(JLabel.HORIZONTAL);
				if (value.equals("Đang khởi hành")) {
					lbLabel.setBackground(new Color(218, 127, 110));
					lbLabel.setText(value.toString());
				} else if (value.equals("Chưa bắt đầu")) {
					lbLabel.setBackground(new Color(67, 198, 209));
					lbLabel.setText(value.toString());
				} else if (value.equals("Đang xử lý")) {
					lbLabel.setBackground(new Color(227, 145, 65));
					lbLabel.setText(value.toString());
				} else {
					lbLabel.setBackground(new Color(240, 212, 107));
					lbLabel.setText(value.toString());
				}
				if (isSelected) {
					lbLabel.setBackground(table.getSelectionBackground());
				}
				return lbLabel;
			}
		};
		table.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int tongTrang = 1;
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String noiKhoiHanh = noiKhoiHanhComboBox.getSelectedItem().toString();
		if (noiKhoiHanh.equals("Nơi khởi hành"))
			noiKhoiHanh = "";
		int gia = giaComboBox.getSelectedIndex();
		double min = 0, max = 0;
		if (gia == 1) {
			min = 0;
			max = 1000000;
		} else if (gia == 2) {
			min = 1000000;
			max = 4000000;
		} else if (gia == 3) {
			min = 4000000;
			max = 6000000;
		} else if (gia == 4) {
			min = 6000000;
			max = 10000000;
		} else if (gia == 5) {
			min = 10000000;
			max = 1000000000;
		}
		String loaiChuyenDi = loaiChuyenComboBox.getSelectedItem().toString();
		if(loaiChuyenDi.equals("<Loại chuyến>"))
			loaiChuyenDi = "";
		else
			loaiChuyenDi = LoaiChuyenDi_DAO.getLoaiChuyenDiTheoTen(loaiChuyenDi).getMaLoai();
		String trangThaiValues = trangThaiComboBox.getSelectedItem().toString();
		if(trangThaiValues.equals("<Trạng thái>"))
			trangThaiValues = "";
		int soLuongChuyenDi = 0;
		Date ngayKH = null;
		Date ngayKT = null;
		if (ngayKhoiHanh.getDate() != null && ngayKetThuc.getDate() != null) {
			ngayKH = Date.valueOf(sdf2.format(ngayKhoiHanh.getDate()));
			ngayKT = Date.valueOf(sdf2.format(ngayKetThuc.getDate()));
			soLuongChuyenDi = cd_DAO.soLuongChuyenDi(noiKhoiHanh, min, max, ngayKH, ngayKT, loaiChuyenDi, trangThaiValues);

		} else
			soLuongChuyenDi = cd_DAO.soLuongChuyenDi(noiKhoiHanh, min, max, loaiChuyenDi, trangThaiValues);
		tongTrang = soLuongChuyenDi % 20 == 0 ? soLuongChuyenDi / 20 : (soLuongChuyenDi / 20) + 1;
		Object o = e.getSource();
		if (o.equals(noiKhoiHanhComboBox) || o.equals(giaComboBox) || o.equals(loaiChuyenComboBox) || o.equals(trangThaiComboBox)) {
			tableModel.setRowCount(0);
			soTrang.setText("1");
			table.clearSelection();
			themVaoBangChuyenDi();
		} else if (o.equals(lamMoiButton)) {
			noiKhoiHanhComboBox.setSelectedIndex(0);
			giaComboBox.setSelectedIndex(0);
			ngayKhoiHanh.setDate(Date.valueOf(LocalDate.now()));
			ngayKetThuc.setCalendar(null);
		} else if (o.equals(themChuyenDiButton)) {
			DialogThemChuyenDi dialogThemChuyenDi = new DialogThemChuyenDi();
			dialogThemChuyenDi.setVisible(true);
			dialogThemChuyenDi.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					tableModel.setRowCount(0);
					themVaoBangChuyenDi();
				}
			});
		} else if (o.equals(buttonKeTiep)) {
			int page = Integer.parseInt(soTrang.getText()) + 1;
			if (page <= tongTrang) {
				tableModel.getDataVector().removeAllElements();
				soTrang.setText(Integer.toString(page));
				table.clearSelection();
				themVaoBangChuyenDi();
			}
		} else if (o.equals(buttonTruoc)) {
			int page = Integer.parseInt(soTrang.getText()) - 1;
			if (page > 0) {
				tableModel.getDataVector().removeAllElements();
				soTrang.setText(Integer.toString(page));
				table.clearSelection();
				themVaoBangChuyenDi();
			}
		} else if (o.equals(buttonCuoiTrang)) {
			int page = Integer.parseInt(soTrang.getText());
			if (page != tongTrang) {
				tableModel.getDataVector().removeAllElements();
				soTrang.setText(Integer.toString(tongTrang));
				table.clearSelection();
				themVaoBangChuyenDi();
			}
		} else if (o.equals(buttonDautrang)) {
			int page = Integer.parseInt(soTrang.getText());
			if (page != 1) {
				tableModel.getDataVector().removeAllElements();
				soTrang.setText("1");
				table.clearSelection();
				themVaoBangChuyenDi();
			}
		} else if (o.equals(themDiaDanhButton)) {
			createDialogDiaDanh();
		}else if (o.equals(xoaButton)) {
			if(table.getSelectedRow() != -1) {
				String maChuyenDi = table.getValueAt(table.getSelectedRow(), 0).toString();
				String trangThai = table.getValueAt(table.getSelectedRow(), 5).toString();
				if(trangThai.equals("Chưa bắt đầu")) {
					if(JOptionPane.showConfirmDialog(null, "Chuyến đi đã lên lịch, bạn có đồng ý xóa?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						if(cd_DAO.deleteChuyenDi(maChuyenDi)) {
							JOptionPane.showMessageDialog(this, "Xóa thành công!");
							tableModel.setRowCount(0);
							themVaoBangChuyenDi();
						}else
							JOptionPane.showMessageDialog(this, "Xóa thất bại!");
					}
				}else if(trangThai.equals("Đang khởi hành")) {
					JOptionPane.showMessageDialog(this, "Chuyến đi đã bắt đầu không thể xóa!");
				}else {
					if(JOptionPane.showConfirmDialog(null, "Bạn có đồng ý xóa?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						if(cd_DAO.deleteChuyenDi(maChuyenDi)) {
							JOptionPane.showMessageDialog(this, "Xóa thành công!");
							tableModel.setRowCount(0);
							themVaoBangChuyenDi();
						}else
							JOptionPane.showMessageDialog(this, "Xóa thất bại!");
					}
				}
			} else
				JOptionPane.showMessageDialog(this, "Chưa chọn chuyến đi để xóa!");
		}else if(o.equals(lenLichButton)) {
			if(table.getSelectedRow() != -1) {
				String trangThai = table.getValueAt(table.getSelectedRow(), 5).toString();
				String maChuyenDi = table.getValueAt(table.getSelectedRow(), 0).toString();
				if(trangThai.equals("Đang xử lý")) {
					if(CT_ThamQuan_DAO.soLuongDiaDanhCuaChuyenDi(maChuyenDi) >= 2) {
						if(cd_DAO.updateTrangThaiChuyenDi(maChuyenDi, "Chưa bắt đầu")) {
							JOptionPane.showMessageDialog(this, "Lên lịch thành công!");
							tableModel.getDataVector().removeAllElements();
							themVaoBangChuyenDi();
						}else
							JOptionPane.showMessageDialog(this, "Lên lịch thất bại!");
					}else
						JOptionPane.showMessageDialog(this, "Chuyến đi chưa đủ địa danh để lên lịch!");
				}else
					JOptionPane.showMessageDialog(this, "Chuyến đi đang ở trạng thái " + "\"" + trangThai +"\"");
			}else
				JOptionPane.showMessageDialog(this, "Chưa chọn chuyến đi!");
		}else if (o.equals(huyLichButton)) {
			if(table.getSelectedRow() != -1) {
				String trangThai = table.getValueAt(table.getSelectedRow(), 5).toString();
				String maChuyenDi = table.getValueAt(table.getSelectedRow(), 0).toString();
				if(trangThai.equals("Chưa bắt đầu")) {
					if(cd_DAO.updateTrangThaiChuyenDi(maChuyenDi, "Đang xử lý")) {
						JOptionPane.showMessageDialog(this, "Hủy lịch thành công!");
						tableModel.getDataVector().removeAllElements();
						themVaoBangChuyenDi();
					}else
						JOptionPane.showMessageDialog(this, "Hủy lịch thất bại!");
				}else if(trangThai.equals("Đang khởi hành")){
					JOptionPane.showMessageDialog(this, "Chuyến đi đã bắt đầu không thể hủy!");
				}else if(trangThai.equals("Đang xử lý")) {
					JOptionPane.showMessageDialog(this, "Chuyến đi chưa được lên lịch!");
				}else if(trangThai.equals("Kết thúc")) {
					JOptionPane.showMessageDialog(this, "Chuyến đi đã kết thúc!");
				}
			}
		}else if (o.equals(suaButton)) {
			if(table.getSelectedRow() != -1) {
				String trangThai = table.getValueAt(table.getSelectedRow(), 5).toString();
				if(trangThai.equals("Chưa bắt đầu") || trangThai.equals("Đang xử lý")) {
					String maChuyenDi = table.getValueAt(table.getSelectedRow(), 0).toString();
					DialogSuaChuyenDi dialogSuaChuyenDi = new DialogSuaChuyenDi(maChuyenDi);
					dialogSuaChuyenDi.setVisible(true);
					dialogSuaChuyenDi.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							tableModel.setRowCount(0);
							themVaoBangChuyenDi();
						}
					});
				}else if (trangThai.equals("Đang khởi hành")) {
					JOptionPane.showMessageDialog(this, "Chuyến đi đã bắt đầu không thể sửa");
				}
				else {
					JOptionPane.showMessageDialog(this, "Chuyến đã kết thúc");
				}
			}
		}else if (o.equals(suaDiaDanhButton)) {
			String trangThai = table.getValueAt(table.getSelectedRow(), 5).toString();
			if(trangThai.equals("Chưa bắt đầu") || trangThai.equals("Đang xử lý")) {
				createDialogDiaDanh();
			}
			else if(trangThai.equals("Đang khởi hành")) {
				JOptionPane.showMessageDialog(this, "Chuyến đi đã bắt đầu không thể sửa");
			}
			else {
				JOptionPane.showMessageDialog(this, "Chuyến đi đã kết thúc");
			}
		}
	}

	public void slideShowStop() {
		slideShowAnh.stop();
	}

	public void slideShowStart() {
		slideShowAnh.start();
	}

	private void setChiTiet(String maChuyenDi) {
		String tenDiaDanh = "";
		for (String string : CT_ThamQuan_DAO.getTenDiaTheoChuyenDi(maChuyenDi)) {
			tenDiaDanh += string + " - ";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("#,##0 VND");
		slideShowAnh.setListToolTip(CT_ThamQuan_DAO.getTenDiaTheoChuyenDi(maChuyenDi));
		tenChuyenDi.setText(tenDiaDanh + "TOP TRAVEL :)");
		slideShowAnh.setListImg(CT_ThamQuan_DAO.getAnhChuyenDiTheoChuyenDi(maChuyenDi));
		slideShowAnh.setImageNumber(0);
		slideShowAnh.setImageSize(slideShowAnh.getImageNumber());
		slideShowAnh.stop();
		slideShowAnh.start();
		moTa.setText(ChuyenDi_DAO.getChuyenDi(maChuyenDi).getMoTa() + "\r\n\r\nKhởi hành:\t"
				+ sdf.format(ChuyenDi_DAO.getChuyenDi(maChuyenDi).getNgayKhoiHanh()) + "\r\nKết thúc:\t"
				+ sdf.format(ChuyenDi_DAO.getChuyenDi(maChuyenDi).getNgayKetThuc()) + "\r\nNơi khởi hành:\t"
				+ ChuyenDi_DAO.getChuyenDi(maChuyenDi).getNoiKhoiHanh().getTinhThanh() + "\r\nGiá:\t"
				+ df.format(ChuyenDi_DAO.getChuyenDi(maChuyenDi).getGiaChuyenDi()) + "\r\nSố chỗ:\t"
				+ ChuyenDi_DAO.getChuyenDi(maChuyenDi).getSoChoConNhan() + "/" + ChuyenDi_DAO.getChuyenDi(maChuyenDi).getSoCho());
		ct_ThamQuanScrollPane.getVerticalScrollBar().setValue(0);
	}

	private void ktTrangThai(String maChuyenDi) {
		if (CT_ThamQuan_DAO.soLuongDiaDanhCuaChuyenDi(maChuyenDi) >= 2) {
			ct_ThamQuanScrollPane.setViewportView(CT_ThamQuanPane);
			setChiTiet(maChuyenDi);
		} else
			ct_ThamQuanScrollPane.setViewportView(themDiaDanh_Pane);
	}

	private void themVaoBangChuyenDi() {
		int page = Integer.parseInt(soTrang.getText());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String noiKhoiHanh = noiKhoiHanhComboBox.getSelectedItem().toString();
		if (noiKhoiHanh.equals("Nơi khởi hành"))
			noiKhoiHanh = "";
		int gia = giaComboBox.getSelectedIndex();
		double min = 0, max = 0;
		if (gia == 1) {
			min = 0;
			max = 1000000;
		} else if (gia == 2) {
			min = 1000000;
			max = 4000000;
		} else if (gia == 3) {
			min = 4000000;
			max = 6000000;
		} else if (gia == 4) {
			min = 6000000;
			max = 10000000;
		} else if (gia == 5) {
			min = 10000000;
			max = 1000000000;
		}
		String loaiChuyenDi = loaiChuyenComboBox.getSelectedItem().toString();
		if(loaiChuyenDi.equals("<Loại chuyến>"))
			loaiChuyenDi = "";
		else
			loaiChuyenDi = LoaiChuyenDi_DAO.getLoaiChuyenDiTheoTen(loaiChuyenDi).getMaLoai();
		String trangThai = trangThaiComboBox.getSelectedItem().toString();
		if(trangThai.equals("<Trạng thái>"))
			trangThai = "";
		Date ngayKH = null;
		Date ngayKT = null;
		if (ngayKhoiHanh.getDate() != null && ngayKetThuc.getDate() != null) {
			ngayKH = Date.valueOf(sdf2.format(ngayKhoiHanh.getDate()));
			ngayKT = Date.valueOf(sdf2.format(ngayKetThuc.getDate()));
			dsChuyenDi = cd_DAO.themVaoBangChuyenDi(page - 1, noiKhoiHanh, min, max, ngayKH, ngayKT, loaiChuyenDi, trangThai);
		} else
			dsChuyenDi = cd_DAO.themVaoBangChuyenDi(page - 1, noiKhoiHanh, min, max, loaiChuyenDi, trangThai);
		for (ChuyenDi chuyenDi : dsChuyenDi) {
			tableModel.addRow(new Object[] { chuyenDi.getMaChuyenDi(), sdf.format(chuyenDi.getNgayKhoiHanh()),
					sdf.format(chuyenDi.getNgayKetThuc()), chuyenDi.getSoChoConNhan() + "/" + chuyenDi.getSoCho(),
					chuyenDi.getLoaiChuyenDi().getTenLoai(), chuyenDi.getTrangThai() });
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		tableModel.setRowCount(0);
		soTrang.setText("1");
		table.clearSelection();
		themVaoBangChuyenDi();
	}

	private void createDialogDiaDanh() {
		DiaDanh_Pane diaDanh_Pane = new DiaDanh_Pane();
		JDialog themDiaDanh = new JDialog();
		themDiaDanh.setSize(1300, 500);
		themDiaDanh.setLocationRelativeTo(null);
		themDiaDanh.setModal(true);
		JPanel main = new JPanel();
		main.setBackground(MainScreen.BACKGROUND_COLOR);
		main.setLayout(null);
		themDiaDanh.add(main);
		diaDanh_Pane.getTimKiemTextField().setBounds(10, 10, 200, 30);
		main.add(diaDanh_Pane.getTimKiemTextField());
		diaDanh_Pane.getThuocTinhComboBox().setBounds(230, 10, 200, 30);
		main.add(diaDanh_Pane.getThuocTinhComboBox());
		diaDanh_Pane.getThemDiaDanhButton().setBounds(480, 10, 90, 30);
		main.add(diaDanh_Pane.getThemDiaDanhButton());
		int[] columnWidth = { 90, 200, 180, 70 };
		for (int i = 0; i < columnWidth.length; i++) {
			TableColumn column = diaDanh_Pane.getTable().getColumnModel().getColumn(i);
			column.setMinWidth(columnWidth[i]);
			column.setMaxWidth(columnWidth[i]);
			column.setPreferredWidth(columnWidth[i]);
		}
		diaDanh_Pane.getSpTableDiaDanh().setBounds(10, 50, 570, 320);
		main.add(diaDanh_Pane.getSpTableDiaDanh());
		diaDanh_Pane.getButtonDauTrang().setBounds(140, 380, 40, 30);
		main.add(diaDanh_Pane.getButtonDauTrang());
		diaDanh_Pane.getButtonTruoc().setBounds(200, 380, 40, 30);
		main.add(diaDanh_Pane.getButtonTruoc());
		diaDanh_Pane.getSoTrang().setBounds(260, 380, 40, 30);
		main.add(diaDanh_Pane.getSoTrang());
		diaDanh_Pane.getButtonKeTiep().setBounds(320, 380, 40, 30);
		main.add(diaDanh_Pane.getButtonKeTiep());
		diaDanh_Pane.getButtonCuoiTrang().setBounds(380, 380, 40, 30);
		main.add(diaDanh_Pane.getButtonCuoiTrang());

		JButton button = new JButton();
		button.setIcon(new ImageIcon(getClass().getResource("/gui/icon/double_right_24px.png")));
		button.setBounds(610, 200, 70, 40);

		main.add(button);

		String[] columnTitle = { "ID", "Tên địa danh", "Tỉnh" };
		DefaultTableModel tableDiaDanhModel = new DefaultTableModel(columnTitle, 0);
		String maChuyenDi = table.getValueAt(table.getSelectedRow(), 0).toString();
		ArrayList<DiaDanh> dsDiaDanh = CT_ThamQuan_DAO.getDiaDanhTheoChuyenDi(maChuyenDi);
		if(dsDiaDanh != null) {
			for (DiaDanh diaDanh : dsDiaDanh) {
				tableDiaDanhModel.addRow(new Object[] {diaDanh.getMaDiaDanh(), diaDanh.getTenDiaDanh(), diaDanh.getThuocTinh()});
			}
		}
		JTable tableDiaDanh = new JTable(tableDiaDanhModel) {
			@Override
			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			}
		};
		tableDiaDanh.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
		tableDiaDanh.getTableHeader().setBackground(MainScreen.COLOR_MAIN);
		tableDiaDanh.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		tableDiaDanh.getTableHeader().setPreferredSize(new Dimension(1220, 30));
		tableDiaDanh.getTableHeader().setResizingAllowed(false);
		tableDiaDanh.getTableHeader().setReorderingAllowed(false);
		tableDiaDanh.setFillsViewportHeight(true);
		tableDiaDanh.setShowGrid(true);
		tableDiaDanh.setGridColor(MainScreen.BACKGROUND_COLOR);
		tableDiaDanh.setRowHeight(30);
		tableDiaDanh.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JScrollPane huongDanVienJScrollPane = new JScrollPane(tableDiaDanh, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		huongDanVienJScrollPane.setBounds(700, 50, 570, 320);
		main.add(huongDanVienJScrollPane);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (diaDanh_Pane.getTable().getSelectedRow() != -1) {
					String maDiaDanh = diaDanh_Pane.getTable().getValueAt(diaDanh_Pane.getTable().getSelectedRow(), 0)
							.toString();
					String tenDiaDanh = diaDanh_Pane.getTable().getValueAt(diaDanh_Pane.getTable().getSelectedRow(), 1)
							.toString();
					String thuocTinh = diaDanh_Pane.getTable().getValueAt(diaDanh_Pane.getTable().getSelectedRow(), 2)
							.toString();

					for (int i = 0; i < tableDiaDanhModel.getRowCount(); i++) {
						if (maDiaDanh.equals(tableDiaDanh.getValueAt(i, 0).toString())) {
							JOptionPane.showMessageDialog(null, "Đã có địa danh này!");
							return;
						}
					}
					tableDiaDanhModel.addRow(new Object[] { maDiaDanh, tenDiaDanh, thuocTinh });
				}
			}
		});

		JButton xoa = new JButton();
		xoa.setIcon(new ImageIcon(getClass().getResource("/gui/icon/delete_24px.png")));
		xoa.setBounds(610, 250, 70, 40);
		xoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableDiaDanh.getSelectedRow() != -1) {
					tableDiaDanhModel.removeRow(tableDiaDanh.getSelectedRow());
				}

			}
		});
		main.add(xoa);
		JButton them = new JButton("Lưu");
		them.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		them.setBounds(1150, 420, 100, 40);
		them.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tableModel.getRowCount() != 0) {
					String maChuyenDi = table.getValueAt(table.getSelectedRow(), 0).toString();
					CT_ThamQuan_DAO.deleteCT_ThamQuan(maChuyenDi);
					for (int i = 0; i < tableDiaDanhModel.getRowCount(); i++) {
						String maDiaDanh = tableDiaDanhModel.getValueAt(i, 0).toString();
						CT_ThamQuan_DAO.insertCT_ThamQuan(maDiaDanh, maChuyenDi);
					}
					JOptionPane.showMessageDialog(null, "Lưu thành công!");
					themDiaDanh.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Bạn phải thêm địa danh!");
				}
			}
		});
		main.add(them);
		themDiaDanh.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				tableModel.getDataVector().removeAllElements();
				themVaoBangChuyenDi();
			}
		});
		themDiaDanh.setVisible(true);
	}
}
