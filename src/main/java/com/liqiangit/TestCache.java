package com.liqiangit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Before;
import org.junit.Test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class TestCache {
	AtomicLong idIdx = new AtomicLong();
	CacheManager manager;// 缓存管理器
	Cache cache;// 本程序操作的缓存对象

	@Before
	public void init() {
		manager = CacheManager.newInstance(getClass().getResourceAsStream("/ehcache-jgroups.xml"));// new
																									// CacheManager(fileName);
		System.out.println(manager.getActiveConfigurationText());
		// 取出所有的cacheName
		String names[] = manager.getCacheNames();
		for (String name : names) {
			System.out.println(name);// 输出所有Cache名称
		}
		cache = manager.getCache("book");// 得到本程序操作的cache
	}

	@Test
	public void test8() throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100_0000; i++) {
			Element element = cache.get(i + "");
			if (element == null) {
				String[] libraryName = new String[] { "name" };
				String featureId = UUIDUtil.getUUID();
				String peopleId = UUIDUtil.getUUID();
				Row row = new Row();
				row.setLibraryName(libraryName);
				row.setFeatureId(featureId);
				row.setPeopleId(peopleId);
				row.setBioSubtype(1);
				row.setFaceId(idIdx.incrementAndGet());
				row.setAddress(new Integer[] { 0, i });
				row.setUpdatedOn(System.currentTimeMillis());
				cache.put(new Element(i + "", row));
			}
		}
		System.out.println(System.currentTimeMillis() - start);
		Thread.sleep(Integer.MAX_VALUE);
	}

	@Test
	public void test9() throws Exception {
		long start = System.currentTimeMillis();
		Map<Integer, Row> map = new HashMap<>();
		for (int i = 0; i < 100_0000; i++) {
			String[] libraryName = new String[] { "name" };
			String featureId = UUIDUtil.getUUID();
			String peopleId = UUIDUtil.getUUID();
			Row row = new Row();
			row.setLibraryName(libraryName);
			row.setFeatureId(featureId);
			row.setPeopleId(peopleId);
			row.setBioSubtype(1);
			row.setFaceId(idIdx.incrementAndGet());
			row.setAddress(new Integer[] { 0, i });
			row.setUpdatedOn(System.currentTimeMillis());
			map.put(i, row);
		}
		System.out.println(map.get(20));
		System.out.println(map.containsKey(20));
		System.out.println(System.currentTimeMillis() - start);
		Thread.sleep(Integer.MAX_VALUE);
	}
}
