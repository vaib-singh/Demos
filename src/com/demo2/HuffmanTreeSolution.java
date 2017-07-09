package com.demo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class HuffmanTreeSolution {

	private static int count=0, freq = 100;
	private static Map<Integer, List<Integer>>  map = new TreeMap<Integer, List<Integer>>();
	private static Stack<HuffmanTree> left = new Stack<HuffmanTree>(), right = new Stack<HuffmanTree>();
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		int inputs = 0;
		input = scanner.nextLine();
		while(!input.equals("0")){
		inputs++;
		String[] values  = input.split(" ");
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
		root.setMinValue(freq);
		root.setMaxValue(freq);
		calculateTrees(root);
		System.out.println("Case "+ inputs + ": "+ count);
		count = 0;
		left.clear();
		right.clear();
		input = scanner.nextLine();
		}
		scanner.close();
	}

	public static void populateTree(HuffmanTree node) {
		if(!node.hasChildren()){
			List<Integer> list = map.get(node.getaPath().length());
			if(list == null) {
				list = new ArrayList<>();
			}
			list.add(node.getData());
			map.put(node.getaPath().length(), list);
			return;
		}
		if(node.hasChildren()) {
			populateTree(node.getLeft());
		}
		if(node.hasChildren()) {
			populateTree(node.getRight());
			if(node.getParent() != null) {
				node.setData(node.getLeft().getData() + node.getRight().getData());
				List<Integer> list = map.get(node.getaPath().length());
				if (list == null) {
					list = new ArrayList<>();
				}
				list.add(node.getData());
				map.put(node.getaPath().length(), list);
			}
		}
	}

	public static void validateHuffman(HuffmanTree node) throws Exception {
		if(node == null) {
			return;
		}
		if(node != null) {
			validateHuffman(node.getLeft());
			if(node.hasChildren()){
				if(node.getLeft().getData() > node.getRight().getData()){
					throw new Exception("Tree not Huffman");
				}
			}
			if(node.getParent()!=null && node.getParent().getParent() != null &&
					node.getParent().isRight() && node.getParent().getParent().getLeft().getData() < node.getData()) {
				throw new Exception("Tree not Huffman");
			}
		}
		if(node != null) {
			 validateHuffman(node.getRight());
			 if(node.getParent()==null && node.getData() != node.getLeft().getData() + node.getRight().getData()) {
				 throw new Exception("Invalid Huffman");
			 }
			 else if(node.getParent() == null) {
				 validate2();
			 }
		}
	}

	private static void validate2() throws Exception {
		for (Entry<Integer, List<Integer>> entry : map.entrySet()) {
			List<Integer> actualValues = entry.getValue();
			List<Integer> sortedValues = new ArrayList<>();
			sortedValues.addAll(actualValues);
			Collections.sort(sortedValues);
			if(!sortedValues.equals(actualValues)) {
				throw new Exception("Invalid order  huffman Tree");
			}
		}
	}

	public static void initializeTree(HuffmanTree node) {
		if(null == node) {
			return;
		}
		if(null != node) {
			if(node.getParent()!= null){
				getMinMaxValues(node);
			}
			initializeTree(node.getLeft());
			if(!node.hasChildren()) {
				node.setData(node.getMinValue());
				left.push(node);
			}
		}
		if(null != node) {
			initializeTree(node.getRight());
		}
	}
	
	public static void calculateTrees(HuffmanTree root) {
		initializeTree(root);
		right.push(left.pop());
		boolean complete = false;
		HuffmanTree node;
		List<HuffmanTree> constantNodes = new ArrayList<HuffmanTree>();
		while(true) {
			map.clear();
			try {
				populateTree(root);
				validateHuffman(root);
//				System.out.println("Valid");
				count++;
			} catch (Exception e) {
//				System.out.println("Invalid");
			}
			if(left.peek().getData() >= left.peek().getMaxValue() && right.peek().getData() >= right.peek().getMaxValue()) {
				node = right.pop();
				node.setData(node.getMinValue());
				right.push(node);
				node = left.pop();
				node.setData(node.getMinValue());
				right.push(node);
				int ct = 0;
				constantNodes.clear();
				while(right.size() !=1) {
					if(left.size() == 0) {
						complete = true;
						break;
					}
					if(left.peek().getMinValue() == left.peek().getMaxValue() && !constantNodes.contains(left.peek())) {
						constantNodes.add(left.peek());
						right.push(left.pop());
					}
					else if(left.peek().getData()>= left.peek().getMaxValue() && !constantNodes.contains(left.peek())) {
						node = left.pop();
						node.setData(node.getMinValue());
						right.push(node);
					}
					else {
						if(left.peek().getData()< left.peek().getMaxValue() && ct ==0) {
							node = left.pop();
							node.setData(node.getData()+1);
							left.push(node);
							ct++;
						}	
						left.push(right.pop());
					}
				}
				if(complete) {
					break;
				}
			} 
			else if(left.peek().getData() < left.peek().getMaxValue() && right.peek().getData()>= right.peek().getMaxValue()){
				node = left.pop();
				node.setData(node.getData()+1);
				left.push(node);
				node = right.pop();
				node.setData(node.getMinValue());
				right.push(node);
			}
			else if (right.peek().getData() >= right.peek().getMaxValue()) {
				node = right.pop();
				node.setData(node.getMinValue());
				right.push(node);
				
			}
			else if(right.peek().getData()< right.peek().getMaxValue()) {
				node = right.pop();
				node.setData(node.getData()+1);
				right.push(node);
			}
	}
	}
	public static void getMinMaxValues(HuffmanTree node) {
		if(node.isLeft()) {
			node.setMaxValue(node.getParent().getMaxValue()/2);
			if(node.getParent().getRight().hasChildren()) {
				node.setMinValue((int) Math.ceil((double)node.getParent().getMinValue()/3));
			}
			else{
				node.setMinValue(1);
			}
		}
		if(node.isRight()){
			node.setMinValue((int)Math.ceil((double)node.getParent().getMinValue()/2));
			node.setMaxValue(node.getParent().getMaxValue() - node.getParent().getLeft().getMinValue());
			
		}
	}
}
 