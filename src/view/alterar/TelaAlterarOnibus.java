package view.alterar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.Onibus;
import net.miginfocom.swing.MigLayout;
import repository.FuncionarioDAO;
import repository.LinhaDAO;
import repository.OnibusDAO;

public class TelaAlterarOnibus {

	private JFrame frameAlterarOnibus = new JFrame("Alterar ônibus");
	private JPanel contentPane;

	private OnibusDAO onibusDAO = new OnibusDAO();
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private LinhaDAO linhaDAO = new LinhaDAO();

	private JTextField localizacaoOnibus;
	private JTextField idFuncionarioOnibus;
	private JTextField idLinhaOnibus;
	private JTextField capacidade;

	/**
	 * Create the frame.
	 * 
	 * @param id
	 */
	public TelaAlterarOnibus(int id) {
		frameAlterarOnibus.setIconImage(Executavel.icone);
		frameAlterarOnibus.setMinimumSize(new Dimension(500, 320));
		frameAlterarOnibus.setBounds(100, 100, 500, 320);
		frameAlterarOnibus.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		frameAlterarOnibus.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel formularioOnibus = new JPanel();
		contentPane.add(formularioOnibus);
		formularioOnibus
				.setLayout(new MigLayout("", "[70px,grow][70px,grow][70px,grow][70px,grow][70px,grow][70px,grow]",
						"[30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow][30px,grow]"));

		JLabel campoLocalizacaoOnibus = new JLabel("Localiza\u00E7\u00E3o");
		campoLocalizacaoOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioOnibus.add(campoLocalizacaoOnibus, "cell 0 0 2 1,growx,aligny bottom");

		localizacaoOnibus = new JTextField();
		localizacaoOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioOnibus.add(localizacaoOnibus, "cell 0 1 3 1,growx,aligny top");
		localizacaoOnibus.setColumns(10);

		JLabel campoIdFuncionarioOnibus = new JLabel("ID do funcion\u00E1rio");
		campoIdFuncionarioOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioOnibus.add(campoIdFuncionarioOnibus, "cell 0 2 2 1,growx,aligny bottom");

		idFuncionarioOnibus = new JTextField();
		idFuncionarioOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioOnibus.add(idFuncionarioOnibus, "cell 0 3 3 1,growx,aligny top");
		idFuncionarioOnibus.setColumns(10);

		JLabel campoIdLinhaOnibus = new JLabel("ID da linha");
		campoIdLinhaOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioOnibus.add(campoIdLinhaOnibus, "cell 0 4 2 1,growx,aligny bottom");

		idLinhaOnibus = new JTextField();
		idLinhaOnibus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioOnibus.add(idLinhaOnibus, "cell 0 5 3 1,growx,aligny top");
		idLinhaOnibus.setColumns(10);

		JLabel campoCapacidade = new JLabel("Capacidade");
		campoCapacidade.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioOnibus.add(campoCapacidade, "cell 0 6 2 1,growx,aligny bottom");

		capacidade = new JTextField();
		capacidade.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		formularioOnibus.add(capacidade, "cell 0 7 3 1,growx,aligny top");
		capacidade.setColumns(10);

		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(painelBotoes, BorderLayout.SOUTH);

		JButton botaoAlterar = new JButton("Alterar");
		botaoAlterar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		botaoAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Onibus onibus = new Onibus();
				onibus.setIdOnibus(id);

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

				onibusDAO.update(onibus);
				JOptionPane.showMessageDialog(formularioOnibus,
						"Opera\u00E7\u00E3o realizada com sucesso!" + "\nDados do ônibus alterados.", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				frameAlterarOnibus.dispose();
			}
		});
		painelBotoes.add(botaoAlterar);
		contentPane.getRootPane().setDefaultButton(botaoAlterar);

		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAlterarOnibus.dispose();
			}
		});
		painelBotoes.add(botaoCancelar);

		frameAlterarOnibus.setVisible(true);
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
