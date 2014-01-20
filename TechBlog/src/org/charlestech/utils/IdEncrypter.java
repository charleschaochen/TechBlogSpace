package org.charlestech.utils;

import java.util.Random;
import java.lang.NumberFormatException;

/**
 * Numeric ID Encrypter
 * 
 * @author Charles Chen
 * 
 */
public class IdEncrypter {
	private static char[] SYMB = new char[] { 'Q', 'A', 'Z', 'W', 'S', 'X',
			'E', 'D', 'C', 'R' };

	/**
	 * Encrypt numeric ID
	 * 
	 * @return
	 */
	public static String encrypt(int id) {
		if (id < 0)
			return null;
		char[] shuffled = shuffleChars();
		String idStr = String.valueOf(id);
		String translatedId = "";
		for (int i = 0; i < idStr.length(); i++) {
			int bit = Integer.parseInt(idStr.substring(i, i + 1));
			translatedId += search(shuffled, numToChar(bit));
		}
		String decryptedStr = String.valueOf(shuffled) + translatedId;
		return decryptedStr;
	}

	/**
	 * Decrypt numeric ID
	 * 
	 * @return
	 */
	public static String decrypt(String encryptedStr) {
		if (encryptedStr.length() <= 10) {
			return null;
		}
		String idStr = "";
		try {
			char[] symbols = encryptedStr.substring(0, 10).toCharArray();
			String translatedId = encryptedStr.substring(10);
			int[] ids = new int[translatedId.length()];
			for (int i = 0; i < translatedId.length(); i++) {
				ids[i] = Integer.parseInt(translatedId.substring(i, i + 1));
			}
			for (int id : ids) {
				char c = symbols[id];
				int num = charToNum(c);
				if (num < 0)
					return null;
				idStr += num;
			}
		} catch (Exception e) {
			return null;
		}
		return idStr;
	}

	/**
	 * Shuffle the char array
	 * 
	 * @return
	 */
	private static char[] shuffleChars() {
		char[] shuffled = new char[10];
		// Initialise char array
		int i;
		for (i = 0; i < shuffled.length; i++) {
			shuffled[i] = SYMB[i];
		}
		Random random = new Random();
		for (i = 0; i < shuffled.length; i++) {
			int index = random.nextInt(shuffled.length);
			char tmp = shuffled[i];
			shuffled[i] = shuffled[index];
			shuffled[index] = tmp;
		}
		return shuffled;
	}

	/**
	 * Translate number to char
	 * 
	 * @param num
	 * @return
	 */
	private static char numToChar(int num) {
		return SYMB[num];
	}

	/**
	 * Translate char to number
	 * 
	 * @param c
	 * @return
	 */
	private static int charToNum(char c) {
		for (int i = 0; i < SYMB.length; i++) {
			if (SYMB[i] == c)
				return i;
		}
		return -1;
	}

	/**
	 * Search char in char array and return index
	 * 
	 * @param chars
	 * @param c
	 * @return
	 */
	private static int search(char[] chars, char c) {
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == c)
				return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		int id = 5;
		String encrypted = encrypt(id);
		String idStr = decrypt(encrypted);
		System.out.println(encrypted);
		System.out.println(idStr);
	}
}
