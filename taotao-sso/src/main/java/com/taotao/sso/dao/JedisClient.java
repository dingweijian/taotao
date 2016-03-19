package com.taotao.sso.dao;

public interface JedisClient {

	String get(String key);
	String set(String key, String value);
	String hget(String hkey, String key);
	long hset(String hkey, String key, String value);
	long incr(String key);
	/**
	 * 设置过期时间
	 * @param key
	 * @param second
	 * @return
	 */
	long expire(String key, int second);
	/**
	 * 查看当前key什么时候到期，-1表示永久性，-2表示已经过期
	 * @param key
	 * @return
	 */
	long ttl(String key);
	
	long del(String key);
	long hdel(String hkey, String key);
	
}
