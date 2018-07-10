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
	public static String GerarCodigoUnicoEquipe() {
		Calendar simple = Calendar.getInstance();
		UUID uuid = UUID.randomUUID();

		String codigo = "EQ"+ (Integer.toString(simple.hashCode()).substring(0,8) + uuid.toString()).substring(0, 18).toUpperCase();
		log.info("Gerando código da equipe através do UUID. O Código é: " + codigo);
			return codigo;	
	}
	
	public static String GerarCodigoUnicoEvento() {
		Calendar simple = Calendar.getInstance();
		UUID uuid = UUID.randomUUID();

		String codigo = "EV"+ (Integer.toString(simple.hashCode()).substring(0,5) + uuid.toString()).substring(0, 13).toUpperCase();
		log.info("Gerando código do evento através do UUID. O Código é: " + codigo);
			return codigo;	
	}
}
	
