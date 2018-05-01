package com.ifg.sistema.sisgesport.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.CargoDTO;
import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.services.CargoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("sisgesport/cargo")
public class CargoController extends baseController<CargoDTO, Cargo, CargoService>{

}
