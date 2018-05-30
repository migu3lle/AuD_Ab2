package ab2.impl.Gaggl_Gundacker_Kopali;

import ab2.AbstractHashMap;
import ab2.AuD2;

public class AuD2Impl implements AuD2 {

	@Override
	public AbstractHashMap newHashMapLinear(int minSize) {
		return new HashMapLinear(minSize);
	}

	@Override
	public AbstractHashMap newHashMapQuadratic(int minSize) {
		return new HashMapQuadraticImpl(minSize);
	}

	@Override
	public AbstractHashMap newHashMapDouble(int minSize) {
		return new HashMapDouble(minSize);
	}

	@Override
	public int getMedian(int[] data) {
        MedianOfMedians x = new MedianOfMedians();
       return x.medianOfMedians(data,data.length/2+1,0,data.length-1);
	}
}
