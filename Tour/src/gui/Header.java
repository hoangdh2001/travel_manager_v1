package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dohuyhoang.roundedpane.RoundedPane;

public class Header extends RoundedPane implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dd;
	private Thread th;
	private JLabel dateLbl;
	private JLabel thoiGian;
	private JPanel exitPanel;
	public JPanel getExitPanel() {
		return exitPanel;
	}
	public void setExitPanel(JPanel exitPanel) {
		this.exitPanel = exitPanel;
	}
	public Header(int radius, String direction) {
		super(radius, direction);
		setLayout(null);
		setBackground(new Color(0, 166, 90));
		setOpaque(false);
		buidHeader();
	}
	private void buidHeader() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dateLbl = new JLabel();
		dateLbl.setText("Date: " + dtf.format(date));
		dateLbl.setFont(new Font("Segoe ui", Font.PLAIN, 16));
		dateLbl.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(200, 200, 200)));
		dateLbl.setForeground(Color.WHITE);
		dateLbl.setBounds(70, 20, 140, 20);
		add(dateLbl);
		
		thoiGian = new JLabel();
		thoiGian.setForeground(Color.WHITE);
		thoiGian.setFont(new Font("Segoe ui", Font.PLAIN, 16));
		thoiGian.setBounds(220, 20, 150, 20);
		add(thoiGian);
		th = new Thread(this);
		th.start();
		
		exitPanel = new JPanel();
		exitPanel.setToolTipText("Thoát");
		exitPanel.setBounds(1240, 0, 60, 60);
		exitPanel.setLayout(new BorderLayout());
		exitPanel.setBackground(MainScreen.COLOR_MAIN);
		JLabel exit = new JLabel("", JLabel.CENTER);
		exit.setIcon(new ImageIcon(getClass().getResource("/gui/icon/exit_30px.png")));
		exitPanel.add(exit, BorderLayout.CENTER);
		exitPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		exitPanel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				exitPanel.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitPanel.setBackground(MainScreen.COLOR_MAIN);
			}
		});
		add(exitPanel);
	}
	@Override
	public void run() {
		try
        {
            do
            {
                dd = new Date();
                thoiGian.setText("Time: " + dd.getHours() + " : " + dd.getMinutes() + " : " + dd.getSeconds());
                Thread.sleep(1000);  // 1000 = 1 second
            }while(th.isAlive());
        }
        catch(Exception e)
        {
        	
        }
	}
}
