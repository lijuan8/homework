package com.ada.homework;

import org.apache.commons.lang.StringUtils;

public class Utils {
	public static String removeAllSpace(String input) {
		if (StringUtils.isEmpty(input)) {
			return input;
		}
		char[] nospace = new char[input.length()];
		int j = 0;
		char c;
		for (int i = 0; i < input.length(); i++) {
			if ((c = input.charAt(i))!=' ') nospace[j++] = c;
		}
		return new String(nospace, 0, j);
	}
}
