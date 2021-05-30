package fachada;

import java.util.ArrayList;
import java.util.Random;

import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;
import modelo.Lancamento;
import repositorio.Repositorio;

public class Fachada {
	private static Repositorio repositorio = new Repositorio();

	public static ArrayList<Conta> listarContas() {
		return repositorio.getContas();
	}

	public static ArrayList<Correntista> listarCorrentistas() {
		return repositorio.getCorrentistas();
	}

	public static ArrayList<Lancamento> listarLancamentos() {
		return repositorio.getLancamentos();
	}

	public static Conta criarConta(String numero, String cpf, String telefone, String email, String nome)
			throws Exception {
		Correntista correntista = repositorio.localizarCorrentista(cpf);
		if (correntista != null)
			throw new Exception("correntista ja existe:" + cpf);

		correntista = new Correntista(cpf, telefone, email, nome);
		Conta conta = new Conta(numero);

		conta.setCorrentista(correntista);
		correntista.setConta(conta);

		repositorio.adicionar(correntista);
		return conta;
	}

	public static ContaEspecial criarConta(String numero, double limite, String cpf, String telefone, String email,
			String nome) throws Exception {
		Correntista correntista = repositorio.localizarCorrentista(cpf);
		if (correntista != null)
			throw new Exception("correntista ja existe:" + cpf);

		correntista = new Correntista(cpf, telefone, email, nome);
		ContaEspecial conta = new ContaEspecial(numero, limite);

		conta.setCorrentista(correntista);
		correntista.setConta(conta);

		repositorio.adicionar(correntista);
		return conta;
	}

	public static void criarChave(String cpf, String tipochavePIKS) throws Exception {

		Correntista correntista = repositorio.localizarCorrentista(cpf);

		if (correntista == null)
			throw new Exception("correntista inexistente");

		Conta conta = correntista.getConta();

		if (tipochavePIKS == "cpf") {
			conta.setChavePiks(correntista.getCpf());
		} else if (tipochavePIKS == "email") {
			conta.setChavePiks(correntista.getEmail());
		} else if (tipochavePIKS == "telefone") {
			conta.setChavePiks(correntista.getTelefone());
		} else {
			Random random = new Random();
			String chave = "";
			for (int i = 0; i <= 8; i++) {
				int numero = random.nextInt(9);
				chave += numero;
			}

			conta.setChavePiks(chave);
		}

		repositorio.adicionar(conta);
	}

	public static void transferir(String cpf, String chavePIKS, double quantia) throws Exception {
		Correntista correntista = repositorio.localizarCorrentista(cpf);
		if (correntista == null)
			throw new Exception("correntista inexistente");

		Conta contaOrigem = correntista.getConta();
		Conta contaDestino = repositorio.localizarConta(chavePIKS);

		if (contaDestino == null)
			throw new Exception("conta destino invalida" + chavePIKS);

		if (contaOrigem.getNumero().equals(contaDestino.getNumero())) {
			throw new Exception("A conta destino deve ser diferente da conta origem");
		}

		contaOrigem.debitar(quantia);
		Lancamento lancamentoOrigem = new Lancamento(-quantia, contaDestino.getNumero());
		contaOrigem.adicionarLancamento(lancamentoOrigem);
		repositorio.adicionar(lancamentoOrigem);

		contaDestino.creditar(quantia);
		Lancamento lancamentoDestino = new Lancamento(quantia, contaOrigem.getNumero());
		contaDestino.adicionarLancamento(lancamentoDestino);
		repositorio.adicionar(lancamentoDestino);

	}

	public static void apagarConta(String cpf) throws Exception {
		Correntista correntista = repositorio.localizarCorrentista(cpf);
		if (correntista == null)
			throw new Exception("correntista inexistente");

		Conta conta = correntista.getConta();

		if (conta.getSaldo() != 0) {
			throw new Exception("Conta não pode ser excluída.");
		}

		repositorio.remover(conta);
		repositorio.remover(correntista);

		ArrayList<Lancamento> lancamentos = conta.getLancamentos();

		for (int i = 0; i < lancamentos.size(); i++) {
			repositorio.remover(lancamentos.get(i));
		}

	}

	public static Conta obterContaTop() throws Exception {

		ArrayList<Conta> contas = repositorio.getContas();

		Conta contaComMaisLancamentos = null;
		int maior = 0;
		for (Conta conta : contas) {
			if (conta.getLancamentos().size() > maior) {
				maior = conta.getLancamentos().size();
				contaComMaisLancamentos = conta;
			}
		}
		
		if (contaComMaisLancamentos == null) {
			throw new Exception("Não há lançamentos");
		}
		return contaComMaisLancamentos;
	}

	public static void creditar(String cpf, double quantia) throws Exception {

		Correntista correntista = repositorio.localizarCorrentista(cpf);
		Conta conta = correntista.getConta();

		conta.creditar(quantia);
	}

	public static Conta obterConta(String cpf) {

		Correntista Correntista = repositorio.localizarCorrentista(cpf);
		return Correntista.getConta();
	}

}
