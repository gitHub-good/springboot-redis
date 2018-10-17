package com.xu.springboot.redis.springbootredis.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 字符随机生成类
 */
public class RandomUtil {
	/**
	 * 随机产生类型枚举
	 */
	public static enum TYPE {
		/**小字符型*/
		LETTER,
		/**大写字符型*/
		CAPITAL,
		/**数字型*/
		NUMBER,
		/**大+小字符 型*/
		LETTER_CAPITAL,
		/**小字符+数字 型*/
		LETTER_NUMBER,
		/**大写字符+数字*/
		CAPITAL_NUMBER,
		/**大+小字符+数字 型*/
		LETTER_CAPITAL_NUMBER,
	}
	private static String[] lowercase = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
			"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

	private static String[] capital = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

//	private static String[] number = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
	private static String[] number = { "1", "2", "3", "4", "5", "6", "7", "8", "9"};

	/**
	 * 静态随机数
	 */
	private static Random random = new Random();

	/**
	 * 获取随机组合码
	 * @param num 位数
	 * @param type 类型
	 * @type 
	 * <br>小写字符型 LETTER,
	 * <br>大写字符型 CAPITAL,
	 * <br>数字型 NUMBER,
	 * <br>大+小字符型 LETTER_CAPITAL,
	 * <br>小字符+数字 型 LETTER_NUMBER,
	 * <br>大字符+数字 型 CAPITAL_NUMBER,
	 * <br>大+小字符+数字 型 LETTER_CAPITAL_NUMBER,
	 */
	public static String getRandom(int num, TYPE type) {
		ArrayList<String> temp = new ArrayList<String>();
		StringBuffer code = new StringBuffer();
		switch (type) {
		case LETTER:
			temp.addAll(Arrays.asList(lowercase));
			break;
		case CAPITAL:
			temp.addAll(Arrays.asList(capital));
			break;
		case NUMBER:
			temp.addAll(Arrays.asList(number));
			break;
		case LETTER_CAPITAL:
			temp.addAll(Arrays.asList(lowercase));
			temp.addAll(Arrays.asList(capital));
			break;
		case LETTER_NUMBER:
			temp.addAll(Arrays.asList(lowercase));
			temp.addAll(Arrays.asList(number));
			break;
		case CAPITAL_NUMBER:
			temp.addAll(Arrays.asList(capital));
			temp.addAll(Arrays.asList(number));
			break;
		case LETTER_CAPITAL_NUMBER:
			temp.addAll(Arrays.asList(lowercase));
			temp.addAll(Arrays.asList(capital));
			temp.addAll(Arrays.asList(number));
			break;
		}
		for (int i = 0; i < num; i++) {
			code.append(temp.get(random.nextInt(temp.size())));
		}
		return code.toString();
	}
//	public static void main(String[] args) {
//		System.out.println(RandomUtil.getRandom(10, RandomUtil.TYPE.LETTER_CAPITAL));
//	}
}