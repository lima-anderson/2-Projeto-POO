
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o Orientada a Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package repositorio;

import java.util.ArrayList;
import java.util.TreeMap;

import modelo.Conta;
import modelo.Correntista;
import modelo.Lancamento;

public class Repositorio {
	private TreeMap<String, Conta> contas = new TreeMap<>();
	private TreeMap<String, Correntista> correntistas = new TreeMap<>();
	private ArrayList<Lancamento> lancamentos = new ArrayList<>();

	public void adicionar(Conta c) {
		contas.put(c.getChavePiks(), c);
	}

	public void remover(Conta c) {
		contas.remove(c.getChavePiks());
	}

	public Conta localizarConta(String chavePIKS) {
		return contas.get(chavePIKS);
	}

	public void adicionar(Correntista corr) {
		correntistas.put(corr.getCpf(), corr);
	}

	public void remover(Correntista corr) {
		correntistas.remove(corr.getCpf());
	}

	public Correntista localizarCorrentista(String cpf) {
		return correntistas.get(cpf);
	}

	public void adicionar(Lancamento x) {
		lancamentos.add(x);
	}

	public void remover(Lancamento x) {
		lancamentos.remove(x);
	}

	public ArrayList<Conta> getContas() {
		return new ArrayList<>(contas.values());
	}

	public ArrayList<Correntista> getCorrentistas() {
		return new ArrayList<>(correntistas.values());
	}

	public ArrayList<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public int getTotalContas() {
		return contas.size();
	}

	public int getTotalCorrentistas() {
		return correntistas.size();
	}

	public int getTotalLancamentos() {
		return lancamentos.size();
	}

}
