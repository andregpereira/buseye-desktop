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
import model.Funcionario;
import model.Usuario;
import net.miginfocom.swing.MigLayout;
import repository.FuncionarioDAO;
import repository.UsuarioDAO;

public class TelaAlterarUsuario {

	private JFrame frameAlterarUsuario = new JFrame("Alterar usuário");
	private JPanel contentPane;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

	private JTextField emailUsuario;
	private JTextField localizacaoUsuario;

	/**
	 * Create the frame.
	 * 
	 * @param id
	 */
	public TelaAlterarUsuario(int id) {
		frameAlterarUsuario.setIconImage(Executavel.icone);
		frameAlterarUsuario.setMinimumSize(new Dimension(500, 320));
		frameAlterarUsuario.setBounds(100, 100, 500, 320);
		frameAlterarUsuario.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		frameAlterarUsuario.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel formularioUsuario = new JPanel();
		contentPane.add(formularioUsuario);
		formularioUsuario
				.setLayout(new MigLayout("", "[70px,grow][70px,grow][70px,grow][70px,grow][70px,grow][70px,grow]",
						"[30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow]"));

		JLabel campoEmailUsuario = new JLabel("Email");
		campoEmailUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioUsuario.add(campoEmailUsuario, "cell 0 0 2 1,growx,aligny bottom");

		emailUsuario = new JTextField();
		emailUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		emailUsuario.setColumns(10);
		formularioUsuario.add(emailUsuario, "cell 0 1 3 1,growx,aligny top");

		JLabel campoLocalizacaoUsuario = new JLabel("Localiza\u00E7\u00E3o");
		campoLocalizacaoUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioUsuario.add(campoLocalizacaoUsuario, "cell 0 2 2 1,growx,aligny bottom");

		localizacaoUsuario = new JTextField();
		localizacaoUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		localizacaoUsuario.setColumns(10);
		formularioUsuario.add(localizacaoUsuario, "cell 0 3 3 1,growx,aligny top");

		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
		frameAlterarUsuario.getContentPane().add(painelBotoes, BorderLayout.SOUTH);

		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAlterarUsuario.dispose();
			}
		});

		JButton botaoAlterar = new JButton("Alterar");
		botaoAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(id);

				if (!validarEmail(emailUsuario.getText())) {
					JOptionPane.showMessageDialog(formularioUsuario, "Insira um email v\u00E1lido.", "Email usuário",
							JOptionPane.WARNING_MESSAGE);
					emailUsuario.grabFocus();
					return;
				}

				if (emailRepetidoUsuario(id, emailUsuario.getText())
						|| emailRepetidoFuncionario(emailUsuario.getText())) {
					JOptionPane.showMessageDialog(formularioUsuario, "O email inserido já está em uso.",
							"Email usuário", JOptionPane.WARNING_MESSAGE);
					emailUsuario.grabFocus();
					return;
				}
				usuario.setEmail(emailUsuario.getText());

				if (localizacaoUsuario.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(formularioUsuario, "Insira uma localização válida.", "Localização",
							JOptionPane.WARNING_MESSAGE);
					localizacaoUsuario.grabFocus();
					return;
				}
				usuario.setLocalizacao(localizacaoUsuario.getText().toUpperCase());

				usuarioDAO.update(usuario);
				JOptionPane.showMessageDialog(frameAlterarUsuario,
						"Opera\u00E7\u00E3o realizada com sucesso!" + "\nDados do usuário alterados.", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				frameAlterarUsuario.dispose();
			}
		});
		botaoAlterar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		painelBotoes.add(botaoAlterar);
		frameAlterarUsuario.getRootPane().setDefaultButton(botaoAlterar);
		painelBotoes.add(botaoCancelar);

		frameAlterarUsuario.setVisible(true);
	}

	public boolean validarEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'ç*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	public boolean emailRepetidoUsuario(int id, String email) {
		List<Usuario> listaUsuarios = usuarioDAO.select();

		for (Usuario usuario : listaUsuarios) {
			if (usuario.getEmail().equalsIgnoreCase(email) && usuario.getIdUsuario() != id) {
				return true;
			}
		}
		return false;
	}

	public boolean emailRepetidoFuncionario(String email) {
		List<Funcionario> listaFuncionarios = funcionarioDAO.select();

		for (Funcionario funcionario : listaFuncionarios) {
			if (funcionario.getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}

}
