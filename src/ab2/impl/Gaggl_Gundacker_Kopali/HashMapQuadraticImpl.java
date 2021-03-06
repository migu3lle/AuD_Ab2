package ab2.impl.Gaggl_Gundacker_Kopali;


import ab2.AbstractHashMap;

/**
 * Author: Gundacker Michael
 * Created: 05/22/18
 * HashMap mit quadratischer Sondierungsstrategie
 */

public class HashMapQuadraticImpl extends AbstractHashMap{

    private int elements;

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

        //If HashMap is full, return false
        if(elementCount() >= totalSize())
            return false;

        int idx = calcModuloIndex(key);
        int offset = 0;
        int offsetCount = 1;

        do {
            //Check if element is free: insert and increase counter
            if (getKey(idx + offset) == null) {
                setKeyAndValue(idx + offset, key, value);
                elements++;
                return true;
            }   //If not free but same key: insert but don't increase counter
            if (getKey(idx + offset) == key) {
                setKeyAndValue(idx + offset, key, value);
                return true;
            }
            //Calculate next offset for index
            offset = getNextOffSet(offsetCount, idx);
            offsetCount++;

        }while(idx+offset != idx);  //Just in case...
        return false;
    }

    @Override
    public String get(int key) {
        int idx = calcModuloIndex(key);
        int offset = 0;
        int offsetCount = 1;
        int tmp;

        while(getKey(idx+offset) != null){
            if(getKey(idx+offset) == key){
                return getValue(idx+offset);
            }
            offset = getNextOffSet(offsetCount, idx);
            offsetCount++;
        }

        return null;
    }

    @Override
    public int elementCount() {
        return elements;
    }

    /** Helper method
     * @param offsetCount : for offset calculation
     * @param index : to check if index+offset is full already
     * @return newOffset, so that index+newOffset is free
     */
    private int getNextOffSet(int offsetCount, int index){
        int newOffset = getOffSet(offsetCount);
        int tmp;

        //Check if idx+offset larger than totalSize
        if(index+newOffset > totalSize()){
            newOffset = ((index+newOffset) % totalSize()) - index;
        }
        //Check if idx+offset smaller than zero
        if(index+newOffset < 0){
            tmp = totalSize() + (index+newOffset) % totalSize();
            newOffset = tmp - index;
        }
        return newOffset;
    }

    /** Helper method
     * @param a : initial offset, array was not free at this offset position
     * @return : next possible offset value
     */
    private int getOffSet(int a){
        return (int)(Math.pow((Math.ceil((double)a/2)), 2) * Math.pow(-1,a));
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
