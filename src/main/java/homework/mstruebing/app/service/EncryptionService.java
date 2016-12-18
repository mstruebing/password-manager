package homework.mstruebing.app;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility functions to encrypt and decrypt data
 *
 */
public class EncryptionService
{
	
	// @TODO config
	protected final int defaultLength = 16;
	protected final String defaultCharset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890";

	/**
	 * encrypts a given string
	 * 
	 * @param input the string to encrypt
	 * @return String
	 *
	 */
	public String encrypt(String input) {
		return input;
	}

	/**
	 * decrypts a given string
	 *
	 * @param input the string to decrypt
	 * @return String
	 *
	 */
	public String decrypt(String input) {
		return input;
	}

	public String generatePassword() {
		return generatePassword(defaultCharset, defaultLength);	
	}

	public String generatePassword(String charset) {
		return generatePassword(charset, defaultLength);
	}

	public String generatePassword(int length) {
		return generatePassword(defaultCharset, length);
	}

	public String generatePassword(String charset, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i= 0; i < length; i++) {
			int k = ThreadLocalRandom.current().nextInt(0, charset.length() - 1);   // random number between 0 and set.length()-1 inklusive
			sb.append(charset.charAt(k));
		}
		String result = sb.toString();
		return result;
	}
}
