package ab2.impl.Gaggl_Gundacker_Kopali;

import ab2.AbstractHashMap;

/**
 * Author: Nils Kopali
 * Created: 05/25/18
 * HashMap mit Lineare Sondierungsstrategie
 */
public class HashMapLinear extends AbstractHashMap {

    private int elements;

    public HashMapLinear(int minSize){
        initTable(minSize);
    }

    @Override
    public boolean put(int key, String value) {

       //If HashMap is full, return false
        if(elementCount()>=totalSize()){
            return false;
        }


        int idx = calcModuloIndex(key);         //erste "korrekte" position wird berechnet mit dem übergebenen key
        int offset=0;                           //am anfang ist der offset = 0
        do {
            if(getValue(idx+offset) == null){
                setKeyAndValue(idx + offset, key, value);
                elements++;
                return true;
            }
            if(getKey(idx + offset) == key){
                setKeyAndValue(idx + offset, key, value);
                return true;
            }
            offset--;

        }while(idx+offset != idx);
        return false;
        
    }

    private int calcModuloIndex(int a){
        return a%totalSize();
    }

    @Override
    public String get(int key) {
        int idx=calcModuloIndex(key);
        int offset=0;
        while(getKey(idx+offset)!=null){
            if(getKey(idx+offset)==key){
                return getValue(idx+offset);
            }
            offset-=1;
        }

        return null;
    }

    @Override
    public int elementCount() {
        return elements;
    }
}
