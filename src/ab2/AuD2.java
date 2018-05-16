package ab2;

public interface AuD2 {
	/**
	 * Lierfert eine HashMap-Implementierung mit linearer Sondierungsstrategie. Die Hashtabelle soll mindestens minSize Elemente aufnehmen können.
	 * @param minSize Anzahl an Elementen, die gespeichert werden können müssen
	 * @return Hashmap-Implementierung
	 */
	public AbstractHashMap newHashMapLinear(int minSize);

	/**
	 * Liefert eine HashMap-Implementierung mit qudratischer Sondierungsstrategie. Die Hashtabelle soll mindestens minSize Elemente aufnehmen können.
	 * @param minSize Anzahl an Elementen, die gespeichert werden können müssen
	 * @return Hashmap-Implementierung
	 */
	public AbstractHashMap newHashMapQuadratic(int minSize);

	/**
	 * Liefert eine HashMap-Implementierung mit doppeltem Hashen. Die Hashtabelle soll mindestens minSize Elemente aufnehmen können.
	 * @param minSize Anzahl an Elementen, die gespeichert werden können müssen
	 * @return Hashmap-Implementierung
	 */
	public AbstractHashMap newHashMapDouble(int minSize);

	/**
	 * Bestimmt mittels des Median-of-Medians-Algorithmus den Median des übergebenen Arrays
	 * @param data
	 * @return Median der Daten
	 */
	public int getMedian(int[] data);
}
