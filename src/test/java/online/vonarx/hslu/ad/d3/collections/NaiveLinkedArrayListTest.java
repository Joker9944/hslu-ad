package online.vonarx.hslu.ad.d3.collections;

class NaiveLinkedArrayListTest extends NaiveArrayListTest {

	@Override
	protected NaiveArrayList<String> createList() {
		return new NaiveLinkedArrayList<>();
	}

	@Override
	protected NaiveArrayList<String> createList(int size) {
		return new NaiveLinkedArrayList<>(size);
	}
}
