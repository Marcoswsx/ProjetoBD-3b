package tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import banco.FabricaConexao;
import dominio.Aluno;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Color;

public class CadastrarAluno extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldMatricula;
	private JTextField textFieldCurso;
	private JTextField textFieldTelefone;
	private JList listarAlunos;
	private Aluno alunoEdicao;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarAluno frame = new CadastrarAluno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public CadastrarAluno() throws ClassNotFoundException, SQLException {
		setTitle("Cadastro de Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 397);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Castradar Aluno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 173, 312);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(36, 45, 107, 20);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.setBounds(36, 90, 107, 20);
		panel.add(textFieldMatricula);
		textFieldMatricula.setColumns(10);
		
		textFieldCurso = new JTextField();
		textFieldCurso.setBounds(36, 136, 107, 20);
		panel.add(textFieldCurso);
		textFieldCurso.setColumns(10);
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.setBounds(36, 181, 107, 20);
		panel.add(textFieldTelefone);
		textFieldTelefone.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(36, 29, 46, 14);
		panel.add(lblNome);
		
		JLabel lblNewLabel = new JLabel("Matricula");
		lblNewLabel.setBounds(36, 76, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Curso");
		lblNewLabel_1.setBounds(36, 121, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Telefone");
		lblNewLabel_2.setBounds(36, 167, 46, 14);
		panel.add(lblNewLabel_2);
		
		btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cadastrarAluno();
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
				}
			}

		});
		btnNewButton.setBounds(36, 227, 107, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Listagem de Alunos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(183, 0, 374, 312);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 29, 338, 221);
		panel_1.add(scrollPane_1);
		
		listarAlunos =  new JList();
		scrollPane_1.setViewportView(listarAlunos);
		
		JButton btnNewButton_Exibir = new JButton("Exibir Dados");
		btnNewButton_Exibir.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				Aluno alunoSelecionado = (Aluno) listarAlunos.getSelectedValue();
				
				String msg = "Nome: " + alunoSelecionado.getNome() +
						"\nMatricula " + alunoSelecionado.getMatricula() +
					    "\nCurso " + alunoSelecionado.getCurso() +
					    "\nTelefone " + alunoSelecionado.getTelefone();
				
				ExibirMensagem(msg);
				
				
			}
			
			
		});
		btnNewButton_Exibir.setBounds(20, 269, 107, 20);
		panel_1.add(btnNewButton_Exibir);
		
		JButton btnNewButton_Remover = new JButton("Remover");
		btnNewButton_Remover.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					removerDados();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
		});
		btnNewButton_Remover.setBounds(257, 269, 107, 20);
		panel_1.add(btnNewButton_Remover);
		
		JButton btnNewButton_Editar = new JButton("Editar Dados");
		btnNewButton_Editar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				iniciarEdicaoAluno();
				
			}
			
		});
		btnNewButton_Editar.setBounds(137, 269, 107, 20);
		panel_1.add(btnNewButton_Editar);
		
		atualizarListagemAlunos();
		
	}
	

	protected void removerDados() throws ClassNotFoundException, SQLException {
		
		if(listarAlunos.getSelectedIndex() == -1) {
			exibirMensagemErro("Selecione um aluno");
		}
		
		alunoEdicao = (Aluno) listarAlunos.getSelectedValue();
		
		Connection conexao = FabricaConexao.criarConexao();
		
		String sql = "DELETE FROM ALUNO WHERE ID_ALUNO =?";
		
		PreparedStatement comando = conexao.prepareStatement(sql);
		
		comando.setInt(1, alunoEdicao.getId());
		comando.executeUpdate();
		
		ExibirMensagem("Dados Removidos");
		
		atualizarListagemAlunos();
		
		comando.close();
		conexao.close();
		
		
	}

	protected void iniciarEdicaoAluno() {
		
		if(listarAlunos.getSelectedIndex() == -1) {
			exibirMensagemErro("Selecione um aluno");
		}
		
		alunoEdicao = (Aluno) listarAlunos.getSelectedValue();
		
		textFieldNome.setText(alunoEdicao.getNome());
		textFieldMatricula.setText(alunoEdicao.getMatricula());
		textFieldCurso.setText(alunoEdicao.getCurso());
		textFieldTelefone.setText(alunoEdicao.getTelefone());
		
		btnNewButton.setText("Editar Dados");
		
	}

	protected void ExibirMensagem(String msg) {
		
		JOptionPane.showMessageDialog(null, msg, "info" , JOptionPane.INFORMATION_MESSAGE);
		
		
	}
	

	private void atualizarListagemAlunos() throws ClassNotFoundException, SQLException {
			
		Connection conexao = FabricaConexao.criarConexao();
		
		String sql = "SELECT * FROM ALUNO";
		PreparedStatement comando = conexao.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();
		
		List<Aluno> alunosCadastrados = new ArrayList<Aluno>();
		
		while(resultado.next()) {
			Aluno a = new Aluno();
			
			a.setId(resultado.getInt("id_aluno"));
			
			a.setNome(resultado.getString("nome"));
			a.setMatricula(resultado.getString("Matricula"));
			a.setCurso(resultado.getString("Curso"));
			a.setTelefone(resultado.getString("telefone"));
			
			alunosCadastrados.add(a);
		}
		
		DefaultListModel<Aluno> modelo = new DefaultListModel<>();
		
		for(int i =0; i < alunosCadastrados.size(); i ++) {
			Aluno a = alunosCadastrados.get(i);
			modelo.addElement(a);
		}
		
		listarAlunos.setModel(modelo);
		
		comando.close();
		conexao.close();
		
	
	}
	

	protected void cadastrarAluno() throws ClassNotFoundException, SQLException {
		
		
		if(textFieldNome.getText() == null || textFieldNome.getText().isEmpty()) {
			exibirMensagemErro("Nome não pode ser vazio.");
			return;
		}
		if(textFieldMatricula.getText() == null || textFieldMatricula.getText().isEmpty()) {
			exibirMensagemErro("matricula não pode ser vazio.");
			return;
		}
		if(textFieldCurso.getText() == null || textFieldCurso.getText().isEmpty()) {
			exibirMensagemErro("Curso não pode ser vazio.");
			return;
		}
		if(textFieldTelefone.getText() == null || textFieldTelefone.getText().isEmpty()) {
			exibirMensagemErro("Telefone não pode ser vazio.");
			return;
		}
		
		
		if(btnNewButton.getText().equals("Cadastrar")) {
			
			Connection conexao = FabricaConexao.criarConexao();
		
		
		
		String sql = "INSERT INTO ALUNO (nome,matricula,curso,telefone) VALUES (?,?,?,?)";
		
		Aluno a = new Aluno(); 
		
		a.setNome(textFieldNome.getText());
		a.setMatricula(textFieldMatricula.getText());
		a.setCurso(textFieldCurso.getText());
		a.setTelefone(textFieldTelefone.getText());
		
		PreparedStatement comando = conexao.prepareStatement(sql);
		
		comando.setString(1, a.getNome());
		comando.setString(2, a.getMatricula());
		comando.setString(3, a.getCurso());
		comando.setString(4, a.getTelefone());
		comando.execute();
		
		System.out.println("Fechando conexão...");
		
		comando.close();
		conexao.close();
		
		JOptionPane.showMessageDialog(null, "aluno foi cadastrado com sucesso!", "Info", JOptionPane.INFORMATION_MESSAGE);
		
	}
		else if(btnNewButton.getText().equals("Editar Dados")) {
			
			Connection conexao = FabricaConexao.criarConexao();
			
			alunoEdicao.setNome(textFieldNome.getText());
			alunoEdicao.setMatricula(textFieldMatricula.getText());
			alunoEdicao.setCurso(textFieldCurso.getText());
			alunoEdicao.setTelefone(textFieldTelefone.getText());
			
			String sql = "UPDATE ALUNO SET NOME =?, MATRICULA =?, CURSO =?, TELEFONE =? WHERE ID_ALUNO=?";
			
			PreparedStatement comando = conexao.prepareStatement(sql);
			
			comando.setString(1, alunoEdicao.getNome());
			comando.setString(2, alunoEdicao.getMatricula());
			comando.setString(3, alunoEdicao.getCurso());
			comando.setString(4, alunoEdicao.getTelefone());
			comando.setInt(5, alunoEdicao.getId());
			comando.executeUpdate();
			
			ExibirMensagem("Dados Alterado");
			
			comando.close();
			conexao.close();
			
			alunoEdicao = null;
			
		}
		
		
		atualizarListagemAlunos();
		
		textFieldNome.setText("");
		textFieldMatricula.setText("");
		textFieldCurso.setText("");
		textFieldTelefone.setText("");
		
		
	}

	private void exibirMensagemErro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE );
		
	}
}
