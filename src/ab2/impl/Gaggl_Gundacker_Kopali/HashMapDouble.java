package ab2.impl.Gaggl_Gundacker_Kopali;

import ab2.AbstractHashMap;
/**
 * Author: Nils Kopali
 * Created: 05/25/18
 * HashMap mit doppelten Hashen Sondierungsstrategie
 */
public class HashMapDouble extends AbstractHashMap {
    private int elements;

    public HashMapDouble(int minSize) {
        int prim = getPrim(minSize);
        initTable(prim);
    }

    private int getPrim(int a) {
        int i = a;
        while (!checkPrim(i)) {
            i++;
        }
        return i;
    }

    private boolean checkPrim(int a) {
        if(a%2==0 || a%3==0 || a%5==0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean put(int key, String value) {

        //If HashMap is full, return false
        if (elementCount() >= totalSize()) {
            return false;
        }

        int index = calcModuloIndex(key);
        int offset = 0;
        int offsetCount=1;

        do{
            if(getValue(index + offset) == null){
                setKeyAndValue(index+offset,key,value);
                elements++;
                return true;
            }else if(getKey(index + offset) == key){
                setKeyAndValue(index+offset, key, value);
               return true;
            }

            offset = getSecondFunction(index,offsetCount);
            offsetCount++;

        }while(index + offset != index);

        return false;
    }

    private int getSecondFunction(int index,int offsetCount) {
        return offsetCount*(1+(index%(totalSize()-2)));
    }

    private int calcModuloIndex(int key) {
        return key % totalSize();
    }

    @Override
    public String get(int key) {
       int index = calcModuloIndex(key);
       int offset=0;
       int offsetCount=1;

       while (getKey(index+offset)!=null){
           if(getKey(index+offset)==key){
               return getValue(index+offset);
           }
           offset=getSecondFunction(index,offsetCount);
           offsetCount++;
       }

        return null;
    }

    @Override
    public int elementCount() {
        return elements;
    }
}
