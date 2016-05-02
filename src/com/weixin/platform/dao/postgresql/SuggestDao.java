package com.weixin.platform.dao.postgresql;

import com.weixin.platform.domain.Suggest;
import com.weixin.platform.domain.state.ResponseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Created by ruanzf on 2015/4/21.
 */
public class SuggestDao {
    private NamedParameterJdbcTemplate readTemplate = DbOperator.getReadJdbc();
    private NamedParameterJdbcTemplate writeTemplate = DbOperator.getWriteJdbc();

    public ResponseEnum save(Suggest suggest) {
        int len = suggest.getDetail().length();
        if(len > 128) {
            return ResponseEnum.TO_LONG;
        }
        String sql = "insert into suggest" +
                " (owner, school, detail, createtime)" +
                " values (:owner, :school, :detail, :createtime)";
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(suggest);
        int result = writeTemplate.update(sql, parameterSource);
        if(result > 0){
            return ResponseEnum.SUGGEST;
        }
        return ResponseEnum.ERROR;
    }


    public static void main(String[] args) {
        SuggestDao dao = new SuggestDao();
        Suggest suggest = new Suggest();
        suggest.setDetail("jfeiwsao");
        suggest.setOwner("fgesar");
        dao.save(suggest);
    }
}
