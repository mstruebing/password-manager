package homework.mstruebing.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Encryption.
 */
public class EncryptionServiceTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public EncryptionServiceTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(EncryptionServiceTest.class);
    }


	/**
	 * Test if the encryption is working properly
	 */
	public void testEncryption()
	{
		String toEncrypt = "FOR NOW IT IS THE SAME";
		String encrypted = "FOR NOW IT IS THE SAME";
		EncryptionService encryptionService = new EncryptionService();
		assertTrue(encryptionService.encrypt(toEncrypt).equals(encrypted));
	}

	/**
	 * Test if the decryption is working properly
	 */
	public void testDecryption()
	{
		String toDecrypt = "FOR NOW IT IS THE SAME";
		String decrypted = "FOR NOW IT IS THE SAME";
		EncryptionService encryptionService = new EncryptionService();
		assertTrue(encryptionService.decrypt(toDecrypt).equals(decrypted));
	}

	/**
	 * Test if encryption and decryption of a string gives the same result
	 */
	public void testEncryptAndDecrypt()
	{
		String toCheck = "SHOULD EVERYTIME BE THE SAME";
		EncryptionService encryptionService = new EncryptionService();
		assertTrue(encryptionService.decrypt(encryptionService.encrypt(toCheck)).equals(toCheck));
	}
}
