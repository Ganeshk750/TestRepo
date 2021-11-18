package com.ganesh;

import java.util.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWT_Test {
	
	private static String secret_key = "ganesh123!@#$%^&*";
	
	public static void main(String[] args) {
		
		//Code to tested generated Token
		String token = JWTUtil.generateToken("Token1", secret_key);
		System.out.println("---------TOKEN----------");
		System.out.println(token);
		System.out.println();
		System.out.println("--------CLAIMS---------");
		
		
		//Code to test parsed token: Claims
		
		Claims claims = Jwts.parser()
				        .setSigningKey(Base64.getEncoder().encode(secret_key.getBytes()))
		                .parseClaimsJws(token)
		                .getBody();
		
		System.out.println("TOKEN ID : "+ claims.getId());
		System.out.println("TOKEN SUBJECT: "+ claims.getSubject());
		System.out.println("TOKEN ISSURE: "+ claims.getIssuer());
		System.out.println("TOKEN ISSUE DATE: "+ claims.getIssuedAt());
		System.out.println("TOKEN EXPIRATION DATE: "+ claims.getExpiration());
		System.out.println("TOKEN AUDIENCE: "+ claims.getAudience());
	}

}
