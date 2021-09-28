package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Message extends JDialog {
	
	private JPanel wrapper;
	private JPanel buttonExit;
	private int xx;
	private int xy;
	private JPanel header;
	private JButton buttonHuy;
	
	public JPanel getWrapper() {
		return wrapper;
	}
	public void setWrapper(JPanel wrapper) {
		this.wrapper = wrapper;
	}
	
	public JPanel getButtonExit() {
		return buttonExit;
	}
	public void setButtonExit(JPanel buttonExit) {
		this.buttonExit = buttonExit;
	}
	
	public JPanel getHeader() {
		return header;
	}
	public void setHeader(JPanel header) {
		this.header = header;
	}
	public JButton getButtonHuy() {
		return buttonHuy;
	}
	public void setButtonHuy(JButton buttonHuy) {
		this.buttonHuy = buttonHuy;
	}
	public Message() {
		setSize(400, 500);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		buidMessage();
	}
	private void buidMessage() {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				requestFocus();
			}
		});
		
		addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				requestFocus();
			}
		});
		
		wrapper = new JPanel();
		wrapper.setBackground(new Color(255, 255, 255, 0));
		wrapper.setLayout(null);
		header = new JPanel();
		header.setBackground(MainScreen.HEADER_COLOR);
		header.setLayout(null);
		header.setBounds(0, 0, 400, 40);
		buttonExit = new JPanel(new BorderLayout());
		JLabel exitLabel = new JLabel();
		exitLabel.setHorizontalAlignment(JLabel.CENTER);
		exitLabel.setIcon(new ImageIcon(getClass().getResource("/gui/icon/multiply_20px.png")));
		buttonExit.add(exitLabel, BorderLayout.CENTER);
		buttonExit.setBounds(360,  0, 40, 40);
		buttonExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonExit.setBackground(MainScreen.HEADER_COLOR);
		buttonExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonExit.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				buttonExit.setBackground(MainScreen.HEADER_COLOR);
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - xx, y - xy - 100);
			}
		});
		header.add(buttonExit);
		wrapper.add(header);
		
		JLabel message = new JLabel("Bạn có muốn thoát chương trình?");
		message.setFont(new Font(MainScreen.FONT_TEXT, Font.ITALIC, 16));
		message.setBounds(80, 80, 300, 20);
		wrapper.add(message);
		
		JButton buttonThoat = new JButton("Đồng ý");
		buttonThoat.requestFocus();
		buttonThoat.setBackground(MainScreen.COLOR_MAIN);
		buttonThoat.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		buttonThoat.setBounds(40, 150, 80, 30);
		buttonThoat.setForeground(Color.WHITE);
		buttonThoat.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonThoat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttonThoat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.exit(0);
				}
			}
		});
		wrapper.add(buttonThoat);
		
		buttonHuy = new JButton("Hủy");
		buttonHuy.setBackground(MainScreen.COLOR_MAIN);
		buttonHuy.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		buttonHuy.setBounds(270, 150, 80, 30);
		buttonHuy.setForeground(Color.WHITE);
		buttonHuy.setCursor(new Cursor(Cursor.HAND_CURSOR));
		wrapper.add(buttonHuy);
		add(wrapper);
		
		
		requestFocus();
	}
}
