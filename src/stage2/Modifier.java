/**
 * 
 */
package stage2;

/**
 * @author George
 *
 */
public class Modifier {
	
	private boolean scopePublic;
	private boolean scopePrivate;
	private boolean scopeProtected;
	private boolean scopeDefault = true;
	private boolean scopeStatic;
	
	public boolean isScopePublic() {
		return scopePublic;
	}
	public void setScopePublic(boolean scopePublic) {
		this.scopePublic = scopePublic;
		this.scopeDefault = false;
	}
	public boolean isScopePrivate() {
		return scopePrivate;
	}
	public void setScopePrivate(boolean scopePrivate) {
		this.scopePrivate = scopePrivate;
		this.scopeDefault = false;
	}
	public boolean isScopeProtected() {
		return scopeProtected;
	}
	public void setScopeProtected(boolean scopeProtected) {
		this.scopeProtected = scopeProtected;
		this.scopeDefault = false;
	}
	public boolean isScopeDefault() {
		return scopeDefault;
	}
	public void setScopeDefault(boolean scopeDefault) {
		this.scopeDefault = scopeDefault;
	}
	public boolean isScopeStatic() {
		return scopeStatic;
	}
	public void setScopeStatic(boolean scopeStatic) {
		this.scopeStatic = scopeStatic;
	}
}
