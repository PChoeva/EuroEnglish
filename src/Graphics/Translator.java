package Graphics;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import backEndPackage.Text;

public class Translator extends JFrame {

	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Translator frame = new Translator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Translator() {
		setTitle("Euro English Translator");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 961, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPaneEnglish = new JTextPane();
		textPaneEnglish.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textPaneEnglish.setBounds(24, 44, 401, 468);
		contentPane.add(textPaneEnglish);
		
		JTextPane textPaneEuroEnglish = new JTextPane();
		textPaneEuroEnglish.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPaneEuroEnglish.setBounds(534, 44, 401, 468);
		contentPane.add(textPaneEuroEnglish);
		

		JButton btnTranslate = new JButton("Translate");
		btnTranslate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Text text = new Text(textPaneEnglish.getText());
				textPaneEuroEnglish.setText(text.translate());
			}
		});
		btnTranslate.setBounds(435, 44, 89, 23);
		contentPane.add(btnTranslate);
		
		JLabel lblEnglish = new JLabel("English");
		lblEnglish.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnglish.setBounds(153, 19, 89, 23);
		contentPane.add(lblEnglish);
		
		JLabel lblEuroenglish = new JLabel("EuroEnglish");
		lblEuroenglish.setForeground(Color.BLACK);
		lblEuroenglish.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEuroenglish.setBounds(682, 19, 89, 23);
		contentPane.add(lblEuroenglish);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(".\\eu-puzzle.jpg"));
		lblNewLabel.setBounds(0, 0, 945, 523);
		contentPane.add(lblNewLabel);
		
	}
}
