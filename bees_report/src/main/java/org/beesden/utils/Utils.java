package org.beesden.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class Utils {

	protected static Logger logger = Logger.getLogger("controller");

	public static String cleanUrl(String s) {
		s = s.toLowerCase().replaceAll("%20", " ").replaceAll("[-_ ]", "-").replaceAll("&(amp;)?", "&amp;");
		return s;
	}

	public static String cleanUrlQuery(String s) {
		s = "REPLACE(name,' ','-') = '" + cleanUrl(s).replaceAll("&amp;", "&") + "'";
		return s;
	}

	public static Map<String, String> getUrl(HttpServletRequest request) {
		Map<String, String> pageMap = new HashMap<String, String>();
		String query = request.getQueryString();
		if (query != null) {
			query = request.getRequestURL() + "?" + query;
		} else {
			query = request.getRequestURL().toString();
		}
		pageMap.put("par", query);

		String ref = request.getHeader("referer");
		if (ref == null) {
			ref = request.getContextPath();
		}
		pageMap.put("ref", ref);

		pageMap.put("uri", request.getRequestURI());
		return pageMap;
	}

	public static Date isDate(String date, String pattern) {
		Date d = new Date();
		try {
			d = new SimpleDateFormat(pattern).parse(date);
			logger.debug(d + " is valid date");
		} catch (ParseException nfe) {
			return null;
		}
		return d;
	}

	public static boolean isNumeric(String str) {
		try {
			Integer d = Integer.parseInt(str);
			logger.debug(d + " is numeric");
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static String replaceAll(String string, String pattern, String replacement) {
		return string.replaceAll(pattern, replacement);
	}
}