package com.aggrepoint.demo.svc.impl;

import java.util.Collection;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CommonServiceImpl {
	/**
	 * 批量建立多对多的关系。如果关系已经存在，则跳过不重复创建
	 * 
	 * @param parentA
	 * @param parentB
	 * @param existing
	 * @param getA
	 * @param getB
	 * @param create
	 * @return
	 */
	public static <T> int addManyToMany(Integer[] parentA, Integer[] parentB,
			Supplier<Collection<T>> existing, Function<T, Integer> getA,
			Function<T, Integer> getB, BiConsumer<Integer, Integer> create) {
		if (parentA == null || parentB == null || parentA.length == 0
				|| parentB.length == 0)
			return 0;

		Set<String> set = existing.get().stream()
				.map(p -> getA.apply(p) + "." + getB.apply(p))
				.collect(Collectors.toSet());

		int count = 0;
		for (int a : parentA)
			for (int b : parentB) {
				if (set.contains(a + "." + b)) // 已经存在，不要再创建
					continue;

				create.accept(a, b);
				count++;
			}

		return count;
	}

}
