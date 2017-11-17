package com.algaworks.algamoneyapi.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {

	
		public static void main(String[] args) {
			BCryptPasswordEncoder password = new BCryptPasswordEncoder();
			System.out.println(password.encode("raul"));		
		}
}
