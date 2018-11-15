package com.ifg.sistema.sisgesport.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.ifg.sistema.sisgesport.api.dto.commom_entities.PessoaDTO;
import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.entities.Evento;

public class ServidorDTO extends PessoaDTO {
	private CargoDTO cargo;

	public ServidorDTO() {
	}

	public CargoDTO getCargo() {
		return cargo;
	}

	public void setCargo(CargoDTO cargo) {
		this.cargo = cargo;
	}
}
