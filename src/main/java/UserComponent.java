import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public abstract class UserComponent {
	public void add(UserComponent userComponent) {
		throw new UnsupportedOperationException();
	}
	public void remove(UserComponent userComponent) {
		throw new UnsupportedOperationException();
	}
	public UserComponent getChild(String value) {
		throw new UnsupportedOperationException();
	}
  
	public String getName() {
		throw new UnsupportedOperationException();
	}
	
	public Set<String> getOriginalPermissions() {
		throw new UnsupportedOperationException();
	}

	public Set<String> getPermissions() {
		throw new UnsupportedOperationException();
	}
	
	public UserComponent getParent() {
		throw new UnsupportedOperationException();
	}

	public void setParent(UserComponent parent) {
		throw new UnsupportedOperationException();
	}
	
	public void setPermissions(Set<String> permissions) {
		throw new UnsupportedOperationException();
	}
	
	public Map<String, UserComponent> getUserComponents() {
		throw new UnsupportedOperationException();
	}
	
	public void addPermissionForUser(Set<String> addedPermissions) {
		throw new UnsupportedOperationException();
	}
	
	public void removePermissionForUser(Set<String> removedPermissions) {
		throw new UnsupportedOperationException();
	}

	public abstract HashMap<String, UserComponent> traversedUserComponents();
 
	public void print() {
		throw new UnsupportedOperationException();
	}
}
