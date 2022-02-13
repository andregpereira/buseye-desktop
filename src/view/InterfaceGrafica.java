package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import launcher.Executavel;
import model.Funcionario;
import model.Linha;
import model.ListaFavoritos;
import model.Onibus;
import model.Usuario;
import net.miginfocom.swing.MigLayout;
import repository.FuncionarioDAO;
import repository.LinhaDAO;
import repository.ListaFavoritosDAO;
import repository.OnibusDAO;
import repository.UsuarioDAO;
import view.alterar.TelaAlterarFuncionario;
import view.alterar.TelaAlterarLinha;
import view.alterar.TelaAlterarListaFavoritos;
import view.alterar.TelaAlterarOnibus;
import view.alterar.TelaAlterarUsuario;
import view.buscar.TelaBuscarFuncionarios;
import view.buscar.TelaBuscarLinhas;
import view.buscar.TelaBuscarListaFavoritos;
import view.buscar.TelaBuscarOnibus;
import view.buscar.TelaBuscarUsuarios;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class InterfaceGrafica {

	private JFrame frameBusEye = new JFrame("BusEye");
	private JPanel contentPane;

	public static BufferedImage iconeRedimensionado;

	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private LinhaDAO linhaDAO = new LinhaDAO();
	private OnibusDAO onibusDAO = new OnibusDAO();
	private ListaFavoritosDAO favoritosDAO = new ListaFavoritosDAO();

	private JTextField nomeFuncionario;
	private JTextField cpf;
	private JDateChooser dataNascimento;
	private JTextField emailFuncionario;
	private JTextField telefone;
	private JTextField cargo;
	private JTextField emailUsuario;
	private JTextField localizacaoUsuario;
	private JTextField nomeLinha;
	private JTextField duracaoPercurso;
	private JTextField idFuncionarioLinha;
	private JTextField localizacaoOnibus;
	private JTextField idFuncionarioOnibus;
	private JTextField idLinhaOnibus;
	private JTextField capacidade;
	private JTextField idLinhaFavoritos;
	private JTextField idUsuarioFavoritos;
	private final ButtonGroup situacao = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public InterfaceGrafica() {
		frameBusEye.setIconImage(Executavel.icone);
		frameBusEye.setMinimumSize(new Dimension(500, 480));
		frameBusEye.setBounds(100, 100, 500, 480);
		frameBusEye.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		frameBusEye.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		try {
			iconeRedimensionado = resizeImage(ImageIO.read(getClass().getResource("/view/icon/BusEyeLogo.png")), 86,
					70);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		JPanel cabecalho = new JPanel();
		contentPane.add(cabecalho, BorderLayout.NORTH);
		cabecalho.setLayout(new FlowLayout(FlowLayout.CENTER, 140, 10));

		JLabel titulo = new JLabel("BusEye");
		titulo.setFont(new Font("Segoe Print", Font.BOLD, 28));
		cabecalho.add(titulo);

		JLabel lblBusEye = new JLabel("");
		lblBusEye.setIcon(new ImageIcon(iconeRedimensionado));
		cabecalho.add(lblBusEye);

		JTabbedPane formularios = new JTabbedPane(JTabbedPane.TOP);
		formularios.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		contentPane.add(formularios, BorderLayout.CENTER);

		JPanel formularioFuncionario = new JPanel();
		formularios.addTab("Funcion\u00E1rio", null, formularioFuncionario, null);
		formularioFuncionario.setLayout(new BorderLayout(0, 0));

		JPanel formF = new JPanel();
		formularioFuncionario.add(formF);
		formF.setLayout(new MigLayout("", "[70px,grow][70px,grow][70px,grow][70px,grow][70px,grow][70px,grow]",
				"[30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow]"));

		JLabel campoNomeFuncionario = new JLabel("Nome");
		campoNomeFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formF.add(campoNomeFuncionario, "cell 0 0 2 1,growx,aligny bottom");

		nomeFuncionario = new JTextField();
		nomeFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		nomeFuncionario.setColumns(10);
		formF.add(nomeFuncionario, "cell 0 1 3 1,growx,aligny top");

		JLabel campoCpf = new JLabel("CPF");
		campoCpf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formF.add(campoCpf, "cell 0 2 2 1,growx,aligny bottom");

		cpf = new JTextField();
		cpf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cpf.setColumns(10);
		formF.add(cpf, "cell 0 3 3 1,growx,aligny top");

		JLabel campoDataNascimento = new JLabel("Data de nascimento");
		campoDataNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formF.add(campoDataNascimento, "cell 0 4 2 1,growx,aligny bottom");

		dataNascimento = new JDateChooser();
		dataNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formF.add(dataNascimento, "cell 0 5 3 1,growx,aligny top");

		JLabel campoEmailFuncionario = new JLabel("Email");
		campoEmailFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formF.add(campoEmailFuncionario, "cell 0 6 2 1,growx,aligny bottom");

		emailFuncionario = new JTextField();
		emailFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		emailFuncionario.setColumns(10);
		formF.add(emailFuncionario, "cell 0 7 3 1,growx,aligny top");

		JLabel campoTelefone = new JLabel("Telefone");
		campoTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formF.add(campoTelefone, "cell 3 0 2 1,growx,aligny bottom");

		telefone = new JTextField();
		telefone.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		telefone.setColumns(10);
		formF.add(telefone, "cell 3 1 3 1,growx,aligny top");

		JLabel campoCargo = new JLabel("Cargo");
		campoCargo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formF.add(campoCargo, "cell 3 2 2 1,growx,aligny bottom");

		cargo = new JTextField();
		cargo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cargo.setColumns(10);
		formF.add(cargo, "cell 3 3 3 1,growx,aligny top");

		JLabel campoSituacao = new JLabel("Situa\u00E7\u00E3o (ATIVO ou INATIVO)");
		campoSituacao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formF.add(campoSituacao, "cell 3 4 3 1,growx,aligny bottom");

		JRadioButton botaoSituacaoAtivo = new JRadioButton("ATIVO");
		botaoSituacaoAtivo.setActionCommand("ATIVO");
		situacao.add(botaoSituacaoAtivo);
		botaoSituacaoAtivo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formF.add(botaoSituacaoAtivo, "cell 3 5,growx,aligny top");

		JRadioButton botaoSituacaoInativo = new JRadioButton("INATIVO");
		botaoSituacaoInativo.setActionCommand("INATIVO");
		botaoSituacaoInativo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		situacao.add(botaoSituacaoInativo);
		formF.add(botaoSituacaoInativo, "cell 4 5,growx,aligny top");

		JButton botaoBuscarFuncionario = new JButton("Buscar");
		botaoBuscarFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		botaoBuscarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaBuscarFuncionarios();
			}
		});
		formF.add(botaoBuscarFuncionario, "cell 5 8,grow");

		JButton botaoInserirFuncionario = new JButton("Inserir");
		botaoInserirFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		botaoInserirFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario funcionario = new Funcionario();

				if (nomeFuncionario.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(formularioFuncionario, "Insira o nome do funcionário.",
							"Nome funcionário", JOptionPane.WARNING_MESSAGE);
					nomeFuncionario.grabFocus();
					return;
				}
				funcionario.setNome(nomeFuncionario.getText().toUpperCase());

				try {
					Integer.parseInt(cpf.getText());
					if (cpfRepetido(cpf.getText())) {
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

				if (emailRepetidoFuncionario(emailFuncionario.getText())
						|| emailRepetidoUsuario(emailFuncionario.getText())) {
					JOptionPane.showMessageDialog(formularioFuncionario, "O email inserido já está em uso.",
							"Email funcionário", JOptionPane.WARNING_MESSAGE);
					emailFuncionario.grabFocus();
					return;
				}
				funcionario.setEmail(emailFuncionario.getText());

				try {
					Integer.parseInt(telefone.getText());
					if (telefoneRepetido(telefone.getText())) {
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

				if (situacao.getSelection() == null) {
					JOptionPane.showMessageDialog(formularioFuncionario, "Escolha situação ATIVO ou INATIVO.",
							"Situação", JOptionPane.WARNING_MESSAGE);

					return;
				}
				funcionario.setSituacao(situacao.getSelection().getActionCommand());

				funcionarioDAO.insert(funcionario);
				limparFormulario();
				JOptionPane
						.showMessageDialog(
								formularioFuncionario, "Opera\u00E7\u00E3o realizada com sucesso!" + "\nFuncionário: "
										+ funcionario.getNome() + " - registrado.",
								"Sucesso", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		formF.add(botaoInserirFuncionario, "cell 4 8,grow");

		JButton botaoDeletarFuncionario = new JButton("Deletar");
		botaoDeletarFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		botaoDeletarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Funcionario> listaFuncionarios = funcionarioDAO.select();
				int id = Integer.parseInt(JOptionPane.showInputDialog(formularioFuncionario,
						"Insira a ID do funcionário a ser deletado", "Deletar funcionário", JOptionPane.PLAIN_MESSAGE));

				for (Funcionario funcionario : listaFuncionarios) {
					if (funcionario.getIdFuncionario() == id) {
						funcionarioDAO.delete(funcionario);
						JOptionPane.showMessageDialog(
								formularioFuncionario, "Opera\u00E7\u00E3o realizada com sucesso!" + "\nFuncionário: "
										+ funcionario.getNome() + " - excluído.",
								"Sucesso", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
				JOptionPane.showMessageDialog(formularioFuncionario, "Funcionário não encontrado.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		formF.add(botaoDeletarFuncionario, "cell 5 9,grow");

		JButton botaoAlterarFuncionario = new JButton("Alterar");
		botaoAlterarFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		botaoAlterarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog(formularioFuncionario,
						"Insira a ID do funcionário a ser alterado", "Alterar funcionário", JOptionPane.PLAIN_MESSAGE));

				if (!procurarFuncionarioPorId(id)) {
					JOptionPane.showMessageDialog(formularioFuncionario, "Funcionário não encontrado.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else
					new TelaAlterarFuncionario(id);
			}
		});
		formF.add(botaoAlterarFuncionario, "cell 4 9,grow");

		JPanel formularioUsuario = new JPanel();
		formularios.addTab("Usu\u00E1rio", null, formularioUsuario, null);
		formularioUsuario.setLayout(new BorderLayout(0, 0));

		JPanel formU = new JPanel();
		formularioUsuario.add(formU);
		formU.setLayout(new MigLayout("", "[70px,grow][70px,grow][70px,grow][70px,grow][70px,grow][70px,grow]",
				"[30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow]"));

		JLabel campoEmailUsuario = new JLabel("Email");
		campoEmailUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formU.add(campoEmailUsuario, "cell 0 0 2 1,growx,aligny bottom");

		emailUsuario = new JTextField();
		emailUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		emailUsuario.setColumns(10);
		formU.add(emailUsuario, "cell 0 1 3 1,growx,aligny top");

		JLabel campoLocalizacaoUsuario = new JLabel("Localiza\u00E7\u00E3o");
		campoLocalizacaoUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formU.add(campoLocalizacaoUsuario, "cell 0 2 2 1,growx,aligny bottom");

		localizacaoUsuario = new JTextField();
		localizacaoUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		localizacaoUsuario.setColumns(10);
		formU.add(localizacaoUsuario, "cell 0 3 3 1,growx,aligny top");

		JButton botaoBuscarUsuario = new JButton("Buscar");
		botaoBuscarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaBuscarUsuarios();
			}
		});
		botaoBuscarUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formU.add(botaoBuscarUsuario, "cell 5 8,grow");

		JButton botaoInserirUsuario = new JButton("Inserir");
		botaoInserirUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();

				if (!validarEmail(emailUsuario.getText())) {
					JOptionPane.showMessageDialog(formularioUsuario, "Insira um email v\u00E1lido.", "Email usuário",
							JOptionPane.WARNING_MESSAGE);
					emailUsuario.grabFocus();
					return;
				}

				if (emailRepetidoUsuario(emailUsuario.getText()) || emailRepetidoFuncionario(emailUsuario.getText())) {
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

				usuarioDAO.insert(usuario);
				limparFormulario();
				JOptionPane.showMessageDialog(formularioUsuario,
						"Opera\u00E7\u00E3o realizada com sucesso!" + "\nUsuário registrado.", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botaoInserirUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formU.add(botaoInserirUsuario, "cell 4 8,grow");

		JButton botaoDeletarUsuario = new JButton("Deletar");
		botaoDeletarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Usuario> listaUsuarios = usuarioDAO.select();
				int id = Integer.parseInt(JOptionPane.showInputDialog(formularioUsuario,
						"Insira a ID do usuário a ser deletado", "Deletar usuário", JOptionPane.PLAIN_MESSAGE));

				for (Usuario usuario : listaUsuarios) {
					if (usuario.getIdUsuario() == id) {
						usuarioDAO.delete(usuario);
						JOptionPane.showMessageDialog(formularioUsuario,
								"Opera\u00E7\u00E3o realizada com sucesso!" + "\nUsuário excluído.", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}

				JOptionPane.showMessageDialog(formularioUsuario, "Usuário não encontrado.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		botaoDeletarUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formU.add(botaoDeletarUsuario, "cell 5 9,grow");

		JButton botaoAlterarUsuario = new JButton("Alterar");
		botaoAlterarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog(formularioUsuario,
						"Insira a ID do usuário a ser alterado", "Alterar usuário", JOptionPane.PLAIN_MESSAGE));

				if (!procurarUsuarioPorId(id)) {
					JOptionPane.showMessageDialog(formularioUsuario, "Usuário não encontrado.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else
					new TelaAlterarUsuario(id);
			}
		});
		botaoAlterarUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formU.add(botaoAlterarUsuario, "cell 4 9,grow");

		JPanel formularioLinha = new JPanel();
		formularios.addTab("Linha", null, formularioLinha, null);
		formularioLinha.setLayout(new BorderLayout(0, 0));

		JPanel formL = new JPanel();
		formularioLinha.add(formL);
		formL.setLayout(new MigLayout("", "[70px,grow][70px,grow][70px,grow][70px,grow][70px,grow][70px,grow]",
				"[30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow]"));

		JLabel campoNomeLinha = new JLabel("Nome");
		campoNomeLinha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formL.add(campoNomeLinha, "cell 0 0,growx,aligny bottom");

		nomeLinha = new JTextField();
		nomeLinha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		nomeLinha.setColumns(10);
		formL.add(nomeLinha, "cell 0 1 3 1,growx,aligny top");

		JLabel campoDuracaoPercurso = new JLabel(
				"<html><body>Dura\u00E7\u00E3o do percurso<br>(HoraMinutoMinuto ou MinutoMinuto)</body></html>");
		campoDuracaoPercurso.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formL.add(campoDuracaoPercurso, "cell 0 2 3 1,growx,aligny bottom");

		duracaoPercurso = new JTextField();
		duracaoPercurso.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		duracaoPercurso.setColumns(10);
		formL.add(duracaoPercurso, "cell 0 3 3 1,growx,aligny top");

		JLabel campoIdFuncionarioLinha = new JLabel("ID do funcion\u00E1rio");
		campoIdFuncionarioLinha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formL.add(campoIdFuncionarioLinha, "cell 0 4 2 1,growx,aligny bottom");

		idFuncionarioLinha = new JTextField();
		idFuncionarioLinha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		idFuncionarioLinha.setColumns(10);
		formL.add(idFuncionarioLinha, "cell 0 5 3 1,growx,aligny top");

		JButton botaoAlterarLinha = new JButton("Alterar");
		botaoAlterarLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog(formularioLinha,
						"Insira a ID da linha a ser alterada", "Alterar linha", JOptionPane.PLAIN_MESSAGE));

				if (!procurarLinhaPorId(id)) {
					JOptionPane.showMessageDialog(formularioLinha, "Linha não encontrada.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else
					new TelaAlterarLinha(id);
			}
		});
		botaoAlterarLinha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formL.add(botaoAlterarLinha, "cell 4 9,grow");

		JButton botaoInserirLinha = new JButton("Inserir");
		botaoInserirLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Linha linha = new Linha();

				if (nomeLinha.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(formularioLinha, "Insira o nome da linha.", "Nome linha",
							JOptionPane.WARNING_MESSAGE);
					nomeLinha.grabFocus();
					return;
				}
				linha.setNome(nomeLinha.getText().toUpperCase());

				Time tempo;

				if (duracaoPercurso.getDocument().getLength() <= 2) {
					SimpleDateFormat sdfmm = new SimpleDateFormat("mm");
					try {
						tempo = new Time(sdfmm.parse(duracaoPercurso.getText()).getTime());
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(formularioLinha,
								"Insira um tempo de percurso válido." + "\n(HoraMinutoMinuto ou MinutoMinuto)",
								"Duração do percurso", JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
						duracaoPercurso.grabFocus();
						return;
					}
				} else if (duracaoPercurso.getDocument().getLength() == 3) {
					SimpleDateFormat sdfHmm = new SimpleDateFormat("Hmm");
					try {
						tempo = new Time(sdfHmm.parse(duracaoPercurso.getText()).getTime());
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(formularioLinha,
								"Insira um tempo de percurso válido." + "\n(HoraMinutoMinuto ou MinutoMinuto)",
								"Duração do percurso", JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
						duracaoPercurso.grabFocus();
						return;
					}
				} else {
					JOptionPane.showMessageDialog(formularioLinha,
							"Insira um tempo de percurso válido." + "\n(HoraMinutoMinuto ou MinutoMinuto)",
							"Duração do percurso", JOptionPane.WARNING_MESSAGE);
					duracaoPercurso.grabFocus();
					return;
				}
				linha.setDuracaoPercurso(tempo);

				int idFuncionario;

				try {
					idFuncionario = Integer.parseInt(idFuncionarioLinha.getText());
					if (!procurarFuncionarioPorId(idFuncionario)) {
						JOptionPane.showMessageDialog(formularioLinha, "Funcionário não encontrado.", "ID funcionário",
								JOptionPane.ERROR_MESSAGE);
						idFuncionarioLinha.grabFocus();
						return;
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(formularioLinha, "Insira uma ID válida do funcionário.",
							"ID funcionário", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					idFuncionarioLinha.grabFocus();
					return;
				}
				linha.setIdFuncionario(idFuncionario);

				linhaDAO.insert(linha);
				limparFormulario();
				JOptionPane.showMessageDialog(formularioLinha,
						"Opera\u00E7\u00E3o realizada com sucesso!" + "\nLinha: " + linha.getNome() + " - registrada.",
						"Sucesso", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botaoInserirLinha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formL.add(botaoInserirLinha, "cell 4 8,grow");

		JButton botaoBuscarLinha = new JButton("Buscar");
		botaoBuscarLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaBuscarLinhas();
			}
		});
		botaoBuscarLinha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formL.add(botaoBuscarLinha, "cell 5 8,grow");

		JButton botaoDeletarLinha = new JButton("Deletar");
		botaoDeletarLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Linha> listaLinhas = linhaDAO.select();
				int id = Integer.parseInt(JOptionPane.showInputDialog(formularioLinha,
						"Insira a ID da linha a ser deletada", "Deletar linha", JOptionPane.PLAIN_MESSAGE));

				for (Linha linha : listaLinhas) {
					if (linha.getIdLinha() == id) {
						linhaDAO.delete(linha);
						JOptionPane.showMessageDialog(formularioLinha,
								"Opera\u00E7\u00E3o realizada com sucesso!" + "\nLinha excluída.", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}

				JOptionPane.showMessageDialog(formularioLinha, "Linha não encontrada.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		botaoDeletarLinha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formL.add(botaoDeletarLinha, "cell 5 9,grow");

		JPanel formularioOnibus = new JPanel();
		formularios.addTab("Ônibus", null, formularioOnibus, null);
		formularioOnibus.setLayout(new BorderLayout(0, 0));

		JPanel formO = new JPanel();
		formularioOnibus.add(formO, BorderLayout.CENTER);
		formO.setLayout(new MigLayout("", "[70px,grow][70px,grow][70px,grow][70px,grow][70px,grow][70px,grow]",
				"[30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow]"));

		JLabel campoLocalizacaoOnibus = new JLabel("Localiza\u00E7\u00E3o");
		campoLocalizacaoOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formO.add(campoLocalizacaoOnibus, "cell 0 0 2 1,growx,aligny bottom");

		localizacaoOnibus = new JTextField();
		localizacaoOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formO.add(localizacaoOnibus, "cell 0 1 3 1,growx,aligny top");
		localizacaoOnibus.setColumns(10);

		JLabel campoIdFuncionarioOnibus = new JLabel("ID do funcion\u00E1rio");
		campoIdFuncionarioOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formO.add(campoIdFuncionarioOnibus, "cell 0 2 2 1,growx,aligny bottom");

		idFuncionarioOnibus = new JTextField();
		idFuncionarioOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formO.add(idFuncionarioOnibus, "cell 0 3 3 1,growx,aligny top");
		idFuncionarioOnibus.setColumns(10);

		JLabel campoIdLinhaOnibus = new JLabel("ID da linha");
		campoIdLinhaOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formO.add(campoIdLinhaOnibus, "cell 0 4 2 1,growx,aligny bottom");

		idLinhaOnibus = new JTextField();
		idLinhaOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formO.add(idLinhaOnibus, "cell 0 5 3 1,growx,aligny top");
		idLinhaOnibus.setColumns(10);

		JLabel campoCapacidade = new JLabel("Capacidade");
		campoCapacidade.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formO.add(campoCapacidade, "cell 0 6 2 1,growx,aligny bottom");

		capacidade = new JTextField();
		capacidade.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formO.add(capacidade, "cell 0 7 3 1,growx,aligny top");
		capacidade.setColumns(10);

		JButton botaoInserirOnibus = new JButton("Inserir");
		botaoInserirOnibus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Onibus onibus = new Onibus();

				if (localizacaoOnibus.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(formularioOnibus, "Insira uma localização válida.", "Localização",
							JOptionPane.WARNING_MESSAGE);
					localizacaoOnibus.grabFocus();
					return;
				}
				onibus.setLocalizacao(localizacaoOnibus.getText().toUpperCase());

				int idFuncionario;

				try {
					idFuncionario = Integer.parseInt(idFuncionarioOnibus.getText());
					if (!procurarFuncionarioPorId(idFuncionario)) {
						JOptionPane.showMessageDialog(formularioOnibus, "Funcionário não encontrado.", "ID linha",
								JOptionPane.ERROR_MESSAGE);
						idFuncionarioOnibus.grabFocus();
						return;
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(formularioOnibus, "Insira uma ID válida do funcionário.", "ID linha",
							JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					idFuncionarioOnibus.grabFocus();
					return;
				}
				onibus.setIdFuncionario(idFuncionario);

				int idLinha;

				try {
					idLinha = Integer.parseInt(idLinhaOnibus.getText());
					if (!procurarLinhaPorId(idLinha)) {
						JOptionPane.showMessageDialog(formularioOnibus, "Linha não encontrada.", "ID linha",
								JOptionPane.ERROR_MESSAGE);
						idLinhaOnibus.grabFocus();
						return;
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(formularioOnibus, "Insira uma ID válida da linha.", "ID linha",
							JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					idLinhaOnibus.grabFocus();
					return;
				}
				onibus.setIdLinha(idLinha);

				int intCapacidade;

				try {
					intCapacidade = Integer.parseInt(capacidade.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(formularioOnibus, "Insira uma capacidade válida.", "Capacidade",
							JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					capacidade.grabFocus();
					return;
				}
				onibus.setCapacidade(intCapacidade);

				onibusDAO.insert(onibus);
				limparFormulario();
				JOptionPane.showMessageDialog(formularioOnibus,
						"Opera\u00E7\u00E3o realizada com sucesso!" + "\nÔnibus registrado.", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botaoInserirOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formO.add(botaoInserirOnibus, "cell 4 8,grow");

		JButton botaoBuscarOnibus = new JButton("Buscar");
		botaoBuscarOnibus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaBuscarOnibus();
			}
		});
		botaoBuscarOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formO.add(botaoBuscarOnibus, "cell 5 8,grow");

		JButton botaoAlterarOnibus = new JButton("Alterar");
		botaoAlterarOnibus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog(formularioOnibus,
						"Insira a ID do ônibus a ser alterado", "Alterar ônibus", JOptionPane.PLAIN_MESSAGE));

				if (!procurarOnibusPorId(id)) {
					JOptionPane.showMessageDialog(formularioOnibus, "Ônibus não encontrado.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else
					new TelaAlterarOnibus(id);
			}
		});
		botaoAlterarOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formO.add(botaoAlterarOnibus, "cell 4 9,grow");

		JButton botaoDeletarOnibus = new JButton("Deletar");
		botaoDeletarOnibus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Onibus> listaOnibus = onibusDAO.select();

				int id = Integer.parseInt(JOptionPane.showInputDialog(formularioOnibus,
						"Insira a ID do ônibus a ser deletado", "Deletar ônibus", JOptionPane.PLAIN_MESSAGE));

				for (Onibus onibus : listaOnibus) {
					if (onibus.getIdOnibus() == id) {
						onibusDAO.delete(onibus);
						JOptionPane.showMessageDialog(formularioOnibus,
								"Opera\u00E7\u00E3o realizada com sucesso!" + "\nÔnibus excluído.", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
				JOptionPane.showMessageDialog(formularioOnibus, "Ônibus não encontrado.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		botaoDeletarOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formO.add(botaoDeletarOnibus, "cell 5 9,grow");

		JPanel formularioFavoritos = new JPanel();
		formularios.addTab("Lista favoritos", null, formularioFavoritos, null);
		formularioFavoritos.setLayout(new BorderLayout(0, 0));

		JPanel formFav = new JPanel();
		formularioFavoritos.add(formFav, BorderLayout.CENTER);
		formFav.setLayout(new MigLayout("", "[70px,grow][70px,grow][70px,grow][70px,grow][70px,grow][70px,grow]",
				"[30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow]"));

		JLabel campoIdLinhaFavoritos = new JLabel("ID da linha");
		campoIdLinhaFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formFav.add(campoIdLinhaFavoritos, "cell 0 0 2 1,growx,aligny bottom");

		idLinhaFavoritos = new JTextField();
		idLinhaFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formFav.add(idLinhaFavoritos, "cell 0 1 3 1,growx,aligny top");
		idLinhaFavoritos.setColumns(10);

		JLabel campoIdUsuarioFavoritos = new JLabel("ID do usu\u00E1rio");
		campoIdUsuarioFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formFav.add(campoIdUsuarioFavoritos, "cell 0 2 2 1,growx,aligny bottom");

		idUsuarioFavoritos = new JTextField();
		idUsuarioFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formFav.add(idUsuarioFavoritos, "cell 0 3 3 1,growx,aligny top");
		idUsuarioFavoritos.setColumns(10);

		JButton botaoInserirFavoritos = new JButton("Inserir");
		botaoInserirFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaFavoritos favoritos = new ListaFavoritos();

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

				int idUsuario;

				List<ListaFavoritos> listaFavoritos = favoritosDAO.select();

				try {
					idUsuario = Integer.parseInt(idUsuarioFavoritos.getText());
					if (!procurarUsuarioPorId(idUsuario)) {
						JOptionPane.showMessageDialog(formularioFavoritos, "Usuário não encontrado.", "ID usuário",
								JOptionPane.ERROR_MESSAGE);
						idUsuarioFavoritos.grabFocus();
						return;
					}
					for (ListaFavoritos favoritosSelect : listaFavoritos) {
						if (favoritosSelect.getIdUsuario() == idUsuario) {
							favoritos.setIdUsuario(idUsuario);
							favoritosDAO.insert(favoritos);
							limparFormulario();
							JOptionPane.showMessageDialog(formularioFavoritos,
									"Opera\u00E7\u00E3o realizada com sucesso!"
											+ "\nLinha adicionada na lista de favoritos.",
									"Sucesso", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(formularioFavoritos, "Insira uma ID válida do usuário.", "ID usuário",
							JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					idUsuarioFavoritos.grabFocus();
					return;
				}
				favoritos.setIdUsuario(idUsuario);

				favoritosDAO.insert(favoritos);
				limparFormulario();
				JOptionPane.showMessageDialog(formularioFavoritos,
						"Opera\u00E7\u00E3o realizada com sucesso!" + "\nLista de favoritos registrada.", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botaoInserirFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formFav.add(botaoInserirFavoritos, "cell 4 8,grow");

		JButton botaoBuscarFavoritos = new JButton("Buscar");
		botaoBuscarFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaBuscarListaFavoritos();
			}
		});
		botaoBuscarFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formFav.add(botaoBuscarFavoritos, "cell 5 8,grow");

		JButton botaoAlterarFavoritos = new JButton("Alterar");
		botaoAlterarFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<ListaFavoritos> listaFavoritos = favoritosDAO.select();

				int idUsuario = Integer.parseInt(JOptionPane.showInputDialog(formularioFavoritos,
						"Insira a ID do usuário a ter a lista de favoritos alterada", "Alterar lista favoritos",
						JOptionPane.PLAIN_MESSAGE));

				if (!procurarUsuarioPorId(idUsuario)) {
					JOptionPane.showMessageDialog(formularioFavoritos, "Usuário não encontrado.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				for (ListaFavoritos favoritos : listaFavoritos) {
					if (favoritos.getIdUsuario() == idUsuario) {
						int idLista = Integer.parseInt(JOptionPane.showInputDialog(formularioFavoritos,
								"Insira a ID da lista de favoritos a ser alterada", "Alterar favoritos",
								JOptionPane.PLAIN_MESSAGE));
						if (!procurarListaFavoritosPorId(idLista) || favoritos.getIdLista() != idLista) {
							JOptionPane.showMessageDialog(formularioFavoritos, "Lista não encontrada.", "Erro",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						new TelaAlterarListaFavoritos(idUsuario, idLista);
						return;
					}
				}
				JOptionPane.showMessageDialog(formularioFavoritos, "O usuário não possui uma lista de favoritos.",
						"Erro", JOptionPane.ERROR_MESSAGE);
			}
		});
		botaoAlterarFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formFav.add(botaoAlterarFavoritos, "cell 4 9,grow");

		JButton botaoDeletarFavoritos = new JButton("Deletar");
		botaoDeletarFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<ListaFavoritos> listaFavoritos = favoritosDAO.select();

				int idUsuario = Integer.parseInt(JOptionPane.showInputDialog(formularioFavoritos,
						"Insira a ID do usuário a ter a lista de favoritos deletada", "Deletar lista de favoritos",
						JOptionPane.PLAIN_MESSAGE));

				if (!procurarUsuarioPorId(idUsuario)) {
					JOptionPane.showMessageDialog(formularioFavoritos, "Usuário não encontrado.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				for (ListaFavoritos favoritos : listaFavoritos) {
					if (favoritos.getIdUsuario() == idUsuario) {
						favoritosDAO.delete(favoritos);
						JOptionPane.showMessageDialog(formularioFavoritos,
								"Opera\u00E7\u00E3o realizada com sucesso!" + "\nLista de favoritos excluída.",
								"Sucesso", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
				JOptionPane.showMessageDialog(formularioFavoritos, "O usuário não possui uma lista de favoritos.",
						"Erro", JOptionPane.ERROR_MESSAGE);
			}

		});
		botaoDeletarFavoritos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formFav.add(botaoDeletarFavoritos, "cell 5 9,grow");

		JPanel botoesPlus = new JPanel();
		contentPane.add(botoesPlus, BorderLayout.SOUTH);
		botoesPlus.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JButton botaoSair = new JButton("Sair");
		botaoSair.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		botoesPlus.add(botaoSair);

		JButton botaoLimpar = new JButton("Limpar formul\u00E1rio");
		botaoLimpar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}
		});
		botoesPlus.add(botaoLimpar);

		frameBusEye.setVisible(true);
	}

	public boolean validarEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	public boolean cpfRepetido(String cpf) {
		List<Funcionario> listaFuncionarios = funcionarioDAO.select();

		for (Funcionario funcionario : listaFuncionarios) {
			if (funcionario.getCpf().equals(cpf)) {
				return true;
			}
		}
		return false;
	}

	public boolean telefoneRepetido(String telefone) {
		List<Funcionario> listaFuncionarios = funcionarioDAO.select();

		for (Funcionario funcionario : listaFuncionarios) {
			if (funcionario.getTelefone().equals(telefone)) {
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

	public boolean emailRepetidoUsuario(String email) {
		List<Usuario> listaUsuarios = usuarioDAO.select();

		for (Usuario usuario : listaUsuarios) {
			if (usuario.getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}

	public boolean procurarFuncionarioPorId(int id) {
		List<Funcionario> listaFuncionarios = funcionarioDAO.select();

		for (Funcionario funcionario : listaFuncionarios) {
			if (funcionario.getIdFuncionario() == id) {
				return true;
			}
		}
		return false;
	}

	public boolean procurarUsuarioPorId(int id) {
		List<Usuario> listaUsuarios = usuarioDAO.select();

		for (Usuario usuario : listaUsuarios) {
			if (usuario.getIdUsuario() == id) {
				return true;
			}
		}
		return false;
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

	public boolean procurarOnibusPorId(int id) {
		List<Onibus> listaOnibus = onibusDAO.select();

		for (Onibus onibus : listaOnibus) {
			if (onibus.getIdOnibus() == id) {
				return true;
			}
		}
		return false;
	}

	public boolean procurarListaFavoritosPorId(int id) {
		List<ListaFavoritos> listaFavoritos = favoritosDAO.select();

		for (ListaFavoritos favoritos : listaFavoritos) {
			if (favoritos.getIdLista() == id) {
				return true;
			}
		}
		return false;
	}

	public void limparFormulario() {
		nomeFuncionario.setText("");
		cpf.setText("");
		dataNascimento.setDate(null);
		emailFuncionario.setText("");
		telefone.setText("");
		cargo.setText("");
		situacao.clearSelection();
		emailUsuario.setText("");
		localizacaoUsuario.setText("");
		nomeLinha.setText("");
		duracaoPercurso.setText("");
		idFuncionarioLinha.setText("");
		localizacaoOnibus.setText("");
		idFuncionarioOnibus.setText("");
		idLinhaOnibus.setText("");
		capacidade.setText("");
		idLinhaFavoritos.setText("");
		idUsuarioFavoritos.setText("");
	}

	public static BufferedImage resizeImage(Image originalImage, int targetWidth, int targetHeight) throws IOException {
		BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		graphics2D.dispose();
		return resizedImage;
	}

}
