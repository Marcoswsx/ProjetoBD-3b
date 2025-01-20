package tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import banco.FabricaConexao;
import dominio.Produto;

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
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CadastrarProdutos extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldPreco;
	private JTextField textFieldTipo;
	private JTextField textFieldQuantidade;
	private JList listarProdutos;
	private Produto ProdutoEdicao;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarProdutos frame = new CadastrarProdutos();
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
	public CadastrarProdutos() throws ClassNotFoundException, SQLException {
		setTitle("CADASTRO DE PRODUTO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 607);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(16, 58, 14));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(16, 58, 14));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CADASTRAR PRODUTO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(225, 221, 189)));
		panel.setBounds(29, 10, 762, 209);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(39, 80, 137, 34);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldPreco = new JTextField();
		textFieldPreco.setBounds(220, 80, 137, 34);
		panel.add(textFieldPreco);
		textFieldPreco.setColumns(10);
		
		textFieldTipo = new JTextField();
		textFieldTipo.setBounds(397, 80, 137, 34);
		panel.add(textFieldTipo);
		textFieldTipo.setColumns(10);
		
		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setBounds(574, 80, 137, 34);
		panel.add(textFieldQuantidade);
		textFieldQuantidade.setColumns(10);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setForeground(new Color(225, 221, 189));
		lblNome.setBackground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNome.setBounds(76, 54, 63, 26);
		panel.add(lblNome);
		
		JLabel lblNewLabel = new JLabel("PREÇO");
		lblNewLabel.setForeground(new Color(225, 221, 189));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(264, 55, 93, 25);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("TIPO");
		lblNewLabel_1.setForeground(new Color(225, 221, 189));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(439, 52, 70, 31);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("QUANTIDADE");
		lblNewLabel_2.setForeground(new Color(225, 221, 189));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(591, 60, 120, 14);
		panel.add(lblNewLabel_2);
		
		btnNewButton = new JButton("Cadastrar");
		btnNewButton.setForeground(new Color(16, 58, 14));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cadastrarProdutos();
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
				}
			}

		});
		btnNewButton.setBounds(273, 144, 236, 44);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(16, 58, 14));
		panel_1.setBackground(new Color(9, 53, 21));
		panel_1.setBounds(76, 265, 647, 273);
		contentPane.add(panel_1);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "LISTA DOS PRODUTOS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(225, 221, 189)));
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(283, 21, 338, 221);
		panel_1.add(scrollPane_1);
		
		listarProdutos =  new JList();
		listarProdutos.setBackground(new Color(225, 221, 189));
		scrollPane_1.setViewportView(listarProdutos);
		
		JButton btnNewButton_Editar = new JButton("Editar Produto");
		btnNewButton_Editar.setForeground(new Color(255, 255, 255));
		btnNewButton_Editar.setBackground(new Color(16, 58, 14));
		btnNewButton_Editar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_Editar.setBounds(55, 53, 175, 31);
		panel_1.add(btnNewButton_Editar);
		
		JButton btnNewButton_Remover = new JButton("Remover");
		btnNewButton_Remover.setForeground(new Color(255, 255, 255));
		btnNewButton_Remover.setBackground(new Color(16, 58, 14));
		btnNewButton_Remover.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_Remover.setBounds(55, 120, 175, 31);
		panel_1.add(btnNewButton_Remover);
		
		JButton btnNewButton_Exibir = new JButton("Exibir Produto");
		btnNewButton_Exibir.setForeground(new Color(255, 255, 255));
		btnNewButton_Exibir.setBackground(new Color(16, 58, 14));
		btnNewButton_Exibir.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_Exibir.setBounds(55, 189, 175, 31);
		panel_1.add(btnNewButton_Exibir);
		btnNewButton_Exibir.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				Produto produtoSelecionado = (Produto) listarProdutos.getSelectedValue();
				
				String msg = "Nome: " + produtoSelecionado.getNome() +
						"\npreco " + produtoSelecionado.getPreco() +
					    "\nTipo " + produtoSelecionado.getTipo() +
					    "\nQuantidade " + produtoSelecionado.getQuantidade();
				
				ExibirMensagem(msg);
				
				
			}
			
			
		});
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
		btnNewButton_Editar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				iniciarEdicaoProduto();
				
			}
			
		});
		
		atualizarListagemProdutos();
		
	}
	

	
	protected void removerDados() throws ClassNotFoundException, SQLException {
		
		if(listarProdutos.getSelectedIndex() == -1) {
			exibirMensagemErro("Selecione um Produto");
		}
		
		ProdutoEdicao = (Produto) listarProdutos.getSelectedValue();
		
		Connection conexao = FabricaConexao.criarConexao();
		
		String sql = "DELETE FROM PRODUTO WHERE ID_PRODUTO =?";
		
		PreparedStatement comando = conexao.prepareStatement(sql);
		
		comando.setInt(1, ProdutoEdicao.getId());
		comando.executeUpdate();
		
		ExibirMensagem("Produto Removidos");
		
		atualizarListagemProdutos();
		
		comando.close();
		conexao.close();
		
		
	}

	protected void iniciarEdicaoProduto() {
		
		if(listarProdutos.getSelectedIndex() == -1) {
			exibirMensagemErro("Selecione um Produto");
		}
		
		ProdutoEdicao = (Produto) listarProdutos.getSelectedValue();
		
		textFieldNome.setText(ProdutoEdicao.getNome());
		textFieldPreco.setText(ProdutoEdicao.getPreco());
		textFieldTipo.setText(ProdutoEdicao.getTipo());
		textFieldQuantidade.setText(ProdutoEdicao.getQuantidade());
		
		btnNewButton.setText("Editar Dados");
		
	}

	protected void ExibirMensagem(String msg) {
		
		JOptionPane.showMessageDialog(null, msg, "produto" , JOptionPane.INFORMATION_MESSAGE);
		
		
	}
	

	private void atualizarListagemProdutos() throws ClassNotFoundException, SQLException {
			
		Connection conexao = FabricaConexao.criarConexao();
		
		String sql = "SELECT * FROM PRODUTO";
		PreparedStatement comando = conexao.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();
		
		List<Produto> ProdutosCadastrados = new ArrayList<Produto>();
		
		while(resultado.next()) {
			Produto a = new Produto();
			
			a.setId(resultado.getInt("id_produto"));
			
			a.setNome(resultado.getString("nome"));
			a.setPreco(resultado.getString("Preco"));
			a.setTipo(resultado.getString("Tipo"));
			a.setQuantidade(resultado.getString("Quantidade"));
			
			ProdutosCadastrados.add(a);
		}
		
		DefaultListModel<Produto> modelo = new DefaultListModel<>();
		
		for(int i =0; i < ProdutosCadastrados.size(); i ++) {
			Produto a = ProdutosCadastrados.get(i);
			modelo.addElement(a);
		}
		
		listarProdutos.setModel(modelo);
		
		comando.close();
		conexao.close();
		
	
	}
	

	protected void cadastrarProdutos() throws ClassNotFoundException, SQLException {
		
		
		if(textFieldNome.getText() == null || textFieldNome.getText().isEmpty()) {
			exibirMensagemErro("Nome não pode ser vazio.");
			return;
		}
		if(textFieldPreco.getText() == null || textFieldPreco.getText().isEmpty()) {
			exibirMensagemErro("Preco não pode ser vazio.");
			return;
		}
		if(textFieldTipo.getText() == null || textFieldTipo.getText().isEmpty()) {
			exibirMensagemErro("Tipo não pode ser vazio.");
			return;
		}
		if(textFieldQuantidade.getText() == null || textFieldQuantidade.getText().isEmpty()) {
			exibirMensagemErro("Quantidade não pode ser vazio.");
			return;
		}
		
		
		if(btnNewButton.getText().equals("Cadastrar")) {
			
			Connection conexao = FabricaConexao.criarConexao();
		
		
		
		String sql = "INSERT INTO PRODUTO (nome,preco,tipo,quantidade) VALUES (?,?,?,?)";
		
		Produto a = new Produto(); 
		
		a.setNome(textFieldNome.getText());
		a.setPreco(textFieldPreco.getText());
		a.setTipo(textFieldTipo.getText());
		a.setQuantidade(textFieldQuantidade.getText());
		
		PreparedStatement comando = conexao.prepareStatement(sql);
		
		comando.setString(1, a.getNome());
		comando.setString(2, a.getPreco());
		comando.setString(3, a.getTipo());
		comando.setString(4, a.getQuantidade());
		comando.execute();
		
		System.out.println("Fechando conexão...");
		
		comando.close();
		conexao.close();
		
		JOptionPane.showMessageDialog(null, "Produto foi cadastrado com sucesso!", "fazenda", JOptionPane.INFORMATION_MESSAGE);
		
	}
		else if(btnNewButton.getText().equals("Editar Dados")) {
			
			Connection conexao = FabricaConexao.criarConexao();
			
			ProdutoEdicao.setNome(textFieldNome.getText());
			ProdutoEdicao.setPreco(textFieldPreco.getText());
			ProdutoEdicao.setTipo(textFieldTipo.getText());
			ProdutoEdicao.setQuantidade(textFieldQuantidade.getText());
			
			String sql = "UPDATE PRODUTO SET NOME =?, PRECO =?, TIPO =?, QUANTIDADE =? WHERE ID_PRODUTO=?";
			
			PreparedStatement comando = conexao.prepareStatement(sql);
			
			comando.setString(1, ProdutoEdicao.getNome());
			comando.setString(2, ProdutoEdicao.getPreco());
			comando.setString(3, ProdutoEdicao.getTipo());
			comando.setString(4, ProdutoEdicao.getQuantidade());
			comando.setInt(5, ProdutoEdicao.getId());
			comando.executeUpdate();
			
			ExibirMensagem("Produtos Alterado");
			
			comando.close();
			conexao.close();
			
			ProdutoEdicao = null;
			
		}
		
		
		atualizarListagemProdutos();
		
		textFieldNome.setText("");
		textFieldPreco.setText("");
		textFieldTipo.setText("");
		textFieldQuantidade.setText("");
		btnNewButton.setText("Cadastrar");
		
	}

	private void exibirMensagemErro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE );
		
	}
}
