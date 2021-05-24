package aplicacao_swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Conta;

public class TelaCriarConta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label;
	private JLabel label_1;
	private JButton btnCriar;
	private JLabel lblmsg;
	private JLabel label_2;
	private JTextField textField_2;
	private JLabel label_3;
	private JTextField textField_3;
	private JLabel label_4;
	private JTextField textField_4;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;

	public TelaCriarConta() {
		setTitle("Criar Conta e Correntista");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(99, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		label = new JLabel("numero:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(20, 8, 69, 14);
		contentPane.add(label);

		label_1 = new JLabel("cpf:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(20, 36, 69, 14);
		contentPane.add(label_1);

		textField_1 = new JTextField();
		textField_1.setBounds(99, 33, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		btnCriar = new JButton("Cadastrar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblmsg.setText("");
					if (textField.getText().isEmpty() || textField_1.getText().isEmpty()
							|| textField_2.getText().isEmpty() || textField_3.getText().isEmpty()) {
						lblmsg.setText("campo vazio");
						return;
					}
					String numero = textField.getText();
					String cpf = textField_1.getText();
					String telefone = textField_2.getText();
					String email = textField_3.getText();
					String nome = textField_4.getText();
					Conta conta;
					if (radioButton_1.isSelected()) {
						String s = JOptionPane.showInputDialog("qual o limite de saldo negativo da conta?");
						double limite = Double.parseDouble(s);
						conta = Fachada.criarConta(numero, limite, cpf, telefone, email, nome);
					} else
						conta = Fachada.criarConta(numero, cpf, telefone, email, nome);

					// lblmsg.setText("cadastrou conta:"+conta.getNumero()+"
					// correntista:"+conta.getCorrentista().getCpf() );
					textField.setText("");
					textField.requestFocus();
				} catch (Exception erro) {
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(198, 95, 121, 23);
		contentPane.add(btnCriar);

		lblmsg = new JLabel("mensagem");
		lblmsg.setBounds(20, 151, 273, 14);
		contentPane.add(lblmsg);

		label_2 = new JLabel("telefone:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(20, 61, 69, 14);
		contentPane.add(label_2);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(99, 61, 86, 20);
		contentPane.add(textField_2);

		label_3 = new JLabel("email:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(20, 89, 69, 14);
		contentPane.add(label_3);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(99, 89, 86, 20);
		contentPane.add(textField_3);

		label_4 = new JLabel("nome:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(20, 119, 69, 14);
		contentPane.add(label_4);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(99, 119, 86, 20);
		contentPane.add(textField_4);

		radioButton = new JRadioButton("conta normal");
		radioButton.setSelected(true);
		radioButton.setBounds(199, 51, 160, 23);
		contentPane.add(radioButton);

		radioButton_1 = new JRadioButton("conta especial");
		radioButton_1.setBounds(199, 14, 160, 23);
		contentPane.add(radioButton_1);

		ButtonGroup group = new ButtonGroup(); // tornar selecao binaria
		group.add(radioButton);
		group.add(radioButton_1);
	}
}
