package aplicacao_swing;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fachada.Fachada;
import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;
import modelo.Lancamento;

public class TelaPrincipal {
	private JFrame frame;
	private JLabel label;
	private JMenu mnConta;
	private JMenuItem mntmCadastrar;
	private JMenuItem mntmApagar;
	private JMenuItem mntmCriarChave;
	private JMenuItem mntmTransferir;
	private JMenu mnListagem;
	private JMenuItem mntmListarContas;
	private JMenuItem mntmListarCorrentistas;
	private JMenuItem mntmListarlancamentos;
	private JMenu mnConsulta;
	private JMenuItem menuItem;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Sistema PIKS");

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {

//					// cadastro inicial
					Conta conta;
					ContaEspecial contaesp;
					conta = Fachada.criarConta("1", "111.111.001", "9999001", "joao@ifpb", "joao");
					conta = Fachada.criarConta("2", "111.111.002", "9999002", "maria@ifpb", "maria");
					contaesp = Fachada.criarConta("3", 100.0, "111.111.003", "9999003", "ana@ifpb", "ana");
					contaesp = Fachada.criarConta("4", 100.0, "111.111.004", "9999004", "paulo@ifpb", "paulo");
					Fachada.creditar("111.111.001", 500);
					Fachada.creditar("111.111.002", 500);
					Fachada.creditar("111.111.003", 500);
					Fachada.creditar("111.111.004", 500);
					Fachada.criarChave("111.111.001", "cpf");
					Fachada.criarChave("111.111.002", "telefone");
					Fachada.criarChave("111.111.003", "email");
					Fachada.criarChave("111.111.004", "aleatorio");

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "problema no cadastro inicial:" + e.getMessage());
				}

			}
//
//			@Override
//			public void windowClosing(WindowEvent e) {
//				JOptionPane.showMessageDialog(frame, "at?? breve !");
//			}
		});

		frame.setBounds(100, 100, 384, 271);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// imagem de fundo
		label = new JLabel("");
		label.setBounds(0, 0, 384, 220); // fundo da janela

		ImageIcon imagem = new ImageIcon(getClass().getResource("/imagens/dinheiro.jpg"));
		imagem = new ImageIcon(
				imagem.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(imagem);
		frame.getContentPane().add(label);
		frame.setResizable(false);

		// -------------BARRA DE MENU-----------------------------------
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		// -------------MENU Conta-----------------------------------
		mnConta = new JMenu("Conta");
		menuBar.add(mnConta);

		mntmCadastrar = new JMenuItem("Criar");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCriarConta j = new TelaCriarConta();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmCadastrar);

		mntmApagar = new JMenuItem("Apagar");
		mntmApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaApagarConta j = new TelaApagarConta();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmApagar);

		mntmCriarChave = new JMenuItem("Criar chavePIKS");
		mntmCriarChave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCriarChavePIKS j = new TelaCriarChavePIKS();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmCriarChave);

		mntmTransferir = new JMenuItem("Transferir");
		mntmTransferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Transferir transferir = new Transferir();
				transferir.setVisible(true);
			}
		});
		mnConta.add(mntmTransferir);

		// -------------MENU Listagem-----------------------------------
		mnListagem = new JMenu("Listagem");
		menuBar.add(mnListagem);

		mntmListarContas = new JMenuItem("Contas");
		mntmListarContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String texto;
				ArrayList<Conta> lista = Fachada.listarContas();
				texto = "Listagem de contas: \n\n";
				if (lista.isEmpty())
					texto += "n??o existe conta\n";
				else
					for (Conta c : lista)
						texto += c + "\n\n";

				// A mesma tela de listagem ??? usada para v???rias listagens
				TelaListagem j = new TelaListagem(texto);
				j.setVisible(true);
			}
		});
		mnListagem.add(mntmListarContas);

		mntmListarCorrentistas = new JMenuItem("Correntistas");
		mntmListarCorrentistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String texto;
				ArrayList<Correntista> lista = Fachada.listarCorrentistas();
				texto = "Listagem de correntistas: \n\n";
				if (lista.isEmpty())
					texto += "N??o existe correntista\n";
				else
					for (Correntista c : lista)
						texto += c + "\n\n";

				// A mesma tela de listagem ??? usada para v???rias listagens
				TelaListagem j = new TelaListagem(texto);
				j.setVisible(true);
			}
		});
		mnListagem.add(mntmListarCorrentistas);

		mntmListarlancamentos = new JMenuItem("Lancamentos");
		mntmListarlancamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String texto;
				ArrayList<Lancamento> lista = Fachada.listarLancamentos();
				texto = "Listagem de lan??amentos: \n\n";
				if (lista.isEmpty())
					texto += "N??o existe lan??amentos\n";
				else
					for (Lancamento l : lista)
						texto += l + "\n\n";

				TelaListagem j = new TelaListagem(texto);
				j.setVisible(true);
			}
		});
		mnListagem.add(mntmListarlancamentos);

		menuItem = new JMenuItem("Conta top");

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String texto = "Conta top: \n\n";

				try {

					Conta conta = Fachada.obterContaTop();

					if (conta == null) {
						texto += "N??o h?? lan??amentos";
					}
					texto += "Conta: " + conta.getNumero() + "\n" + "N??mero de lan??amentos: "
							+ conta.getLancamentos().size();
				} catch (Exception e) {
					texto += e.getMessage();
				}

				TelaConsulta telaConsulta = new TelaConsulta(texto);
				telaConsulta.setVisible(true);
			}
		});

		mnListagem.add(menuItem);

	}
}
