package aplicacao_swing;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import repositorio.Repositorio;

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
//
//					// cadastro inicial
					Conta conta;
					ContaEspecial contaesp;
					conta = Fachada.criarConta("1", "111.111.001", "9999001", "joao@ifpb", "joao");
//					conta = Fachada.criarConta("2", "111.111.002", "9999002", "maria@ifpb", "maria");
//					contaesp = Fachada.criarConta("3", 100.0, "111.111.003", "9999003", "ana@ifpb", "ana");
//					contaesp = Fachada.criarConta("4", 100.0, "111.111.004", "9999004", "paulo@ifpb", "paulo");
					Fachada.creditar("111.111.001", 500);
//					Fachada.creditar("111.111.002", 500);
//					Fachada.creditar("111.111.003", 500);
//					Fachada.creditar("111.111.004", 500);
					Fachada.criarChave("111.111.001", "cpf");
//					Fachada.criarChave("111.111.002", "telefone");
//					Fachada.criarChave("111.111.003", "email");
//					Fachada.criarChave("111.111.004", "aleatorio");
//
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "problema no cadastro inicial:" + e.getMessage());
				}

				try {
					// cadastro inicial
					Conta conta;
					ContaEspecial contaesp;

					Repositorio repositorio = new Repositorio();

					Correntista correntista1 = repositorio.localizarCorrentista("111.111.001");
					Correntista correntista2 = repositorio.localizarCorrentista("111.111.002");
					Correntista correntista3 = repositorio.localizarCorrentista("111.111.003");
					Correntista correntista4 = repositorio.localizarCorrentista("111.111.004");

					if (correntista1 != null || correntista2 != null || correntista3 != null || correntista4 != null) {

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

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(frame, "até breve !");
			}
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
				texto = "Listagem de contas: \n";
				if (lista.isEmpty())
					texto += "n�o existe conta\n";
				else
					for (Conta c : lista)
						texto += c + "\n";

				// A mesma tela de listagem � usada para v�rias listagens
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
				texto = "Listagem de correntistas: \n";
				if (lista.isEmpty())
					texto += "n�o existe correntista\n";
				else
					for (Correntista c : lista)
						texto += c + "\n";

				// A mesma tela de listagem � usada para v�rias listagens
				TelaListagem j = new TelaListagem(texto);
				j.setVisible(true);
			}
		});
		mnListagem.add(mntmListarCorrentistas);

		mntmListarlancamentos = new JMenuItem("Lancamentos");
		mntmListarlancamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnListagem.add(mntmListarlancamentos);

		// -------------MENU-----------------------------------
		mnConsulta = new JMenu("conta Top");
		menuBar.add(mnConsulta);
		mnConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TelaConsulta j = new TelaConsulta();
				// j.setVisible(true);
			}
		});

	}
}
