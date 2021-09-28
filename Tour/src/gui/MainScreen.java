package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import dohuyhoang.animation.AnimationPane;
import dohuyhoang.animation.AnimationPaneColor;
import dohuyhoang.roundedpane.RoundedPane;

public class MainScreen extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Color COLOR_MAIN = new Color(26,34,38);
	static Color HEADER_COLOR = new Color(0, 166, 90);
	static Color SUBPANE_COLOR = new Color(245,248,253);
	static Color COLOR_TEXT = new Color(0, 0, 0);
	static Color COLOR_SUB_TEXT = new Color(12829635);
	static Color BACKGROUND_COLOR = new Color(233, 235, 250);
	static String FONT_TEXT = "Segoe ui";
	private final int radius = 10;
	private JPanel wrapper;
	private JPanel shadowBlack;
	private TrangChu_Pane trangchu;
	private int xx;
	private int xy;
	private KhachHang_Pane khachHang;
	private ChuyenDi_Pane chuyenDi;
	private Message message;
	private JScrollPane spTrangChu;
	private DiaDanh_Pane diaDanh;
	private NhanVien_Pane nhanVien_Pane;
	private HuongDanVien_Pane huongDanVien_Pane;
	private DonDatVe_Pane donDatVe_Pane;

	public MainScreen() {
		setUndecorated(true);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		setLocation(120, 10);
		requestFocus();
		buidMainScreen();
	}

	private void buidMainScreen() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		wrapper = new RoundedPane(radius);
		wrapper.setOpaque(false);
		wrapper.setBackground(BACKGROUND_COLOR);
		wrapper.setLayout(null);
		
		Nav nav = new Nav(radius, "right");
		nav.setBounds(0, 0, 60, 800);
		nav.getMenuBtn().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(!(message.isVisible())) {
					if(nav.getWidth() == 60) {
							AnimationPane animation = new AnimationPane(nav, new Rectangle(nav.getX(), nav.getY(), 60, nav.getHeight()), new Rectangle(nav.getX(), nav.getY(), 200, nav.getHeight()));
							animation.start();
							shadowBlack.setVisible(true);
							AnimationPane animation2 = new AnimationPane(nav.getMenuBtn(), new Rectangle(-140, 0, 200, 60), new Rectangle(0, 0, 200, 60));
							animation2.start();
							shadowBlack.requestFocus();
							chuyenDi.slideShowStop();
					}
					else {
						AnimationPane animation = new AnimationPane(nav, new Rectangle(nav.getX(), nav.getY(), 200, nav.getHeight()), new Rectangle(nav.getX(), nav.getY(), 60, nav.getHeight()));
						animation.start();
						shadowBlack.setVisible(false);
						AnimationPane animation2 = new AnimationPane(nav.getMenuBtn(), new Rectangle(0, 0, 200, 60), new Rectangle(-140, 0, 200, 60));
						animation2.start();
						chuyenDi.slideShowStart();
					}
				}
			}
		});
		wrapper.add(nav);
		
		
		
		shadowBlack = new RoundedPane(radius);
		shadowBlack.setOpaque(false);
		shadowBlack.setBackground(new Color(0, 0, 0, 0.3f));
		shadowBlack.setVisible(false);
		shadowBlack.setBounds(0, 0, 1300, 800);
		shadowBlack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(!(message.isVisible())) {
					AnimationPane animation = new AnimationPane(nav, new Rectangle(nav.getX(), nav.getY(), 200, nav.getHeight()), new Rectangle(nav.getX(), nav.getY(), 60, nav.getHeight()));
					animation.start();
					shadowBlack.setVisible(false);
					AnimationPane animation2 = new AnimationPane(nav.getMenuBtn(), new Rectangle(0, 0, 200, 60), new Rectangle(-140, 0, 200, 60));
					animation2.start();
					chuyenDi.slideShowStart();
				}
			}
		});
		wrapper.add(shadowBlack);
		
		Header header = new Header(radius, "bottom");
		header.setBounds(0, 0, 1300, 60);
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
				setLocation(x - xx, y - xy);
			}
		});
		message = new Message();
		header.getExitPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				message.getWrapper().setBounds(new Rectangle());
				shadowBlack.setVisible(true);
				message.setVisible(true);
				AnimationPane animation = new AnimationPane(message.getWrapper(), new Rectangle(0, 0, 400, 200), new Rectangle(0, 100, 400, 200));
				AnimationPaneColor animationColor = new AnimationPaneColor(message.getWrapper(), MainScreen.BACKGROUND_COLOR);
				animationColor.start();
				animation.start();
				message.setLocationRelativeTo(null);
				shadowBlack.requestFocus();
				chuyenDi.slideShowStop();
			}
		});
		message.getButtonExit().addMouseListener(new MouseAdapter() {
			private Timer tm;

			@Override
			public void mousePressed(MouseEvent e) {
				AnimationPane animationPane = new AnimationPane(message.getWrapper(), new Rectangle(0, 100, 400, 200), new Rectangle(0, 200, 400, 200));
				animationPane.start();
				AnimationPaneColor animationPaneColor = new AnimationPaneColor(message.getWrapper(), new Color(255, 255, 255, 0));
				animationPaneColor.start();
				tm =new Timer(200, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						shadowBlack.setVisible(false);
						message.dispose();
						message.invalidate();
						requestFocus();
						tm.stop();
						message.getWrapper().setLocation(0, 0);
						message.getWrapper().repaint();
					}
				});
				tm.start();
				chuyenDi.slideShowStart();
			}
		});
		message.getButtonHuy().addMouseListener(new MouseAdapter() {
			private Timer tm;
			@Override
			public void mousePressed(MouseEvent e) {
				AnimationPane animationPane = new AnimationPane(message.getWrapper(), new Rectangle(0, 100, 400, 200), new Rectangle(0, 200, 400, 200));
				animationPane.start();
				AnimationPaneColor animationPaneColor = new AnimationPaneColor(message.getWrapper(), new Color(255, 255, 255, 0));
				animationPaneColor.start();
				tm =new Timer(200, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						shadowBlack.setVisible(false);
						message.dispose();
						message.invalidate();
						requestFocus();
						tm.stop();
						message.getWrapper().setLocation(0, 0);
						message.getWrapper().repaint();
					}
				});
				tm.start();
				chuyenDi.slideShowStart();
			}
		});
		wrapper.add(header);
		
		
		trangchu = new TrangChu_Pane();
//		trangchu.setBounds(60, 100, 1240, 680);
		nav.getTrangChuBtn().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				khachHang.setVisible(false);
				chuyenDi.setVisible(false);
				diaDanh.setVisible(false);
				nhanVien_Pane.setVisible(false);
				huongDanVien_Pane.setVisible(false);
				donDatVe_Pane.setVisible(false);
				spTrangChu.setVisible(true);
			}
		});
		trangchu.setPreferredSize(new Dimension(1210, 1000));
		spTrangChu = new JScrollPane(trangchu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		spTrangChu.setBounds(60, 100, 1240, 680);
		spTrangChu.getVerticalScrollBar().setUnitIncrement(16);
		wrapper.add(spTrangChu);
		
		
		khachHang = new KhachHang_Pane();
		khachHang.setVisible(false);
		khachHang.setBounds(60, 100, 1240, 680);
		nav.getKhachHangBtn().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				spTrangChu.setVisible(false);
				chuyenDi.setVisible(false);
				diaDanh.setVisible(false);
				nhanVien_Pane.setVisible(false);
				huongDanVien_Pane.setVisible(false);
				donDatVe_Pane.setVisible(false);
				khachHang.setVisible(true);
			}
		});
		wrapper.add(khachHang);
		
		chuyenDi = new ChuyenDi_Pane();
		chuyenDi.setVisible(false);
		chuyenDi.setBounds(60, 100, 1240, 680);
		nav.getTourBtn().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				spTrangChu.setVisible(false);
				khachHang.setVisible(false);
				diaDanh.setVisible(false);
				nhanVien_Pane.setVisible(false);
				huongDanVien_Pane.setVisible(false);
				donDatVe_Pane.setVisible(false);
				chuyenDi.setVisible(true);
			}
		});
		wrapper.add(chuyenDi);
		
		diaDanh= new DiaDanh_Pane();
		diaDanh.setVisible(false);
		diaDanh.setBounds(60, 100, 1240, 680);
		nav.getDiaDanhBtn().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				spTrangChu.setVisible(false);
				khachHang.setVisible(false);
				chuyenDi.setVisible(false);
				nhanVien_Pane.setVisible(false);
				huongDanVien_Pane.setVisible(false);
				donDatVe_Pane.setVisible(false);
				diaDanh.setVisible(true);
			}
		});
		wrapper.add(diaDanh);
		
		nhanVien_Pane = new NhanVien_Pane();
		nhanVien_Pane.setVisible(false);
		nhanVien_Pane.setBounds(60, 100, 1240, 680);
		nav.getNhanVienBtn().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				spTrangChu.setVisible(false);
				khachHang.setVisible(false);
				chuyenDi.setVisible(false);
				diaDanh.setVisible(false);
				huongDanVien_Pane.setVisible(false);
				donDatVe_Pane.setVisible(false);
				nhanVien_Pane.setVisible(true);
			}
		});
		wrapper.add(nhanVien_Pane);
		
		huongDanVien_Pane = new HuongDanVien_Pane(); 
		huongDanVien_Pane.setVisible(false);
		huongDanVien_Pane.setBounds(60, 100, 1240, 680);
		nav.getHuongDanVienBtn().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				spTrangChu.setVisible(false);
				khachHang.setVisible(false);
				chuyenDi.setVisible(false);
				diaDanh.setVisible(false);
				nhanVien_Pane.setVisible(false);
				donDatVe_Pane.setVisible(false);
				huongDanVien_Pane.setVisible(true);
			}
		});
		wrapper.add(huongDanVien_Pane);
		
		
		donDatVe_Pane = new DonDatVe_Pane();
		donDatVe_Pane.setVisible(false);
		donDatVe_Pane.setBounds(60, 100, 1240, 680);
		nav.getDonDatVeBtn().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				spTrangChu.setVisible(false);
				khachHang.setVisible(false);
				chuyenDi.setVisible(false);
				diaDanh.setVisible(false);
				nhanVien_Pane.setVisible(false);
				huongDanVien_Pane.setVisible(false);
				donDatVe_Pane.setVisible(true);
			}
		});
		
		wrapper.add(donDatVe_Pane);
		
		// Create wrapper layout
		GroupLayout wrapperLayout = new GroupLayout(wrapper);
		wrapper.setLayout(wrapperLayout);
		wrapperLayout.setHorizontalGroup(
				wrapperLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 1300, Short.MAX_VALUE));
		wrapperLayout.setVerticalGroup(
				wrapperLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 800, Short.MAX_VALUE));
		setContentPane(wrapper);
		pack();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				message.getWrapper().setBounds(new Rectangle());
				shadowBlack.setVisible(true);
				message.setVisible(true);
				AnimationPane animation = new AnimationPane(message.getWrapper(), new Rectangle(0, 0, 400, 200), new Rectangle(0, 100, 400, 200));
				AnimationPaneColor animationColor = new AnimationPaneColor(message.getWrapper(), MainScreen.BACKGROUND_COLOR);
				animationColor.start();
				animation.start();
				message.setLocationRelativeTo(null);
			}
		});
	}
}

