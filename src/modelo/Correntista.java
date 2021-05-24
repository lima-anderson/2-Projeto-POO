package modelo;

public class Correntista {
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private Conta conta;

	public Correntista(String cpf, String nome, String telefone, String email) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
		conta.setCorrentista(this);
	}

	public String toString() {
		return "Cpf: " + cpf + "\nNome: " + nome + "\nTelefone: " + telefone + "\nE-mail: " + email + "\nConta: "
				+ conta.getNumero();
	}
}
