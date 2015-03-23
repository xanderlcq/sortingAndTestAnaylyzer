import java.util.*;
import acm.program.*;
public class sortingMethods extends ConsoleProgram {

	public void run() {
		setSize(500,500);
		println("Insertion sort ---- 1");
		println("Selection sort ---- 2");
		println("Merge sort ---- 3");
		int sortMethod = readInt("What kind of sort do you want to do: ");
		int elements = readInt("How many elements do you want in the array: ");
		int range = readInt("What do you want the range to be? (from 0~): ");
		int[] test = arrayGen(elements, range);
		println("Sorting " + elements + " elements");
		//------actual program
		long initial;
		long elapsed;
		if (sortMethod == 3) {
			int[] mirror = new int[test.length];// empty same length mirror array
			initial = System.currentTimeMillis();
			mergeSort(test, mirror, 0, test.length);
			elapsed = System.currentTimeMillis() - initial;
		}else if(sortMethod ==2){
			initial = System.currentTimeMillis();
			selectionSort(test);
			elapsed = System.currentTimeMillis() - initial;
		}else{
			initial = System.currentTimeMillis();
			insertionSort(test);
			elapsed = System.currentTimeMillis() - initial;
		}
		//print results
		print("Merge Final: ");
		println(elapsed + "ms");
		println("Is it sorted: " + isSortedHighLeft(test));
	}
	
	
	private boolean isSortedHighLeft(int[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			if (list[i] > list[i + 1]) {
				return false;
			}
		}
		return true;
	}
	private void mergeSort(int[] array, int[] mirror, int lowerIndex, int end) {
		if (end-lowerIndex == 1) {
			return;
		}
		int middle = (lowerIndex + end) / 2;
		mergeSort(array, mirror, lowerIndex, middle);
		mergeSort(array, mirror, middle, end);
		merge(array, mirror, lowerIndex, middle, end);
	}

	private void merge(int[] a, int[] mirror, int lowerIndex, int higherIndex,
			int end) {
		int initialLowerIndex = lowerIndex;// because the lowerIndex is
											// constantly increasing
		int midIndex = higherIndex;// because the higherIndex is constantly
									// increasing
		int mirrorIndex = 0;// where we start the mirror doesn't matter
		while (lowerIndex < midIndex && higherIndex < end) {
			if (a[lowerIndex] < a[higherIndex]) {
				mirror[mirrorIndex++] = a[lowerIndex++];
			} else {
				mirror[mirrorIndex++] = a[higherIndex++];
			}
		}
		// check lower remaining
		while (lowerIndex < midIndex) {
			mirror[mirrorIndex++] = a[lowerIndex++];
		}
		// check upper remaining
		while (higherIndex < end) {
			mirror[mirrorIndex++] = a[higherIndex++];
		}
		// transfer the data in mirror back to original array a
		for (int k = 0; k < end - initialLowerIndex; k++) {
			a[initialLowerIndex + k] = mirror[k];
		}

	}

	public void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			// find smallest
			int smallest = i;
			for (int k = i; k < array.length; k++) {
				if (array[k] < array[smallest]) {
					smallest = k;
				}
			}
			// replace the first one of unsorted pile
			int temp = array[i];
			array[i] = array[smallest];
			array[smallest] = temp;
		}
	}

	public void insertionSort(int[] array) {
		int i;
		int current;
		int k;
		// created variables above so we can use it throughout the loop
		for (i = 1; i < array.length; i++) {
			current = array[i];
			for (k = i - 1; (k >= 0) && (current < array[k]); k--) {
				array[k + 1] = array[k];
			}
			array[k + 1] = current;// k+1 because k-- is executed after for loop

		}
	}

	private int[] arrayGen(int length, int range) {
		int[] array = new int[length];
		for (int i = 0; i < length; i++) {
			array[i] = (int) (Math.random() * range);
		}
		return array;
	}

}
