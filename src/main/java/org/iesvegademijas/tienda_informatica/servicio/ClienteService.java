package org.iesvegademijas.tienda_informatica.servicio;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.dao.ClienteDAO;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	private final ClienteDAO clienteDAO;

	public ClienteService(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public List<Cliente> listAll() {
		return clienteDAO.getAll();
	}

	public Optional<Cliente> one(int id) {
		return clienteDAO.find(id);
	}

	public void newCliente(Cliente cliente) {
		clienteDAO.create(cliente);
	}

	public void replaceCliente(Cliente cliente) {
		clienteDAO.update(cliente);
	}

	public void deleteCliente(int id) {
		clienteDAO.delete(id);
	}
}
