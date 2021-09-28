package gui;

import java.awt.Color;
import java.awt.Font;
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
import dao.HuongDanVien_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.DiaChi;
import entity.HuongDanVien;
import entity.NhanVien;

public class DialogSuaHuongDanVien extends JDialog implements ActionListener{
	private JPanel wrapper;
	private JTextField sDTTextField;
	private DefaultComboBoxModel<String> tinhThanhComboBoxModel;
	private JComboBox<String> tinhThanhComboBox;
	private DefaultComboBoxModel<String> quanHuyenComboBoxModel;
	private JComboBox<String> quanHuyenComboBox;
	private DefaultComboBoxModel<String> phuongXaComboBoxModel;
	private JComboBox<String> phuongXaComboBox;
	private JTextField hoTenTextField;
	private JLabel maLabel2;
	private JCheckBox gioiTinhCheckBox;
	private JButton huyButton;
	private JButton lamMoiButton;
	private JButton luuButton;
	private HuongDanVien_DAO hdv_DAO;
	private HuongDanVien huongDanVien;
	public DialogSuaHuongDanVien(String maHuongDanVien) {
		setModal(true);
		hdv_DAO = new HuongDanVien_DAO();
		huongDanVien = HuongDanVien_DAO.getHuongDanVien(maHuongDanVien);
		setSize(800, 400);
		setLocationRelativeTo(null);
		buidDialogSuaHuongDanVien();
	}
	private void buidDialogSuaHuongDanVien() {
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
		
		JLabel title = new JLabel("Thêm hướng dẫn viên", JLabel.CENTER);
		title.setForeground(Color.WHITE);
		title.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 18));
		title.setBounds(0, 50, 800, 30);
		header.add(title);
		
		JLabel maLabel1 = new JLabel("Mã HDV: ");
		maLabel1.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		maLabel1.setBounds(10, 85, 130, 30);
		wrapper.add(maLabel1);
		
		maLabel2 = new JLabel();
		maLabel2.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		maLabel2.setBounds(140, 85, 100, 30);
		wrapper.add(maLabel2);
		
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

		JLabel diaChiLabel = new JLabel("Địa chỉ (*): ");
		diaChiLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		diaChiLabel.setBounds(10, 235, 100, 30);
		wrapper.add(diaChiLabel);

		tinhThanhComboBoxModel = new DefaultComboBoxModel<String>();
		tinhThanhComboBox = new JComboBox<String>(tinhThanhComboBoxModel);
		tinhThanhComboBox.addItem("<Tỉnh/TP>");
		tinhThanhComboBoxModel.addAll(DiaChi_DAO.getAllTinh());
		tinhThanhComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		tinhThanhComboBox.setBounds(140, 235, 200, 30);
		wrapper.add(tinhThanhComboBox);

		quanHuyenComboBoxModel = new DefaultComboBoxModel<String>();
		quanHuyenComboBox = new JComboBox<String>(quanHuyenComboBoxModel);
		quanHuyenComboBox.addItem("<Quận/Huyện>");
		quanHuyenComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		quanHuyenComboBox.setBounds(360, 235, 200, 30);
		wrapper.add(quanHuyenComboBox);

		phuongXaComboBoxModel = new DefaultComboBoxModel<String>();
		phuongXaComboBox = new JComboBox<String>(phuongXaComboBoxModel);
		phuongXaComboBox.addItem("<Phường/xã>");
		phuongXaComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		phuongXaComboBox.setBounds(580, 235, 200, 30);
		wrapper.add(phuongXaComboBox);
		
		huyButton = new JButton("Hủy");
		huyButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		huyButton.setBounds(10, 300, 100, 40);
		wrapper.add(huyButton);
		
		lamMoiButton = new JButton("Làm mới");
		lamMoiButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		lamMoiButton.setBounds(560, 300, 100, 40);
		wrapper.add(lamMoiButton);
		
		luuButton = new JButton("Thêm");
		luuButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		luuButton.setBounds(670, 300, 100, 40);
		wrapper.add(luuButton);
		
		setContentPane(wrapper);
		
		tinhThanhComboBox.addActionListener(this);
		quanHuyenComboBox.addActionListener(this);
		huyButton.addActionListener(this);
		lamMoiButton.addActionListener(this);
		luuButton.addActionListener(this);
		
		maLabel2.setText(HuongDanVien_DAO.phatSinhMaHuongDanVien());
		themDuLieuMacDinh();
		
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
		else if(o.equals(luuButton)) {
			if(checkData_ThemHuongDanVien()) {
				String maHuongDanVien = maLabel2.getText();
				String tenHuongDanVien = hoTenTextField.getText().trim();
				boolean gioiTinh = gioiTinhCheckBox.isSelected();
				String soDienThoai = sDTTextField.getText().trim();
				DiaChi diaChi = DiaChi_DAO.getDiaChi(tinhThanhComboBox.getSelectedItem().toString(), quanHuyenComboBox.getSelectedItem().toString(), phuongXaComboBox.getSelectedItem().toString());
				HuongDanVien huongDanVien = new HuongDanVien();
				huongDanVien.setMaHuongDanVien(maHuongDanVien);
				huongDanVien.setTenHuongDanVien(tenHuongDanVien);
				huongDanVien.setGioiTinh(gioiTinh);
				huongDanVien.setSoDienThoai(soDienThoai);
				huongDanVien.setDiaChi(diaChi);
				if(hdv_DAO.updateHuongDanVien(huongDanVien)) {
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
		maLabel2.setText(huongDanVien.getMaHuongDanVien());
		hoTenTextField.setText(huongDanVien.getTenHuongDanVien());
		gioiTinhCheckBox.setSelected(huongDanVien.isGioiTinh());
		sDTTextField.setText(huongDanVien.getSoDienThoai());
		tinhThanhComboBox.setSelectedItem(huongDanVien.getDiaChi().getTinhThanh());
		quanHuyenComboBoxModel.addAll(DiaChi_DAO.getQuanHuyenTheoTinh(huongDanVien.getDiaChi().getTinhThanh()));
		phuongXaComboBoxModel.addAll(DiaChi_DAO.getPhuongXaTheoQuanHuyenVaTinh(huongDanVien.getDiaChi().getTinhThanh(), huongDanVien.getDiaChi().getQuanHuyen()));
		quanHuyenComboBox.setSelectedItem(huongDanVien.getDiaChi().getQuanHuyen());
		phuongXaComboBox.setSelectedItem(huongDanVien.getDiaChi().getQuanHuyen());
		hoTenTextField.requestFocus();
	}
	private void selectAllText() {
		hoTenTextField.selectAll();
		sDTTextField.selectAll();
	}
	private boolean checkData_ThemHuongDanVien()
	{
		String tenNhanVien = hoTenTextField.getText().trim();
		if(!(tenNhanVien.length()>0 && tenNhanVien.matches("^([ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*\\s)+([ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*)$")))
		{
			if (tenNhanVien.length() == 0 ) {
				JOptionPane.showMessageDialog(this, "Hãy nhập tên hướng dẫn viên.");
			}
			else {
				JOptionPane.showMessageDialog(this, "Tên hướng dẫn viên phải viết theo định dạng VD: Nguyễn Văn A");				
			}
			hoTenTextField.selectAll();
			hoTenTextField.requestFocus();
			return false;
		}
		//
		
		String soDienThoai = sDTTextField.getText().trim();
		if (!(soDienThoai.length()>0 && soDienThoai.matches("^0[0-9]{9}$"))) {
			if (soDienThoai.length() == 0 ) {
				JOptionPane.showMessageDialog(this, "Hãy nhập số điện thoại của hướng dẫn viên.");
			}
			else {
				JOptionPane.showMessageDialog(this, "Số điện thoại có 10 số và bắt đầu bằng số 0.");				
			}
			sDTTextField.selectAll();
			sDTTextField.requestFocus();
			return false;
		}
		//
		
		if (tinhThanhComboBox.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this , "Hay chọn địa chỉ.");
			return false;
		}
		
		return true;
	}
	
}
