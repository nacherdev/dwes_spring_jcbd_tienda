package org.iesvegademijas.tienda_informatica.dao;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

	private final JdbcTemplate jdbcTemplate;

	public ClienteDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void create(Cliente cliente) {
		jdbcTemplate.update(
				"INSERT INTO cliente (nombre, apellido1, apellido2, ciudad, `categoría`) VALUES (?, ?, ?, ?, ?)",
				cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(), cliente.getCiudad(),
				cliente.getCategoria());
	}

	@Override
	public List<Cliente> getAll() {
		return jdbcTemplate.query(
				"SELECT id, nombre, apellido1, apellido2, ciudad, `categoría` AS categoria FROM cliente ORDER BY id",
				(rs, rowNum) -> new Cliente(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("apellido1"),
						rs.getString("apellido2"),
						rs.getString("ciudad"),
						(rs.getObject("categoria") == null) ? null : rs.getInt("categoria")));
	}

	@Override
	public Optional<Cliente> find(int id) {
		List<Cliente> clientes = jdbcTemplate.query(
				"SELECT id, nombre, apellido1, apellido2, ciudad, `categoría` AS categoria FROM cliente WHERE id = ?",
				(rs, rowNum) -> new Cliente(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("apellido1"),
						rs.getString("apellido2"),
						rs.getString("ciudad"),
						(rs.getObject("categoria") == null) ? null : rs.getInt("categoria")),
				id);
		return clientes.stream().findFirst();
	}

	@Override
	public void update(Cliente cliente) {
		jdbcTemplate.update(
				"UPDATE cliente SET nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, `categoría` = ? WHERE id = ?",
				cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(), cliente.getCiudad(),
				cliente.getCategoria(), cliente.getId());
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM cliente WHERE id = ?", id);
	}
}
