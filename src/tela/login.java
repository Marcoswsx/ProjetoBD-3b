package tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JPasswordField passwordFieldSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(130, 81, 166, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(130, 130, 166, 20);
		contentPane.add(passwordFieldSenha);
		
		JLabel lblNewLabel = new JLabel("E-mail:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(84, 84, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setBounds(84, 133, 53, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("LOGIN");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_2.setBounds(182, 34, 67, 20);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButtonEntrar = new JButton("Entrar");
		btnNewButtonEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fazerLogin();
				
			}
		});
		btnNewButtonEntrar.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButtonEntrar.setBounds(148, 185, 131, 32);
		contentPane.add(btnNewButtonEntrar);
	}

	protected void fazerLogin() {
		
		
		
	}
}
