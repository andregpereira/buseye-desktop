package view.buscar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import launcher.Executavel;
import model.Linha;
import repository.LinhaDAO;

import java.awt.event.*;
import java.text.SimpleDateFormat;

public class TelaBuscarLinhas {

	private JFrame frameBuscarLinhas = new JFrame("Buscar linhas");
	private JPanel contentPane;
	private JTable tabelaLinhas;

	private LinhaDAO linhaDAO = new LinhaDAO();
	private List<Linha> listaUsuarios = linhaDAO.select();

	/**
	 * Create the frame.
	 */
	public TelaBuscarLinhas() {
		frameBuscarLinhas.setIconImage(Executavel.icone);
		frameBuscarLinhas.setMinimumSize(new Dimension(640, 300));
		frameBuscarLinhas.setBounds(100, 100, 640, 300);
		frameBuscarLinhas.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		frameBuscarLinhas.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(painelBotoes, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameBuscarLinhas.dispose();
			}
		});
		okButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		painelBotoes.add(okButton);
		contentPane.getRootPane().setDefaultButton(okButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tabelaLinhas = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabelaLinhas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tabelaLinhas.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tabelaLinhas);

		SimpleDateFormat sdf = new SimpleDateFormat("H:mm");

		DefaultTableModel modelo = new DefaultTableModel(
				new String[] { "ID", "Nome", "Duração percurso (h:mm)", "ID funcionário" }, 0);
		tabelaLinhas.setModel(modelo);

		listaUsuarios.forEach(p -> modelo.addRow(new Object[] { p.getIdLinha(), p.getNome(),
				sdf.format(p.getDuracaoPercurso()), p.getIdFuncionario() }));

		frameBuscarLinhas.setVisible(true);
	}

}
