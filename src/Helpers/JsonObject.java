package Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class JsonObject {
	private HashMap<String, Object> map;

	public static void main(String[] args) {
		String str = "{\"Space\" : {\"turns\" : [{\"space\": \"yo\"}, \"null\", \"3\"] }}";
		JsonObject obj = new JsonObject(str);
		System.out.println(toString(obj));
	}
	
	/*
	 * For passing references of existing objects 
	 * for the creation of other objects
	 */
	public void addKeyManually(String key, Object value) {
		map.put(key, value);
	}
	
	/**
	 * for testing.
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		String ret = "";
		if(obj instanceof JsonObject) {
			HashMap<String, Object> map = ((JsonObject) obj).getMap();
			for (Entry<String, Object> entry : map.entrySet())
			{	
				ret += "{KEY(" + entry.getKey() + ") / VALUE(" + toString(entry.getValue()) + ")}";
			}
		} else if(obj instanceof Object[]) {
			Object[] arr = (Object[]) obj;
			ret += "[";
			for(Object o : arr) 
				ret += toString(o) + ",";
			ret.substring(0, ret.length()-1);
			ret += "]";
		}
		else 
			ret += (String) obj + "*" + (obj == null ? "*NULL*" : obj.getClass().getSimpleName() )+ "*";
		return ret;
	}

	public JsonObject (String serial) { 
		map = new HashMap<String, Object>();
		loadSerial(serial);
	}
	
	/**
	 * returns the value mapped to the key as a String
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		return (String) map.get(key);
	}
	
	/**
	 * returns the value mapped to the key as a JsonObjects
	 * @param key
	 * @return
	 */
	public JsonObject getJsonObject(String key) {
		return (JsonObject) map.get(key);
	}
	
	public Object getObject(String key) {
		return map.get(key);
	}
	
	/**
	 * returns the value mapped to the key as an Array of Strings
	 * @param key
	 * @return
	 */
	public String[] getStringArray(String key) {
		return (String[]) map.get(key);
	}
	
	/**
	 * returns the value mapped to the key as an Array of JsonObjects
	 * @param key
	 * @return
	 */
	public JsonObject[] getJsonObjectArray(String key) {
		return (JsonObject[]) map.get(key);
	}
	
	/**
	 * parses a value into either an Object, Array or String. Returns result as an Object
	 * @param serial
	 * @return
	 */
	private Object loadValue(String serial) {
		serial = serial.trim();
		if(serial.charAt(0) == '{') {
			JsonObject object = new JsonObject(serial);
			return object;
		}
		else if(serial.charAt(0) == '[') {
			Object[] arr = loadArray(serial);
			return arr;
		}
		if(serial.equals("\"null\"") || serial.equals("null"))
			return null;
		if(serial.charAt(0) == '\"')
			return serial.substring(1,serial.length()-1);
		return serial;
	}
	
	/**
	 * Main method. Loads a serialized array and Fills in map for this JsonObject
	 * @param serial
	 */
	private void loadSerial(String serial) {
		serial = serial.trim();
		serial = serial.charAt(0) == '{' ? serial.substring(1, serial.length() - 1) : serial;

		String[] pairs = split(serial);
		for(String pair : pairs) {
			String[] keyValue = splitPair(pair);
			map.put((String)loadValue(keyValue[0]), loadValue(keyValue[1]));
		}		
	}
	
	/**
	 * splits an array of values and sends them to loadValue, which parses them correctly. 
	 * @param serial
	 * @return
	 */
	private Object[] loadArray(String serial) {
		serial = serial.trim();
		serial = serial.charAt(0) == '[' ? serial.substring(1, serial.length() - 1) : serial;
		String[] arr = split(serial);
		Object[] ret = new Object[arr.length];
		for(int x = 0; x < arr.length; ++x) {
			ret[x] = loadValue(arr[x]);
		}
		return ret;
	}

	private static String[] splitPair(String serial) {
		return new String[] {serial.substring(0,serial.indexOf(":")), serial.substring(serial.indexOf(":") + 1)};
	}
	
	/**
	 * splits serial string by commas and returns the array of strings
	 * @param serial
	 * @return
	 */
	private static String[] split(String serial) {
		ArrayList<String> list = new ArrayList<String>();
		boolean quotes = false;
		int lastIndex = 0; 
		int count = 0; 
		for(int x = 0; x < serial.length(); ++x) {
			char ch = serial.charAt(x);
			if(x > 0 && ch == '\"' && serial.charAt(x-1) != '\\')
				quotes = !quotes;
			if(quotes)
				continue;
			if(ch == '{' || ch == '[')
				count++;
			else if(ch == '}' || ch == ']')
				count--;
			else if(count == 0 && ch == ',') {
				list.add(serial.substring(lastIndex, x).trim());
				lastIndex = x + 1;
			}
		}
		if(lastIndex != serial.length() - 1)
			list.add(serial.substring(lastIndex).trim());
		return list.toArray((new String[list.size()]));
	}
	
	private HashMap<String, Object> getMap() {
		return map;
	}

}
