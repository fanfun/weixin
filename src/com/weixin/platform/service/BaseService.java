package com.weixin.platform.service;

import com.weixin.platform.dao.redis.RedisOperator;

/**
 * Created by ruanzf on 2015/4/23.
 */
public class BaseService {

    protected RedisOperator jedis;

    protected BaseService() {
        jedis = new RedisOperator();
    }


}
