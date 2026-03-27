package online.vonarx.hslu.ad.d3.chars.ex;

import java.io.IOException;

import static online.vonarx.hslu.ad.ResourceReader.readResourceAsString;
import static online.vonarx.hslu.ad.d3.chars.LetterCountDecryptor.decrypt;

public class SecretDecryption {

	public static void main(String[] args) throws IOException {
		final var secret = readResourceAsString("/d3/secret.txt");
		final var decrypted = decrypt(secret);
		System.out.println(decrypted);
	}
}
