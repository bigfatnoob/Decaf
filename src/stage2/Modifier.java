/**
 * 
 */
package stage2;

/**
 * @author George
 *
 */
public class Modifier {
	
	private boolean scopePublic = true;
	private boolean scopePrivate;
	private boolean scopeProtected;
	private boolean scopeStatic;
	
	public boolean isScopePublic() {
		return scopePublic;
	}
	public void setScopePublic(boolean scopePublic) {
		this.scopePublic = scopePublic;
	}
	public boolean isScopePrivate() {
		return scopePrivate;
	}
	public void setScopePrivate(boolean scopePrivate) {
		this.scopePrivate = scopePrivate;
		this.scopePublic = false;
	}
	public boolean isScopeProtected() {
		return scopeProtected;
	}
	public void setScopeProtected(boolean scopeProtected) {
		this.scopeProtected = scopeProtected;
		this.scopePublic = false;
	}
	public boolean isScopeStatic() {
		return scopeStatic;
	}
	public void setScopeStatic(boolean scopeStatic) {
		this.scopeStatic = scopeStatic;
	}
}
