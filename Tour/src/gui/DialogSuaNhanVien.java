package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import dao.DiaChi_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.DiaChi;
import entity.NhanVien;

public class DialogSuaNhanVien extends JDialog implements ActionListener{
	private JPanel wrapper;
	private JTextField sDTTextField;
	private JTextField emailTextField;
	private DefaultComboBoxModel<String> tinhThanhComboBoxModel;
	private JComboBox<String> tinhThanhComboBox;
	private DefaultComboBoxModel<String> quanHuyenComboBoxModel;
	private JComboBox<String> quanHuyenComboBox;
	private DefaultComboBoxModel<String> phuongXaComboBoxModel;
	private JComboBox<String> phuongXaComboBox;
	private JPasswordField nhapLaiMKField;
	private JTextField hoTenTextField;
	private JLabel maLabel2;
	private JCheckBox gioiTinhCheckBox;
	private JButton huyButton;
	private JButton lamMoiButton;
	private JButton luuButton;
	private NhanVien_DAO nv_DAO;
	private NhanVien nhanVien;
	private JButton doiMKButton;
	private JCheckBox trangThaiCheckBox;
	public DialogSuaNhanVien(String maNhanVien) {
		setModal(true);
		nv_DAO = new NhanVien_DAO();
		nhanVien = NhanVien_DAO.getNhanVien(maNhanVien);
		setSize(800, 530);
		setLocationRelativeTo(null);
		buidDialogSuaKhachHang();
	}
	private void buidDialogSuaKhachHang() {
		wrapper = new JPanel();
		wrapper.setLayout(null);
		wrapper.setBackground(MainScreen.BACKGROUND_COLOR);
		
		JPanel header = new JPanel();
		header.setBackground(MainScreen.HEADER_COLOR);
		header.setBounds(0, 0, 800, 80);
		header.setLayout(null);
		wrapper.add(header);
		
		JLabel tenCongTy = new JLabel("TOP TRAVEL", JLabel.CENTER);
		tenCongTy.setForeground(Color.WHITE);
		tenCongTy.setFont(new Font(MainScreen.FONT_TEXT, Font.BOLD, 46));
		tenCongTy.setBounds(0, 0, 800, 40);
		header.add(tenCongTy);
		
		JLabel title = new JLabel("Sửa nhân viên", JLabel.CENTER);
		title.setForeground(Color.WHITE);
		title.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 18));
		title.setBounds(0, 50, 800, 30);
		header.add(title);
		
		JLabel maLabel1 = new JLabel("Mã nhân viên");
		maLabel1.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		maLabel1.setBounds(10, 85, 130, 30);
		wrapper.add(maLabel1);
		
		maLabel2 = new JLabel();
		maLabel2.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		maLabel2.setBounds(140, 85, 100, 30);
		wrapper.add(maLabel2);
		
		JLabel trangThaiLabel = new JLabel("Trạng thái: ");
		trangThaiLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		trangThaiLabel.setBounds(410, 85, 100, 30);
		wrapper.add(trangThaiLabel);
		
		trangThaiCheckBox = new JCheckBox("Đang làm");
		trangThaiCheckBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		trangThaiCheckBox.setBounds(500, 85, 100, 30);
		wrapper.add(trangThaiCheckBox);
		
		JLabel gioiTinhLabel = new JLabel("Giới tính: ");
		gioiTinhLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		gioiTinhLabel.setBounds(630, 85, 100, 30);
		wrapper.add(gioiTinhLabel);
		
		gioiTinhCheckBox = new JCheckBox("Nữ");
		gioiTinhCheckBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		gioiTinhCheckBox.setBounds(700, 85, 100, 30);
		wrapper.add(gioiTinhCheckBox);
		
		JLabel hoTenLabel = new JLabel("Họ tên (*): ");
		hoTenLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		hoTenLabel.setBounds(10, 135, 100, 30);
		wrapper.add(hoTenLabel);

		hoTenTextField = new JTextField();
		hoTenTextField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		hoTenTextField.setBounds(140, 135, 640, 30);
		wrapper.add(hoTenTextField);

		JLabel sDTLabel = new JLabel("Số điện thoại (*):");
		sDTLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		sDTLabel.setBounds(10, 185, 120, 30);
		wrapper.add(sDTLabel);

		sDTTextField = new JTextField();
		sDTTextField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		sDTTextField.setBounds(140, 185, 640, 30);
		wrapper.add(sDTTextField);

		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		emailLabel.setBounds(10, 235, 100, 30);
		wrapper.add(emailLabel);

		emailTextField = new JTextField();
		emailTextField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		emailTextField.setBounds(140, 235, 640, 30);
		wrapper.add(emailTextField);

		JLabel diaChiLabel = new JLabel("Địa chỉ (*): ");
		diaChiLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		diaChiLabel.setBounds(10, 285, 100, 30);
		wrapper.add(diaChiLabel);

		tinhThanhComboBoxModel = new DefaultComboBoxModel<String>();
		tinhThanhComboBox = new JComboBox<String>(tinhThanhComboBoxModel);
		tinhThanhComboBox.addItem("<Tỉnh/TP>");
		tinhThanhComboBoxModel.addAll(DiaChi_DAO.getAllTinh());
		tinhThanhComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		tinhThanhComboBox.setBounds(140, 285, 200, 30);
		wrapper.add(tinhThanhComboBox);

		quanHuyenComboBoxModel = new DefaultComboBoxModel<String>();
		quanHuyenComboBox = new JComboBox<String>(quanHuyenComboBoxModel);
		quanHuyenComboBox.addItem("<Quận/Huyện>");
		quanHuyenComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		quanHuyenComboBox.setBounds(360, 285, 200, 30);
		wrapper.add(quanHuyenComboBox);

		phuongXaComboBoxModel = new DefaultComboBoxModel<String>();
		phuongXaComboBox = new JComboBox<String>(phuongXaComboBoxModel);
		phuongXaComboBox.addItem("<Phường/xã>");
		phuongXaComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		phuongXaComboBox.setBounds(580, 285, 200, 30);
		wrapper.add(phuongXaComboBox);
		
		doiMKButton = new JButton("Đổi mật khẩu");
		doiMKButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		doiMKButton.setBounds(10, 395, 140, 40);
		wrapper.add(doiMKButton);
		
		nhapLaiMKField = new JPasswordField() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(getText().length() > 0) {
					return;
				}
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.GRAY);
				g2d.setFont(new Font(MainScreen.FONT_TEXT, Font.ITALIC, 16));
				g2d.drawString("Nhập mật khẩu", getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
			}
		};
		nhapLaiMKField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		nhapLaiMKField.setBounds(160, 400, 200, 30);
		nhapLaiMKField.setVisible(false);
		wrapper.add(nhapLaiMKField);
		
		huyButton = new JButton("Hủy");
		huyButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		huyButton.setBounds(10, 445, 100, 40);
		wrapper.add(huyButton);
		
		lamMoiButton = new JButton("Làm mới");
		lamMoiButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		lamMoiButton.setBounds(560, 445, 100, 40);
		wrapper.add(lamMoiButton);
		
		luuButton = new JButton("Lưu");
		luuButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		luuButton.setBounds(670, 445, 100, 40);
		wrapper.add(luuButton);
		
		setContentPane(wrapper);
		themDuLieuMacDinh();
		
		tinhThanhComboBox.addActionListener(this);
		quanHuyenComboBox.addActionListener(this);
		doiMKButton.addActionListener(this);
		huyButton.addActionListener(this);
		lamMoiButton.addActionListener(this);
		luuButton.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(tinhThanhComboBox)) {
			String tinhThanh = tinhThanhComboBox.getSelectedItem().toString();
			if(!tinhThanh.equals("<Tỉnh/TP>")) {
				quanHuyenComboBoxModel.removeAllElements();
				quanHuyenComboBoxModel.addAll(DiaChi_DAO.getQuanHuyenTheoTinh(tinhThanh));
				quanHuyenComboBox.setSelectedIndex(0);
			}
			else {
				quanHuyenComboBoxModel.removeAllElements();
				quanHuyenComboBox.addItem("<Quận/Huyện>");
				quanHuyenComboBox.setSelectedIndex(0);
			}
			
		}
		else if (o.equals(quanHuyenComboBox)) {
			if(quanHuyenComboBoxModel.getSelectedItem() != null) {
				String quanHuyen = quanHuyenComboBoxModel.getSelectedItem().toString();
				if(!quanHuyen.equals("<Quận/Huyện>")) {
					phuongXaComboBoxModel.removeAllElements();
					phuongXaComboBoxModel.addAll(DiaChi_DAO.getPhuongXaTheoQuanHuyenVaTinh(tinhThanhComboBox.getSelectedItem().toString(), quanHuyen));
					phuongXaComboBox.setSelectedIndex(0);
				}
				else {
					phuongXaComboBoxModel.removeAllElements();
					phuongXaComboBox.addItem("<Phường/xã>");
					phuongXaComboBox.setSelectedIndex(0);
				}
			}
		}
		else if (o.equals(huyButton)) {
			dispose();
		}
		else if(o.equals(lamMoiButton)) {
			themDuLieuMacDinh();
		}
		else if (o.equals(doiMKButton)) {
			if(nhapLaiMKField.isVisible())
				nhapLaiMKField.setVisible(false);
			else
				nhapLaiMKField.setVisible(true);
		}
		else if(o.equals(luuButton)) {
			if(checkData_SuaNhanVien()) {
				String maNhanVien = maLabel2.getText();
				String tenNhanVien = hoTenTextField.getText().trim();
				boolean gioiTinh = gioiTinhCheckBox.isSelected();
				boolean trangThai = trangThaiCheckBox.isSelected();
				String soDienThoai = sDTTextField.getText().trim();
				String email = emailTextField.getText().trim();
				DiaChi diaChi = DiaChi_DAO.getDiaChi(tinhThanhComboBox.getSelectedItem().toString(), quanHuyenComboBox.getSelectedItem().toString(), phuongXaComboBox.getSelectedItem().toString());
				String matKhau = nhapLaiMKField.getText();
				if(!soDienThoai.equals(nhanVien.getSoDienThoai())) {
					if(TaiKhoan_DAO.getTaiKhoan(soDienThoai) != null) {
						JOptionPane.showMessageDialog(this, "Số điện thoại đã được đăng ký!");
						selectAllText();
						sDTTextField.requestFocus();
						return;
					}
				}
				NhanVien nhanVien = new NhanVien();
				nhanVien.setMaNhanVien(maNhanVien);
				nhanVien.setTenNhanVien(tenNhanVien);
				nhanVien.setGioiTinh(gioiTinh);
				nhanVien.setTrangThai(trangThai);
				nhanVien.setDiaChi(diaChi);
				nhanVien.setSoDienThoai(soDienThoai);
				nhanVien.setEmail(email);
				nhanVien.setMatKhau(matKhau);
				
				if(nv_DAO.updateNhanVien(nhanVien, this.nhanVien.getSoDienThoai())) {
					JOptionPane.showMessageDialog(this, "Sửa thành công!");
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(this, "Sửa thất bại!");
					selectAllText();
					hoTenTextField.requestFocus();
				}
			}
		}
	}
	
	private void themDuLieuMacDinh() {
		maLabel2.setText(nhanVien.getMaNhanVien());
		trangThaiCheckBox.setSelected(nhanVien.isTrangThai());
		gioiTinhCheckBox.setSelected(nhanVien.isGioiTinh());
		hoTenTextField.setText(nhanVien.getTenNhanVien());
		sDTTextField.setText(nhanVien.getSoDienThoai());
		emailTextField.setText(nhanVien.getEmail());
		tinhThanhComboBox.setSelectedItem(nhanVien.getDiaChi().getTinhThanh());
		quanHuyenComboBoxModel.addAll(DiaChi_DAO.getQuanHuyenTheoTinh(nhanVien.getDiaChi().getTinhThanh()));
		phuongXaComboBoxModel.addAll(DiaChi_DAO.getPhuongXaTheoQuanHuyenVaTinh(nhanVien.getDiaChi().getTinhThanh(), nhanVien.getDiaChi().getQuanHuyen()));
		quanHuyenComboBox.setSelectedItem(nhanVien.getDiaChi().getQuanHuyen());
		phuongXaComboBox.setSelectedItem(nhanVien.getDiaChi().getPhuongXa());
		hoTenTextField.requestFocus();
	}
	private void selectAllText() {
		hoTenTextField.selectAll();
		sDTTextField.selectAll();
		emailTextField.selectAll();
		if(nhapLaiMKField.isVisible()) {
			nhapLaiMKField.setText("");
		}
	}
	private void getShowMessage(String str, JTextField txt)
	{
		JOptionPane.showMessageDialog(this , str);
		txt.selectAll();
		txt.requestFocus();
	}
	private boolean checkData_SuaNhanVien()
	{
		String mess = "";
		String tenNhanVien = hoTenTextField.getText().trim();
		if(!(tenNhanVien.length()>0 && tenNhanVien.matches("^([ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*\\s)+([ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*)$")))
		{
			if (tenNhanVien.length() == 0 ) {
				JOptionPane.showMessageDialog(this, "Hãy nhập tên nhân viên.");
			}
			else {
				JOptionPane.showMessageDialog(this, "Tên nhân viên phải viết theo định dạng VD: Nguyễn Văn A");				
			}
			hoTenTextField.selectAll();
			hoTenTextField.requestFocus();
			return false;
		}
		//
		
		String soDienThoai = sDTTextField.getText().trim();
		if (!(soDienThoai.length()>0 && soDienThoai.matches("^0[0-9]{9}$"))) {
			if (soDienThoai.length() == 0 ) {
				JOptionPane.showMessageDialog(this, "Hãy nhập số điện thoại của nhân viên.");
			}
			else {
				JOptionPane.showMessageDialog(this, "Số điện thoại có 10 số và bắt đầu bằng số 0.");				
			}
			sDTTextField.selectAll();
			sDTTextField.requestFocus();
			return false;
		}
		
		//
		String email = emailTextField.getText().trim();
		if (!(email.matches("^[A-Za-z0-9._]+@[A-Za-z0-9.]+\\.[a-z]{2,4}$"))) {
			if (email.length() == 0) {
				mess = "";
			} else {
			mess = "Email phai đúng theo định dạng (VD: Abc@gmail.com)";
			getShowMessage(mess,emailTextField);
			return false;
			}				
		}
		//
		
		if (tinhThanhComboBox.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this , "Hay chọn địa chỉ.");
			return false;
		}
		//
		String matKhau = nhapLaiMKField.getText().trim();
		if(nhapLaiMKField.isVisible()) {
			if (!(matKhau.length()>0 && matKhau.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_+=@$!%*#?&])[A-Za-z\\d-_+=@$!%*#?&]{6,20}$"))) {
				if (matKhau.length() <= 0) {
					mess = "Hãy nhập mật khẩu.";
				}
				else {
					mess = "Mật khẩu phải trên 6 ký tự trong dó có một chữ số, một chữ cái thường, một chữ hoa và một ký tự đặc biệt";
				}
				getShowMessage(mess, nhapLaiMKField);
				return false;
			}
		}
		return true;
	}
}
