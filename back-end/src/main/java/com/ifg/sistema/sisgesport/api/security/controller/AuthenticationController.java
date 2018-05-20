package com.ifg.sistema.sisgesport.api.security.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.security.dto.JwtAuthenticationDTO;
import com.ifg.sistema.sisgesport.api.security.dto.TokenDTO;
import com.ifg.sistema.sisgesport.api.security.utils.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer";
	private Response<TokenDTO> response = new Response<TokenDTO>();
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Gera um novo token para o usuário.
	 * 
	 * @param jwtAuthDTO
	 * @param result
	 * @return ResponseEntity<Response<TokenDTO>>
	 * @throws AuthenticationException
	 */
	@PostMapping
	public ResponseEntity<Response<TokenDTO>> gerarTokenJwt(@Valid @RequestBody JwtAuthenticationDTO jwtAuthDTO,
			BindingResult result) throws AuthenticationException {

		if (result.hasErrors()) {
			log.error("Erro ao validar acesso: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		log.info("Gerando token para a matrícula: {}", jwtAuthDTO.getMatricula());
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jwtAuthDTO.getMatricula(), jwtAuthDTO.getMatricula()));
		SecurityContextHolder.getContext().setAuthentication(auth);

		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthDTO.getMatricula());
		response.setData(new TokenDTO(jwtTokenUtil.obterToken(userDetails)));
		return ResponseEntity.ok(response);
	}

	/***
	 * Atualiza o token do usuário, gerando um novo token e retornando.
	 * @param request
	 * @return ResponseEntity<Response<TokenDTO>>
	 */
	@PostMapping(value = "/refresh")
	public ResponseEntity<Response<TokenDTO>> atualizarToken(HttpServletRequest request) {
		log.info("Atualizando token JWT.");
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));

		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
		}
		if (!token.isPresent()) {
			response.getErrors().add("Token não informado");
		} else if (!jwtTokenUtil.tokenValido(token.get())) {
			response.getErrors().add("Token inválido ou expirado.");
		}
		if (!response.getErrors().isEmpty()) {
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(new TokenDTO(jwtTokenUtil.refreshToken(token.get())));
		return ResponseEntity.ok(response);
	}
}
