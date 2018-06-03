package ab2.impl.Gaggl_Gundacker_Kopali;
/**
 * Author: Nils Kopali
 * Created: 05/25/18
 * Median Of Medians
 */
public class MedianOfMedians {

    public static int medianOfMedians(int array[], int k, int lo, int hi)
    {

        if(lo == hi)
        {
            return array[lo];
        }

        //sort the x'th largest element in array
        int x = divider(array,lo,hi);

        //Modify the position corresponding to the array being processed
        int length = x - lo + 1;

        //If x'th element is the median
        if(length == k)
        {
            return array[x]; //return it
        }

        //If x'th element is greater than median
        if(length > k)
        {
            return medianOfMedians(array,k,lo,x-1); //search in the left subarray
        }
        else
        {
            return medianOfMedians(array,k-length,x+1,hi);//search in the right subarray
        }

    }


    private static int divider(int array[], int lo, int hi)
    {
        //Get pivotvalue
        int pivotValue = getPivotVal(array, lo, hi);

        //Find the sorted position for the Pivot Value and return it's index
        while(lo < hi)
        {
            while(array[lo] < pivotValue)
            {
                lo++;
            }

            while(array[hi] > pivotValue)
            {
                hi--;
            }

            if(array[lo] == array[hi])
            {
                lo++;
            }
            else if(lo < hi)
            {
                int temp = array[lo];
                array[lo] = array[hi];
                array[hi] = temp;
            }

        }
        return hi;
    }

    //Find pivot value which is "near" the actual median
    private static int getPivotVal(int array[], int lo, int hi)
    {
        //If number of elements in the array are small
        if(hi-lo+1 <= 9)
        {
            return array[array.length/2];//return the actual median
        }

        //Else divide the array in 5 blocks with 5 elements each

        int tmp[];//Array to hold the 5 elements

        //Array to hold the meadian of the 5 values subArrays
        int meadian[] = new int[(int)Math.ceil((double)(hi-lo+1)/5)];
        int medianIdx = 0;

        while(lo <= hi)
        {
            //Getting the size of the next element which can be less than 5
            tmp = new int[Math.min(5,hi-lo+1)];

            //copy the 5 elements or less in the subarray
            for(int j=0;j<tmp.length && lo <= hi;j++)
            {
                tmp[j] = array[lo];
                lo++;
            }

            //find median and save it in Array
            meadian[medianIdx] = tmp[tmp.length/2];
            medianIdx++;
        }

        //Recursively calling to find the median of meadian
        return getPivotVal(meadian,0,meadian.length-1);
    }


}
