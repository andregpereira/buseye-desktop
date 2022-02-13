package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectionFactory.ConnectionFactory;
import model.Usuario;

public class UsuarioDAO {

	private Connection conexao;

	public UsuarioDAO() {
		this.conexao = new ConnectionFactory().getConnection();
	}

	// inserir registros no DB
	public void insert(Usuario usuario) {
		String sql = "insert into usuario(email, localizacao) values (?,?)";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getLocalizacao());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// buscar todos os registros do DB
	public List<Usuario> select() {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>(); // criar a lista para guardar objetos Contato

			PreparedStatement stmt = conexao.prepareStatement("select * from usuario");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) { // enquanto tiver registros no DB execute
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));

				usuarios.add(usuario);
			}
			rs.close();
			stmt.close();
			return usuarios;

		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException();
		}
	}

	// excluir um registro
	public void delete(Usuario usuario) {
		String sql = "delete from usuario where idUsuario=?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, usuario.getIdUsuario());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// alterar um registro
	public void update(Usuario usuario) {
		String sql = "update usuario set email=?, localizacao=? where idUsuario=?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getLocalizacao());
			stmt.setInt(3, usuario.getIdUsuario());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}