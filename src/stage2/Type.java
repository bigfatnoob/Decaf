package stage2;

public class Type {
	String name;
	boolean isArray;
	int arraySize;
	boolean isClass;
	
	public Type(String name, boolean isArray, int arraySize, boolean isClass){
		this.name = name;
		this.isArray = isArray;
		this.arraySize = arraySize;
		this.isClass = isClass;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + arraySize;
		result = prime * result + (isArray ? 1231 : 1237);
		result = prime * result + (isClass ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Type other = (Type) obj;
		if (arraySize != other.arraySize)
			return false;
		if (isArray != other.isArray)
			return false;
		if (isClass != other.isClass)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
