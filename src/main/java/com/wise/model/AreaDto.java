package com.wise.model;

import java.util.List;

public class AreaDto {
	
	private String idDireccion;	
	private String descDireccion;
	private List<ResponsableDto> responsables;
	
	public String getIdDireccion() {
		return idDireccion;
	}
	public void setIdDireccion(String idDireccion) {
		this.idDireccion = idDireccion;
	}
	public String getDescDireccion() {
		return descDireccion;
	}
	public void setDescDireccion(String descDireccion) {
		this.descDireccion = descDireccion;
	}
	public List<ResponsableDto> getResponsables() {
		return responsables;
	}
	public void setResponsables(List<ResponsableDto> responsables) {
		this.responsables = responsables;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descDireccion == null) ? 0 : descDireccion.hashCode());
		result = prime * result
				+ ((idDireccion == null) ? 0 : idDireccion.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaDto other = (AreaDto) obj;
		if (descDireccion == null) {
			if (other.descDireccion != null)
				return false;
		} else if (!descDireccion.equals(other.descDireccion))
			return false;
		if (idDireccion == null) {
			if (other.idDireccion != null)
				return false;
		} else if (!idDireccion.equals(other.idDireccion))
			return false;
		return true;
	}	
}
