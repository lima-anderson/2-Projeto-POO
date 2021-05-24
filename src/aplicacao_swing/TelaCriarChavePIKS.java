
package aplicacao_swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import fachada.Fachada;

public class TelaCriarChavePIKS extends JFrame {
	private JPanel contentPane;
	private JLabel lblNome;
	private JTextField textField;
	private JButton btnApagar;
	private JLabel lblmsg;
	private JList list;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCriarChavePIKS window = new TelaCriarChavePIKS();
//					window.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public TelaCriarChavePIKS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Criar chave PIKS");
		setBounds(100, 100, 364, 216);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNome = new JLabel("cpf:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(10, 29, 50, 14);
		contentPane.add(this.lblNome);
		textField = new JTextField();
		textField.setBounds(70, 26, 86, 20);
		contentPane.add(this.textField);
		textField.setColumns(10);
		btnApagar = new JButton("Criar chave");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String cpf = textField.getText();
					String tipochave = (String) list.getSelectedValue();
					Fachada.criarChave(cpf, tipochave);

					lblmsg.setText("chave criada");
				} catch (Exception e) {
					lblmsg.setText(e.getMessage());
				}
			}
		});
		btnApagar.setBounds(43, 71, 113, 23);
		contentPane.add(this.btnApagar);
		lblmsg = new JLabel("Mensagem");
		lblmsg.setBounds(41, 140, 294, 14);
		contentPane.add(this.lblmsg);

		list = new JList();
		list.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Tipo da chave",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "cpf", "email", "telefone", "aleatorio" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);
		list.setBounds(184, 11, 121, 101);
		contentPane.add(list);

	}
}
