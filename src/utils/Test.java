package utils;

public class Test {
	
	public static void doubleIt(DummyInt dummyInt) {
		dummyInt.integer = 2 * dummyInt.integer;
	}
	
	public class DummyInt{
		Integer integer;
	}
	
	public static void main(String[] args) {
		DummyInt dummyInt = new Test().new DummyInt();
		dummyInt.integer = 5;
		doubleIt(dummyInt);
		System.out.println(dummyInt.integer);
	}
}

