package utils;

import java.io.File;

import stage2.Decaf;

public class Test {
	
	public final static String FOLDER_PATH = "X:/NCSU/CSC-512 Compilers/Project/NewDecafTestCases/";
	
	static public void main(String[] args) {
		final File folder = new File(FOLDER_PATH);
		String[] mainArgs = new String[1];
		for (final File file: folder.listFiles()) {
			System.out.println(file.getName());
			mainArgs[0] = FOLDER_PATH+file.getName();
			Decaf.main(mainArgs);
		}
	}
}

