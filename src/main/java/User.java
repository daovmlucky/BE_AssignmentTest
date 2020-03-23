import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


public class User extends UserComponent{
	Map<String,UserComponent> userComponents = new HashMap<String,UserComponent>();
	String name;
	Set<String> originalPermissions;
	Set<String> permissions;
	UserComponent parent;
	static String ROOT = "0";

	public User() {
		super();
		originalPermissions = new HashSet<>();
		permissions = new HashSet<>();
	}
	
	public User(String name, Set<String> originalPermissions) {
		super();
		this.name = name;
		this.originalPermissions = originalPermissions;
		this.permissions = new HashSet<>();
		this.permissions.addAll(originalPermissions);
	}

	public void add(UserComponent userComponent) {
		userComponents.put(userComponent.getName(), userComponent);
		userComponent.setParent(this);
		updatePermission(userComponent);
	}
 
	public void remove(UserComponent userComponent) {
		userComponents.remove(userComponent.getName());
	}
 
	public UserComponent getChild(String name) {
		Map<String, UserComponent> map = traversedUserComponents();
		if(map.containsKey(name)) {
			return map.get(name);
		}else {
			return null;
		}
		
	}

	public String getName() {
		return name;
	}

	public Set<String> getOriginalPermissions() {
		return originalPermissions;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	@Override
	public HashMap<String, UserComponent> traversedUserComponents() {
		// TODO Auto-generated method stub
		HashMap<String, UserComponent> map = new HashMap<>();
		Stack stack = new Stack();
		stack.push(this);
		while(!stack.isEmpty()) {
			UserComponent entity = (UserComponent)stack.pop();
			if(entity.getUserComponents().size() != 0) {
				for (Map.Entry<String, UserComponent> entryUserComponent: entity.getUserComponents().entrySet()) {
					stack.push(entryUserComponent.getValue());
					map.put(entryUserComponent.getKey(), entryUserComponent.getValue());
				}
			}
			
		}
		map.put(ROOT, this);
		return map;
	}
	
	private void updatePermission(UserComponent userComponent) {

		Set<String> currentPermissions = new HashSet<>();
		UserComponent parentUser = userComponent.getParent();
		parentUser.getPermissions().addAll(userComponent.getPermissions());
		currentPermissions.addAll(parentUser.getPermissions());
		while(parentUser != null) {
			currentPermissions.addAll(parentUser.getOriginalPermissions());
			parentUser.getPermissions().addAll(currentPermissions);
			parentUser = parentUser.getParent();
		}
		
	}
	
	public void addPermissionForUser(Set<String> addedPermissions) {
		addedPermissions.addAll(this.getPermissions());
		this.setPermissions(addedPermissions);
		updatePermission(this);
	}
	
	public void removePermissionForUser(Set<String> removedPermissions) {
		for (String item : removedPermissions) {
			if(this.getPermissions().contains(item)) {
				this.getPermissions().remove(item);
			}
		}
		removePermissionRelation(this,removedPermissions);
	}

	private void removePermissionRelation(User user, Set<String> removedPermissions) {
		for (String item : removedPermissions) {
			UserComponent parentUser = user.getParent();
			while(parentUser != null) {
				if(parentUser.getPermissions().contains(item)) {
					parentUser.getPermissions().remove(item);
				}
				parentUser = parentUser.getParent();
			}
		}
		
	}

	public void setOriginalPermissions(Set<String> originalPermissions) {
		this.originalPermissions = originalPermissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	public UserComponent getParent() {
		return parent;
	}

	public void setParent(UserComponent parent) {
		this.parent = parent;
	}

	public Map<String, UserComponent> getUserComponents() {
		return userComponents;
	}

	public void setUserComponents(Map<String, UserComponent> userComponents) {
		this.userComponents = userComponents;
	}
	
	
	


}
