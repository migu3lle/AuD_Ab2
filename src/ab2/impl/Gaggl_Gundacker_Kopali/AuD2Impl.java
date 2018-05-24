package ab2.impl.Gaggl_Gundacker_Kopali;

import ab2.AbstractHashMap;
import ab2.AuD2;

import java.util.Arrays;

public class AuD2Impl implements AuD2 {

	@Override
	public AbstractHashMap newHashMapLinear(int minSize) {
		// TODO Auto-generated method stub
		return new HashMapLinear(minSize);
	}

	@Override
	public AbstractHashMap newHashMapQuadratic(int minSize) {
		return new HashMapQuadraticImpl(minSize);
	}

	@Override
	public AbstractHashMap newHashMapDouble(int minSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMedian(int[] data) {
		// TODO Auto-generated method stub
        MedianOfMedians x = new MedianOfMedians();
       return x.findMedian(data,data.length/2+1,0,data.length-1);
	}
}
