package ab2;

/**
 * @author Raphael Wigoutschnigg
 */
public abstract class AbstractHashMap {
	private String[] table;
	private Integer[] keyTable;

	/**
	 * Initialisiert die Tabelle mit der gegebenen Größe (Anzahl der Elemente in dem Array).
	 */
	protected void initTable(int size) {
		if (table != null)
			throw new IllegalStateException();
		table = new String[size];
		keyTable = new Integer[size];
	}

	/**
	 * Gibt die Größe der Hashtabelle zurück (Anzahl der Elemente in dem Array).
	 */
	public int totalSize() {
		checkInitialized();
		return table.length;
	}

	/**
	 * Setzt einen Schlüssel und Wert an die angegebene Stelle. Ein bereits vorhandener Schlüssel/Wert wird überschrieben.
	 */
	protected void setKeyAndValue(int idx, int key, String value) {
		checkInitialized();
		table[idx] = value;
		keyTable[idx] = key;
	}

	/**
	 * Liefert den Wert an der angegebenen Stelle
	 */
	protected String getValue(int idx) {
		checkInitialized();
		return table[idx];
	}

	/**
	 * Liefert den Schlüssel an der angegebenen Stelle
	 */
	protected Integer getKey(int idx) {
		checkInitialized();
		return keyTable[idx];
	}

	/**
	 * Liefert true, wenn die jeweilige Stelle nicht belegt ist. Andernfalls false.
	 */
	protected boolean isEmpty(int idx) {
		checkInitialized();
		return table[idx] == null;
	}

	/**
	 * Setzt die Tabelle zurück. Die Größe bleibt erhalten.
	 */
	protected void clear() {
		table = null;
		keyTable = null;
	}

	private void checkInitialized() {
		if (table == null)
			throw new IllegalStateException();
	}

	/**
	 * Fügt ein Key-Value-Paar in die Hashtabelle ein. Wird ein Schlüssel abermals
	 * eigefügt, soll das gespeicherte Value-Objekt ersetzt werden.
	 *
	 * @param key
	 *            Schlüssel, darf nicht null sein
	 * @param value
	 * @return liefert false, falls die Tabelle bereits voll ist
	 */
	public abstract boolean put(int key, String value);

	/**
	 * Liefert das gespeicherte Value-Objekt oder null, falls unter dem Schlüssel
	 * nichts gespeichert ist
	 *
	 * @param key
	 * @return null, falls der Schlüssel nicht vorkomment, andernfalls das
	 *         dazugehörige Objekt
	 */
	public abstract String get(int key);

	/**
	 * Liefert die Anzahl der gespeicherten Elemente in der Hashtabelle
	 *
	 * @return Anzahl der gespeicherten Elemente
	 */
	public abstract int elementCount();
}