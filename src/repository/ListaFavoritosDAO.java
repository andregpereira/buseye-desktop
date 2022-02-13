package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectionFactory.ConnectionFactory;
import model.ListaFavoritos;

public class ListaFavoritosDAO {

	private Connection conexao;

	public ListaFavoritosDAO() {
		this.conexao = new ConnectionFactory().getConnection();
	}

	// inserir registros no DB
	public void insert(ListaFavoritos favoritos) {
		String sql = "insert into listaFavoritos(linha_idLinha, usuario_idUsuario) values(?,?)";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, favoritos.getIdLinha());
			stmt.setInt(2, favoritos.getIdUsuario());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// buscar todos os registros do DB
	public List<ListaFavoritos> select() {
		try {
			List<ListaFavoritos> listasFavoritos = new ArrayList<ListaFavoritos>(); // criar a lista para guarda objetos
																					// Contato
			PreparedStatement stmt = conexao.prepareStatement("select * from listaFavoritos");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) { // enquanto tiver registros no DB execute
				ListaFavoritos listaFavoritos = new ListaFavoritos();
				listaFavoritos.setIdLista(rs.getInt("idLista"));
				listaFavoritos.setIdLinha(rs.getInt("linha_idLinha"));
				listaFavoritos.setIdUsuario(rs.getInt("usuario_idUsuario"));

				listasFavoritos.add(listaFavoritos);
			}
			rs.close();
			stmt.close();
			return listasFavoritos;

		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException();
		}
	}

	// excluir um registro
	public void delete(ListaFavoritos favoritos) {
		String sql = "delete from listaFavoritos where usuario_idUsuario=?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, favoritos.getIdUsuario());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// alterar um registro
	public void update(ListaFavoritos favoritos) {
		String sql = "update listaFavoritos set linha_idLinha=?, usuario_idUsuario=? where idLista=?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, favoritos.getIdLinha());
			stmt.setInt(2, favoritos.getIdUsuario());
			stmt.setInt(3, favoritos.getIdLista());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}