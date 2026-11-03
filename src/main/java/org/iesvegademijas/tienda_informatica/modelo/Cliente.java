package org.iesvegademijas.tienda_informatica.modelo;

public class Cliente {

	private Integer id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String ciudad;
	private Integer categoria;

	public Cliente() {
	}

	public Cliente(Integer id, String nombre, String apellido1, String apellido2, String ciudad, Integer categoria) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.ciudad = ciudad;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
}
