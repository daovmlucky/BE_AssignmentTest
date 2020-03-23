package data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserInfo {
	public Integer size;
	public Map<String, Set<String>> userPermission;
	public Map<String, String> userRelation;
	public Map<String, Set<String>> addedUserPermission;
	public Set<String> queryAddedUser;
	public Map<String, Set<String>> removedUserPermission;
	public Set<String> queryRemovedUser;
	

	public UserInfo() {
		super();
		userPermission = new HashMap<>();
		userRelation = new HashMap<>();
		addedUserPermission = new HashMap<>();
		queryAddedUser = new HashSet<>();
		removedUserPermission = new HashMap<>();
		queryRemovedUser = new HashSet<>();		
	}
	
	public UserInfo(Integer size, Map<String, Set<String>> userPermission, Map<String, String> userRelation,
			Map<String, Set<String>> addedUserPermission, Set<String> queryAddedUser,
			Map<String, Set<String>> removedUserPermission, Set<String> queryRemovedUser) {
		super();
		this.size = size;
		this.userPermission = userPermission;
		this.userRelation = userRelation;
		this.addedUserPermission = addedUserPermission;
		this.queryAddedUser = queryAddedUser;
		this.removedUserPermission = removedUserPermission;
		this.queryRemovedUser = queryRemovedUser;
	}
	
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Map<String, Set<String>> getUserPermission() {
		return userPermission;
	}
	public void setUserPermission(Map<String, Set<String>> userPermission) {
		this.userPermission = userPermission;
	}
	public Map<String, String> getUserRelation() {
		return userRelation;
	}
	public void setUserRelation(Map<String, String> userRelation) {
		this.userRelation = userRelation;
	}
	public Map<String, Set<String>> getAddedUserPermission() {
		return addedUserPermission;
	}
	public void setAddedUserPermission(Map<String, Set<String>> addedUserPermission) {
		this.addedUserPermission = addedUserPermission;
	}
	public Set<String> getQueryAddedUser() {
		return queryAddedUser;
	}
	public void setQueryAddedUser(Set<String> queryAddedUser) {
		this.queryAddedUser = queryAddedUser;
	}
	public Map<String, Set<String>> getRemovedUserPermission() {
		return removedUserPermission;
	}
	public void setRemovedUserPermission(Map<String, Set<String>> removedUserPermission) {
		this.removedUserPermission = removedUserPermission;
	}
	public Set<String> getQueryRemovedUser() {
		return queryRemovedUser;
	}
	public void setQueryRemovedUser(Set<String> queryRemovedUser) {
		this.queryRemovedUser = queryRemovedUser;
	}
	
	
	
}
