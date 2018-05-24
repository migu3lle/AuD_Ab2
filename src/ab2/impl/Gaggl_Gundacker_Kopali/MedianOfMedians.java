package ab2.impl.Gaggl_Gundacker_Kopali;

public class MedianOfMedians {

    public static int findMedian(int arr[], int k, int lo, int hi)
    {

        if(lo == hi)
        {
            return arr[lo];
        }

        // sort the mth largest element in the given array
        int m = partition(arr,lo,hi);

        // Adjust position relative to the current subarray being processed
        int length = m - lo + 1;

        // If mth element is the median, return it
        if(length == k)
        {
            return arr[m];
        }

        // If mth element is greater than median, search in the left subarray
        if(length > k)
        {
            return findMedian(arr,k,lo,m-1);
        }
        // otherwise search in the right subArray
        else
        {
            return findMedian(arr,k-length,m+1,hi);
        }

    }


    private static int partition(int arr[],int lo, int hi)
    {
        // Get pivotvalue by finding median of medians
        int pivotValue = getPivot(arr, lo, hi);

        // Find the sorted position for pivotVale and return it's index
        while(lo < hi)
        {
            while(arr[lo] < pivotValue)
            {
                lo ++;
            }

            while(arr[hi] > pivotValue)
            {
                hi--;
            }

            if(arr[lo] == arr[hi])
            {
                lo ++;
            }
            else if(lo < hi)
            {
                int temp = arr[lo];
                arr[lo] = arr[hi];
                arr[hi] = temp;
            }

        }
        return hi;
    }

    // Find pivot value, such the it is always 'closer' to the actual median
    private static int getPivot(int arr[], int lo, int hi)
    {
        // If number of elements in the array are small, return the actual median
        if(hi-lo+1 <= 9)
        {
            return arr[arr.length/2];
        }

        //Otherwise divide the array into subarray of 5 elements each, and recursively find the median

        // Array to hold '5 element' subArray, last subArray may have less than 5 elements
        int temp[] = null;

        // Array to hold the medians of all '5-element SubArrays'
        int medians[] = new int[(int)Math.ceil((double)(hi-lo+1)/5)];
        int medianIndex = 0;

        while(lo <= hi)
        {
            // get size of the next element, it can be less than 5
            temp = new int[Math.min(5,hi-lo+1)];

            // copy next 5 (or less) elements, in the subarray
            for(int j=0;j<temp.length && lo <= hi;j++)
            {
                temp[j] = arr[lo];
                lo++;
            }


            // find mean and store it in median Array
            medians[medianIndex] = temp[temp.length/2];
            medianIndex++;
        }

        // Call recursively to find median of medians
        return getPivot(medians,0,medians.length-1);
    }


}
