 package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connect.ConnectDB;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dohuyhoang.animation.slideshow.SlideShowPaneAnimation;

public class Login extends JFrame implements ActionListener {
	private JTextField userTXT;
	private JPasswordField passTXT;
	private KhachHang_DAO kh_DAO;
	private NhanVien_DAO nv_DAO;
	private JCheckBox checkNhanVien;
	private JButton buttonDangNhap;
	private JButton buttonDangKy;

	public Login() {
		setTitle("Đăng nhập");
		buidLogin();
	}

	private void buidLogin() {

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		kh_DAO = new KhachHang_DAO();
		nv_DAO = new NhanVien_DAO();

		JPanel wrapper = new JPanel();
		wrapper.setBackground(new Color(68, 187, 182));
		wrapper.setLayout(null);

		JPanel rightPN = new JPanel();
		rightPN.setBounds(300, 0, 300, 350);
		rightPN.setBackground(new Color(68, 187, 182));
		rightPN.setLayout(null);
		JLabel userLabel = new JLabel();
		userLabel.setIcon(new ImageIcon(getClass().getResource("/gui/icon/icons8_user_20px.png")));
		userLabel.setBounds(30, 80, 30, 30);
		rightPN.add(userLabel);
		userTXT = new JTextField() {
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
				g.drawString("Tên đăng nhập", getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
			}

		};
//		userTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		userTXT.setFont(new Font("Segoe ui", Font.PLAIN, 16));
		userTXT.requestFocus(false);
		userTXT.setBounds(60, 80, 200, 30);
		userTXT.setToolTipText("Tên đăng nhập là số điện thoại");
		rightPN.add(userTXT);

		JLabel passLabel = new JLabel();
		passLabel.setIcon(new ImageIcon(getClass().getResource("/gui/icon/icons8_lock_20px.png")));
		passLabel.setBounds(30, 130, 30, 30);
		rightPN.add(passLabel);
		passTXT = new JPasswordField() {
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
				g.drawString("Mật khẩu", getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
			}
		};
		passTXT.setOpaque(false);
		passTXT.setForeground(Color.BLACK);
//		passTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		passTXT.setFont(new Font("Segoe ui", Font.PLAIN, 16));
		passTXT.setBounds(60, 130, 200, 30);
		rightPN.add(passTXT);

		checkNhanVien = new JCheckBox("Nhân viên");
		checkNhanVien.setBounds(50, 160, 200, 30);
		checkNhanVien.setOpaque(false);
		checkNhanVien.setForeground(Color.WHITE);
		checkNhanVien.setFont(new Font("Segoe ui", Font.ITALIC, 12));
		checkNhanVien.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rightPN.add(checkNhanVien);

		buttonDangNhap = new JButton("Đăng nhập");
		buttonDangNhap.setFont(new Font("Segoe ui", Font.PLAIN, 14));
		buttonDangNhap.setForeground(Color.WHITE);
		buttonDangNhap.setFocusPainted(false);
		buttonDangNhap.setBounds(30, 220, 110, 40);
		buttonDangNhap.setBackground(new Color(68, 71, 90));
		buttonDangNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rightPN.add(buttonDangNhap);

		buttonDangKy = new JButton("Đăng ký");
		buttonDangKy.setFont(new Font("Segoe ui", Font.PLAIN, 14));
		buttonDangKy.setForeground(Color.WHITE);
		buttonDangKy.setFocusPainted(false);
		buttonDangKy.setBounds(150, 220, 110, 40);
		buttonDangKy.setBackground(new Color(68, 71, 90));
		buttonDangKy.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rightPN.add(buttonDangKy);

		wrapper.add(rightPN);

		String[] listPath = { "/gui/img/picture.jpg", "/gui/img/picture2.jpg", "/gui/img/picture3.jpg",
				"/gui/img/picture4.jpeg" };
		String[] listTitle = { "Welcome TOP TRAVEL", "Du lịch an toàn", "An tâm trải nghiệm",
				"Tận hưởng bản sắc Việt" };
		SlideShowPaneAnimation slideShowPane = new SlideShowPaneAnimation(listPath, 2000, 1000,
				new Rectangle(0, 0, 300, 350), listTitle);
		slideShowPane.start();
		slideShowPane.setBackground(new Color(68, 187, 182));
		slideShowPane.setFontTitle(new Font(MainScreen.FONT_TEXT, Font.BOLD, 24));
		slideShowPane.setColorTitle(Color.WHITE);
		slideShowPane.setBounds(0, 0, 300, 350);
		wrapper.add(slideShowPane);

		GroupLayout wrapperLayout = new GroupLayout(wrapper);
		wrapper.setLayout(wrapperLayout);
		wrapperLayout.setHorizontalGroup(
				wrapperLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 600, Short.MAX_VALUE));
		wrapperLayout.setVerticalGroup(
				wrapperLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 350, Short.MAX_VALUE));
		setContentPane(wrapper);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		userTXT.addActionListener(this);
		passTXT.addActionListener(this);
		buttonDangNhap.addActionListener(this);
		buttonDangKy.addActionListener(this);
	}
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		
//		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Login().setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(userTXT) || o.equals(buttonDangNhap) || o.equals(passTXT)) {
			ktDangNhap();
		}
		else if(o.equals(buttonDangKy)) {
			new SignIn().setVisible(true);
			dispose();
		}
	}
	
	private boolean validForm() {

		String user = userTXT.getText().trim();
		String pass = passTXT.getText().trim();

		if (!(user.length() > 0 && user.matches("^0[0-9]{9}$"))) {
			JOptionPane.showMessageDialog(null, "Nhập sai định dạng.\nTên đăng nhập là số điện thoại (10 chữ số)");
			userTXT.selectAll();
			userTXT.requestFocus();
			return false;
		}

//		if (!(pass.length() > 0 && pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+-=]).{6,20}$"))) {
//			JOptionPane.showMessageDialog(null, "Mật khẩu từ 6 đến 20 kí tự, bao gồm:\n Ít nhất 1 kí tự chữ in hoa\n"
//					+ " Ít nhất 1 kí tự chữ in thường\n Ít nhất 1 kí tự số\n Ít nhất 1 kí tự đặc biệt !@#$%^&*()_+-=");
//			passTXT.selectAll();
//			passTXT.requestFocus();
//			return false;
//		}

		return true;
	}
	private void ktDangNhap() {
		if (validForm() == false) {
			return;
		}
		String user = userTXT.getText().trim();
		String pass = passTXT.getText().trim();

		if (checkNhanVien.isSelected() == true) {

			String passDB = nv_DAO.getPassword(user);
			if (pass.equals(passDB)) {
				new Progress().setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Đăng nhập thất bại.");
			}

		} else {
			if (pass.equals(kh_DAO.getPassword(user))) {
				new GiaoDienKhachHang(user).setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Đăng nhập thất bại.");
			}
		}
	}
}