package com.leo.utils;

import java.text.MessageFormat;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SampleUtils {

	public static void main(String[] args) {
//		String res = Normalizer.normalize("ｺﾚﾊーﾃｽﾄﾃﾞｽ～$'<>",
//				Normalizer.Form.NFKC);
//		System.out.println(res);

		List<Long> list = Arrays.asList(11L, 2L, 3L,11L,3L,2L);
//		List<List<Integer>> test = Arrays.asList(list, list, list);
//
//		System.out.println(test.stream().count());
//		System.out.println(test.stream().flatMap(l -> l.stream()).count());

		Iterator<Long> it = list.iterator();
		if (it.hasNext()) {
			it.next();
		}
		//it.forEachRemaining(System.out::println);
		
		list.stream().distinct().forEach(System.out::println);
		
		String s = MessageFormat.format("aaa%s", "sss");
		System.out.println(s);
		
		
	}

}
