import acm.program.*;

public class numberSort extends ConsoleProgram {
	public void run() {
		int[] test = new int[100000];
		for(int i = 0; i < test.length; i++) {
		    test[i] = (int)(Math.random()*1000);
		};
		println("Sorting....");
		long start = System.currentTimeMillis();
		int[] result = insertion(test);
		long elapsed = System.currentTimeMillis() - start;
		/*for (int i = 0; i < result.length; i++) {
			println(result[i]);
		}*/
		println(elapsed+"ms");
	}

	public int[] selection(int[] array) {
		for (int i = 0; i < array.length-1; i++) {
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
		return array;
	}
	

	
	public int[] insertion(int[] array) {
		int i;
		int current;
		int k;
		//created variables above so we can use it throughout the loop
		for (i = 1; i < array.length; i++) {
			current = array[i];
			for (k = i - 1; (k >= 0)&&(current < array[k]); k--) {
				array[k + 1] = array[k];
			}
			array[k+1] = current;//k+1 because k-- is executed after for loop

		}
		return array;
	}
}
