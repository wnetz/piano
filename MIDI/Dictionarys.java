package MIDI;

import java.util.*;

public class Dictionarys {
	private Dictionary<String, Integer> clef;
	private Dictionary<String, Integer> expression;
	private Dictionary<Integer, String> keyList;
	private Dictionary<Integer, String> keyLong;
	private Dictionary<String, Integer> keyShort;
	private Dictionary<String, Integer> notes;
	private Dictionary<String, Integer> volumeInt;
	private Dictionary<Integer, Integer> volumeLoop;
	private Dictionary<Integer, String> volumeStr;

	public Dictionarys() {
		clef = new Hashtable<String, Integer>();
		expression = new Hashtable<String, Integer>();
		keyList = new Hashtable<Integer, String>();
		keyLong = new Hashtable<Integer, String>();
		keyShort = new Hashtable<String, Integer>();
		notes = new Hashtable<String, Integer>();
		volumeInt = new Hashtable<String, Integer>();
		volumeStr = new Hashtable<Integer, String>();
		volumeLoop = new Hashtable<Integer, Integer>();


		clef.put("t", 1);
		clef.put("a", 2);
		clef.put("b", 3);

		expression.put("Lacrimoso", 1);

		keyList.put(1, ""); // C
		keyList.put(2, "fB"); // F
		keyList.put(3, "fBE"); // Bd
		keyList.put(4, "fABE"); // Ed
		keyList.put(5, "fABDE"); // Ad
		keyList.put(6, "fABDEG"); // Dd/C#
		keyList.put(7, "fABCDEG"); // Gd/F#
		keyList.put(8, "fABCDEFG"); // Cd/B
		keyList.put(9, "sF"); // G
		keyList.put(10, "sCF");// D
		keyList.put(11, "sCFG");// A
		keyList.put(12, "sCDFG");// E

		keyLong.put(1, "nnnnnnn"); // C
		keyLong.put(2, "ndnnnnn"); // F
		keyLong.put(3, "ndnndnn"); // Bd
		keyLong.put(4, "ddnndnn"); // Ed
		keyLong.put(5, "ddnddnn"); // Ad
		keyLong.put(6, "ddnddnd"); // Dd/C#
		keyLong.put(7, "dddddnd"); // Gd/F#
		keyLong.put(8, "ddddddd"); // Cd/B
		keyLong.put(9, "nnnnn#n"); // G
		keyLong.put(10, "nn#nn#n");// D
		keyLong.put(11, "nn#nn##");// A
		keyLong.put(12, "nn##n##");// E

		keyShort.put("nnnnnnn", 1); // C
		keyShort.put("ndnnnnn", 2); // F
		keyShort.put("ndnndnn", 3); // Bd
		keyShort.put("ddnndnn", 4); // Ed
		keyShort.put("ddnddnn", 5); // Ad
		keyShort.put("ddnddnd", 6); // Dd/C#
		keyShort.put("dddddnd", 7); // Gd/F#
		keyShort.put("ddddddd", 8); // Cd/B
		keyShort.put("nnnnn#n", 9); // G
		keyShort.put("nn#nn#n", 10);// D
		keyShort.put("nn#nn##", 11);// A
		keyShort.put("nn##n##", 12);// E

		int start = 24;
		notes.put("A0",21);
		notes.put("A0s",22);
		notes.put("B0",23);
		for (int i = 1; i < 8; i++) {			
			notes.put("C" + i, start);
			notes.put("C" + i + "s", start + 1);
			notes.put("D" + i + "d", start + 1);
			notes.put("D" + i, start + 2);
			notes.put("D" + i + "s", start + 3);
			notes.put("E" + i + "d", start + 3);
			notes.put("E" + i, start + 4);
			notes.put("E" + i + "s", start + 5);
			notes.put("F" + i + "d", start + 4);
			notes.put("F" + i, start + 5);
			notes.put("F" + i + "s", start + 6);
			notes.put("G" + i + "d", start + 6);
			notes.put("G" + i, start + 7);
			notes.put("G" + i + "s", start + 8);
			notes.put("A" + (i + 1) + "d", start + 8);
			notes.put("A" + i, start + 9);
			notes.put("A" + i + "s", start + 10);
			notes.put("B" + i + "d", start + 10);
			notes.put("B" + i, start + 11);
			notes.put("B" + i + "s", start + 3);
			notes.put("C" + i + "d", start + 2);
			start += 12;
		}
		notes.put("A8", 105);
		notes.put("A8s", 106);
		notes.put("B8d", 106);
		notes.put("B8", 107);
		notes.put("B8s", 108);
		notes.put("C8d", 107);
		notes.put("C8", 108);
		notes.put("R0", 0);

		volumeInt.put("pppp", 8);
		volumeInt.put("ppp ", 20);
		volumeInt.put("pp  ", 31);
		volumeInt.put("p   ", 42);
		volumeInt.put("mp  ", 53);
		volumeInt.put("mf  ", 64);
		volumeInt.put("f   ", 80);
		volumeInt.put("ff  ", 96);
		volumeInt.put("fff ", 112);
		volumeInt.put("ffff", 127);

		volumeLoop.put(1, 8);
		volumeLoop.put(2, 20);
		volumeLoop.put(3, 31);
		volumeLoop.put(4, 42);
		volumeLoop.put(5, 53);
		volumeLoop.put(6, 64);
		volumeLoop.put(7, 80);
		volumeLoop.put(8, 96);
		volumeLoop.put(9, 112);
		volumeLoop.put(10, 127);

		volumeStr.put(8, "pppp");
		volumeStr.put(20, "ppp ");
		volumeStr.put(31, "pp  ");
		volumeStr.put(42, "p   ");
		volumeStr.put(53, "mp  ");
		volumeStr.put(64, "mf  ");
		volumeStr.put(80, "f   ");
		volumeStr.put(96, "ff  ");
		volumeStr.put(112, "fff ");
		volumeStr.put(127, "ffff");

	}

	public int getClef(String key) {
		return clef.get(key);
	}
	public int getExpression(String key) {
		return expression.get(key);
	}
	public String getKey(int key) {
		return keyLong.get(key);
	}
	public String getKeyList(String key) {
		return keyList.get(keyShort.get(key));
	}
	public int getKey(String key) {
		return keyShort.get(key);
	}
	public int getNote(String key) {
		return notes.get(key);
	}
	public String getVolume(int velocity) {
		int diff = 128;
		String closest = "";
		for (int i = 1; i < 11; i++) {
			if (Math.abs(velocity - volumeLoop.get(i)) < diff) {
				diff = Math.abs(velocity - volumeLoop.get(i));
				closest = volumeStr.get(volumeLoop.get(i));
			}
		}
		return closest;
	}
	public int getVolume(String key) {
		return volumeInt.get(key);
	}

}
