

package Helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import Models.GameModel;
import Models.Serializable;

public class Json {
	
	/**
	 * Just for testing
	 */
	public static void main(String[] args) throws IOException {
		GameModel game;
		Scanner in = new Scanner(new File("in"));
		String str = "";
		while(in.hasNextLine()) {
			str += in.nextLine() + "\n";
		}
		in.close();
		//System.out.println(game.getBoard().toString());
		FileWriter fw = new FileWriter(new File("out"));
		JsonObject json = new JsonObject(str);
		//System.out.println(JsonObject.toString(json));
		game = new GameModel(2).loadObject(json);
		fw.write(game.serialize());
		fw.close();		
	}

	/**
	 * wraps object in curly brackets({})
	 * @param members
	 * @return
	 */
	public static String jsonObject(String members) {
		return "{\n" + addTab(members) + "\n}";
	}
	
	/**
	 * implode members in commas(,)
	 * @param members
	 * @return
	 */
	public static String jsonMembers(String... members) {
		StringBuilder ret = new StringBuilder();
		for(int x = 0; x < members.length; ++x) {
			ret.append(members[x] + (x == members.length - 1 ? "" : ",\n"));
		}
		return ret.toString();
	}
	
	/**
	 * separates the pair with a colon(:)
	 * @param type
	 * @param value
	 * @return
	 */
	public static String jsonPair(String type, String value) {
		return "\"" + type + "\" : " + value;
	}

	/**
	 * wraps arrays in brackets([])
	 * @param elements
	 * @return
	 */
	public static String jsonArray(String elements) {
		return "[\n" + addTab(elements) + "\n]";
	}
	
	/**
	 * implodes elements with commas(,)
	 * @param elements
	 * @return
	 */
	public static String jsonElements(String... elements) {
		StringBuilder ret = new StringBuilder();
		for(int x = 0; x < elements.length; ++x) {
			ret.append(elements[x] + (x == elements.length - 1 ? "" : ",\n"));
		}
		return ret.toString();
	}
	
	/**
	 * Wraps json value in quotes("")
	 * @param value
	 * @return
	 */
	public static String jsonValue(String value) {
		return "\"" + value + "\"";
	}
	
	/**
	 * serializeArray supports: Lists, Stacks and Arrays. 
	 * These data structures can hold int, long, boolean, char
	 * String and Object(must implement Serializable)
	 * 
	 * @param obj
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String serializeArray(Object obj) {
    	LinkedList<String> list = new LinkedList<String>();
    	if(obj instanceof Stack) {
    		obj = ((Stack<Object>) obj).toArray();
        	System.out.println(obj);
    	}
    	else if(obj instanceof List) {
    		obj = ((List<Object>) obj).toArray();
    	}
    	if(obj instanceof Object[] && ((Object[])obj)[0] instanceof String) {
			for(int x = 0; x < ((Object[])obj).length; ++x) 
				list.add(Json.jsonValue(((Object[])obj)[x] + ""));
    	}
    	else if(obj instanceof int[]) {
			int[] arr = (int[]) obj;
			for(int x = 0; x < arr.length; ++x) 
				list.add(Json.jsonValue(arr[x] + ""));
		}
    	else if(obj instanceof boolean[]) {
			boolean[] arr = (boolean[]) obj;
			for(int x = 0; x < arr.length; ++x) 
				list.add(Json.jsonValue(arr[x] + ""));
		}
    	else if(obj instanceof long[]) {
			long[] arr = (long[]) obj;
			for(int x = 0; x < arr.length; ++x) 
				list.add(Json.jsonValue(arr[x] + ""));
		}
    	else if(obj instanceof char[]) {
			char[] arr = (char[]) obj;
			for(int x = 0; x < arr.length; ++x) 
				list.add(Json.jsonValue(arr[x] + ""));
		}
    	else if(obj instanceof String[]) {
			String[] arr = (String[]) obj;
			for(int x = 0; x < arr.length; ++x) 
				list.add(Json.jsonValue(arr[x] + ""));
    	}
    	
    	if(!(obj instanceof Object[]) || (((Object[])obj)[0] instanceof String))
    		return Json.jsonArray(Json.jsonElements((String[]) list.toArray(new String[1])));
    		
		Object[] arr =  (Object[]) obj;
		for(int x = 0; x < arr.length; ++x) {
			if(arr[x] instanceof Stack || arr[x] instanceof List || arr[x] instanceof Object[]) {
				list.add(serializeArray(arr[x]));
			}
			else {
				list.add(arr[x] == null? null : ((Serializable<Object>) arr[x]).serialize());
			}
		}
    	return Json.jsonArray(Json.jsonElements(list.toArray((new String[1]))));
    }
	
	/**
	 * Adds tabs to every line. Meant for easy editing of json
	 * @param str
	 * @return
	 */
	private static String addTab(String str) {
		Scanner in = new Scanner(str); 
		StringBuilder ret = new StringBuilder();
		while(in.hasNextLine()) {
			ret.append("\n  " + in.nextLine());
		}
		in.close();
		return ret.toString();
	}
}
