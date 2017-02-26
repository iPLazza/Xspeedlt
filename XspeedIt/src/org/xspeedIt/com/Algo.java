package org.xspeedIt.com;

import java.util.Comparator;
import java.util.Vector;
import java.util.Iterator;

public class Algo {
	public static final int MAX_BOX_SIZE = 10;
	public static final String value = "163841689525773";

	public static void normal() {
		String res = "";
		int somme = 0;
		String tmp = "";
		for (int i = 0; i < value.length(); i++) {
			tmp += value.charAt(i);
			somme += Integer.parseInt(value.charAt(i) + "");
			
			if ((i < value.length() - 1 && somme
					+ Integer.parseInt(value.charAt(i + 1) + "") > MAX_BOX_SIZE)
					|| i == value.length() - 1) {
				
				res += tmp + "/";
				tmp = "";
				somme = 0;
			}
		}
		res = res.substring(0, res.length() - 1);
		System.out.println("Articles : " + value);
		System.out.println("Robot normale : " + res);
	}

	public static void optimise() {
		String res = "";
		Vector<Integer> intValue = new Vector<>();
		
		Vector<Vector<Integer>> intResult = new Vector<>();

		//////////////////       TRI       ////////////////////////
		for (int i = 0; i < value.length(); i++) {
			int var1 = Character.getNumericValue(value.charAt(i));
			intValue.add(var1);
		}
		intValue.sort(Comparator.reverseOrder());
		// ////////////////////////////////////////////////////////

		Iterator<Integer> iter = intValue.iterator();
		int val = iter.next();

		while (iter.hasNext()) {
			boolean inserted = false;
			for (int i = 0; i < intResult.size(); i++) {
				Vector<Integer> vec = intResult.get(i);
				int count = 0;
				for (int j = 0; j < vec.size(); j++) {
					count += vec.get(j);
				}
				if (val + count <= MAX_BOX_SIZE) {
					intResult.get(i).add(val);
					inserted = true;
					iter.remove();
					val = iter.next();
				}
			}
			if (inserted == false) {
				Vector<Integer> vec = new Vector<>();
				vec.add(val);
				intResult.add(vec);
				inserted = true;
				iter.remove();
				val = iter.next();
			}
		}

		for (int i = 0; i < intResult.size(); i++) {
			for (int j = 0; j < intResult.get(i).size(); j++) {
				res += intResult.get(i).get(j);
			}
			res += "/";
		}
		System.out.println("Robot optimisé : " + res);
	}

	public static void main(String[] args) {
		normal();
		optimise();
	}

}
