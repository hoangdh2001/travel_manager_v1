package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import dao.DiaChi_DAO;
import dao.DonDatVe_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.CT_ThamQuan;
import entity.DonDatVe;
import entity.KhachHang;

public class DialogDatTour extends JDialog implements ActionListener {
	private CT_ThamQuan ct_ThamQuan;
	private DefaultListModel<CT_ThamQuan> model;
	private JList<CT_ThamQuan> list;
	private JScrollPane scrollPane;
	private KhachHang khachHang;
	private JTextField tenKhachHang;
	private JCheckBox gioiTinhCb;
	private JDateChooser ngaySinhDateChooser;
	private JComboBox<String> tinhThanhComboBox;
	private JComboBox<String> quanHuyenComboBox;
	private JComboBox<String> phuongXaComboBox;
	private JTextField soDienThoai;
	private JSpinner soCho;
	private DefaultComboBoxModel<String> quanHuyenModel;
	private DefaultComboBoxModel<String> tinhThanhModel;
	private DefaultComboBoxModel<String> phuongXaModel;
	private double giaChuyenDi;
	private JLabel giaLabel;
	private JButton datTourButton;
	private JButton lamMoiButton;
	private JButton huyButton;
	private JTextField email;

	public DialogDatTour(CT_ThamQuan ct_ThamQuan, KhachHang khachHang) {
		this.ct_ThamQuan = ct_ThamQuan;
		this.khachHang = khachHang;
		setModal(true);
		setSize(800, 500);
		setLayout(null);
		setLocationRelativeTo(null);
		buidDialogDatTour();
	}

	private void buidDialogDatTour() {

		model = new DefaultListModel<CT_ThamQuan>();
		list = new JList<CT_ThamQuan>(model);
		model.addElement(ct_ThamQuan);
		list.setCellRenderer(new CellRenderer());
		list.setFixedCellHeight(200);
		list.setSelectionBackground(Color.LIGHT_GRAY);
		scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 800, 200);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane);

		JLabel tenKhachHangLabel = new JLabel("Khách hàng: ");
		tenKhachHangLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tenKhachHangLabel.setBounds(5, 200, 100, 30);
		add(tenKhachHangLabel);

		tenKhachHang = new JTextField();
		tenKhachHang.setBounds(100, 205, 200, 20);
		tenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tenKhachHang.setEditable(false);
		tenKhachHang.setBorder(BorderFactory.createEmptyBorder());
		tenKhachHang.setToolTipText("Click 2 lần để sửa");
		tenKhachHang.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					tenKhachHang.setEditable(true);
					tenKhachHang.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
			};
		});
		tenKhachHang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				tenKhachHang.setEditable(false);
				tenKhachHang.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		add(tenKhachHang);

		gioiTinhCb = new JCheckBox("Nữ");
		gioiTinhCb.setFont(new Font("Tahoma", Font.PLAIN, 13));
		gioiTinhCb.setBounds(320, 205, 100, 30);
		add(gioiTinhCb);

		JLabel ngaySinhLabel = new JLabel("Ngày sinh:");
		ngaySinhLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ngaySinhLabel.setBounds(420, 205, 100, 30);
		add(ngaySinhLabel);

		ngaySinhDateChooser = new JDateChooser();
		ngaySinhDateChooser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ngaySinhDateChooser.setBounds(520, 205, 200, 20);
		ngaySinhDateChooser.setDateFormatString("dd/MM/yyyy");
		add(ngaySinhDateChooser);

		JLabel diaChiLabel = new JLabel("Địa chỉ:");
		diaChiLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		diaChiLabel.setBounds(5, 240, 100, 30);
		add(diaChiLabel);

		tinhThanhModel = new DefaultComboBoxModel<String>();
		tinhThanhComboBox = new JComboBox<String>(tinhThanhModel);
		tinhThanhModel.addAll(DiaChi_DAO.getAllTinh());
		tinhThanhComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tinhThanhComboBox.setBounds(100, 245, 200, 20);
		tinhThanhComboBox.setEditable(false);
		add(tinhThanhComboBox);

		quanHuyenModel = new DefaultComboBoxModel<String>();
		quanHuyenComboBox = new JComboBox<String>(quanHuyenModel);
		quanHuyenComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		quanHuyenComboBox.setBounds(310, 245, 200, 20);
		quanHuyenComboBox.setEditable(false);
		add(quanHuyenComboBox);

		phuongXaModel = new DefaultComboBoxModel<String>();
		phuongXaComboBox = new JComboBox<String>(phuongXaModel);
		phuongXaComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		phuongXaComboBox.setBounds(520, 245, 200, 20);
		phuongXaComboBox.setEditable(false);
		add(phuongXaComboBox);

		JLabel soDienThoaiLabel = new JLabel("Số điện thoại:");
		soDienThoaiLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		soDienThoaiLabel.setBounds(5, 280, 100, 30);
		add(soDienThoaiLabel);

		soDienThoai = new JTextField();
		soDienThoai.setBounds(100, 285, 150, 20);
		soDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		soDienThoai.setEditable(false);
		soDienThoai.setBorder(BorderFactory.createEmptyBorder());
		soDienThoai.setToolTipText("Click 2 lần để sửa");
		soDienThoai.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					soDienThoai.setEditable(true);
					soDienThoai.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
			};
		});
		soDienThoai.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				soDienThoai.setEditable(false);
				soDienThoai.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		add(soDienThoai);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailLabel.setBounds(260, 280, 60, 30);
		add(emailLabel);

		email = new JTextField();
		email.setBounds(310, 285, 200, 20);
		email.setFont(new Font("Tahoma", Font.PLAIN, 13));
		email.setEditable(false);
		email.setBorder(BorderFactory.createEmptyBorder());
		email.setToolTipText("Click 2 lần để sửa");
		email.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					email.setEditable(true);
					email.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
			};
		});
		email.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				email.setEditable(false);
				email.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		add(email);

		JLabel soChoLabel = new JLabel("Số chỗ:");
		soChoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		soChoLabel.setBounds(5, 320, 100, 30);
		add(soChoLabel);

		giaChuyenDi = ct_ThamQuan.getChuyenDi().getGiaChuyenDi();
		DecimalFormat df = new DecimalFormat("#,##0 VND");
		giaLabel = new JLabel(df.format(giaChuyenDi));
		giaLabel.setBounds(170, 320, 200, 30);
		giaLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		giaLabel.setForeground(Color.RED);
		add(giaLabel);

		SpinnerModel sm = new SpinnerNumberModel(1, 1, ct_ThamQuan.getChuyenDi().getSoChoConNhan(), 1);
		soCho = new JSpinner(sm);
		soCho.setBounds(100, 325, 50, 20);
		soCho.setFont(new Font("Tahoma", Font.PLAIN, 13));
		soCho.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int soChoDat = Integer.parseInt(soCho.getValue().toString());
				giaChuyenDi = ct_ThamQuan.getChuyenDi().getGiaChuyenDi() * soChoDat;
				DecimalFormat df = new DecimalFormat("#,##0 VND");
				giaLabel.setText(df.format(giaChuyenDi));

			}
		});
		add(soCho);

		huyButton = new JButton("Hủy");
		huyButton.setBounds(10, 420, 80, 30);
		add(huyButton);

		lamMoiButton = new JButton("Làm mới");
		lamMoiButton.setBounds(610, 420, 80, 30);
		add(lamMoiButton);

		datTourButton = new JButton("Đặt tour");
		datTourButton.setBounds(700, 420, 80, 30);
		add(datTourButton);
		themDuLieuMacDinh();
		
		tinhThanhComboBox.addActionListener(this);
		quanHuyenComboBox.addActionListener(this);
		datTourButton.addActionListener(this);
		huyButton.addActionListener(this);
		lamMoiButton.addActionListener(this);
	}

	private void themDuLieuMacDinh() {
		tenKhachHang.setText(khachHang.getTenKhachHang());
		gioiTinhCb.setSelected(khachHang.isGioiTinh());
		ngaySinhDateChooser.setDate(khachHang.getNgaySinh());
		tinhThanhComboBox.setSelectedItem(khachHang.getDiaChi().getTinhThanh());
		quanHuyenModel.addAll(DiaChi_DAO.getQuanHuyenTheoTinh(khachHang.getDiaChi().getTinhThanh()));
		quanHuyenComboBox.setSelectedItem(khachHang.getDiaChi().getQuanHuyen());
		phuongXaModel.addAll(DiaChi_DAO.getPhuongXaTheoQuanHuyenVaTinh(khachHang.getDiaChi().getTinhThanh(),
				khachHang.getDiaChi().getQuanHuyen()));
		phuongXaComboBox.setSelectedItem(khachHang.getDiaChi().getPhuongXa());
		soDienThoai.setText(khachHang.getSoDienThoai());
		email.setText(khachHang.getEmail());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(tinhThanhComboBox)) {
			String tinhThanh = tinhThanhComboBox.getSelectedItem().toString();
			quanHuyenModel.removeAllElements();
			quanHuyenModel.addAll(DiaChi_DAO.getQuanHuyenTheoTinh(tinhThanh));
			quanHuyenComboBox.setSelectedIndex(0);
		} else if (o.equals(quanHuyenComboBox)) {
			if (quanHuyenModel.getSelectedItem() != null) {
				String quanHuyen = quanHuyenModel.getSelectedItem().toString();
				phuongXaModel.removeAllElements();
				phuongXaModel.addAll(DiaChi_DAO
						.getPhuongXaTheoQuanHuyenVaTinh(tinhThanhComboBox.getSelectedItem().toString(), quanHuyen));
				phuongXaComboBox.setSelectedIndex(0);
			}
		}else if (o.equals(huyButton)) {
			dispose();
		} else if (o.equals(lamMoiButton)) {
			themDuLieuMacDinh();
		}
		else if(o.equals(datTourButton)) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			KhachHang khachHang = new KhachHang();
			khachHang.setMaKhachHang(this.khachHang.getMaKhachHang());
			khachHang.setTenKhachHang(tenKhachHang.getText().trim());
			khachHang.setGioiTinh(gioiTinhCb.isSelected());
			khachHang.setNgaySinh(Date.valueOf(df.format(ngaySinhDateChooser.getDate())));
			khachHang.setDiaChi(DiaChi_DAO.getDiaChi(tinhThanhComboBox.getSelectedItem().toString(), quanHuyenComboBox.getSelectedItem().toString(), phuongXaComboBox.getSelectedItem().toString()));
			khachHang.setSoDienThoai(soDienThoai.getText().trim());
			khachHang.setEmail(email.getText().trim());
			khachHang.setMatKhau("");
			
			DonDatVe donDatVe = new DonDatVe();
			donDatVe.setKhachHang(khachHang);
			donDatVe.setNhanVien(NhanVien_DAO.getNgauNhienNhanVien());
			donDatVe.setTongTien(giaChuyenDi);
			donDatVe.setSoLuong(Integer.valueOf(soCho.getValue().toString()));
			donDatVe.setChuyenDi(ct_ThamQuan.getChuyenDi());
			donDatVe.setNhanVien(NhanVien_DAO.getNgauNhienNhanVien());
			if(KhachHang_DAO.updateKhachHang(khachHang, this.khachHang.getSoDienThoai()) && DonDatVe_DAO.insertDonDatVe(donDatVe)) {
				JOptionPane.showMessageDialog(this, "Đặt tour thành công");
				dispose();
			}else
				JOptionPane.showMessageDialog(this, "Đặt tour thất bại");
		}

	}

}
