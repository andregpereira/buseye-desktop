package view.alterar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import launcher.Executavel;
import model.Funcionario;
import model.Linha;
import net.miginfocom.swing.MigLayout;
import repository.FuncionarioDAO;
import repository.LinhaDAO;

public class TelaAlterarLinha {

	private JFrame frameAlterarLinha = new JFrame("Alterar linha");
	private JPanel contentPane;

	private LinhaDAO linhaDAO = new LinhaDAO();
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

	private JTextField nomeLinha;
	private JTextField duracaoPercurso;
	private JTextField idFuncionarioLinha;

	/**
	 * Create the frame.
	 * 
	 * @param id
	 */
	public TelaAlterarLinha(int id) {
		frameAlterarLinha.setIconImage(Executavel.icone);
		frameAlterarLinha.setMinimumSize(new Dimension(500, 320));
		frameAlterarLinha.setBounds(100, 100, 500, 320);
		frameAlterarLinha.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		frameAlterarLinha.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel formularioLinha = new JPanel();
		contentPane.add(formularioLinha);
		formularioLinha
				.setLayout(new MigLayout("", "[70px,grow][70px,grow][70px,grow][70px,grow][70px,grow][70px,grow]",
						"[30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow]"));

		JLabel campoNomeLinha = new JLabel("Nome");
		campoNomeLinha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioLinha.add(campoNomeLinha, "cell 0 0,growx,aligny bottom");

		nomeLinha = new JTextField();
		nomeLinha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		nomeLinha.setColumns(10);
		formularioLinha.add(nomeLinha, "cell 0 1 3 1,growx,aligny top");

		JLabel campoDuracaoPercurso = new JLabel(
				"<html><body>Dura\u00E7\u00E3o do percurso<br>(HoraMinutoMinuto ou MinutoMinuto)</body></html>");
		campoDuracaoPercurso.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioLinha.add(campoDuracaoPercurso, "cell 0 2 3 1,growx,aligny bottom");

		duracaoPercurso = new JTextField();
		duracaoPercurso.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		duracaoPercurso.setColumns(10);
		formularioLinha.add(duracaoPercurso, "cell 0 3 3 1,growx,aligny top");

		JLabel campoIdFuncionarioLinha = new JLabel("ID do funcion\u00E1rio");
		campoIdFuncionarioLinha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioLinha.add(campoIdFuncionarioLinha, "cell 0 4 2 1,growx,aligny bottom");

		idFuncionarioLinha = new JTextField();
		idFuncionarioLinha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		idFuncionarioLinha.setColumns(10);
		formularioLinha.add(idFuncionarioLinha, "cell 0 5 3 1,growx,aligny top");

		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(painelBotoes, BorderLayout.SOUTH);

		JButton botaoAlterar = new JButton("Alterar");
		botaoAlterar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		botaoAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Linha linha = new Linha();
				linha.setIdLinha(id);

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

				linhaDAO.update(linha);
				JOptionPane.showMessageDialog(formularioLinha, "Opera\u00E7\u00E3o realizada com sucesso!" + "\nLinha: "
						+ linha.getNome() + " - dados alterados.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				frameAlterarLinha.dispose();
			}
		});
		painelBotoes.add(botaoAlterar);
		contentPane.getRootPane().setDefaultButton(botaoAlterar);

		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAlterarLinha.dispose();
			}
		});
		painelBotoes.add(botaoCancelar);

		frameAlterarLinha.setVisible(true);
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

}
