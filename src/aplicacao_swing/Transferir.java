package aplicacao_swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;

public class Transferir extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JTextField textField;
	private JLabel label_1;
	private JTextField textField_1;
	private JButton button;
	private JLabel label_2;
	private JLabel label_3;
	private JTextField textField_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transferir frame = new Transferir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Transferir() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel("CPF (origem)");
		label.setBounds(32, 26, 99, 15);
		contentPane.add(label);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(226, 24, 182, 19);
		contentPane.add(textField);

		label_1 = new JLabel("Chave Piks (destino)");
		label_1.setBounds(32, 75, 154, 15);
		contentPane.add(label_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(226, 73, 182, 19);
		contentPane.add(textField_1);

		button = new JButton("Transferir");

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().isEmpty() || textField_1.getText().isEmpty()) {

					label_2.setText("Campo obrigatório está vazio.");
					return;
				}

				String cpf = textField.getText();
				String chavePiks = textField_1.getText();
				String quantia = textField_2.getText();

				try {
					Fachada.transferir(cpf, chavePiks, Integer.parseInt(quantia));
					label_2.setText("Transferência realizada com Sucesso.");
				} catch (Exception e) {
					label_2.setText(e.getMessage());
//					label_2.setText("DEU ERRADO");

				}

			}
		});

		button.setBounds(35, 188, 118, 25);
		contentPane.add(button);

		label_2 = new JLabel("");
		label_2.setBounds(203, 193, 218, 15);
		contentPane.add(label_2);

		label_3 = new JLabel("Quantia");
		label_3.setBounds(35, 126, 70, 15);
		contentPane.add(label_3);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(226, 124, 182, 19);
		contentPane.add(textField_2);
	}

}
