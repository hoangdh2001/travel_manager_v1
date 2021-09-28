package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import connect.ConnectDB;
import dao.CT_ThamQuan_DAO;
import dao.KhachHang_DAO;
import entity.CT_ThamQuan;
import entity.KhachHang;

public class GiaoDienKhachHang extends JFrame {
	private int width = 800;
	private int height = 800;
	private CT_ThamQuan_DAO ct_ThamQuan_DAO;
	private JList<CT_ThamQuan> list;
	private DefaultListModel<CT_ThamQuan> model;
	private JScrollPane panel;
	private KhachHang khachHang;

	public GiaoDienKhachHang(String user) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e2) {
			// TODO: handle exception
		}
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		ct_ThamQuan_DAO = new CT_ThamQuan_DAO();
		khachHang = KhachHang_DAO.getKhachHangBangSdt(user);
		setTitle("Đặt tour");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		buidGiaoDienKhachHang();
		
	}
	
	private void buidGiaoDienKhachHang() {
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BorderLayout(5, 5));
		wrapper.setBackground(MainScreen.BACKGROUND_COLOR);
		
		model = new DefaultListModel<>();
		list = new JList<CT_ThamQuan>(model);
		list.setCellRenderer(new CellRenderer());
		list.setFixedCellHeight(200);
		list.setSelectionBackground(new Color(67, 198, 209));
		list.addMouseListener(new MouseAdapter() {
			CT_ThamQuan_Pane ct_ThamQuan_Pane;
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					wrapper.setVisible(false);
					ct_ThamQuan_Pane = new CT_ThamQuan_Pane(list.getSelectedValue(), khachHang);
					ct_ThamQuan_Pane.setVisible(true);
					ct_ThamQuan_Pane.setBounds(0, 0, width, height);
					getContentPane().add(ct_ThamQuan_Pane);
					ct_ThamQuan_Pane.getBack().addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							ct_ThamQuan_Pane.setVisible(false);
							list.clearSelection();
							themVaoDanhSachTour(1);
							wrapper.setVisible(true);
						}
					});
				}
				else {
					 
				}
			}
		});
		list.setLayout(null);
		panel = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setBounds(0, 100, width, 500);
		panel.getVerticalScrollBar().setUnitIncrement(16);
		wrapper.add(panel, BorderLayout.CENTER);
		
		int tongTrang = 1;
		int soLuongChuyenDi = ct_ThamQuan_DAO.getSoLuong();
		tongTrang = soLuongChuyenDi % 5 == 0 ? soLuongChuyenDi / 5 : (soLuongChuyenDi / 5) + 1;
		JPanel footer = new JPanel();
		for (int i = 1; i <= tongTrang; i++) {
			JButton trang = new JButton(i + "");
			trang.setOpaque(true);
			trang.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int page = Integer.parseInt(trang.getText());
					themVaoDanhSachTour(page);
				}
			}); 
			footer.add(trang);
		}
		
		wrapper.add(footer, BorderLayout.SOUTH);
		getContentPane().add(wrapper);
		themVaoDanhSachTour(1);
	}

	private void themVaoDanhSachTour(int page) {
		panel.getVerticalScrollBar().setValue(0);
		model.removeAllElements();
		model.addAll(ct_ThamQuan_DAO.themVaoBangCT_ThamQuan(page - 1));
	}
}
