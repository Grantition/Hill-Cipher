import java.util.Scanner;
import java.util.TreeMap;

//Grant
public class HillCipher {
	
	//alphabet is mapped to a modulus 26
	static TreeMap<Character, Integer> mod26 = new TreeMap<Character, Integer>();
	
	// 3 x 3 matrix
	static int[][] twoDArray = {{6,24,1},
						{13,16,10},
						{20,17,15}};

	//sets the alphabet to its integer modulus values
	public static void alphabetFill() {

		Character c;
		Integer i = 0;

		for (c = 'a'; c <= 'z'; c++) {
			mod26.put(c, i);
			i++;
		}

	}
	
	public static String toStringNew(Integer[] v) {
		return "[ " + v[0] + " " + v[1] + " " + v[2] + " ]";
		
	}
	
	public static Integer[] matrixMultiplication(Integer[] a) {
		Integer[] vectoredArray = { 0, 0, 0 };

		for (int i = 0; i < twoDArray.length; i++) {
			for (int j = 0; j < twoDArray[i].length; j++) {
				if (i == 0)
					vectoredArray[0] = vectoredArray[0] + twoDArray[i][j] * a[j];
				if (i == 1)
					vectoredArray[1] = vectoredArray[1] + twoDArray[i][j] * a[j];
				if (i == 2)
					vectoredArray[2] = vectoredArray[2] + twoDArray[i][j] * a[j];
			}
		}
		return vectoredArray;

	}

	public static Integer[] vectorEncryption(Character[] a) {
		Integer[] vectoredArray = new Integer[3];
		int count = 0;
		for (Character s : a) {
			vectoredArray[count] = (mod26.get(s));
			count++;
		}
		return vectoredArray;
	}
	
	public static Integer[] modularForm(Integer[] a) {
		Integer[] shortForm = a;
		shortForm[0] = a[0] - ((a[0] / 26) * 26);
		shortForm[1] = a[1] - ((a[1] / 26) * 26);
		shortForm[2] = a[2] - ((a[2] / 26) * 26);
		return shortForm;
	}
	
	// modulo 26

	public static void main(String[] args) {
		alphabetFill();
		System.out.println("Plese enter your three letter word in all lowercase letters: ");
		// user inputs
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		System.out.println("You have entered '" + input + "'");
		in.close();

		Character[] unicodeToVector = input.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
		Integer[] newVector = vectorEncryption(unicodeToVector);
		System.out.println("Your message in the vector form: " + toStringNew(newVector));
		System.out.println("Your message is being encrypted...");
		Integer[] saveThis = matrixMultiplication(newVector);
		System.out.println("With the applied key: " + toStringNew(matrixMultiplication(newVector)));
		System.out.println("Your message in (mod 26) form " + toStringNew(modularForm(saveThis)));

	}

}
