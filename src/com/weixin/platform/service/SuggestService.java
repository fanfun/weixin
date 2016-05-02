package com.weixin.platform.service;

import com.weixin.platform.dao.postgresql.ProductDao;
import com.weixin.platform.dao.postgresql.SuggestDao;
import com.weixin.platform.domain.Product;
import com.weixin.platform.domain.Suggest;
import com.weixin.platform.domain.state.ProductEnum;
import com.weixin.platform.domain.state.ResponseEnum;

/**
 * Created by Administrator on 2015/5/2.
 */
public class SuggestService extends BaseService {

    private SuggestDao suggestDao;

    public SuggestService() {
        this.suggestDao = new SuggestDao();
    }

    public ResponseEnum save(String detail, String owner) {
        Suggest suggest = new Suggest();
        suggest.setOwner(owner);
        suggest.setDetail(detail);
        return suggestDao.save(suggest);
    }

}
