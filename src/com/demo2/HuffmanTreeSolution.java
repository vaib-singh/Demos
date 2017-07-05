package com.demo2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class HuffmanTreeSolution {

	private static int min =1, max=4, count=0, freq = 8, childNodes;
	private static Map<String, Integer> map = new HashMap<String, Integer>(), removedNode = new HashMap<String, Integer>();
	public static void main(String[] args) {
		int sum = 0;
		int multiple =1;
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] values  = input.split(" ");
		childNodes = values.length -1;
		int noOfNodes = Integer.parseInt(values[0]);
		HuffmanTree root = new HuffmanTree("");
		root.setData(freq);
		for(int i=1 ; i<values.length; i++) {
			String binaryCode = values[i];
			HuffmanTree node = root;
			for(int j = 0; j<binaryCode.length(); j++) {
				if(binaryCode.charAt(j) == '0') {
					if(null == node.getLeft()) {
						node.setLeft(new HuffmanTree(binaryCode.substring(0, j+1)));
						node.getLeft().setParent(node);
					}
					node = node.getLeft();
				} else {
					if(null == node.getRight()) {
						node.setRight(new HuffmanTree(binaryCode.substring(0, j+1)));
						node.getRight().setParent(node);
					}
					node = node.getRight();
				}
			}
		}
		scanner.close();
		
		traverse(root);
		/*List<List<Integer>> combinations = new ArrayList<List<Integer>>();
		while(true) {
			if(root.getLeft().getData()!= 0) {
				if(root.getLeft().hasChildren() && !root.getRight().hasChildren() && root.getLeft().getData() == root.getLeft().getMinValue())
					break;
				else if(root.getLeft().getData() == root.getLeft().getMaxValue()) 
					break;
			}
			List<Integer> currentCombo = new ArrayList<Integer>();
			traverse(root, currentCombo, combinations);
		}
		for (List<Integer> list : combinations) {
			for (Integer value : list) {
				multiple*=value;
			}
			sum+=multiple;
			multiple = 1;
		}
		System.out.println(sum);*/
		}
	private static void traverse(HuffmanTree node, List<Integer> combination, List<List<Integer>> combinations) {
		if(!node.hasChildren()) {
			return;
		}
		if(node.getLeft().hasChildren() && !node.getRight().hasChildren()) {
			HuffmanTree left = node.getLeft();
			HuffmanTree right = node.getRight();
			if(0 == left.getMinValue()) {
				left.setMinValue(2);
				right.setMaxValue(node.getData() - left.getMinValue());
				left.setMaxValue(node.getData()/2);
				right.setMinValue(node.getData() - left.getMaxValue());
				left.setData(left.getMaxValue());
				right.setData(right.getMinValue());
			} else {
				left.setData(left.getData() - 1);
				right.setData(right.getData() + 1);
				left.getLeft().setMinValue(0);
			}
		}
		else if(node.getLeft().hasChildren() || node.getRight().hasChildren()) {
			HuffmanTree left = node.getLeft();
			HuffmanTree right = node.getRight();
			if(0 == left.getMinValue()) {
				if(node.getData() % 3 == 0 ) {
					left.setMinValue(node.getData()/3);
				} else {
					left.setMinValue(node.getData()/3 + 1);
				}
				right.setMaxValue(node.getData() - left.getMinValue());
				left.setMaxValue(node.getData()/2);
				right.setMinValue(node.getData() - left.getMaxValue());
				left.setData(left.getMinValue());
				right.setData(right.getMaxValue());
			} else {
				left.setData(left.getData()+1);
				right.setData(right.getData()-1);
			}
		}
		if (node.hasChildren() && !node.getLeft().hasChildren() && !node.getRight().hasChildren()){
			int mutipleValue = 0;
			if(null == node.getParent()) {
				combination.add(node.getData()/2);
			}
			else if(node.isLeft()) {
				combination.add(node.getData()/2);
			} else {
				if(node.getData() == node.getParent().getLeft().getData()) {
					mutipleValue = node.getData()/2;
				}
				else if((node.getData() % 2 ==0 && node.getParent().getLeft().getData() % 2 == 0)
						|| (node.getData() %2 ==0 && node.getParent().getLeft().getData() % 2 != 0)) {
					mutipleValue = node.getParent().getLeft().getData() - (node.getData()/2) +1;
				} else {
					mutipleValue = node.getParent().getLeft().getData() - (node.getData()/2);
				}
				if(0 == mutipleValue) {
					mutipleValue = 1;
				}
				combination.add(mutipleValue);
			}
			return;
		}
		if (node != null) {
			traverse(node.getLeft(), combination, combinations);
		}
		if (node != null) {
			traverse(node.getRight(), combination, combinations);
		}
		if(node.getLeft().getMinValue() != 0 && node.getLeft().getMaxValue() >= node.getLeft().getData()) {
			List<Integer> newCombination = new ArrayList<Integer>();
			if(combinations.contains(combination)) {
				List<Integer> combo  = combinations.get(combinations.indexOf(combination));
				if (combo != combination) {
					combinations.add(combination);
					newCombination.addAll(combination);
					newCombination.remove(newCombination.size() - 1);
				} else 
					return;
			} else {
				combinations.add(combination);
				newCombination.addAll(combination);
				newCombination.remove(newCombination.size() - 1);
			}
					
			traverse(node, newCombination, combinations);
		} else {
			node.getLeft().setMinValue(0);
		}
	}

	public static void traverse(HuffmanTree node) {
		if(node== null) {
			return; 
		}
		if(null != node) {
			if(node.getLeft() == null || (!map.containsKey(node.getLeft().getaPath()) || removedNode.containsKey(node.getRight().getaPath()))){
				traverse(node.getLeft());
			}
			if(!node.hasChildren() && node.getData() > max){
				return;
			}
			// condition for parent's sibling min 
			if (!node.hasChildren()) {
				if(node.isRight() && node.getData() == 0 && !node.hasChildren()) {
					node.setData(node.getParent().getLeft().getData());
				} else {
					node.setData(node.getData() + 1);
				}
				map.put(node.getaPath(), node.getData());
				removedNode.remove(node.getaPath());
			}
			if(null != node.getParent() && null != node.getParent().getParent() && node.getParent().isRight() 
                    && !node.hasChildren() && node.getParent().getParent().getLeft().getData()< node.getData()) {
			    node.setData(0);
			    map.remove(node.getaPath());
                removedNode.put(node.getaPath(), node.getData());
                return;
            }
			if(map.size() == childNodes) {
				checkSum();
				map.remove(node.getaPath());
				removedNode.put(node.getaPath(), node.getData());
				traverse(node);
				return;
			}
		}
		if(null != node) {
			traverse(node.getRight());
			if(node.hasLeft() && !node.getLeft().hasChildren() && node.getLeft().getData() < max && removedNode.containsKey(node.getRight().getaPath())){
				traverse(node);
				map.remove(node.getLeft().getaPath());
				removedNode.put(node.getaPath(), node.getData());
			}
			if(node.getParent() !=null && node.hasChildren()) {
				node.setData(node.getLeft().getData() + node.getRight().getData());
			}
			
		}
	}
	private static boolean checkSum() {
		List<Integer> values = new ArrayList<Integer>(map.values());
		int sum = 0;
		for(Integer value: values) {
			sum+=value;
		}
		if(sum == freq){
		    count++;
		    return true;
		}
			
		return false;
	}
}
