package view.buscar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import launcher.Executavel;
import model.Usuario;
import repository.UsuarioDAO;

import java.awt.event.*;

public class TelaBuscarUsuarios {

	private JFrame frameBuscarUsuarios = new JFrame("Buscar usuários");
	private JPanel contentPane;
	private JTable tabelaUsuarios;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private List<Usuario> listaUsuarios = usuarioDAO.select();

	/**
	 * Create the frame.
	 */
	public TelaBuscarUsuarios() {
		frameBuscarUsuarios.setIconImage(Executavel.icone);
		frameBuscarUsuarios.setMinimumSize(new Dimension(640, 300));
		frameBuscarUsuarios.setBounds(100, 100, 640, 300);
		frameBuscarUsuarios.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		frameBuscarUsuarios.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(painelBotoes, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameBuscarUsuarios.dispose();
			}
		});
		okButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		painelBotoes.add(okButton);
		contentPane.getRootPane().setDefaultButton(okButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tabelaUsuarios = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabelaUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tabelaUsuarios.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tabelaUsuarios);

		DefaultTableModel modelo = new DefaultTableModel(new String[] { "ID", "Email", "Localização" }, 0);
		tabelaUsuarios.setModel(modelo);

		listaUsuarios.forEach(p -> modelo.addRow(new Object[] { p.getIdUsuario(), p.getEmail(), p.getLocalizacao() }));

		frameBuscarUsuarios.setVisible(true);
	}

}
