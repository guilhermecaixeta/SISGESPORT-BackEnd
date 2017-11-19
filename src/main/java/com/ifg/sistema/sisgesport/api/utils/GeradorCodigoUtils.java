package com.ifg.sistema.sisgesport.api.utils;

import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeradorCodigoUtils {
	
	private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);
		
	/**
	 * Gera um código randomico através do UUID e da data atual, assim gerando um código único.
	 * @return string
	 */
	public static String GerarCodigoUnico() {
		Calendar simple = Calendar.getInstance();
		UUID uuid = UUID.randomUUID();

		String codigo = (simple.hashCode() + uuid.toString()).substring(0, 20);
		log.info("Gerando código através do UUID. O Código é: " + codigo);
			return codigo;
		
	}
}
	
