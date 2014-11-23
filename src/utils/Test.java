package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import stage2.Decaf;

public class Test {
	
	public final static String FOLDER_PATH = "X:/NCSU/CSC-512 Compilers/Project/DecafTests/";
	Test2 x;
	static public void main(String[] args) throws Exception {
		final File folder = new File(FOLDER_PATH);
		
		/*String[] mainArgs = new String[1];
		for (final File file: folder.listFiles()) {
			System.out.println(file.getName());
			mainArgs[0] = FOLDER_PATH+file.getName();
			Decaf.main(mainArgs);
			System.out.println();
		}*/
		String[] mainArgs = new String[1];
		mainArgs[0] = FOLDER_PATH+"animals.decaf";
		Decaf.main(mainArgs);
	}
}

class Test2{
	String name;
	String gender;
}

