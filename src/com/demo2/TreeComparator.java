package com.demo2;

import java.util.Comparator;
import java.util.Map.Entry;

public class TreeComparator implements Comparator<Pair<Pair<Integer, Integer>, Integer>>{

	@Override
	public int compare(Pair<Pair<Integer, Integer>, Integer> left,Pair<Pair<Integer, Integer>, Integer> right) {
		if(left.first.first > right.first.first || (left.first.first == right.first.first && left.first.second < right.first.second)) {
			return -1;
		}
		return 1;
	}

}
