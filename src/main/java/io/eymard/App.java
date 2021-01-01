package io.eymard;

import java.text.ParseException;
import java.util.Scanner;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.AESDecrypter;

public class App {
	
	public void ui(Scanner scanner) {	
		
		// Prompt for the 256-bit encryption key
		System.out.println("\nEnter the 256-bit encryption key that was used to encrypt the data.\n => ");
		String key = scanner.next();
		
		// Prompt for the encrypted data
		System.out.println("\nEnter the data you want decrypted.\n => ");
		String value = scanner.next();
		
		System.out.println("Decrypting...");
		
		String result = "";
		
		try {
			
			result = decryptClaim(key, value);
			// System.out.println("Result:\n " + result);
			System.out.println(String.format("Result:\n%s", result));
			
			
			System.out.println("\n\nWant to decrypt another? (y/n)\n =>");
			String response = scanner.next();
				
			
			if (response.toString().toLowerCase().matches("y") || response.toString().toLowerCase().matches("yes")) {
				Decryptor2.StartApp();
			} else {
				scanner.close();
				System.out.println("\nGoodbye");
			}
			
		} catch (ParseException e) {
			System.out.println("\nA ParseException error occurred.\nYou entered an invalid key/value pair.");
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
			System.out.println("\nWant to try again? (y/n)\n =>");
			String response = scanner.next();
			
			if (response.toString().toLowerCase().matches("y") || response.toString().toLowerCase().matches("yes")) {
				Decryptor2.StartApp();
			} else {
				scanner.close();
				System.out.println("\nGoodbye");
			}
			
		} catch (JOSEException e) {
			System.out.println("An error occurred.\n");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static String decryptClaim(final String key, String claim) throws ParseException, JOSEException {
		
	    JWEObject jweObject = JWEObject.parse(claim);
	    
	    byte[] keyBytes = key.getBytes();
	    
	    SecretKey secretKey = new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
	    
	    jweObject.decrypt(new AESDecrypter(secretKey));
	   
	    return jweObject.getPayload().toString();
	}

}
