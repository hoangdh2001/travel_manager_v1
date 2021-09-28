package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.DiaChi_DAO;
import dao.DiaDanh_DAO;
import entity.DiaDanh;

public class DialogThemDiaDanh extends JDialog implements ActionListener {
	private JPanel wrapper;
	private JLabel hinhAnh;
	private JButton huy;
	private JButton themButton;
	private JButton buttonThemFile;
	private DefaultComboBoxModel<String> tinhThanhBoxModel;
	private JComboBox<String> tinhThanhCB;
	private JTextField tenDiaDanTextField;
	private String filePath;
	
	public DialogThemDiaDanh() {
		setModal(true);
		setSize(650, 520);
		setLocationRelativeTo(null);
		buidDialogThemDiaDanh();
	}
	private void buidDialogThemDiaDanh() {
		wrapper = new JPanel();
		wrapper.setLayout(null);
		wrapper.setBackground(MainScreen.BACKGROUND_COLOR);
		
		tenDiaDanTextField = new JTextField() {
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
				g.drawString("Nhập tên địa danh", getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
			}
		};
		tenDiaDanTextField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		tenDiaDanTextField.setBounds(10, 10, 300, 30);
		wrapper.add(tenDiaDanTextField);
		tenDiaDanTextField.requestFocus();
		
		tinhThanhBoxModel = new DefaultComboBoxModel<String>();
		tinhThanhCB = new JComboBox<String>(tinhThanhBoxModel);
		tinhThanhCB.addItem("<Tỉnh thành>");
		tinhThanhBoxModel.addAll(DiaChi_DAO.getAllTinh());
		tinhThanhCB.setBounds(330, 10, 300, 30);
		tinhThanhCB.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		wrapper.add(tinhThanhCB);
		
		hinhAnh = new JLabel();
		hinhAnh.setBounds(10, 80, 615, 350);
		hinhAnh.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		wrapper.add(hinhAnh);
		
		filePath = "";
		buttonThemFile = new JButton("Thêm ảnh");
		buttonThemFile.setBounds(10, 45, 100, 30);
		buttonThemFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonThemFile.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		buttonThemFile.setForeground(Color.WHITE);
		buttonThemFile.setBackground(MainScreen.COLOR_MAIN);
		buttonThemFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
		wrapper.add(buttonThemFile);
		
		huy = new JButton("Hủy");
		huy.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		huy.setForeground(Color.WHITE);
		huy.setCursor(new Cursor(Cursor.HAND_CURSOR));
		huy.setBackground(MainScreen.COLOR_MAIN);
		huy.setBounds(220, 440, 100, 40);
		huy.setCursor(new Cursor(Cursor.HAND_CURSOR));
		wrapper.add(huy);
		
		themButton = new JButton("Thêm");
		themButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		themButton.setForeground(Color.WHITE);
		themButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		themButton.setBackground(MainScreen.COLOR_MAIN);
		themButton.setBounds(330, 440, 100, 40);
		themButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		wrapper.add(themButton);
		setContentPane(wrapper);
		
		buttonThemFile.addActionListener(this);
		huy.addActionListener(this);
		themButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(buttonThemFile)) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			    "JPG & GIF Images", "jpg", "gif", "jpeg", "png");
			JFileChooser fs = new JFileChooser(new File("E:\\"));
			fs.setLocation(100, 100);
			int number = fs.showOpenDialog(null);
			fs.addChoosableFileFilter(filter);
			fs.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fs.setAcceptAllFileFilterUsed(false);
			if (number == JFileChooser.APPROVE_OPTION) {
				File file = fs.getSelectedFile();
				if(file != null) {
					ImageIcon icon = new ImageIcon(file.getAbsolutePath());
					hinhAnh.setIcon(new ImageIcon(icon.getImage().getScaledInstance(hinhAnh.getWidth(), hinhAnh.getHeight(), Image.SCALE_SMOOTH)));
					filePath = file.getAbsolutePath();
				}
				else
					JOptionPane.showMessageDialog(null, "File không hợp lệ!");
			} else {
				 System.out.println("No Selection ");
			}
			tenDiaDanTextField.requestFocus();
		}
		else if(o.equals(huy)) {
			dispose();
		}
		else if(o.equals(themButton)) {
			if(checkData_ThemDiaDanh()) {
				if(filePath.equals("")) {
					JOptionPane.showMessageDialog(this, "Chưa chọn file ảnh");
					return;
				}
				String tenDiaDanh = tenDiaDanTextField.getText().trim();
				String thuocTinh = tinhThanhCB.getSelectedItem().toString();
				DiaDanh diaDanh = new DiaDanh();
				diaDanh.setMaDiaDanh(DiaDanh_DAO.phatSinhMaDiaDanh());
				diaDanh.setTenDiaDanh(tenDiaDanh);
				diaDanh.setThuocTinh(thuocTinh);;
				diaDanh.setAnhDiaDanh(null);
				if(DiaDanh_DAO.themDiaDanh(diaDanh, filePath))  {
					JOptionPane.showMessageDialog(this, "Thêm thành công!");
					dispose();
				}
				else
					JOptionPane.showMessageDialog(this, "Thêm thất bại!");
			}
		}
	}
	private boolean checkData_ThemDiaDanh()
	{
		String tenDiaDanh = tenDiaDanTextField.getText().trim();
		if (!tenDiaDanh.equals("") && !tenDiaDanh.matches("([ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*){1}(\\s+[ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*)*$")) {
			if (tenDiaDanh.equals("")) {
				JOptionPane.showMessageDialog(this, "Tên địa danh không hợp lệ");
			} else {

				JOptionPane.showMessageDialog(this, "Tên địa danh phải viết Hoa chữ cái đầu cho tên địa danh và từ đầu tiên.");
			}
			return false;
		}
		String thuocTinh = tinhThanhCB.getSelectedItem().toString();
		if(thuocTinh.equals("<Tỉnh thành>")) {
			JOptionPane.showMessageDialog(this, "Chưa chọn địa chỉ!");
			return false;
		}
		return true;
	}
}
