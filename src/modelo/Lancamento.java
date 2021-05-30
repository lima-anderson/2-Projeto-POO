package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Lancamento {

	private String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy' - 'HH:mm:ss"));
	private Double valor;
	private String numero;

	public Lancamento(Double valor, String numero) {
		this.valor = valor;
		this.numero = numero;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDataHora() {
		return dataHora;
	}

	public String toString() {
		return "Data: " + dataHora + "\nValor: " + valor + "\nNúmero: " + numero;
	}

}
