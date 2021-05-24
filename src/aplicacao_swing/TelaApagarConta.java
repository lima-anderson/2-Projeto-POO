
package aplicacao_swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;

public class TelaApagarConta extends JFrame {
	private JPanel contentPane;
	private JLabel lblNome;
	private JTextField textField;
	private JButton btnApagar;
	private JLabel lblmsg;
	private JButton btnLimpar;

	public TelaApagarConta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Apagar Conta e Correntista");
		setBounds(100, 100, 345, 175);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNome = new JLabel("cpf:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(19, 28, 102, 14);
		contentPane.add(this.lblNome);
		textField = new JTextField();
		textField.setBounds(124, 25, 86, 20);
		contentPane.add(this.textField);
		textField.setColumns(10);
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String cpf = textField.getText();
					Fachada.apagarConta(cpf);

					lblmsg.setText("conta excluida com sucesso ");
				} catch (Exception e) {
					lblmsg.setText(e.getMessage());
				}
			}
		});
		btnApagar.setBounds(43, 71, 112, 23);
		contentPane.add(this.btnApagar);
		lblmsg = new JLabel("Mensagem do usuario");
		lblmsg.setBounds(19, 111, 294, 14);
		contentPane.add(this.lblmsg);
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField.requestFocus();
			}
		});
		btnLimpar.setBounds(172, 71, 117, 23);
		contentPane.add(this.btnLimpar);

	}
}
