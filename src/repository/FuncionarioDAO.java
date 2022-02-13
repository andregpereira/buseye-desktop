package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectionFactory.ConnectionFactory;
import model.Funcionario;

public class FuncionarioDAO {

	private Connection conexao;

	public FuncionarioDAO() {
		this.conexao = new ConnectionFactory().getConnection();
	}

	// inserir registros no DB
	public void insert(Funcionario funcionario) {
		String sql = "insert into funcionario(nome, cpf, dataNasc, email, telefone, cargo, situacao) values (?,?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setDate(3, funcionario.getDataNasc());
			stmt.setString(4, funcionario.getEmail());
			stmt.setString(5, funcionario.getTelefone());
			stmt.setString(6, funcionario.getCargo());
			stmt.setString(7, funcionario.getSituacao());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// buscar todos os registros do DB
	public List<Funcionario> select() {

		try {
			List<Funcionario> funcionarios = new ArrayList<Funcionario>(); // criar a lista para guardar objetos Contato

			PreparedStatement stmt = conexao.prepareStatement("select * from funcionario");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) { // enquanto tiver registros no DB execute
				Funcionario funcionario = new Funcionario();
				funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setDataNasc(rs.getDate("dataNasc"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setCargo(rs.getString("cargo"));
				funcionario.setSituacao(rs.getString("situacao"));

				funcionarios.add(funcionario);
			}
			rs.close();
			stmt.close();
			return funcionarios;

		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException();
		}
	}

	// excluir um registro
	public void delete(Funcionario funcionario) {
		String sql = "delete from funcionario where idFuncionario=?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, funcionario.getIdFuncionario());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// alterar um registro
	public void update(Funcionario funcionario) {
		String sql = "update funcionario set nome=?, cpf=?, dataNasc=?, email=?, telefone=?, cargo=?, situacao=? where idFuncionario=?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setDate(3, funcionario.getDataNasc());
			stmt.setString(4, funcionario.getEmail());
			stmt.setString(5, funcionario.getTelefone());
			stmt.setString(6, funcionario.getCargo());
			stmt.setString(7, funcionario.getSituacao());
			stmt.setInt(8, funcionario.getIdFuncionario());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}