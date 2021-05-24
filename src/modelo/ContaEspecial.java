package modelo;

public class ContaEspecial extends Conta {

	private double limite;

	public ContaEspecial(String numero, double limite) {
		super(numero);
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public boolean debitar(double valor) {
		this.setSaldo(getSaldo() - valor);
		return true;
	}

}