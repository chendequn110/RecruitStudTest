package com.tiandu.recruit.stud.base.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;


import com.tiandu.recruit.stud.api.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public class Logger {

	private static final boolean logFlag = Api.IS_DEV;
	private static final int logLevel = Log.VERBOSE;

	/**
	 * It is used for json pretty print
	 */
	private static final int JSON_INDENT = 2;
	private static final String SUFFIX = ".java";
	private static final int STACK_TRACE_INDEX = 5;
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	private static String tag = "[LearnStud]";

	private static String getFunctionName() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement targetElement = stackTrace[STACK_TRACE_INDEX];

		String methodName = targetElement.getMethodName();
		String className = targetElement.getClassName();
		String[] classNameInfo = className.split("\\.");
		if (classNameInfo.length > 0) {
			className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
		}
		if (className.contains("$")) {
			className = className.split("\\$")[0] + SUFFIX;
		}

		int lineNumber = targetElement.getLineNumber();
		if (lineNumber < 0) lineNumber = 0;

		return "[ (" + className + ":" + lineNumber + ") " + methodName + " ] ";

	}

	/**
	 * The Log Level:i
	 *
	 * @param str
	 */
	public static void i(Object str) {
		if (logFlag) {
			if (logLevel <= Log.INFO) {
				String name = getFunctionName();
				if (name != null) {
					Log.i(tag, name + " - " + str);
				} else {
					Log.i(tag, str.toString());
				}
			}
		}
	}

	/**
	 * The Log Level:d
	 *
	 * @param str
	 */
	public static void d(Object str) {
		if (logFlag) {
			if (logLevel <= Log.DEBUG) {
				String name = getFunctionName();
				if (name != null) {
					Log.d(tag, name + " - " + str + "\n\t");
				} else {
					Log.d(tag, str.toString());
				}
			}
		}
	}

	/**
	 * The Log Level:V
	 *
	 * @param str
	 */
	public static void v(Object str) {
		if (logFlag) {
			if (logLevel <= Log.VERBOSE) {
				String name = getFunctionName();
				if (name != null) {
					Log.v(tag, name + " - " + str);
				} else {
					Log.v(tag, str.toString());
				}
			}
		}
	}

	/**
	 * The Log Level:w
	 * @param str
	 */
	public static  void w(Object str) {
		if (logFlag) {
			if (logLevel <= Log.WARN) {
				String name = getFunctionName();
				if (name != null) {
					Log.w(tag, name + " - " + str);
				} else {
					Log.w(tag, str.toString());
				}
			}
		}
	}

	/**
	 * The Log Level:e
	 *
	 * @param str
	 */
	public static void e(Object str) {
		if (logFlag) {
			if (logLevel <= Log.ERROR) {
				String name = getFunctionName();
				if (name != null) {
					Log.e(tag, name + " - " + str);
				} else {
					Log.e(tag, str.toString());
				}
			}
		}
	}

	/**
	 * The Log Level:e
	 *
	 * @param ex
	 */
	public static void e(Exception ex) {
		if (logFlag) {
			if (logLevel <= Log.ERROR) {
				Log.e(tag, "error", ex);
			}
		}
	}

	public static void json(String json) {
		if (TextUtils.isEmpty(json)) return ;
		String message;
		if (logFlag) {
			try {
				json = json.trim();
				if (json.startsWith("{")) {
					JSONObject jsonObject = new JSONObject(json);
					message = jsonObject.toString(JSON_INDENT);
					d(message);
					return;
				}
				if (json.startsWith("[")) {
					JSONArray jsonArray = new JSONArray(json);
					message = jsonArray.toString(JSON_INDENT);
					d(message);
					return;
				}

			} catch (JSONException e) {
				e("Invalid Json");
			}
		}
	}

	public static void xml(@NonNull String xml) {
		if (TextUtils.isEmpty(xml)) return ;
		if (logFlag) {
			try {
				Source xmlInput = new StreamSource(new StringReader(xml));
				StreamResult xmlOutput = new StreamResult(new StringWriter());
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				transformer.transform(xmlInput, xmlOutput);
				d(xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
			} catch (TransformerException e) {
				e("Invalid xml");
			}
		}
	}
}
