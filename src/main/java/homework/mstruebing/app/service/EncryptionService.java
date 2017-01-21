package homework.mstruebing.app;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Base64;
import java.io.UnsupportedEncodingException;

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
	 * @param String input - the string to encrypt
	 * @return String - the encrypted string
	 *
	 */
	public String encrypt(String input)
	{
		String encodedString = "";

		try {
			encodedString =  Base64.getEncoder().encodeToString(input.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error :" + e.getMessage());
      }

	  return encodedString;
	}

	/**
	 * decrypts a given string
	 *
	 * @param String input - the string to decrypt
	 * @return String - the decrypted string
	 *
	 */
	public String decrypt(String encodedString)
	{
		String decodedString = "";
		byte[] base64decodedBytes;

		try {
			base64decodedBytes = Base64.getDecoder().decode(encodedString);
			decodedString = new String(base64decodedBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error :" + e.getMessage());
		}

		return decodedString;
	}

	/**
	 * gernates a random password
	 * uses the default charset and default length
	 *
	 * @return String - a random password
	 */
	public String generatePassword()
	{
		return generatePassword(defaultCharset, defaultLength);
	}

	/**
	 * gernates a random password
	 * uses the default length
	 *
	 * @param String
	 * @return String
	 */
	public String generatePassword(String charset)
	{
		return generatePassword(charset, defaultLength);
	}

	/**
	 * gernates a random password
	 * uses the default charset
	 *
	 * @param int
	 * @return String
	 */
	public String generatePassword(int length)
	{
		return generatePassword(defaultCharset, length);
	}

	/**
	 * gernates a random password
	 *
	 * @param String
	 * @param int
	 * @return String
	 */
	public String generatePassword(String charset, int length)
	{
		StringBuilder sb = new StringBuilder();

		for (int i= 0; i < length; i++) {
			int k = ThreadLocalRandom.current().nextInt(0, charset.length() - 1);   // random number between 0 and set.length()-1 inklusive
			sb.append(charset.charAt(k));
		}

		return sb.toString();
	}
}
