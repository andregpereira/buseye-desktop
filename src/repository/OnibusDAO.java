package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectionFactory.ConnectionFactory;
import model.Onibus;

public class OnibusDAO {

	private Connection conexao;

	public OnibusDAO() {
		this.conexao = new ConnectionFactory().getConnection();
	}

	// inserir registros no DB
	public void insert(Onibus onibus) {
		String sql = "insert into onibus(localizacao, funcionario_idFuncionario, linha_idLinha, capacidade) values (?,?,?,?)";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, onibus.getLocalizacao());
			stmt.setInt(2, onibus.getIdFuncionario());
			stmt.setInt(3, onibus.getIdLinha());
			stmt.setInt(4, onibus.getCapacidade());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// buscar todos os registros do DB
	public List<Onibus> select() {
		try {
			List<Onibus> onibusList = new ArrayList<Onibus>(); // criar a lista para guardar objetos Contato

			PreparedStatement stmt = conexao.prepareStatement("select * from onibus");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) { // enquanto tiver registros no DB execute
				Onibus onibus = new Onibus();
				onibus.setIdOnibus(rs.getInt("idOnibus"));
				onibus.setLocalizacao(rs.getString("localizacao"));
				onibus.setIdFuncionario(rs.getInt("funcionario_idFuncionario"));
				onibus.setIdLinha(rs.getInt("linha_idLinha"));
				onibus.setCapacidade(rs.getInt("capacidade"));

				onibusList.add(onibus);
			}
			rs.close();
			stmt.close();
			return onibusList;

		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException();
		}
	}

	// excluir um registro
	public void delete(Onibus onibus) {
		String sql = "delete from onibus where idOnibus=?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, onibus.getIdOnibus());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// alterar um registro
	public void update(Onibus onibus) {
		String sql = "update onibus set localizacao=?, funcionario_idFuncionario=?, linha_idLinha=?, capacidade=? where idOnibus=?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, onibus.getLocalizacao());
			stmt.setInt(2, onibus.getIdFuncionario());
			stmt.setInt(3, onibus.getIdLinha());
			stmt.setInt(4, onibus.getCapacidade());
			stmt.setInt(5, onibus.getIdOnibus());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}