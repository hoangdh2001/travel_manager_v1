package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import dao.CT_ThamQuan_DAO;
import entity.CT_ThamQuan;
import entity.DiaDanh;

public class CellRenderer extends JPanel implements ListCellRenderer<CT_ThamQuan> {

	private JLabel img = new JLabel();
	private JTextArea Name = new JTextArea();
	private JLabel trangThai = new JLabel();
	private JLabel gia = new JLabel();
	private JLabel trangThaiVe = new JLabel();
	private JLabel soCho = new JLabel();
	private JLabel ngayDi = new JLabel();
	private JLabel noiKhoiHanh = new JLabel();
	private JLabel soNgay = new JLabel();
	private JPanel panelIcon;

	public CellRenderer() {
		setPreferredSize(new Dimension(500, 100));
		setLayout(null);
		
		Name.setBounds(270, 10, 500, 50);
		add(Name);
		gia.setBounds(270, 60, 200, 30);
		add(gia);
		trangThai.setBounds(270, 90, 100, 20);
		add(trangThai);
		soCho.setBounds(500, 90, 200, 20);
		add(soCho);
		ngayDi.setBounds(270, 110, 200, 20);
		add(ngayDi);
		soNgay.setBounds(500, 110, 200, 20);
		add(soNgay);
		noiKhoiHanh.setBounds(270, 130, 200, 20);
		add(noiKhoiHanh);
		trangThaiVe.setBounds(270, 150, 200, 20);
		add(trangThaiVe);
		

		panelIcon = new JPanel();
		panelIcon.setBounds(10, 5, 250, 180);
		panelIcon.add(img);
		
		add(panelIcon);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends CT_ThamQuan> list,
			CT_ThamQuan ct_ThamQuan, int index, boolean isSelected, boolean cellHasFocus) {

		Image image =CT_ThamQuan_DAO.getAnhChuyenDiTheoChuyenDi(ct_ThamQuan.getChuyenDi().getMaChuyenDi()).get(0).getScaledInstance(250, 180, Image.SCALE_SMOOTH);
		img.setIcon(new ImageIcon(image));
		String tenDiaDanh = "";
		for (DiaDanh diaDanh : ct_ThamQuan.getDsDiaDanh()) {
			tenDiaDanh += diaDanh.getTenDiaDanh() + " - ";
		}
		Name.setText(tenDiaDanh + "TOP TRAVEL :))");
		if(ct_ThamQuan.getChuyenDi().getTrangThai().equals("Chưa bắt đầu"))
			trangThai.setForeground(MainScreen.HEADER_COLOR);
		else if(ct_ThamQuan.getChuyenDi().equals("Đang xử lý")) {
			trangThai.setForeground(new Color(218, 127, 110));
		}
		trangThai.setText(ct_ThamQuan.getChuyenDi().getTrangThai());
		DecimalFormat df = new DecimalFormat("#,##0 VND");
		gia.setText(df.format(ct_ThamQuan.getChuyenDi().getGiaChuyenDi()));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(ct_ThamQuan.getChuyenDi().getSoChoConNhan() == 0) {
			trangThaiVe.setText("Hết vé");
		}
		else 
			trangThaiVe.setText("");
		soCho.setText("Số chỗ: " + ct_ThamQuan.getChuyenDi().getSoChoConNhan() + "/" + ct_ThamQuan.getChuyenDi().getSoCho());
		Date ngayKhoiHanh = ct_ThamQuan.getChuyenDi().getNgayKhoiHanh();
		Date ngayKetThuc = ct_ThamQuan.getChuyenDi().getNgayKetThuc();
		ngayDi.setText("Ngày đi: " + sdf.format(ngayKhoiHanh));
		noiKhoiHanh.setText("Nơi khởi hành: " + ct_ThamQuan.getChuyenDi().getNoiKhoiHanh().getTinhThanh());
		long startDate = ngayKhoiHanh.getTime();
		long endDate = ngayKetThuc.getTime();
		long tmp = Math.abs(startDate - endDate);
		
		long kq = tmp / (24 * 60 * 60 * 1000);
		soNgay.setText("Số ngày: " + kq);
		
		
		trangThaiVe.setForeground(Color.ORANGE);
		trangThaiVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gia.setForeground(Color.blue);
		Name.setBorder(BorderFactory.createEmptyBorder());
		gia.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		trangThaiVe.setForeground(new Color(227, 145, 65));
		Name.setOpaque(true);
		Name.setLineWrap(true);
		Name.setWrapStyleWord(true);
		
		if (isSelected) {
			Name.setBackground(list.getSelectionBackground());
			Name.setForeground(list.getSelectionForeground());
			setBackground(list.getSelectionBackground());
			panelIcon.setBackground(list.getSelectionBackground());
		} else { 
			Name.setBackground(list.getBackground());
			Name.setForeground(Color.BLACK);
			setBackground(list.getBackground());
			panelIcon.setBackground(list.getBackground());
		}
		return this;
	}
}
