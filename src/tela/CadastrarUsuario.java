package tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import banco.FabricaConexao;
import dominio.Usuario;
import util.CriptografiaUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class CadastrarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JLabel lblSenha;
	private JPasswordField passwordFieldSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarUsuario frame = new CadastrarUsuario();
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
	public CadastrarUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 444);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(133, 108, 163, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(194, 88, 47, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CADASTRO DE USUARIO");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblNewLabel_1.setBounds(93, 36, 261, 20);
		contentPane.add(lblNewLabel_1);
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEmail.setBounds(194, 162, 47, 14);
		contentPane.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(133, 182, 163, 20);
		contentPane.add(textFieldEmail);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSenha.setBounds(194, 229, 47, 14);
		contentPane.add(lblSenha);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			
			//função para cadastrar aluno
			public void actionPerformed(ActionEvent e) {
				try {
					cadastrarUsuario();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBounds(133, 317, 163, 41);
		contentPane.add(btnNewButton);
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(133, 270, 163, 20);
		contentPane.add(passwordFieldSenha);
	}
            //metodo para cadastrar aluno
	
	protected void cadastrarUsuario() throws ClassNotFoundException, SQLException {
		
		String nome = textFieldNome.getText();
		String email = textFieldEmail.getText();
		String senha = new String(passwordFieldSenha.getPassword());
		String senhaCriptografada = CriptografiaUtils.criptografarMD5(senha);
		
		   //exibir mensagem de erro
		if(nome == null || nome.isEmpty()) {
			exibirMensagemErro("Nome não pode ser vazio");
		}
		if(email == null || email.isEmpty()) {
			exibirMensagemErro("E-mail não pode ser vazio");
		}
		if(senha == null || senha.isEmpty()) {
			exibirMensagemErro("Senha não pode ser vazio");
		}
		
		  //envia os dados do usuario pro Banco de Dados
		Usuario u = new Usuario();
		u.setNome(nome);
		u.setEmail(email);
		u.setSenha(senhaCriptografada);
		
		String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?,?,?)";
		
		Connection conexao = FabricaConexao.criarConexao();
			
		PreparedStatement comando = conexao.prepareStatement(sql);
		
		comando.setString(1, u.getNome());
		comando.setString(2, u.getEmail());
		comando.setString(3, u.getSenha());
		comando.execute();
		
		comando.close();
		conexao.close();
		
		exibirMensagemErro("usuario - " + nome + " - cadastrado com sucesso!");
	}

	private void exibirMensagemErro(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		
	}
	
	
}
