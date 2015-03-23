import java.io.*;
import java.util.*;
import acm.program.*;
import acm.io.*;
import acm.util.ErrorException;
import acmx.export.java.io.FileReader;

public class TextAnalyzerX extends ConsoleProgram {
	String[][] alphabeticalData;
	String[][] mostFrequentData;
	double totalNumberOfWords;

	public void run() {
		setSize(600, 800);
		selectionUI();
		searchUI();

	}

	private void searchUI() {
		while (true) {
			println();
			println("1------Search data for a key word");
			println("2------find most frequently used word");
			println("3------print all words alphabetically");
			println("4------print all words base on frequency");
			println("5------number of different words writter used");
			println("6------exit program");

			int userInput = readInt("Enter an index: ");
			while (userInput > 6 || userInput < 1) {
				userInput = readInt("Enter a valid input: ");
			}
			if (userInput == 1) {
				println();
				IODialog search = new IODialog();
				
				String key = search.readLine("Enter a key word that you want to search: ");
				println();
				binaryKeyWordSearch(key);
				println();
			} else if (userInput == 2) {
				int k = 0;
				println();
				print("\"" + mostFrequentData[k][0] + "\"" + " occurs ");
				print(mostFrequentData[k][1] + " times, and the frequence is ");
				println(mostFrequentData[k][2]);
				println();
			} else if (userInput == 3) {
				println();
				for (int k = 0; k < alphabeticalData.length; k++) {
					print("\"" + alphabeticalData[k][0] + "\"" + " occurs ");
					print(alphabeticalData[k][1]
							+ " times, and the frequence is ");
					println(alphabeticalData[k][2]);
				}
				println();
			} else if (userInput == 4) {
				println();
				for (int k = 0; k < mostFrequentData.length; k++) {
					print("\"" + mostFrequentData[k][0] + "\"" + " occurs ");
					print(mostFrequentData[k][1]
							+ " times, and the frequence is ");
					println(mostFrequentData[k][2]);
				}
				println();

			} else if (userInput == 5) {
				println();
				println(alphabeticalData.length);
				println();
			} else if (userInput == 6) {
				IODialog confirm = new IODialog();
				if (confirm.readBoolean("Are you sure you want to exit?")) {
					IODialog seeYou = new IODialog();
					seeYou.println("Thank you for using this app!-----Xander Li");
					System.exit(0);
				}
			}
		}
	}

	private void selectionUI() {
		println("Choose a text to search");
		println("1------Romeo and Juliet----William Shakespeare");
		println("2------Ideal Commonwealth----Francis Bacon");
		int userSelection = readInt("Please enter the index: ");
		while (userSelection != 1 && userSelection != 2) {
			userSelection = readInt("Please enter a correct index: ");
		}
		println();
		println("Wait...Collection Data");
		println();
		if (userSelection == 1) {
			fileProcessor("romeo");
		} else {
			fileProcessor("bacon");
		}
		mergeSortMaster();
	}

	private void fileProcessor(String fileName) {
		File f = new File(fileName);
		ArrayList<String> test = tokenizeText(f);
		countWords(test);
	}

	private void mergeSortMaster() {
		String[][] temp = new String[alphabeticalData.length][3];
		mergeSort(alphabeticalData, temp, 0, alphabeticalData.length, 0);
		String[][] temp1 = new String[mostFrequentData.length][3];
		mergeSort1(mostFrequentData, temp1, 0, mostFrequentData.length, 1);
	}

	private void binaryKeyWordSearch(String key) {
		int lowerBound = 0;
		int higherBound = alphabeticalData.length - 1;
		while (lowerBound < higherBound - 1) {
			int mid = (lowerBound + higherBound) / 2;
			if (key.equals(alphabeticalData[mid][0])) {
				print("\"" + alphabeticalData[mid][0] + "\"" + " occurs ");
				print(alphabeticalData[mid][1]
						+ " times, and the frequence is ");
				println(alphabeticalData[mid][2]);
				return;
			} else if (key.compareTo(alphabeticalData[mid][0]) < 0) {
				// move down
				higherBound = mid;
			} else {
				// move up
				lowerBound = mid;
			}

		}
		println("not found");
		return;

	}

	private void countWords(ArrayList<String> masterList) {
		ArrayList<String> count = new ArrayList<String>();
		ArrayList<String> word = new ArrayList<String>();
		ArrayList<String> freq = new ArrayList<String>();
		totalNumberOfWords = masterList.size();
		while (true) {
			if (masterList.size() == 0)
				break;
			String key = masterList.get(0);
			double tempCount = 0;
			for (int i = 0; i < masterList.size(); i++) {
				if (key.equals(masterList.get(i))) {
					tempCount++;
					masterList.remove(i);
					i--;
				}
			}
			String temp = "" + tempCount;
			String frequence = "" + tempCount / totalNumberOfWords;
			count.add(temp);
			word.add(key);
			freq.add(frequence);
		}
		alphabeticalData = new String[count.size()][3];
		mostFrequentData = new String[count.size()][3];
		for (int k = 0; k < count.size(); k++) {
			alphabeticalData[k][0] = word.get(k);
			alphabeticalData[k][1] = count.get(k);
			alphabeticalData[k][2] = freq.get(k);

			mostFrequentData[k][0] = word.get(k);
			mostFrequentData[k][1] = count.get(k);
			mostFrequentData[k][2] = freq.get(k);
		}
	}

	private ArrayList<String> tokenizeText(File f) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			BufferedReader hangmanWords = new BufferedReader(new FileReader(f));
			while (true) {
				String line = hangmanWords.readLine();// read line by line
				if (line == null)
					break;// no more line
				StringTokenizer st = new StringTokenizer(line,
						" ,.?;:()*&^%$#@!-_[]");
				while (st.hasMoreTokens()) {
					list.add((st.nextToken()).toLowerCase());// put the word in
																// array list
				}
			}
			hangmanWords.close();// closes the stream

		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		return list;
	}

	private void mergeSort(String[][] array, String[][] temp, int lowerIndex,
			int end, int base) {
		if (end - lowerIndex == 1) {
			return;
		}
		int middle = (lowerIndex + end) / 2;
		mergeSort(array, temp, lowerIndex, middle, base);
		mergeSort(array, temp, middle, end, base);
		merge(array, temp, lowerIndex, middle, end, base);
	}

	private void merge(String[][] a, String[][] temp, int lowerIndex,
			int higherIndex, int end, int base) {
		int initialLowerIndex = lowerIndex;// because the lowerIndex is
											// constantly increasing
		int midIndex = higherIndex;// because the higherIndex is constantly
									// increasing
		int tempIndex = 0;// where we start the temp doesn't matter
		while (lowerIndex < midIndex && higherIndex < end) {
			if (a[lowerIndex][base].compareTo(a[higherIndex][base]) <= 0) {
				temp[tempIndex][0] = a[lowerIndex][0];
				temp[tempIndex][2] = a[lowerIndex][2];
				temp[tempIndex++][1] = a[lowerIndex++][1];
			} else {
				temp[tempIndex][0] = a[higherIndex][0];
				temp[tempIndex][2] = a[higherIndex][2];
				temp[tempIndex++][1] = a[higherIndex++][1];
			}
		}
		// check lower remaining
		while (lowerIndex < midIndex) {
			temp[tempIndex][0] = a[lowerIndex][0];
			temp[tempIndex][2] = a[lowerIndex][2];
			temp[tempIndex++][1] = a[lowerIndex++][1];
		}
		// check upper remaining
		while (higherIndex < end) {
			temp[tempIndex][0] = a[higherIndex][0];
			temp[tempIndex][2] = a[higherIndex][2];
			temp[tempIndex++][1] = a[higherIndex++][1];
		}
		// transfer the data in temp back to original array a
		for (int k = 0; k < end - initialLowerIndex; k++) {
			a[initialLowerIndex + k][0] = temp[k][0];
			a[initialLowerIndex + k][2] = temp[k][2];
			a[initialLowerIndex + k][1] = temp[k][1];
		}

	}

	private void mergeSort1(String[][] array1, String[][] temp1,
			int lowerIndex, int end, int base) {
		if (end - lowerIndex == 1) {
			return;
		}
		int middle = (lowerIndex + end) / 2;
		mergeSort1(array1, temp1, lowerIndex, middle, base);
		mergeSort1(array1, temp1, middle, end, base);
		merge1(array1, temp1, lowerIndex, middle, end, base);
	}

	private void merge1(String[][] a1, String[][] temp1, int lowerIndex,
			int higherIndex, int end, int base) {
		int initialLowerIndex = lowerIndex;// because the lowerIndex is
											// constantly increasing
		int midIndex = higherIndex;// because the higherIndex is constantly
									// increasing
		int tempIndex = 0;// where we start the temp doesn't matter
		while (lowerIndex < midIndex && higherIndex < end) {
			double low = Double.parseDouble(a1[lowerIndex][base]);
			double high = Double.parseDouble(a1[higherIndex][base]);
			if (low >= high) {
				temp1[tempIndex][0] = a1[lowerIndex][0];
				temp1[tempIndex][2] = a1[lowerIndex][2];
				temp1[tempIndex++][1] = a1[lowerIndex++][1];
			} else {
				temp1[tempIndex][0] = a1[higherIndex][0];
				temp1[tempIndex][2] = a1[higherIndex][2];
				temp1[tempIndex++][1] = a1[higherIndex++][1];
			}
		}
		// check lower remaining
		while (lowerIndex < midIndex) {
			temp1[tempIndex][0] = a1[lowerIndex][0];
			temp1[tempIndex][2] = a1[lowerIndex][2];
			temp1[tempIndex++][1] = a1[lowerIndex++][1];
		}
		// check upper remaining
		while (higherIndex < end) {
			temp1[tempIndex][0] = a1[higherIndex][0];
			temp1[tempIndex][2] = a1[higherIndex][2];
			temp1[tempIndex++][1] = a1[higherIndex++][1];
		}
		// transfer the data in temp back to original array a
		for (int k = 0; k < end - initialLowerIndex; k++) {
			a1[initialLowerIndex + k][0] = temp1[k][0];
			a1[initialLowerIndex + k][2] = temp1[k][2];
			a1[initialLowerIndex + k][1] = temp1[k][1];
		}

	}
}
