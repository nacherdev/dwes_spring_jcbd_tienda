package org.iesvegademijas.tienda_informatica.controlador;

import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.servicio.ComercialService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ComercialController {

	private final ComercialService comercialService;

	public ComercialController(ComercialService comercialService) {
		this.comercialService = comercialService;
	}

	@GetMapping("/comerciales")
	public String listar(Model model) {
		model.addAttribute("listaComerciales", comercialService.listAll());
		return "comerciales";
	}

	@GetMapping("/comerciales/crear")
	public String crear(Model model) {
		model.addAttribute("comercial", new Comercial());
		return "crear-comercial";
	}

	@PostMapping("/comerciales/crear")
	public String submitCrear(@ModelAttribute("comercial") Comercial comercial) {
		comercialService.newComercial(comercial);
		return "redirect:/comerciales";
	}

	@GetMapping("/comerciales/{id}")
	public String detalle(@PathVariable int id, Model model) {
		Comercial comercial = comercialService.one(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		model.addAttribute("comercial", comercial);
		return "detalle-comercial";
	}

	@GetMapping("/comerciales/editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		Comercial comercial = comercialService.one(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		model.addAttribute("comercial", comercial);
		return "editar-comercial";
	}

	@PostMapping("/comerciales/editar/{id}")
	public String submitEditar(@PathVariable int id, @ModelAttribute("comercial") Comercial comercial) {
		comercial.setId(id);
		comercialService.replaceComercial(comercial);
		return "redirect:/comerciales";
	}

	@PostMapping("/comerciales/borrar/{id}")
	public String submitBorrar(@PathVariable int id) {
		comercialService.deleteComercial(id);
		return "redirect:/comerciales";
	}
}
