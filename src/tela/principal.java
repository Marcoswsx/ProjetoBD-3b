package tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal frame = new principal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("CADASTRAR PRODUTO");
		btnNewButton.setForeground(new Color(0, 64, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				CadastrarProdutos ca = null;
				try {
					ca = new CadastrarProdutos();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				ca.setLocationRelativeTo(null);
				ca.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				ca.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(78, 151, 289, 43);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("FAZENDA SÃO MARCOS");
		lblNewLabel.setForeground(new Color(16, 58, 14));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(43, 40, 364, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\marco\\OneDrive - Instituto Federal do Rio Grande do Norte\\Documents\\ProjetoBD-3b\\imagens\\soja2.jpg"));
		lblNewLabel_1.setBounds(0, 0, 455, 572);
		contentPane.add(lblNewLabel_1);
	}
}
