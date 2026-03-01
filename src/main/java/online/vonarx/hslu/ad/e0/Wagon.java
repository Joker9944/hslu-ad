package online.vonarx.hslu.ad.e0;

import lombok.Data;

@Data
public class Wagon {

	final String name;
	final int capacity;

	Wagon next;

	public Wagon(final String name, final int capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	public Wagon(final String name, final int capacity, final Wagon next) {
		this(name, capacity);
		this.next = next;
	}
}
