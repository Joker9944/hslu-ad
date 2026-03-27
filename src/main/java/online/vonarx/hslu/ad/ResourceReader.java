package online.vonarx.hslu.ad;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@UtilityClass
public final class ResourceReader {

	public static InputStream readResource(final String resource) {
		return ResourceReader.class.getResourceAsStream(resource);
	}

	public static byte[] readResourceAsBytes(final String resource) throws IOException {
		try (final var in = readResource(resource)) {
			if (in == null) throw new IllegalArgumentException(String.format("Resource %s not found on classpath", resource));

			return in.readAllBytes();
		}
	}

	public static String readResourceAsString(final String resource) throws IOException {
		return new String(readResourceAsBytes(resource), StandardCharsets.UTF_8);
	}
}
