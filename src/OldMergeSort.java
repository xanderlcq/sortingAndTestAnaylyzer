import java.util.Arrays;
import acm.program.*;
import acm.util.*;
//this merge sort literally split the array in recursion.
public class OldMergeSort extends ConsoleProgram {
	public void run() {
		int[] test = arrayGen(1000000,1000);
		println("Sorting 1,000,000 elements");
		long start = System.currentTimeMillis();
		mergeSort(test);
		long elapsed = System.currentTimeMillis() - start;
		//for (int i = 0; i < result.length; i++) {
			//print(result[i]);
			print(" ");
		//}
		println(elapsed+"ms");
	}

	private int[] mergeSort(int[] list) {
		if (list.length <= 1) {
			return list;
		}
		int[] firstHalf = Arrays.copyOfRange(list, 0, list.length / 2);
		int[] secondHalf = Arrays.copyOfRange(list, list.length / 2,
				list.length);
		mergeSort(firstHalf);
		mergeSort(secondHalf);
		merge(firstHalf, secondHalf, list);
		return list;
	}

	private void merge(int[] firstHalf, int[] secondHalf, int[] list) {
		int indexOfFirst = 0;
		int indexOfSecond = 0;
		int indexOfList = 0;
		// compare firstHalf and secondHalf one by one
		while (indexOfFirst < firstHalf.length
				&& indexOfSecond < secondHalf.length) {

			if (firstHalf[indexOfFirst] < secondHalf[indexOfSecond]) {
				list[indexOfList] = firstHalf[indexOfFirst];
				indexOfFirst++;
			} else {
				list[indexOfList] = secondHalf[indexOfSecond];
				indexOfSecond++;
			}
			indexOfList++;
		}
		//add remaining of the 1/2 arrays
		System.arraycopy(firstHalf, indexOfFirst, list, indexOfList, firstHalf.length - indexOfFirst);
		System.arraycopy(secondHalf, indexOfSecond, list, indexOfList, secondHalf.length - indexOfSecond);
	}
	
	private int[] arrayGen(int length,int range){
		int[] array = new int[length];
		for (int i = 0; i < length; i++) {
			array[i] = (int) (Math.random() * range);
		}
		return array;
	}
}
