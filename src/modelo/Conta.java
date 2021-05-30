package modelo;

import java.util.ArrayList;

public class Conta {
	private String numero;
	private double saldo;
	private String chavePiks;
	private String tipoChavePiks;
	private ArrayList<Lancamento> lancamentos = new ArrayList<>();
	private Correntista correntista;

	public Conta(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getChavePiks() {
		return chavePiks;
	}

	public void setChavePiks(String chavePiks) {
		this.chavePiks = chavePiks;
	}

	public String getTipoChavePiks() {
		return tipoChavePiks;
	}

	public void setTipoChavePiks(String tipoChavePiks) {
		this.tipoChavePiks = tipoChavePiks;
	}

	public ArrayList<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public Correntista getCorrentista() {
		return correntista;
	}

	public void setCorrentista(Correntista correntista) {
		this.correntista = correntista;
	}

	public void adicionarLancamento(Lancamento lancamento) {
		lancamentos.add(lancamento);
	}

	public void creditar(double quantia) {
		setSaldo(getSaldo() + quantia);
	}

	public void debitar(double valor) throws Exception {

		if ((this.saldo - valor) < 0) {
			throw new Exception("Saldo insuficiente");
		}

		this.saldo -= valor;
	}

	public void transferir(double valor, Conta destino) throws Exception {
		this.debitar(valor);
		destino.creditar(valor);
	}

	public String toString() {
		return "Número: " + numero + "\nSaldo: " + saldo + "\nChave Pks: " + chavePiks + "\nTipo de Chave: "
				+ tipoChavePiks + "\nLancamentos: " + lancamentos + "\nCorrentista: " + correntista.getNome();
	}

}

/*
 * metodos creditar(), debitar() e transferir() // capitulos 3 e 4 metodo
 * debitar() nao deve permitir saldo negativo
 * 
 */