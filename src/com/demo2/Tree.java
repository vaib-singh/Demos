package com.demo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

public class Tree {	
	
	
	int min, max, sum;
	List<Pair<Pair<Integer, Integer>, Integer>> v = new ArrayList<Pair<Pair<Integer,Integer>,Integer>>();
	
	boolean operate(Tree t) {
		if(min != t.min) {
			return min < t.min;
		}
		if(max != t.max) {
			return max<t.max;
		}
		if(sum !=t.sum) {
			return sum<t.sum;
		}
		return false;
	}
	
	void dump() {
		int n = v.size();
		for(Pair<Pair<Integer, Integer>, Integer> pair : v) {
			for(int i = pair.first.first-1; i>0; --i) {
				int value =pair.first.second;
				byte b =(byte) value;
				System.out.println(b & 1);
			}
		}
	}

	void std() {
		while(true) {
			Collections.sort(v, new TreeComparator());
			int n = v.size();
			if(n >=2 && v.get(n-1).second != -1 && v.get(n-2).second != -1) {
				min = min>v.get(n-1).second?min:v.get(n-1).second;
				min = min>v.get(n-1).second?min:v.get(n-1).second;
				
				--v.get(n-2).first.first;
				v.get(n-2).first.second = (byte)v.get(n-2).first.second.intValue() >>1;
				v.get(n-2).second = v.get(n-2).second + v.get(n-1).second;
				v.remove(v.size()-1);
			} else {
				break;
			}
			max = sum;
			int cnt = 0;
			for(int i=0;i<v.size() -1;++i) {
				if(v.get(i).second != -1) {
					max = max<v.get(i).second?max:v.get(i).second;
				}
				else {
					++cnt;
				}
			}
			if(cnt>0) {
				max = max<sum/cnt?max:sum/cnt;
			}
		}
	}
}
