package com.demo2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class NewHuffmanSolution {
	static Map<Tree, Long> dp = new HashMap<Tree, Long>();
	public static Long gao(Tree s){
		Long ret = 0L;
		if(dp.containsKey(s)){
			return dp.get(s);
		}
		ret= dp.get(s);
		if(s.v.size() ==1) {
			ret = s.sum ==0 ? 1L:0L;
			return ret;
		}
		int n = s.v.size();
		if(s.v.get(n-1).second == -1) {
			for(int i= s.min; i<= s.max; ++i) {
				Tree t = s;
				t.v.get(n-1).second = i;
				t.min = i;
				t.sum =t.sum-i;
				t.std();
				ret = ret+ gao(t);
			}
		} else {
			int lim = s.min>s.v.get(n-1).second?s.min:s.v.get(n-1).second;
			for(int i = lim; i<= s.max; ++i) {
				Tree t = s;
				t.v.get(n-2).second = i;
				t.min = i;
				t.sum = t.sum -i;
				t.std();
				ret = ret  +gao(t);
			}
		}
		return ret;
		
	}

	public static Pair<Integer, Integer> encode(String s) {
		Pair<Integer, Integer> pair = new Pair<Integer, Integer>(s.length(), 0);
		for(int i=0; i<s.length(); ++i) {
			pair.second = (byte)pair.second.intValue() << 1;
			if(s.charAt(i) == '1') {
				pair.second = (byte)pair.second.intValue() |  1;
			}
		}
		return pair;	
		
	}

	public static void main(String[] args) {
		int ri = 0,n;
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		Tree tt = new Tree();
		dp.clear();
		tt.min =1;
		tt.sum = 100;
		tt.v.clear();
		String st[] = s.split(" ");
		s = s.substring(2);
		for(int i =0; i< st.length - 1;++i) {
			for(int j =1; j<st.length; ++j) {
				String node = st[j];
				tt.v.add(new Pair<Pair<Integer,Integer>, Integer>(encode(node), -1));
			}
		}
		tt.std();
		System.out.println(gao(tt));
	}
	
}
