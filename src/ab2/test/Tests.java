package ab2.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import ab2.AbstractHashMap;
import ab2.AuD2;
import ab2.impl.Gaggl_Gundacker_Kopali.AuD2Impl;

public class Tests {
    private AuD2 impl = new AuD2Impl();

    private static final int MOM_TEST_COUNT = 10;
    private static final int MOM_TEST_MINSIZE = 1;
    private static final int MOM_TEST_MAXSIZE = (int) Math.pow(2, 20);

    private static final int HASH_TEST_COUNT = 10;
    private static final int HASH_TEST_MINSIZE = 1;
    private static final int HASH_TEST_MAXSIZE = (int) Math.pow(2, 20);
    private static final double HASH_MAX_LOADFACTOR = 0.5;

    private static final Random rand = new Random(System.currentTimeMillis());

    // 3 Pts
    @Test
    public void testHashLinear_3() {
        AbstractHashMap hm = impl.newHashMapLinear(14);
        Assert.assertEquals(14, hm.totalSize());

        checkFullMap(hm);

        for (int size = HASH_TEST_MINSIZE; size <= HASH_TEST_MAXSIZE; size *= 2) {
            int maxElements = (int) Math.max(size * HASH_MAX_LOADFACTOR, 1);
            for (int i = 0; i < HASH_TEST_COUNT; i++) {
                AbstractHashMap hashMap = impl.newHashMapLinear(size);

                testAbstractHashMap(hashMap, maxElements);
            }
        }
    }

    // 3 Pts
    @Test
    public void testHashQuadratic_3() {
        AbstractHashMap hm = impl.newHashMapQuadratic(14);
        Assert.assertEquals(19, hm.totalSize());

        checkFullMap(hm);

        for (int size = HASH_TEST_MINSIZE; size <= HASH_TEST_MAXSIZE; size *= 2) {
            int maxElements = (int) Math.max(size * HASH_MAX_LOADFACTOR, 1);
            for (int i = 0; i < HASH_TEST_COUNT; i++) {
                AbstractHashMap hashMap = impl.newHashMapQuadratic(size);

                testAbstractHashMap(hashMap, maxElements);
            }
        }
    }

    //3 Pts
    @Test
    public void testHashDouble_3() {
        AbstractHashMap hm = impl.newHashMapDouble(14);
        Assert.assertEquals(17, hm.totalSize());

        checkFullMap(hm);

        for (int size = HASH_TEST_MINSIZE; size <= HASH_TEST_MAXSIZE; size *= 2) {
            int maxElements = (int) Math.max(size * HASH_MAX_LOADFACTOR, 1);
            for (int i = 0; i < HASH_TEST_COUNT; i++) {
                AbstractHashMap hashMap = impl.newHashMapDouble(size);

                testAbstractHashMap(hashMap, maxElements);
            }
        }
    }

    private void checkFullMap(AbstractHashMap hashMap) {
        for(int i = 0; i < hashMap.totalSize(); i++) {
            assertEquals(true, hashMap.put(i, i+""));
        }

        assertEquals(false, hashMap.put(-1, "ein Wert zu viel"));
    }

    private void testAbstractHashMap(AbstractHashMap hashMap, int elementCount) {
        int totalSize = hashMap.totalSize();

        // Als Referenz wird eine Map erzeugt, um die Implementierung zu testen
        Map<Integer, String> hashMapRef = new HashMap<>(totalSize);

        // Befülle die HashMaps
        for (int i = 0; i < elementCount; i++) {
            int key = rand.nextInt(totalSize);
            String value = "Wert " + key;

            hashMap.put(key, value);
            hashMapRef.put(key, value);
        }

        assertEquals(hashMapRef.size(), hashMap.elementCount());

        // Teste, ob die Werte wiedergefunden werden
        for (int i = 0; i < 10*elementCount; i++) {
            int key = rand.nextInt(totalSize);

            String value = hashMap.get(key);
            String valueRef = hashMapRef.get(key);

            assertEquals(valueRef, value);
        }
    }

    // 3 Pts
    @Test
    public void testMediansOfMedians_3() {
        for (int size = MOM_TEST_MINSIZE; size <= MOM_TEST_MAXSIZE; size *= 2) {
            int[] data = new int[size];

            for (int i = 0; i < MOM_TEST_COUNT; i++) {
                fillRandomInts(data, MOM_TEST_MAXSIZE);

                // kopiere das Array, sortiere es und bestimme den Median
                int[] data_sorted = Arrays.copyOf(data, data.length);
                Arrays.sort(data_sorted);
                int median_expected = data_sorted[data_sorted.length / 2];

                // bestimme den berechneten Wert laut Implementierung
                int median = impl.getMedian(data);

                assertEquals(median_expected, median);
            }
        }
    }

    private void fillRandomInts(int[] data, int maxValue) {
        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt(maxValue + 1);
        }
    }
}
