package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dohuyhoang.animation.slideshow.SlideShowPane;

public class TrangChu_Pane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel tieuDe;
	private SlideShowPane slideShowPane;
	public SlideShowPane getSlideShowPane() {
		return slideShowPane;
	}
	public void setSlideShowPane(SlideShowPane slideShowPane) {
		this.slideShowPane = slideShowPane;
	}
	public TrangChu_Pane() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		buidTrangChu();
	}
	private void buidTrangChu() {
		tieuDe = new JLabel();
		tieuDe.setText("Trang chủ");
		tieuDe.setForeground(MainScreen.COLOR_TEXT);
		tieuDe.setFont(new Font(MainScreen.FONT_TEXT, Font.BOLD, 24));
		tieuDe.setBounds(10, 10, 200, 50);
		add(tieuDe);
		
		JLabel slogan = new JLabel("Phần mềm quản lý Tour du lịch") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setColor(Color.WHITE);
				g.drawString("Phần mềm quản lý Tour du lịch", getInsets().left - 2, g.getFontMetrics().getMaxAscent() + getInsets().top + 2);
			}
		};
		slogan.setBounds(30, 100, 500, 50);
		slogan.setFont(new Font(MainScreen.FONT_TEXT, Font.BOLD, 30));
		slogan.setForeground(Color.RED);
		add(slogan);
		
		
		JLabel anh = new JLabel();
		anh.setBounds(10, 70, 1205, 600);
		ImageIcon icon = new ImageIcon(getClass().getResource("/gui/img/e10ea84d99046ff773646e2b91a5d670.jpg"));
		Image img = icon.getImage().getScaledInstance(anh.getWidth(), anh.getHeight(), Image.SCALE_REPLICATE);
		anh.setIcon(new ImageIcon(img));
		add(anh);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.red);
		panel.setBounds(10, 1000, 300, 300);
		add(panel);
	}
}
