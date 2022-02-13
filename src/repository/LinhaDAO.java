package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectionFactory.ConnectionFactory;
import model.Linha;

public class LinhaDAO {

	private Connection conexao;

	public LinhaDAO() {
		this.conexao = new ConnectionFactory().getConnection();
	}

	// inserir registros no DB
	public void insert(Linha linha) {
		String sql = "insert into linha(nome, duracaoPercurso, funcionario_idFuncionario) values (?,?,?)";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, linha.getNome());
			stmt.setTime(2, linha.getDuracaoPercurso());
			stmt.setInt(3, linha.getIdFuncionario());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// buscar todos os registros do DB
	public List<Linha> select() {
		try {
			List<Linha> linhas = new ArrayList<Linha>(); // criar a lista para guardar objetos Contato

			PreparedStatement stmt = conexao.prepareStatement("select * from linha");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) { // enquanto tiver registros no DB execute
				Linha linha = new Linha();
				linha.setIdLinha(rs.getInt("idLinha"));
				linha.setNome(rs.getString("nome"));
				linha.setDuracaoPercurso(rs.getTime("duracaoPercurso"));
				linha.setIdFuncionario(rs.getInt("funcionario_idFuncionario"));

				linhas.add(linha);
			}
			rs.close();
			stmt.close();
			return linhas;

		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException();
		}
	}

	// excluir um registro
	public void delete(Linha linha) {
		String sql = "delete from linha where idLinha=?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, linha.getIdLinha());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// alterar um registro
	public void update(Linha linha) {
		String sql = "update linha set nome=?, duracaoPercurso=?, funcionario_idFuncionario=? where idLinha=?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, linha.getNome());
			stmt.setTime(2, linha.getDuracaoPercurso());
			stmt.setInt(3, linha.getIdFuncionario());
			stmt.setInt(4, linha.getIdLinha());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}