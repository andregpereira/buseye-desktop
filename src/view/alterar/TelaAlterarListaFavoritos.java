package view.alterar;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import launcher.Executavel;
import model.Linha;
import model.ListaFavoritos;
import net.miginfocom.swing.MigLayout;
import repository.LinhaDAO;
import repository.ListaFavoritosDAO;

public class TelaAlterarListaFavoritos {

	private JFrame frameAlterarListaFavoritos = new JFrame("Alterar lista favoritos");
	private JPanel contentPane;

	private ListaFavoritosDAO favoritosDAO = new ListaFavoritosDAO();
	private LinhaDAO linhaDAO = new LinhaDAO();

	private JTextField idLinhaFavoritos;

	/**
	 * Create the frame.
	 * 
	 * @param idUsuario
	 * @param idLista
	 */
	public TelaAlterarListaFavoritos(int idUsuario, int idLista) {
		frameAlterarListaFavoritos.setIconImage(Executavel.icone);
		frameAlterarListaFavoritos.setMinimumSize(new Dimension(500, 320));
		frameAlterarListaFavoritos.setBounds(100, 100, 500, 320);
		frameAlterarListaFavoritos.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		frameAlterarListaFavoritos.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel formularioFavoritos = new JPanel();
		contentPane.add(formularioFavoritos);
		formularioFavoritos
				.setLayout(new MigLayout("", "[70px,grow][70px,grow][70px,grow][70px,grow][70px,grow][70px,grow]",
						"[30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow]"));

		JLabel campoIdLinhaFavoritos = new JLabel("ID da linha");
		campoIdLinhaFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioFavoritos.add(campoIdLinhaFavoritos, "cell 0 0 2 1,growx,aligny bottom");

		idLinhaFavoritos = new JTextField();
		idLinhaFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		idLinhaFavoritos.setColumns(10);
		formularioFavoritos.add(idLinhaFavoritos, "cell 0 1 3 1,growx,aligny top");

		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
		frameAlterarListaFavoritos.getContentPane().add(painelBotoes, BorderLayout.SOUTH);

		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAlterarListaFavoritos.dispose();
			}
		});

		JButton botaoAlterar = new JButton("Alterar");
		botaoAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaFavoritos favoritos = new ListaFavoritos();
				favoritos.setIdLista(idLista);
				favoritos.setIdUsuario(idUsuario);

				int idLinha;

				try {
					idLinha = Integer.parseInt(idLinhaFavoritos.getText());
					if (!procurarLinhaPorId(idLinha)) {
						JOptionPane.showMessageDialog(formularioFavoritos, "Linha não encontrada.", "ID funcionário",
								JOptionPane.ERROR_MESSAGE);
						idLinhaFavoritos.grabFocus();
						return;
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(formularioFavoritos, "Insira uma ID válida da linha.", "ID linha",
							JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					idLinhaFavoritos.grabFocus();
					return;
				}
				favoritos.setIdLinha(idLinha);

				favoritosDAO.update(favoritos);
				JOptionPane.showMessageDialog(formularioFavoritos,
						"Opera\u00E7\u00E3o realizada com sucesso!" + "\nDados da lista de favoritos alterados.",
						"Sucesso", JOptionPane.INFORMATION_MESSAGE);
				frameAlterarListaFavoritos.dispose();
			}
		});
		botaoAlterar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		painelBotoes.add(botaoAlterar);
		frameAlterarListaFavoritos.getRootPane().setDefaultButton(botaoAlterar);
		painelBotoes.add(botaoCancelar);

		frameAlterarListaFavoritos.setVisible(true);
	}

	public boolean validarEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'ç*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	public boolean procurarLinhaPorId(int id) {
		List<Linha> listaLinhas = linhaDAO.select();

		for (Linha linha : listaLinhas) {
			if (linha.getIdLinha() == id) {
				return true;
			}
		}
		return false;
	}

}
