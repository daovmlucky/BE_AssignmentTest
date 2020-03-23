
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import data.UserInfo;


public class FileUtility {
	public static String ROOT = "CEO";
	
	public static UserInfo readFile(String path){
		UserInfo u = new UserInfo();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
			String line;
			Integer index = 0;
			boolean add = false;
			boolean remove = false;
			while ((line = br.readLine()) != null) {
				if(index == 0) {
                	u.setSize(Integer.valueOf(line));
                }else {
                	int tmp = index - 1;
                	if(tmp <= u.getSize()) {
                		String name = tmp == 0 ? ROOT : String.valueOf((tmp));
                		String[] array = line.split(" ");
                		Set<String> originalPermissions = new HashSet<>(Arrays.asList(array));
                		u.getUserPermission().put(name, originalPermissions);
                	}else {
                		Integer indexUser = index - u.getSize() - 1;
                		if(indexUser <= u.getSize()) {
                			u.getUserRelation().put(indexUser.toString(), line);
                		}else {
                			String[] array = line.split(" ");
                			if(array[0].equalsIgnoreCase("ADD")) {
                				add = true;
                				u.getAddedUserPermission().put(array[1], new HashSet<>(Arrays.asList(array[2])));
                			}
                			if(array[0].equalsIgnoreCase("QUERY") && add) {
                				u.getQueryAddedUser().add(array[1]);
                			}
                			if(array[0].equalsIgnoreCase("REMOVE")) {
                				add = false;
                				remove = true;
                				u.getRemovedUserPermission().put(array[1], new HashSet<>(Arrays.asList(array[2])));
                			}
                			if(array[0].equalsIgnoreCase("QUERY") && remove) {
                				u.getQueryRemovedUser().add(array[1]);
                			}
                		}
                	}
                }
                index++;
			}
			return u;
		}catch (Exception e) {
            System.err.format("Exception: %s%n", e);
            return null;
        }
	}
}
