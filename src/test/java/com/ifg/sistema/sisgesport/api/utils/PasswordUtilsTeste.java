package com.ifg.sistema.sisgesport.api.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTeste {
	private static final String senha = "123456";
	private final BCryptPasswordEncoder bCryptEnconder = new BCryptPasswordEncoder();
	
	@Test
	public void testSenhaNula() throws Exception{
		assertNull(PasswordUtils.GerarBCrypt(null));
	}
	
	@Test
	public void testGerarHashSenha() throws Exception{
		String hash = PasswordUtils.GerarBCrypt(senha);
		
		assertTrue(bCryptEnconder.matches(senha, hash));
	}
}
