package com.sapo.drakkar.common.autentication;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by giampaolo.saporito
 * on 28.12.2017 - giovedì
 */
public class UserCodeGenerator
{
	private UserCodeGenerator() {}

	private static final String digits = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0987654321.";
	private static final int LENGTH = 50;
	private static Random rndm = null;

	public static String generateSessionCode() {
		return generate( digits, 75 );
	}

	public static String generateUniqueCode(String usrName) {
		if( usrName == null || usrName.trim().isEmpty() ) {
			usrName = digits;
		}
		return generate( usrName, LENGTH );
	}

	private static String generate(String syms, int lenght) {
		char[] symbols = syms.toCharArray();
		char[] buf = new char[lenght];
		for( int idx = 0; idx < lenght; ++idx ) {
			buf[idx] = symbols[ random().nextInt(symbols.length) ];
		}
		return new String(buf);
	}

//	public static void main(String[] args) {
//		System.out.println( UserCodeGenerator.generateUniqueCode( "puntovendita1" ) );
//	}

	private static Random random() {
		if( rndm == null ) {
			rndm = new SecureRandom();
		}
		return rndm;
	}
}
