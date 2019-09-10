package com.consistent.cuervo.models;

public class Producto {
	
	private String category;
	private String nombre;
	private String material;
	private String descripcion;
	private String capacidad;
	private String precioNormal;
	private String precioBanquete;
	private String precioEspecial;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	public String getPrecioNormal() {
		return precioNormal;
	}
	public void setPrecioNormal(String precioNormal) {
		this.precioNormal = precioNormal;
	}
	public String getPrecioBanquete() {
		return precioBanquete;
	}
	public void setPrecioBanquete(String precioBanquete) {
		this.precioBanquete = precioBanquete;
	}
	public String getPrecioEspecial() {
		return precioEspecial;
	}
	public void setPrecioEspecial(String precioEspecial) {
		this.precioEspecial = precioEspecial;
	}
	public Producto(String category, String nombre, String material, String descripcion, String capacidad,
			String precioNormal, String precioBanquete, String precioEspecial) {
		super();
		this.category = category;
		this.nombre = nombre;
		this.material = material;
		this.descripcion = descripcion;
		this.capacidad = capacidad;
		this.precioNormal = precioNormal;
		this.precioBanquete = precioBanquete;
		this.precioEspecial = precioEspecial;
	}
}
