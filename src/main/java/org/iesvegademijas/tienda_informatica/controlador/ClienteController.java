package org.iesvegademijas.tienda_informatica.controlador;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.servicio.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("/clientes")
	public String listar(Model model) {
		model.addAttribute("listaClientes", clienteService.listAll());
		return "clientes";
	}

	@GetMapping("/clientes/crear")
	public String crear(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "crear-cliente";
	}

	@PostMapping("/clientes/crear")
	public String submitCrear(@ModelAttribute("cliente") Cliente cliente) {
		clienteService.newCliente(cliente);
		return "redirect:/clientes";
	}

	@GetMapping("/clientes/{id}")
	public String detalle(@PathVariable int id, Model model) {
		Cliente cliente = clienteService.one(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		model.addAttribute("cliente", cliente);
		return "detalle-cliente";
	}

	@GetMapping("/clientes/editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		Cliente cliente = clienteService.one(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		model.addAttribute("cliente", cliente);
		return "editar-cliente";
	}

	@PostMapping("/clientes/editar/{id}")
	public String submitEditar(@PathVariable int id, @ModelAttribute("cliente") Cliente cliente) {
		cliente.setId(id);
		clienteService.replaceCliente(cliente);
		return "redirect:/clientes";
	}

	@PostMapping("/clientes/borrar/{id}")
	public String submitBorrar(@PathVariable int id) {
		clienteService.deleteCliente(id);
		return "redirect:/clientes";
	}
}
