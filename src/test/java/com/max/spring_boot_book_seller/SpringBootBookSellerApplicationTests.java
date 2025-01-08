package com.max.spring_boot_book_seller;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;

@SpringBootTest
class SpringBootBookSellerApplicationTests {

	@Test
	void contextLoads() {
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		String base64Key = java.util.Base64.getEncoder().encodeToString(key.getEncoded());
		System.out.println("Base64 Encoded Key: " + base64Key);
	}

}
