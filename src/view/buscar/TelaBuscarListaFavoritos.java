package view.buscar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import launcher.Executavel;
import model.ListaFavoritos;
import repository.ListaFavoritosDAO;

public class TelaBuscarListaFavoritos {

	private JFrame frameBuscarListaFavoritos = new JFrame("Buscar lista favoritos");
	private JPanel contentPane;
	private JTable tabelaFavoritos;

	private ListaFavoritosDAO favoritosDAO = new ListaFavoritosDAO();

	private List<ListaFavoritos> listaFavoritos = favoritosDAO.select();

	/**
	 * Create the frame.
	 */
	public TelaBuscarListaFavoritos() {
		frameBuscarListaFavoritos.setIconImage(Executavel.icone);
		frameBuscarListaFavoritos.setMinimumSize(new Dimension(640, 300));
		frameBuscarListaFavoritos.setBounds(100, 100, 640, 300);
		frameBuscarListaFavoritos.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		frameBuscarListaFavoritos.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(painelBotoes, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameBuscarListaFavoritos.dispose();
			}
		});
		okButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		painelBotoes.add(okButton);
		contentPane.getRootPane().setDefaultButton(okButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tabelaFavoritos = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabelaFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tabelaFavoritos.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tabelaFavoritos);

		DefaultTableModel modelo = new DefaultTableModel(new String[] { "ID", "ID linha", "ID usuário" }, 0);
		tabelaFavoritos.setModel(modelo);

		listaFavoritos.forEach(p -> modelo.addRow(new Object[] { p.getIdLista(), p.getIdLinha(), p.getIdUsuario() }));

		frameBuscarListaFavoritos.setVisible(true);
	}

}
