package view.alterar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import launcher.Executavel;
import model.Funcionario;
import model.Usuario;
import net.miginfocom.swing.MigLayout;
import repository.FuncionarioDAO;
import repository.UsuarioDAO;

public class TelaAlterarFuncionario {

	private JFrame frameAlterarFuncionario = new JFrame("Alterar funcionário");
	private JPanel contentPane;

	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();;

	private JTextField nomeFuncionario;
	private JTextField cpf;
	private JDateChooser dataNascimento;
	private JTextField emailFuncionario;
	private JTextField telefone;
	private JTextField cargo;
	private JTextField situacao;

	/**
	 * Create the dialog.
	 * 
	 * @param id
	 */
	public TelaAlterarFuncionario(int id) {
		frameAlterarFuncionario.setIconImage(Executavel.icone);
		frameAlterarFuncionario.setMinimumSize(new Dimension(500, 320));
		frameAlterarFuncionario.setBounds(100, 100, 500, 320);
		frameAlterarFuncionario.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		frameAlterarFuncionario.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel formularioFuncionario = new JPanel();
		contentPane.add(formularioFuncionario);
		formularioFuncionario
				.setLayout(new MigLayout("", "[70px,grow][70px,grow][70px,grow][70px,grow][70px,grow][70px,grow]",
						"[30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow]"));

		JLabel campoNomeFuncionario = new JLabel("Nome");
		campoNomeFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioFuncionario.add(campoNomeFuncionario, "cell 0 0 2 1,growx,aligny bottom");

		nomeFuncionario = new JTextField();
		nomeFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		nomeFuncionario.setColumns(10);
		formularioFuncionario.add(nomeFuncionario, "cell 0 1 3 1,growx,aligny top");

		JLabel campoCpf = new JLabel("CPF");
		campoCpf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioFuncionario.add(campoCpf, "cell 0 2 2 1,growx,aligny bottom");

		cpf = new JTextField();
		cpf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cpf.setColumns(10);
		formularioFuncionario.add(cpf, "cell 0 3 3 1,growx,aligny top");

		JLabel campoDataNascimento = new JLabel("Data de nascimento");
		campoDataNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioFuncionario.add(campoDataNascimento, "cell 0 4 2 1,growx,aligny bottom");

		dataNascimento = new JDateChooser();
		dataNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioFuncionario.add(dataNascimento, "cell 0 5 3 1,growx,aligny top");

		JLabel campoEmailFuncionario = new JLabel("Email");
		campoEmailFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioFuncionario.add(campoEmailFuncionario, "cell 0 6 2 1,growx,aligny bottom");

		emailFuncionario = new JTextField();
		emailFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		emailFuncionario.setColumns(10);
		formularioFuncionario.add(emailFuncionario, "cell 0 7 3 1,growx,aligny top");

		JLabel campoTelefone = new JLabel("Telefone");
		campoTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioFuncionario.add(campoTelefone, "cell 3 0 2 1,growx,aligny bottom");

		telefone = new JTextField();
		telefone.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		telefone.setColumns(10);
		formularioFuncionario.add(telefone, "cell 3 1 3 1,growx,aligny top");

		JLabel campoCargo = new JLabel("Cargo");
		campoCargo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioFuncionario.add(campoCargo, "cell 3 2 2 1,growx,aligny bottom");

		cargo = new JTextField();
		cargo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cargo.setColumns(10);
		formularioFuncionario.add(cargo, "cell 3 3 3 1,growx,aligny top");

		JLabel campoSituacao = new JLabel("Situa\u00E7\u00E3o (ativo ou inativo)");
		campoSituacao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioFuncionario.add(campoSituacao, "cell 3 4 2 1,growx,aligny bottom");

		situacao = new JTextField();
		situacao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		situacao.setColumns(10);
		formularioFuncionario.add(situacao, "cell 3 5 3 1,growx,aligny top");

		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(painelBotoes, BorderLayout.SOUTH);

		JButton botaoAlterar = new JButton("Alterar");
		botaoAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario funcionario = new Funcionario();
				funcionario.setIdFuncionario(id);

				if (nomeFuncionario.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(formularioFuncionario, "Insira o nome do funcionário.",
							"Nome funcionário", JOptionPane.WARNING_MESSAGE);
					nomeFuncionario.grabFocus();
					return;
				}
				funcionario.setNome(nomeFuncionario.getText().toUpperCase());

				try {
					Integer.parseInt(cpf.getText());
					if (cpfRepetido(id, cpf.getText())) {
						JOptionPane.showMessageDialog(formularioFuncionario, "O CPF inserido já está registrado.",
								"CPF", JOptionPane.WARNING_MESSAGE);
						cpf.grabFocus();
						return;
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(formularioFuncionario, "Insira um CPF válido.", "CPF",
							JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					cpf.grabFocus();
					return;
				}
				funcionario.setCpf(cpf.getText());

				Date sqlDataNascimento;

				try {
					sqlDataNascimento = new Date(dataNascimento.getDate().getTime());
				} catch (NullPointerException | NumberFormatException e1) {
					JOptionPane.showMessageDialog(formularioFuncionario,
							"Insira uma data de nascimento v\u00E1lida." + "\n(dd/mm/aaaa)", "Data de nascimento",
							JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					dataNascimento.grabFocus();
					return;
				}
				funcionario.setDataNasc(sqlDataNascimento);

				if (!validarEmail(emailFuncionario.getText())) {
					JOptionPane.showMessageDialog(formularioFuncionario, "Insira um email v\u00E1lido.",
							"Email funcionário", JOptionPane.WARNING_MESSAGE);
					emailFuncionario.grabFocus();
					return;
				}

				if (emailRepetidoFuncionario(id, emailFuncionario.getText())
						|| emailRepetidoUsuario(emailFuncionario.getText())) {
					JOptionPane.showMessageDialog(formularioFuncionario, "O email inserido já está em uso.",
							"Email funcionário", JOptionPane.WARNING_MESSAGE);
					emailFuncionario.grabFocus();
					return;
				}
				funcionario.setEmail(emailFuncionario.getText());

				try {
					Integer.parseInt(telefone.getText());
					if (telefoneRepetido(id, telefone.getText())) {
						JOptionPane.showMessageDialog(formularioFuncionario, "O telefone inserido já está registrado.",
								"Telefone", JOptionPane.WARNING_MESSAGE);
						telefone.grabFocus();
						return;
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(formularioFuncionario, "Insira um número de telefone válido.",
							"Telefone", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					telefone.grabFocus();
					return;
				}
				funcionario.setTelefone(telefone.getText());

				if (cargo.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(formularioFuncionario, "Insira o cargo do funcionário.",
							"Cargo funcionário", JOptionPane.WARNING_MESSAGE);
					cargo.grabFocus();
					return;
				}
				funcionario.setCargo(cargo.getText());

				if (!situacao.getText().equalsIgnoreCase("ativo") && !situacao.getText().equalsIgnoreCase("inativo")) {
					JOptionPane.showMessageDialog(formularioFuncionario, "Insira situa\u00E7\u00E3o ATIVO ou INATIVO.",
							"Situa\u00E7\u00E3o", JOptionPane.WARNING_MESSAGE);
					situacao.grabFocus();
					return;
				}
				funcionario.setSituacao(situacao.getText().toUpperCase());

				funcionarioDAO.update(funcionario);
				JOptionPane.showMessageDialog(
						frameAlterarFuncionario, "Opera\u00E7\u00E3o realizada com sucesso!" + "\nFuncionário: "
								+ funcionario.getNome() + " - dados alterados.",
						"Sucesso", JOptionPane.INFORMATION_MESSAGE);
				frameAlterarFuncionario.dispose();
			}
		});
		botaoAlterar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		painelBotoes.add(botaoAlterar);
		contentPane.getRootPane().setDefaultButton(botaoAlterar);

		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAlterarFuncionario.dispose();
			}
		});
		painelBotoes.add(botaoCancelar);

		frameAlterarFuncionario.setVisible(true);
	}

	public boolean validarEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'ç*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	public boolean cpfRepetido(int id, String cpf) {
		List<Funcionario> listaFuncionarios = funcionarioDAO.select();

		for (Funcionario funcionario : listaFuncionarios) {
			if (funcionario.getCpf().equals(cpf) && funcionario.getIdFuncionario() != id) {
				return true;
			}
		}
		return false;
	}

	public boolean telefoneRepetido(int id, String telefone) {
		List<Funcionario> listaFuncionarios = funcionarioDAO.select();

		for (Funcionario funcionario : listaFuncionarios) {
			if (funcionario.getTelefone().equals(telefone) && funcionario.getIdFuncionario() != id) {
				return true;
			}
		}
		return false;
	}

	public boolean emailRepetidoFuncionario(int id, String email) {
		List<Funcionario> listaFuncionarios = funcionarioDAO.select();

		for (Funcionario funcionario : listaFuncionarios) {
			if (funcionario.getEmail().equalsIgnoreCase(email) && funcionario.getIdFuncionario() != id) {
				return true;
			}
		}
		return false;
	}

	public boolean emailRepetidoUsuario(String email) {
		List<Usuario> listaUsuarios = usuarioDAO.select();

		for (Usuario usuario : listaUsuarios) {
			if (usuario.getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}

}
