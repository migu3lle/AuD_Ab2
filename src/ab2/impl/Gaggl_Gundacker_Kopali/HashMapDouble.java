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
        int prim = getPrim(minSize);            //bei der initialisierung der Hashmap muss überprüft werden ob die größe des tables eine primzahl ist
        initTable(prim);
    }
    /*
    * Hilfsmethode, die die nächst kleinste primzahl von der eingegeben findet, wenn diese zahl bereits eine primzahl ist wird sie nicht verändert
    */
    private int getPrim(int a) {
        int i = a;
        while (!checkPrim(i)) {
            i++;
        }
        return i;
    }

    /*
    * Hilfsmethode, die überprüft ob eine Zahl eine Primzahl ist oder nicht
    */
    private boolean checkPrim(int a) {
        if(a%2==0 || a%3==0 || a%5==0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean put(int key, String value) {


        if (elementCount() >= totalSize()) {                    //wenn die Hashmap voll ist kann false zurückgegeben werden
            return false;
        }

        int index = calcModuloIndex(key);                       //berechne den Startindex
        int offset = 0;                                         //der Startoffset = 0
        int offsetCount=1;                                      //offsetCount steht für die anzahl der versuchten stellen (steht für j bei der Doppelhashing sondierung: j*(1+(h'(k)))

        do{
            if(getValue(index + offset) == null){           //wenn der wert an der stelle index+offset = null ist dann setze den wert an die stelle index+offset
                setKeyAndValue(index+offset,key,value);
                elements++;                                     //inkrementiere den elements zähler
                return true;
            }else if(getKey(index + offset) == key){        //wenn der wert bereits an der stelle vorkommt dann ist der Wert doppelt vorhanden
                setKeyAndValue(index+offset, key, value);
               return true;
            }

            offset = getSecondFunction(index,offsetCount);     //berechne den wert der zweiten hash-funktion
            offsetCount++;                                     //erhöhe die anzahl der versuchten stellen

        }while(index + offset != index);                       //wiederhole diese schleife bis der berechnete index gleich dem startindex ist, sollte nicht bis dahin kommen, da jede freie stelle gefunden wird

        return false;
    }
    /*
    * Berechnet den Wert der zweiten Hashfunktion: j*(1 + k MOD(m − 2))*/
    private int getSecondFunction(int index,int offsetCount) {
        return offsetCount*(1+(index%(totalSize()-2)));
    }


    /*
    * Methode berechnet den "ersten" index an welchen der Wert gespeichert werden soll, (k MOD m)*/
    private int calcModuloIndex(int key) {
        return key % totalSize();
    }

    @Override
    public String get(int key) {
       int index = calcModuloIndex(key);                //startindex wird berechnet
       int offset=0;                                    //der offset ist am anfang = 0
       int offsetCount=1;                               //offsetCount steht für die anzahl der versuchten stellen

       while (getKey(index+offset)!=null){          //wiederhole diese schleife bis der key an der stelle index+offset = null ist, dann wurde der wert nicht gefunden
           if(getKey(index+offset)==key){           //wenn der key an der stelle index+offset gleich dem zu suchenden key ist dann gib den wert an der stelle zurück
               return getValue(index+offset);
           }
           offset=getSecondFunction(index,offsetCount); //offset wird nach jedem schleifendurchlauf neu berechnet
           offsetCount++;                               //anzahl der versuchten stellen wird erhöht, wichtig um im nächsten schleifendurchlauf einen anderen wert von der zweiten hashfunktion zu bekommen
       }

        return null;
    }

    @Override
    public int elementCount() {
        return elements;
    }
}
