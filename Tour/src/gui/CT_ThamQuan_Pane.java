package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dohuyhoang.animation.slideshow.SlideShowPane;
import entity.CT_ThamQuan;
import entity.DiaDanh;
import entity.KhachHang;

public class CT_ThamQuan_Pane extends JPanel implements ActionListener {
	private JButton back;
	private JButton datTourButton;
	private CT_ThamQuan ct_ThamQuan;
	private KhachHang khachHang;

	/**
	 * @return the back
	 */
	public JButton getBack() {
		return back;
	}

	public CT_ThamQuan_Pane(CT_ThamQuan ct_ThamQuan, KhachHang khachHang) {
		// TODO Auto-generated constructor stub
		this.khachHang = khachHang;
		this.ct_ThamQuan = ct_ThamQuan;
		setLayout(null);
		setBackground(Color.WHITE);
		buidCT_ThamQuan();

	}

	private void buidCT_ThamQuan() {
		ArrayList<Image> listImg = new ArrayList<Image>();
		String tenDiaDanh = "";
		String tinhThanh = "";
		for (DiaDanh diaDanh : ct_ThamQuan.getDsDiaDanh()) {
			listImg.add(new ImageIcon(diaDanh.getAnhDiaDanh()).getImage());
			tenDiaDanh += diaDanh.getTenDiaDanh() + " - ";
			if (diaDanh.getThuocTinh().contains("Tỉnh")) {
				tinhThanh += diaDanh.getThuocTinh() + ",";
			} else {
				tinhThanh += diaDanh.getThuocTinh() + ", ";
			}
		}
		SlideShowPane slideShowPane = new SlideShowPane(listImg, new Dimension(400, 300), 2000);
		slideShowPane.setBounds(0, 0, 800, 300);
		slideShowPane.getPic().setBounds(200, 0, 400, 300);
		slideShowPane.setBackground(new Color(0, 0, 0, 0.9f));
		slideShowPane.start();
		add(slideShowPane);
		JTextArea dsDiaDanh = new JTextArea();
		dsDiaDanh.setText(tenDiaDanh + "TOP TRAVEL :))");
		dsDiaDanh.setEditable(false);
		dsDiaDanh.setFont(new Font(MainScreen.FONT_TEXT, Font.BOLD, 16));
		dsDiaDanh.setLineWrap(true);
		dsDiaDanh.setWrapStyleWord(true);
		dsDiaDanh.setBounds(20, 310, 750, 50);
		add(dsDiaDanh);

		DecimalFormat df = new DecimalFormat("#,##0 VND");
		JTextArea mota = new JTextArea();
		mota.setText(ct_ThamQuan.getChuyenDi().getMoTa() + "\r\n\r\nKhởi hành:\t"
				+ ct_ThamQuan.getChuyenDi().getNgayKhoiHanh() + "\r\nKết thúc:\t"
				+ ct_ThamQuan.getChuyenDi().getNgayKetThuc() + "\r\nNơi khởi hành:\t"
				+ ct_ThamQuan.getChuyenDi().getNoiKhoiHanh().getTinhThanh() + "\r\nSố chỗ:\t\t"
				+ ct_ThamQuan.getChuyenDi().getSoChoConNhan() + "/" + ct_ThamQuan.getChuyenDi().getSoCho()
				+ "\r\nGiá:\t\t" + df.format(ct_ThamQuan.getChuyenDi().getGiaChuyenDi()) + "\r\nCác tỉnh đi qua:"
				+ tinhThanh);
		mota.setEditable(false);
		mota.setLineWrap(true);
		mota.setWrapStyleWord(true);
		mota.setBounds(20, 360, 750, 300);
		add(mota);

		back = new JButton("Trở về");
		back.setBounds(20, 725, 80, 30);
		add(back);

		datTourButton = new JButton("Đặt tour");
		datTourButton.setBounds(690, 725, 80, 30);
		add(datTourButton);
		datTourButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(datTourButton)) {
			if (ct_ThamQuan.getChuyenDi().getSoChoConNhan() != 0) {
				DialogDatTour dialogDatTour = new DialogDatTour(ct_ThamQuan, khachHang);
				dialogDatTour.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Đã hết chỗ!");
			}
		}
	}
}
