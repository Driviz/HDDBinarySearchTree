package com.main.application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class HDDNode {
	private String key;
	private String value;
	private HDDNode left = null;
	private HDDNode right = null;

	public HDDNode(String key, String value) {
		this.key = key;
		this.value = value;
		this.left = null;
		this.right = null;
		writeToFile();
	}

	public HDDNode(String key) throws IOException {
		Path path = Paths.get("HDDBinarySearchTree/data/" + key);
		List<String> fileData = Files.readAllLines(path);
		this.key = fileData.get(0);
		this.value = fileData.get(1);
		if (!fileData.get(2).equals("null"))
			this.left = new HDDNode(fileData.get(2));
		if (!fileData.get(3).equals("null"))
			this.right = new HDDNode(fileData.get(3));
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
		writeToFile();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		writeToFile();
	}

	public HDDNode getLeft() {
		return left;
	}

	public void setLeft(HDDNode left) {
		this.left = left;
		writeToFile();
	}

	public HDDNode getRight() {
		return right;
	}

	public void setRight(HDDNode right) {
		this.right = right;
		writeToFile();
	}

	@Override
	public String toString() {
		return key + "\n" + value + "\n" + ((left == null) ? "null" : left.getKey()) + "\n"
				+ ((right == null) ? "null" : right.getKey());
	}

	private void writeToFile() {
		Path path = Paths.get("HDDBinarySearchTree/data/" + key);
		try {
			Files.write(path, toString().getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
