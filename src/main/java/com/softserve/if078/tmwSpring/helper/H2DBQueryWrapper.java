package com.softserve.if078.tmwSpring.helper;

public class H2DBQueryWrapper {
	private H2DBQueryWrapper() {
	}

	// \"task management wizard\".\"
	public static String h2DBQueryToMySql(String query, String nameOfShema) {
		String g = query.replaceAll(nameOfShema, "");
		return g.replaceAll("\"", "");
	}

}
