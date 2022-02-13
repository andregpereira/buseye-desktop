package view.buscar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import launcher.Executavel;
import model.Onibus;
import repository.OnibusDAO;

import java.awt.event.*;

public class TelaBuscarOnibus {

	private JFrame frameBuscarLinhas = new JFrame("Buscar ônibus");
	private JPanel contentPane;
	private JTable tabelaOnibus;

	private OnibusDAO onibusDAO = new OnibusDAO();
	private List<Onibus> listaOnibus = onibusDAO.select();

	/**
	 * Create the frame.
	 */
	public TelaBuscarOnibus() {
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

		tabelaOnibus = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabelaOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tabelaOnibus.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tabelaOnibus);

		DefaultTableModel modelo = new DefaultTableModel(
				new String[] { "ID", "Localização", "ID funcionário", "ID linha", "Capacidade" }, 0);
		tabelaOnibus.setModel(modelo);

		listaOnibus.forEach(p -> modelo.addRow(new Object[] { p.getIdOnibus(), p.getLocalizacao(), p.getIdFuncionario(),
				p.getIdLinha(), p.getCapacidade() }));

		frameBuscarLinhas.setVisible(true);
	}

}
