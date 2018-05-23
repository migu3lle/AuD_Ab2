package ab2.impl.Gaggl_Gundacker_Kopali;


import ab2.AbstractHashMap;

/**
 * Author: Gundacker Michael
 * Created: 05/22/18
 * HashMap mit quadratischer Sondierungsstrategie
 */

public class HashMapQuadraticImpl extends AbstractHashMap{


    /** Constructor
     * @param size
     * Initializes HashMap with proper prime number as size
     */
    public HashMapQuadraticImpl(int size){
        int prime = getNextPrime(size);
        while(!(prime % 4 == 3)){
            prime = getNextPrime(prime+1);
        }
        initTable(prime);
    }

    @Override
    public boolean put(int key, String value) {
        //System.out.println("I have to put key " + key + " with value " + value);

        //If HashMap is full, return false
        if(elementCount() >= totalSize())
            return false;

        int idx = calcModuloIndex(key);
        int offset = 0;
        int offsetCount = 1;
        int tmp;
        while(getValue(idx+offset) != null && getKey(idx+offset) != key){
            offset = getOffSet(offsetCount);

            //Check if idx+offset larger than totalSize
            if(idx+offset > totalSize()){
                offset = ((idx+offset) % totalSize()) - idx;
            }
            //Check if idx+offset smaller than zero
            if(idx+offset < 0){
                tmp = totalSize() + (idx+offset) % totalSize();
                offset = tmp - idx;
            }

            offsetCount++;
        }
        //System.out.println("Putting key " + key + " to idx " + (idx+offset) + " with value " + value);
        setKeyAndValue(idx+offset, key, value);
        return true;
    }

    @Override
    public String get(int key) {
        for (int i = 0; i < totalSize(); i++) {
            if(getKey(i) != null && getKey(i) == key){
                return getValue(i);
            }
        }
        return null;
    }

    @Override
    public int elementCount() {
        int count = 0;
        for (int i = 0; i < totalSize(); i++) {
            if(!isEmpty(i)){
                count++;
            }
        }
        return count;
    }



    /** Helper method
     * @param a : initial offset, array was not free at this offset position
     * @return : next possible offset value
     */
    private int getOffSet(int a){
        int offset = (int)(Math.pow((double) (Math.ceil((double)a/2)), 2) * Math.pow(-1,a));
        //System.out.println("New Offset: " + offset);
        return offset;
    }

    /** Helper method
     * @param a : Index to calculate position for
     * @return : Index in table array, calculated by MOD size
     */
    public int calcModuloIndex(int a){
        return a % totalSize();
    }

    /** Helper method
     * @param a : input value to find next bigger prime (or itself if it is prime)
     * @return : next prime number
     */
    private int getNextPrime(int a){
        int i = a;
        while(!isPrime(i)){
            i++;
        }
        return i;
    }

    /** Helper method
     * @param a : input to be checked for prime
     * @return : false if a is no prime, true if it is prime
     */
    private boolean isPrime(int a){
        for (int i = a; i > 0; i--) {
            if(a%i == 0 && i != a && i != 1){
                return false;
            }
        }
        return true;
    }
}
