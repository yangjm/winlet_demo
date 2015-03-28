package com.aggrepoint.demo.svc;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import com.aggrepoint.demo.dao.SysRightCatDao;
import com.aggrepoint.demo.domain.SysRightCat;

/**
 * Service interface for SysRightCat
 *
 * @author Jim
 */
public interface SysRightCatService extends SysRightCatDao {
	public HashMap<Integer, Collection<SysRightCat>> getCatsForId(
			HashMap<Integer, HashSet<Integer>> rightsById);
}