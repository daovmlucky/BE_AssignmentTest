import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import data.UserInfo;

public class Main {

	public static String path_input = "conf/input.txt";
	public static String path_output = "conf/output.txt";
	
	public static String path_input_extend = "conf/input_extend.txt";
	public static String path_output_extend = "conf/output_extend.txt";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserService us = new UserService();
		UserInfo data = FileUtility.readFile(path_input);
		us.buildTreeUser(data);
		us.displayUserAndPermission(path_output);
		
		UserInfo data_extend = FileUtility.readFile(path_input_extend);
		us.buildTreeUser(data_extend);
		us.displayUpdatedPermission(path_output_extend,data_extend);

	}

}
