package online.vonarx.hslu.ad;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@UtilityClass
public final class ResourceReader {

	public static String readResource(final String resource) throws IOException {
		try (final var in = ResourceReader.class.getResourceAsStream(resource)) {
			if (in == null) throw new IllegalArgumentException(String.format("Resource %s not found on classpath", resource));

			return new String(in.readAllBytes(), StandardCharsets.UTF_8);
		}
	}
}
