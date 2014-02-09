package org.adventure.commands;

public class TestCommandMatcher {

	public static void main(String[] args) {
		Command c = new Command("put asdfasd asdfasdf in my red jacket.");
		System.out.println(c.matches("put <item> in <container>"));
		System.out.println(c.getItemMap().toString());
		
		c = new Command("take asdfasd asdfasdf from my red jacket.");
		System.out.println(c.matches("put <item> in <container>"));
		System.out.println(c.getItemMap());
		System.out.println(c.matches("take <item>"));
		System.out.println(c.getItemMap());
		System.out.println(c.matches("take <item> from <container>"));
		System.out.println(c.getItemMap());
	}

}
