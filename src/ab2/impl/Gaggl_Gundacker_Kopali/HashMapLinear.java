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

       //Wenn die Hashmap voll ist kann false zurückgegeben werden
        if(elementCount()>=totalSize()){
            return false;
        }


        int idx = calcModuloIndex(key);         //erste "korrekte" position wird berechnet mit dem übergebenen key
        int offset=0;                           //am anfang ist der offset = 0
        do {
            if(getValue(idx+offset) == null){                  //wenn der wert an der stelle idx+offset leer ist kann das element eingefügt werden
                setKeyAndValue(idx + offset, key, value);
                elements++;                                        //erhöhe die anzahl der elemente
                return true;
            }
            if(getKey(idx + offset) == key){                    //wenn der key an der stelle idx+offset = dem zu speichernden wert ist, dann existiert der wert bereits
                setKeyAndValue(idx + offset, key, value);
                return true;
            }
            offset--;                                               //offset wird nach jedem while Schleifendurchlauf dekrementiert (lineare Sondierung)

        }while(idx+offset != idx);                                  //führe die Schleife sooft aus, bis der Index wieder der Startindex ist, dazu sollte es im Normalfall nicht kommen,
        return false;                                               //bereits oben abgefragt wird ob noch platz ist, wenn platz vorhanden ist, so sollte dieser auch gefunden werden

    }
    /*
    * Methode berechnet den "ersten" index an welchen der Wert gespeichert werden soll, (k MOD m)*/
    private int calcModuloIndex(int a){
        return a%totalSize();
    }

    @Override
    public String get(int key) {
        int idx=calcModuloIndex(key);                               //Startindex wird berechnet
        int offset=0;                                               //der offset ist am anfang = 0
        while(getKey(idx+offset)!=null){                        //führe schleife aus bis der wert an der stelle idx+offset leer ist, dann wurde der wert nicht gefunden
            if(getKey(idx+offset)==key){                        //wenn der key an der stelle idx+offset gleich dem gesuchten key ist dann gib den wert an der stelle idx+offset aus
                return getValue(idx+offset);
            }
            offset-=1;                                              //nach jedem Schleifendurchlauf wird der Index verringert (lineare Sondierung)
        }

        return null;
    }

    @Override
    public int elementCount() {
        return elements;
    }
}
