package com.main.application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HDDBST {
	private HDDNode root;
	private Path path = Paths.get("HDDBinarySearchTree/data/root.txt");

	public HDDBST() throws IOException {
		List<String> fileData = Files.readAllLines(path);
		if (!fileData.isEmpty())
			this.root = new HDDNode(fileData.get(0));
		else
			this.root = null;
	}

	synchronized void put(String key, String value) {
		try {
			putHelper(root, key, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void putHelper(HDDNode root, String key, String value) throws IOException {
		if (root == null) {
			this.root = new HDDNode(key, value);
			Files.write(path, this.root.getKey().getBytes(), StandardOpenOption.CREATE);
			return;
		}
		if (root.getKey().equals(key)) {
			System.out.println("Multiple Keys:" + key);
			return;
		}
		if (key.compareTo(root.getKey()) > 0) {
			if (root.getRight() == null) {
				root.setRight(new HDDNode(key, value));
				return;
			}
			putHelper(root.getRight(), key, value);
		} else {
			if (root.getLeft() == null) {
				root.setLeft(new HDDNode(key, value));
				return;
			}
			putHelper(root.getLeft(), key, value);
		}
	}

	String get(String key) {
		return getHelper(this.root, key);
	}

	private String getHelper(HDDNode root, String key) {
		if (root == null)
			return null;
		if (root.getKey().equals(key))
			return root.getValue();
		String result;
		if (key.compareTo(root.getKey()) > 0)
			result = getHelper(root.getRight(), key);
		else
			result = getHelper(root.getLeft(), key);
		return result;
	}

	void printLOT() {
		HDDNode iter = this.root;
		if (iter == null)
			return;
		Queue<HDDNode> Q = new LinkedList<>();
		Q.add(iter);
		Q.add(null);
		while (!Q.isEmpty()) {
			HDDNode polled = Q.remove();
			if (polled == null) {
				System.out.println();
				if (Q.isEmpty())
					return;
				Q.add(null);
				continue;
			}
			System.out.print(polled.getValue() + " ");
			if (polled.getLeft() != null)
				Q.add(polled.getLeft());
			if (polled.getRight() != null)
				Q.add(polled.getRight());
		}
	}
}
