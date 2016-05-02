package com.weixin.platform.service;

import com.weixin.platform.domain.Process;
import com.weixin.platform.domain.state.*;
import com.weixin.platform.wxtools.DateUtil;
import com.weixin.platform.wxtools.JsonUtil;
import com.weixin.platform.wxtools.Tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruanzf on 2015/4/23.
 */
public class StateService extends BaseService {

    public ExecEnum getState(String uname) {
        if(jedis.hexist(uname, Tools.STATE)) {
            String state = jedis.hget(uname, Tools.STATE);
            if(state.equals(ExecEnum.DONE.getName())) {
                jedis.delete(uname);
                return ExecEnum.SHUNT;
            }else {
                return ExecEnum.getExe(state);
            }
        }else {
            return ExecEnum.SHUNT;
        }
    }

    public void refresh(Process process) {
        Map<String, String> maps = new HashMap<String, String>();
        try {
            String value = JsonUtil.toJson(process);
            maps.put(Tools.STATE, process.getState());
            maps.put(Tools.SELL_TYPE, process.getSellType());
            maps.put(Tools.ITEM, process.getTradeType());
            maps.put(Tools.OPERATE, value);
            jedis.hmSet(process.getName(), maps);
            if(process.getState().equals(ExecEnum.DONE.getName())) {
                jedis.delete(process.getName());
            }else {
                jedis.expire(process.getName(), Tools.EXPIRE_TIME);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refresh(ExecEnum execEnum, String name) {
        if(execEnum.equals(ExecEnum.DONE)) {
            jedis.delete(name);
        }else {
            jedis.expire(name, Tools.EXPIRE_TIME);
            jedis.hset(name, Tools.STATE, execEnum.getName());
        }
    }

    public void manual(String name, String type) {
        jedis.hset(name, Tools.MANUAL, type);
    }

    public int guide(String name) {
        String state = jedis.hget(name, Tools.MANUAL);
        if(state == null) {
            return -1;
        }
        try{
            int type = Integer.parseInt(state);
            return type;
        }catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String sellType(String name) {
        String sellType = jedis.hget(name, Tools.SELL_TYPE);
        return sellType;
    }

    public ExecEnum shunt(String content) {
        if(QueryEnum.me(content)) {
            return ExecEnum.QUERY;
        }else if(SellEnum.me(content)) {
            SellEnum sellEnum = SellEnum.getTrade(content);
            operateCount(sellEnum.getCode());
            return SellEnum.getExec(sellEnum);
        }else if(ShuntEnum.me(content)) {
            return ShuntEnum.shunt(content);
        }else {
            return ExecEnum.LUCENCY;
        }
    }

    public Process getProcess(String uname) {
        String value = jedis.hget(uname, Tools.OPERATE);
        try {
            Process process = JsonUtil.fromJson(value, Process.class);
            return process;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void operateCount(String key) {
        String date = DateUtil.dayPattern(System.currentTimeMillis());
        if(!jedis.hexist(key, date)) {
            jedis.hset(key, date, "0");
        }
        jedis.hincrby(key, date, 1L);
    }
}
