/**
 * 
 */
package stage2;
import java.util.ArrayList;
import java.util.List;

/**
 * @author George
 *
 */
public class ScopeElement {
	private ScopeUnit scopeUnit;
	private ScopeElement parent;
	private List<ScopeElement> kids;
	private int level;
	
	public ScopeElement(){
		kids = new ArrayList<ScopeElement>();
	}

	public ScopeUnit getScopeUnit() {
		return scopeUnit;
	}

	public void setScopeUnit(ScopeUnit scopeUnit) {
		this.scopeUnit = scopeUnit;
	}

	public ScopeElement getParent() {
		return parent;
	}

	public void setParent(ScopeElement parent) {
		this.parent = parent;
	}

	public List<ScopeElement> getKids() {
		return kids;
	}

	public void setKids(List<ScopeElement> kids) {
		this.kids = kids;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void addKid(ScopeElement kid){
		kids.add(kid);
		kid.setParent(this);
	}
}