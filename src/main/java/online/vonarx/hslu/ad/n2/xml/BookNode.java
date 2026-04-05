package online.vonarx.hslu.ad.n2.xml;

import online.vonarx.hslu.ad.ResourceReader;
import org.jspecify.annotations.NonNull;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple tree structure to represent an XML document containing books, book, chapter, paragraphs.
 * The structure does not model XML attributes.
 */
public record BookNode(String name, String text, List<BookNode> children) {

	/**
	 * Creates the tree structure from an XML document.
	 */
	public static BookNode getFromXml(final @NonNull String path) {
		try {
			final var builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			final var doc = builder.parse(ResourceReader.readResource(path));
			doc.getDocumentElement().normalize();
			final var books = doc.getElementsByTagName("books").item(0);
			return getFromXml(books);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// forward checked exception to caller in case
			// file is not well-formed or not readable
			throw new RuntimeException("Exception during doc parsing", e);
		}
	}

	private static BookNode getFromXml(final @NonNull Node node) {
		final var childNodesBook = new ArrayList<BookNode>();
		final var childNodesDom = node.getChildNodes();
		for (int i = 0; i < childNodesDom.getLength(); ++i) {
			final var childNodeDom = childNodesDom.item(i);
			if (childNodeDom.getNodeType() == Node.ELEMENT_NODE) {
				childNodesBook.add(getFromXml(childNodeDom));
			}
		}
		return new BookNode(node.getNodeName(), node.getTextContent(), childNodesBook);
	}

	public String getTitle() {
		if (!this.name.equals("book")) throw new IllegalArgumentException("node must be a book node");

		for (final var childNodes : children) {
			if (childNodes.name().equals("title")) {
				return childNodes.text();
			}
		}
		return ""; // no title found
	}
}
