import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data.UserInfo;

public class UserService {
	
	UserComponent userComponent;
	public static String ROOT = "CEO";
	
	public UserService() {
		
	}
	
	public UserService(UserComponent u) {
		this.userComponent = u;
	}
	
/*	public void buildTreeUser(String path) {
		Map<String, UserComponent> mapUserCmp = new HashMap<String, UserComponent>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {

            // read line by line
            String line;
            Integer index = 0;
            Integer size = 0;
            while ((line = br.readLine()) != null) {
                if(index == 0) {
                	size = Integer.valueOf(line);
                }else {
                	int tmp = index - 1;
                	if(tmp <= size) {
                		String name = tmp == 0 ? ROOT : String.valueOf((tmp));
                		String[] array = line.split(" ");
                		Set<String> originalPermissions = new HashSet<>(Arrays.asList(array));
                		UserComponent userCmp = new User(name,originalPermissions);
                		if(name.equalsIgnoreCase(ROOT)) {
                			userComponent = userCmp;
                		}
                		mapUserCmp.put(name, userCmp);
                	}else {
                		Integer indexUser = index - size - 1;
                		UserComponent childUser = mapUserCmp.get(indexUser.toString());
                		UserComponent parentTmp = mapUserCmp.get(line);
            			parentTmp.add(childUser);
                	}
                }
                index++;
            }
            

        } catch (Exception e) {
            System.err.format("Exception: %s%n", e);
        }
	}*/
	
	public void buildTreeUser(UserInfo data) {
		Map<String, UserComponent> mapUserCmp = new HashMap<String, UserComponent>();
		Map<String, Set<String>> userPermission = data.getUserPermission();
		for (Map.Entry<String, Set<String>> entityPermission: userPermission.entrySet()) {
			UserComponent userCmp = new User(entityPermission.getKey(),entityPermission.getValue());
			if(entityPermission.getKey().equalsIgnoreCase(ROOT)) {
    			userComponent = userCmp;
    		}
    		mapUserCmp.put(entityPermission.getKey(), userCmp);
		}
		
		Map<String, String> userRelation = data.getUserRelation();
		for (Map.Entry<String, String> entityRelation : userRelation.entrySet()) {
			UserComponent childUser = mapUserCmp.get(entityRelation.getKey());
    		UserComponent parentTmp = mapUserCmp.get(entityRelation.getValue());
			parentTmp.add(childUser);
		}
		
	}
	
	public void displayUserAndPermission(String path) {
		try {
			//Set<String> setRootPermission = userComponent.getPermissions();
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			//String strRootPermission = String.join(" ", setRootPermission); 
			//writer.write(strRootPermission);
			//writer.newLine();
			Map<String, UserComponent> mapUserCmp = userComponent.traversedUserComponents();
			for (Map.Entry<String, UserComponent> entryUserComponent: mapUserCmp.entrySet()) {
				 String strPermission = String.join(" ", entryUserComponent.getValue().getPermissions());
				 writer.write(strPermission);
				 writer.newLine();
			}
			 writer.close();
		}catch (Exception e) {
            System.err.format("Exception: %s%n", e);
        }
	}
	
	public void displayUpdatedPermission(String path,UserInfo data) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			Map<String, UserComponent> mapUserCmp = userComponent.traversedUserComponents();
			for (Map.Entry<String, UserComponent> entryUserComponent: mapUserCmp.entrySet()) {
				 String strPermission = String.join(" ", entryUserComponent.getValue().getPermissions());
				 writer.write(strPermission);
				 writer.newLine();
			}
			
			Map<String, Set<String>>mapAddedUserPermission = data.getAddedUserPermission();
			handleAddRemovePermissionUser(mapAddedUserPermission,true);
			Set<String>addedUser = data.getQueryAddedUser();
			printedNewPermissionUser(addedUser,writer);
			
			
			Map<String, Set<String>>mapRemovedUserPermission = data.getRemovedUserPermission();
			handleAddRemovePermissionUser(mapRemovedUserPermission,false);
			Set<String>removedUser = data.getQueryRemovedUser();
			printedNewPermissionUser(removedUser,writer);
			
			 writer.close();
		}catch (Exception e) {
            System.err.format("Exception: %s%n", e);
        }
	}
	
	private void handleAddRemovePermissionUser(Map<String, Set<String>> map,boolean added) {
		UserComponent uCmp = null;
		for (Map.Entry<String, Set<String>> entityAdded: map.entrySet()) {
			if(entityAdded.getKey().equalsIgnoreCase(ROOT)) {
				uCmp = userComponent;
			}else {
				uCmp = userComponent.getChild(entityAdded.getKey());
			}
			
			if(uCmp != null) {
				if(added) {
					uCmp.addPermissionForUser(entityAdded.getValue());
				}else {
					uCmp.removePermissionForUser(entityAdded.getValue());
				}
			}
		}
	}
	
	private void printedNewPermissionUser(Set<String>setUser,BufferedWriter writer) {
		try {
			for (String item : setUser) {
				if(item.equalsIgnoreCase(ROOT)) {
					 String strPermission = String.join(" ", userComponent.getPermissions());
					 writer.write(strPermission);
					 writer.newLine();
				}else {
					UserComponent uCmp = userComponent.getChild(item);
					 String strPermission = String.join(" ", uCmp.getPermissions());
					 writer.write(strPermission);
					 writer.newLine();
				}
			}
		}catch (Exception e) {
            System.err.format("Exception: %s%n", e);
        }
	}
	
}
