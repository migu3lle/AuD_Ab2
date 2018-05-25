package ab2.impl.Gaggl_Gundacker_Kopali;

import ab2.AbstractHashMap;

/**
 * Author: Nils Kopali
 * Created: 05/25/18
 * HashMap mit Lineare Sondierungsstrategie
 */
public class HashMapLinear extends AbstractHashMap {

    public HashMapLinear(int minSize){
        initTable(minSize);
    }

    @Override
    public boolean put(int key, String value) {

       //If HashMap is full, return false
        if(elementCount()>=totalSize()){
            return false;
        }


        int idx = calcModuloIndex(key);
        int offset=0;
        while (getValue(idx+offset)!=null && getKey(idx+offset)!=key){
            offset-=1;
        }
        setKeyAndValue(idx+offset,key,value);
        return true;
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
        int count=0;
        for (int i = 0; i <totalSize() ; i++) {
            if(!isEmpty(i)){
                count++;
            }
        }
        return count;
    }
}
