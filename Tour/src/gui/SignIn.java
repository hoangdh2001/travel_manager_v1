package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import connect.ConnectDB;
import dao.DiaChi_DAO;
import dao.KhachHang_DAO;
import entity.DiaChi;
import entity.KhachHang;

public class SignIn extends JFrame implements ActionListener {
	private JButton buttonHuy;
	private KhachHang_DAO kh_DAO;
	private JButton buttonDangKy;
	private JTextField hoTenTextField;
	private JComboBox<String> gioiTinhComboBox;
	private JDateChooser ngaySinhChooser;
	private JTextField sDTTextField;
	private JTextField emailTextField;
	private JComboBox<String> tinhThanhComboBox;
	private JComboBox<String> quanHuyenComboBox;
	private JComboBox<String> phuongXaComboBox;
	private JPasswordField matKhauField;
	private JPasswordField nhapLaiMKField;
	private DefaultComboBoxModel<String> tinhThanhComboBoxModel;
	private DefaultComboBoxModel<String> quanHuyenComboBoxModel;
	private DefaultComboBoxModel<String> phuongXaComboBoxModel;

	public SignIn() {
		buidSignIn();
	}

	private void buidSignIn() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		kh_DAO = new KhachHang_DAO();

		JPanel wrapper = new JPanel();
		wrapper.setBackground(new Color(68, 187, 182));
		wrapper.setLayout(null);

		JLabel title = new JLabel("ĐĂNG KÝ TÀI KHOẢN", JLabel.CENTER);
		title.setFont(new Font(MainScreen.FONT_TEXT, Font.BOLD, 30));
		title.setForeground(Color.WHITE);
		title.setBackground(new Color(68, 71, 90));
		title.setOpaque(true);
		title.setBounds(0, 0, 900, 80);
		wrapper.add(title);

		JLabel hoTenLabel = new JLabel("Họ tên (*): ");
		hoTenLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		hoTenLabel.setForeground(Color.WHITE);
		hoTenLabel.setBounds(10, 85, 100, 30);
		wrapper.add(hoTenLabel);

		hoTenTextField = new JTextField();
		hoTenTextField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		hoTenTextField.setBounds(110, 85, 300, 30);
		wrapper.add(hoTenTextField);

		JLabel gioiTinhLabel = new JLabel("Giới tính (*): ");
		gioiTinhLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		gioiTinhLabel.setForeground(Color.WHITE);
		gioiTinhLabel.setBounds(450, 85, 100, 30);
		wrapper.add(gioiTinhLabel);

		gioiTinhComboBox = new JComboBox<String>();
		gioiTinhComboBox.addItem("Nam");
		gioiTinhComboBox.addItem("Nữ");
		gioiTinhComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		gioiTinhComboBox.setBounds(580, 85, 300, 30);
		wrapper.add(gioiTinhComboBox);

		JLabel ngaySinhLabel = new JLabel("Ngày sinh (*): ");
		ngaySinhLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		ngaySinhLabel.setForeground(Color.WHITE);
		ngaySinhLabel.setBounds(10, 120, 100, 30);
		wrapper.add(ngaySinhLabel);

		String dd = LocalDate.now().toString();

		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dd);
			ngaySinhChooser = new JDateChooser();
			ngaySinhChooser.setBounds(110, 120, 300, 30);
			ngaySinhChooser.setDate(date);
			ngaySinhChooser.setDateFormatString("dd/MM/yyyy");
			ngaySinhChooser.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
			ngaySinhChooser.setBackground(new Color(68, 187, 182));
			wrapper.add(ngaySinhChooser);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel sDTLabel = new JLabel("Số điện thoại (*):");
		sDTLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		sDTLabel.setForeground(Color.WHITE);
		sDTLabel.setBounds(450, 120, 120, 30);
		wrapper.add(sDTLabel);

		sDTTextField = new JTextField();
		sDTTextField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		sDTTextField.setBounds(580, 120, 300, 30);
		wrapper.add(sDTTextField);

		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setBounds(10, 155, 100, 30);
		wrapper.add(emailLabel);

		emailTextField = new JTextField();
		emailTextField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		emailTextField.setBounds(110, 155, 300, 30);
		wrapper.add(emailTextField);

		JLabel diaChiLabel = new JLabel("Địa chỉ (*): ");
		diaChiLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		diaChiLabel.setForeground(Color.WHITE);
		diaChiLabel.setBounds(10, 190, 100, 30);
		wrapper.add(diaChiLabel);

		tinhThanhComboBoxModel = new DefaultComboBoxModel<String>();
		tinhThanhComboBoxModel.addElement("<Tỉnh/Thành>");
		tinhThanhComboBoxModel.addAll(DiaChi_DAO.getAllTinh());
		tinhThanhComboBox = new JComboBox<String>(tinhThanhComboBoxModel);
		tinhThanhComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		tinhThanhComboBox.setBounds(110, 190, 300, 30);
		wrapper.add(tinhThanhComboBox);

		quanHuyenComboBoxModel = new DefaultComboBoxModel<String>();
		quanHuyenComboBoxModel.addElement("<Quận/Huyện>");
		quanHuyenComboBox = new JComboBox<String>(quanHuyenComboBoxModel);
		quanHuyenComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		quanHuyenComboBox.setBounds(430, 190, 230, 30);
		wrapper.add(quanHuyenComboBox);

		phuongXaComboBoxModel = new DefaultComboBoxModel<String>();
		phuongXaComboBoxModel.addElement("<Phường/Xã>");
		phuongXaComboBox = new JComboBox<String>(phuongXaComboBoxModel);
		phuongXaComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		phuongXaComboBox.setBounds(680, 190, 200, 30);
		wrapper.add(phuongXaComboBox);

		JLabel matKhauLabel = new JLabel("Mật khẩu (*): ");
		matKhauLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		matKhauLabel.setForeground(Color.WHITE);
		matKhauLabel.setBounds(10, 225, 100, 30);
		wrapper.add(matKhauLabel);

		matKhauField = new JPasswordField();
		matKhauField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		matKhauField.setBounds(110, 225, 300, 30);
		wrapper.add(matKhauField);

		JLabel nhapLaiMKLabel = new JLabel("Nhập lại mật khẩu:");
		nhapLaiMKLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		nhapLaiMKLabel.setForeground(Color.WHITE);
		nhapLaiMKLabel.setBounds(450, 225, 140, 30);
		wrapper.add(nhapLaiMKLabel);

		nhapLaiMKField = new JPasswordField();
		nhapLaiMKField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		nhapLaiMKField.setBounds(580, 225, 300, 30);
		wrapper.add(nhapLaiMKField);

		buttonHuy = new JButton("Hủy");
		buttonHuy.setBackground(new Color(68, 71, 90));
		buttonHuy.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		buttonHuy.setForeground(Color.WHITE);
		buttonHuy.setBounds(340, 440, 100, 40);
		wrapper.add(buttonHuy);

		buttonDangKy = new JButton("Đăng ký");
		buttonDangKy.setBackground(new Color(68, 71, 90));
		buttonDangKy.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		buttonDangKy.setForeground(Color.WHITE);
		buttonDangKy.setBounds(460, 440, 100, 40);
		wrapper.add(buttonDangKy);

		GroupLayout wrapperLayout = new GroupLayout(wrapper);
		wrapper.setLayout(wrapperLayout);
		wrapperLayout.setHorizontalGroup(
				wrapperLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 900, Short.MAX_VALUE));
		wrapperLayout.setVerticalGroup(
				wrapperLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 500, Short.MAX_VALUE));
		setContentPane(wrapper);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});
		tinhThanhComboBox.addActionListener(this);
		quanHuyenComboBox.addActionListener(this);
		quanHuyenComboBox.addActionListener(this);
		buttonHuy.addActionListener(this);
		buttonDangKy.addActionListener(this);
		
	}

	private boolean validForm() {
		String hoTen = hoTenTextField.getText().trim();
		String sdt = sDTTextField.getText().trim();
		String email = emailTextField.getText().trim();
		String matKhau = matKhauField.getText().trim();

		if (!(hoTen.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Chưa nhập họ tên.");
			hoTenTextField.requestFocus();
			return false;
		} else {
			if (!hoTen.matches("^([ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*\\s)+([ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*)$")) {
				JOptionPane.showMessageDialog(null, "Chưa nhập đầy đủ họ tên. Họ tên bắt đầu bằng chữ in hoa.");
				hoTenTextField.selectAll();
				hoTenTextField.requestFocus();
				return false;
			}
		}

		//lấy ngày tháng năm hiện tại
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		
		//lấy ngày tháng năm sinh
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String chooser = sdf.format(ngaySinhChooser.getDate());

		String[] strings = chooser.split("/");
		int ngaySinh = Integer.parseInt(strings[2]);
		int thangSinh = Integer.parseInt(strings[1]);
		int namSinh = Integer.parseInt(strings[0]);
		
		
		if (namSinh == now.getYear()) {
			if (thangSinh == now.getMonthValue()) {
				if (ngaySinh > now.getMonthValue()) {
					JOptionPane.showMessageDialog(null, "Ngày tháng năm sinh phải nhỏ hơn ngày tháng năm hiện tại 1");
					return false;
				}
			}else if (thangSinh > now.getMonthValue()) {
				JOptionPane.showMessageDialog(null, "Ngày tháng năm sinh phải nhỏ hơn ngày tháng năm hiện tại 2");
				return false;
			}
		}else if (namSinh > now.getYear()) {
			JOptionPane.showMessageDialog(null, "Ngày tháng năm sinh phải nhỏ hơn ngày tháng năm hiện tại 3");
			return false;
		}

		if (!(sdt.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Chưa nhập số điện thoại.");
			sDTTextField.requestFocus();
			return false;
		} else {
			if (!(sdt.matches("^0[0-9]{9}$"))) {
				JOptionPane.showMessageDialog(null,
						"Nhập sai định dạng.\nSố điện thoại bao gồm 10 chữ số bắt đầu là số 0.");
				sDTTextField.selectAll();
				sDTTextField.requestFocus();
				return false;
			}
		}

		if (email.length() > 0) {

			if (!(email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))) {
				JOptionPane.showMessageDialog(null, "Nhập sai định dạng email.");
				emailTextField.selectAll();
				emailTextField.requestFocus();
				return false;
			}
		}

		if (tinhThanhComboBox.getSelectedItem().equals("<Tỉnh/Thành>")) {
			JOptionPane.showMessageDialog(null, "Chưa chọn tỉnh/Thành phố.");
			return false;
		}
		if (quanHuyenComboBox.getSelectedItem().equals("<Quận/Huyện>")) {
			JOptionPane.showMessageDialog(null, "Chưa chọn quận/huyện.");
			return false;
		}
		if (phuongXaComboBox.getSelectedItem().equals("<Phường/Xã>")) {
			JOptionPane.showMessageDialog(null, "Chưa chọn phường/xã.");
			return false;
		}

		if (!(matKhau.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu.");
			matKhauField.requestFocus();
			return false;
		} else {
			if (!(matKhau.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+-=]).{6,20}$"))) {
				JOptionPane.showMessageDialog(null,
						"Mật khẩu từ 6 đến 20 kí tự, bao gồm:\n Có ít nhất 1 kí tự chữ thường"
								+ "\n Có ít nhất 1 kí tự chữ in hoa\n Có ít nhất 1 kí tự số"
								+ "\n Có ít nhất 1 kí tự đặc biệt !@#$%^&*()_+-=");
				matKhauField.selectAll();
				matKhauField.requestFocus();
				return false;
			}
		}

		if (!(nhapLaiMKField.getText().trim().length() > 0)) {
			JOptionPane.showMessageDialog(null, "Chưa nhập lại mật khẩu.");
			nhapLaiMKField.requestFocus();
			return false;
		} else {
			if (!(matKhau.equals(nhapLaiMKField.getText().trim()))) {
				JOptionPane.showMessageDialog(null, "Nhập lại mật khẩu không đúng.");
				nhapLaiMKField.selectAll();
				nhapLaiMKField.requestFocus();
				return false;
			}
		}

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(tinhThanhComboBox)) {
			quanHuyenComboBoxModel.removeAllElements();
			quanHuyenComboBoxModel
					.addAll(DiaChi_DAO.getQuanHuyenTheoTinh(tinhThanhComboBox.getSelectedItem().toString()));
			quanHuyenComboBox.setSelectedIndex(0);
		} else if (o.equals(quanHuyenComboBox)) {
			if(quanHuyenComboBox.getSelectedItem() != null) {
				phuongXaComboBoxModel.removeAllElements();
				phuongXaComboBoxModel.addAll(DiaChi_DAO.getPhuongXaTheoQuanHuyenVaTinh(
						tinhThanhComboBox.getSelectedItem().toString(), quanHuyenComboBox.getSelectedItem().toString()));
				phuongXaComboBox.setSelectedIndex(0);
			}
		} else if (o.equals(buttonHuy)) {
			dispose();
			new Login().setVisible(true);
		} else if (o.equals(buttonDangKy)) {
			if (validForm() == false) {
				return;
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			KhachHang khachHang = new KhachHang();
			khachHang.setMaKhachHang("");
			khachHang.setTenKhachHang(hoTenTextField.getText().trim());
			khachHang.setGioiTinh(gioiTinhComboBox.getSelectedItem().equals("Nữ") ? true : false);
			khachHang.setNgaySinh(java.sql.Date.valueOf(sdf.format(ngaySinhChooser.getDate())));
			khachHang.setDiaChi(DiaChi_DAO.getDiaChi(tinhThanhComboBoxModel.getSelectedItem().toString(),
					quanHuyenComboBoxModel.getSelectedItem().toString(),
					phuongXaComboBoxModel.getSelectedItem().toString()));
			khachHang.setSoDienThoai(sDTTextField.getText().trim());
			khachHang.setEmail(emailTextField.getText().trim());
			khachHang.setMatKhau(nhapLaiMKField.getText());
			if (kh_DAO.insertKhachHang(khachHang)) {
				JOptionPane.showMessageDialog(null, "Đăng ký thành công");
			} else {
				JOptionPane.showMessageDialog(null, "Đăng ký không thành công!");
			}
			dispose();
			new Login().setVisible(true);
		}
	}
}