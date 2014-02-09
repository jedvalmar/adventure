package org.adventure.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {
	static Pattern itemPattern = Pattern.compile("<.*?>");
	
	private String keyWord;
	private String[] tokens;
	private String cmd;
	private Map<String, String> itemMap = new HashMap<String, String>();
	
	
	public Command(String cmd) {
		this.cmd = cmd.toLowerCase();
		StringTokenizer tokens = new StringTokenizer(cmd.toLowerCase());
	    String keyWord = tokens.nextToken();
	    setKeyWord(keyWord);
	    this.tokens = new String[tokens.countTokens()];
	    int i = 0;
	    while (tokens.hasMoreElements()) {
	    	this.tokens[i++] = tokens.nextToken();
	    }
	}
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String[] getCmdTokens() {
		return tokens;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getCmd() {
		return cmd;
	}
	
	public Map<String, String> getItemMap() {
		return itemMap;
	}

	public boolean matches(String commandPattern) {
		// Convert command pattern into regular expression.
		// take <item> from <container> .. Convert to "take .* from .*" and regex match it.
		//Build regex from command pattern.
		String[] keyWords = itemPattern.split(commandPattern);
		StringBuilder sb = new StringBuilder();
        for(String s : keyWords) {
        	sb.append(s);
        	sb.append(".*");
        }
        Pattern p = Pattern.compile(sb.toString());
        boolean meetsPattern =  p.matcher(this.cmd).find();
        if (meetsPattern) {
        	
        	Matcher itemsMatcher = itemPattern.matcher(commandPattern);
        	int i = 0;
        	List<String> itemTokens = new ArrayList<String>();
        	while (itemsMatcher.find()) {
        		String g =itemsMatcher.group();
        		if (g.isEmpty() == false) {        	
        			itemTokens.add(g);
        		}
        	}
        	
        	StringBuilder sb1 = new StringBuilder();
        	for(String s : keyWords) {
        		sb1.append("(").append(s.trim()).append(")|");
        	}
        	String keyWordRegEx = sb1.toString();
        	keyWordRegEx = keyWordRegEx.substring(0, keyWordRegEx.length() -1);
        	Pattern p2 = Pattern.compile(keyWordRegEx);
        	String[] t = p2.split(this.cmd);
        	List<String> itemStrings = new ArrayList<String>();
        	for (String string : t) {
        		if (string.isEmpty() == false) {
        			itemStrings.add(string.trim());				
        		}
        	}
        	i = 0;
        	for (String string : itemTokens) {
        		this.itemMap.put(string, itemStrings.get(i++));
        	}
        }
        return meetsPattern;
	}
}
