import java.util.Arrays;

import acm.program.ConsoleProgram;

//By Gordon Xiang

public class GordoMerge extends ConsoleProgram {
	public void run() {
		int[] test = arrayGen(100000,100);
		for (int i = 0; i < test.length; i++) {
		//	print(test[i] + " "); // prints unsorted list
		}
		println();
		long initial = System.currentTimeMillis();
		int[] result = mergeSort(test, 0, test.length);
		long elapsed = System.currentTimeMillis() - initial;
		for (int i = 0; i < test.length; i++) {
			//print(result[i] + " ");
		}
		println(elapsed);
	}

	int[] mergeSort(int[] list, int start, int end) {
		if (end - start == 1) {
			return list;// trivial case
		} else {
			// int middle = (end-start)/2 + start;
			int middle = (end + start) / 2;
			// System.out.println(start + " " + middle + " " + end);
			// int middle = end/2;

			mergeSort(list, start, middle); // fix rightside and leftside
			// System.out.println("MergeSort: of " + Arrays.toString(list) +
			// " \nof indice (" + start + ", " + end/2 + ") ");

			mergeSort(list, middle, end);
			// System.out.println("MergeSort: of " + Arrays.toString(list) +
			// "\nof indice (" + (end/2+1) + ", " + end + ")");

			return(merge(list, start, middle, end));
		}
	}


	int[] merge(int[] list, int start, int mid, int end) {
		int initial = start;
		int length = end-start;
		int lowerIndex = start;
		int upperIndex = mid;
		int tempListIndex = initial;
		int[] tempList = new int[list.length];
		while (lowerIndex < mid && upperIndex < end) { // check old
																	// code for
																	// the
		// less than or equals
			if (list[lowerIndex] < list[upperIndex]) {
				tempList[tempListIndex] = list[lowerIndex];
				lowerIndex++;
			} else {
				tempList[tempListIndex] = list[upperIndex];
				upperIndex++;
			}
			tempListIndex++;
		}
		while (lowerIndex < mid) {
			tempList[tempListIndex] = list[lowerIndex];
			lowerIndex++;
			tempListIndex++;
		}
		while (upperIndex < end) {
			tempList[tempListIndex] = list[upperIndex];
			upperIndex++;
			tempListIndex++;
		}
		//int k = 0;
		for(int i = 0;i<length;i++){
			list[i+initial] = tempList[i+initial];
			//k++;
		}
		return list;
		//return (tempList);
	}

	// method tested and works
	boolean isSorted(int[] list, int start, int end) {
		if (start == end - 1)
			return true; // single digits are already sorted
		for (int i = start; i < end; i++) { // loop ends at index end-1
			if (list[i] > list[i + 1])
				return false; // if current index is larger than the next value
		}
		return true;
	}
	private int[] arrayGen(int length, int range) {
		int[] array = new int[length];
		for (int i = 0; i < length; i++) {
			array[i] = (int) (Math.random() * range);
		}
		return array;
	}
}

// merge(ms(leftside),ms(rightside)
// think of it as mergeSort(list, start1, end1, list, start2, end2)