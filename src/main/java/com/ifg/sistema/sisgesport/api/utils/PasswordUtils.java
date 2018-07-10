package com.ifg.sistema.sisgesport.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

	private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);
	
	public PasswordUtils() {	}
	
	/**
	 * Gerando senha com Hash BCrypt
	 * 
	 * @param senha
	 * @return String
	 */
	public static String GerarBCrypt(String senha) {
		if(senha == null || senha == "") 
		{
			return senha;
		}
		
		log.info("Gerando hash com o BCrypt.");
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(senha);
	}
}
