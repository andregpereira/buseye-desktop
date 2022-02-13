package view.buscar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import launcher.Executavel;
import model.Funcionario;
import repository.FuncionarioDAO;

public class TelaBuscarFuncionarios {

	private JFrame frameBuscarFuncionarios = new JFrame("Buscar funcionários");
	private JPanel contentPane;
	private JTable tabelaFuncionarios;

	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private List<Funcionario> listaFuncionarios = funcionarioDAO.select();

	/**
	 * Create the frame.
	 */
	public TelaBuscarFuncionarios() {
		frameBuscarFuncionarios.setIconImage(Executavel.icone);
		frameBuscarFuncionarios.setMinimumSize(new Dimension(640, 300));
		frameBuscarFuncionarios.setBounds(100, 100, 640, 300);
		frameBuscarFuncionarios.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		frameBuscarFuncionarios.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(painelBotoes, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameBuscarFuncionarios.dispose();
			}
		});
		okButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		painelBotoes.add(okButton);
		contentPane.getRootPane().setDefaultButton(okButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tabelaFuncionarios = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabelaFuncionarios.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tabelaFuncionarios.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tabelaFuncionarios);

		DefaultTableModel modelo = new DefaultTableModel(new String[] { "ID", "Nome", "CPF",
				"Data nascimento (dd/mm/aaaa)", "Email", "Telefone", "Cargo", "Situação" }, 0);
		tabelaFuncionarios.setModel(modelo);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		listaFuncionarios.forEach(p -> modelo.addRow(new Object[] { p.getIdFuncionario(), p.getNome(), p.getCpf(),
				sdf.format(p.getDataNasc()), p.getEmail(), p.getTelefone(), p.getCargo(), p.getSituacao() }));

		frameBuscarFuncionarios.setVisible(true);
	}

}
