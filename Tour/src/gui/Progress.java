package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class Progress extends JFrame {
	private Timer tm;
	private int x = 0;
	public Progress() {
		setSize(500, 200);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				requestFocus();
			}
		});
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		JPanel wrapper = new JPanel();
		wrapper.setBackground(new Color(0, 0, 0, 0));
		wrapper.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		JProgressBar progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		tm = new Timer(1, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(x);
				progressBar.setString(x + "%");
				x++;
				if(x == 32 || x == 62 || x == 92) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				if(x > 101) {
					new MainScreen().setVisible(true);
					dispose();
					tm.stop();
				}
			}
		});
		tm.start();
		progressBar.setForeground(Color.WHITE);
		progressBar.setFont(new Font("Segoe ui", Font.BOLD, 16));
		progressBar.setStringPainted(true);
		progressBar.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		progressBar.setBounds(100, 90, 300, 30);
		wrapper.setLayout(null);
		wrapper.add(progressBar);
		add(wrapper);
		requestFocus();
	}
}
