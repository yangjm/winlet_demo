package com.aggrepoint.demo.svc.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.annotation.DefaultDao;
import com.aggrepoint.demo.dao.SysRightCatDao;
import com.aggrepoint.demo.domain.SysRight;
import com.aggrepoint.demo.domain.SysRightCat;
import com.aggrepoint.demo.svc.SysRightCatService;

/**
 * Service implementation for SysRightCat
 *
 * @author Jim
 */
@Service
@Transactional
public class SysRightCatServiceImpl implements SysRightCatService {
	@Autowired
	@DefaultDao
	private SysRightCatDao dao;

	@Override
	public HashMap<Integer, Collection<SysRightCat>> getCatsForId(
			HashMap<Integer, HashSet<Integer>> rightsById) {
		// 加载所有权限分类，以及其中包含的权限
		List<SysRightCat> allCats = dao.findAllWithRights();

		// { 通过权限ID映射到对应的权限对象
		HashMap<Integer, SysRight> rightMap = new HashMap<Integer, SysRight>();
		allCats.forEach(p -> p.getRights().forEach(p1 -> {
			rightMap.put(p1.getRightId(), p1);
		}));
		// }

		HashMap<Integer, Collection<SysRightCat>> ret = new HashMap<Integer, Collection<SysRightCat>>();

		for (Integer id : rightsById.keySet()) {
			// 已经找到的权限分类
			HashMap<Integer, SysRightCat> cats = new HashMap<Integer, SysRightCat>();

			rightsById.get(id).stream().map(rightMap::get).forEach(p -> {
				SysRightCat cat = cats.get(p.getRightCatId());
				if (cat == null) {
					cat = p.getCat().clone();
					cats.put(cat.getRightCatId(), cat);
				}

				cat.getRights().add(p);
			});

			ret.put(id, cats.values());
		}

		return ret;
	}
}