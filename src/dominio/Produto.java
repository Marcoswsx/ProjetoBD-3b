package dominio;

public class Produto {

	
    @Override
	public String toString() {
		return "nome=" + nome + " - " + "quantidade=" + quantidade;
	}
	private int id;
    private String nome;
    private String preco;
    private String tipo;
    private String quantidade;
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
