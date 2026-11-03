package org.iesvegademijas.tienda_informatica;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.servicio.ClienteService;
import org.iesvegademijas.tienda_informatica.servicio.ComercialService;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
class TiendaInformaticaJdbcApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ComercialService comercialService;

	@Test
	void contextLoads() {
	}

	@Test
	void customerAndCommercialPagesRender() throws Exception {
		mockMvc.perform(get("/clientes"))
				.andExpect(status().isOk())
				.andExpect(view().name("clientes"));
		mockMvc.perform(get("/clientes/crear"))
				.andExpect(status().isOk())
				.andExpect(view().name("crear-cliente"));
		mockMvc.perform(get("/comerciales"))
				.andExpect(status().isOk())
				.andExpect(view().name("comerciales"));
		mockMvc.perform(get("/comerciales/crear"))
				.andExpect(status().isOk())
				.andExpect(view().name("crear-comercial"));
	}

	@Test
	@Transactional
	void clientAndCommercialCrudWorks() {
		String token = UUID.randomUUID().toString();
		Cliente cliente = new Cliente(null, token, "Apellido", null, "Mijas", 7);
		clienteService.newCliente(cliente);
		Cliente clienteGuardado = clienteService.listAll().stream()
				.filter(c -> c.getNombre().equals(token))
				.findFirst()
				.orElseThrow();
		clienteGuardado.setCiudad("Fuengirola");
		clienteService.replaceCliente(clienteGuardado);
		assertEquals("Fuengirola", clienteService.one(clienteGuardado.getId()).orElseThrow().getCiudad());
		clienteService.deleteCliente(clienteGuardado.getId());
		assertTrue(clienteService.one(clienteGuardado.getId()).isEmpty());

		Comercial comercial = new Comercial(null, token, "Apellido", "Segundo", new BigDecimal("0.15"));
		comercialService.newComercial(comercial);
		Comercial comercialGuardado = comercialService.listAll().stream()
				.filter(c -> c.getNombre().equals(token))
				.findFirst()
				.orElseThrow();
		comercialGuardado.setComision(new BigDecimal("0.20"));
		comercialService.replaceComercial(comercialGuardado);
		assertEquals(0, new BigDecimal("0.20")
				.compareTo(comercialService.one(comercialGuardado.getId()).orElseThrow().getComision()));
		comercialService.deleteComercial(comercialGuardado.getId());
		assertTrue(comercialService.one(comercialGuardado.getId()).isEmpty());
	}
}
