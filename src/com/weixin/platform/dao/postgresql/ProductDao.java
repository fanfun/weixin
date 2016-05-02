package com.weixin.platform.dao.postgresql;

import com.google.common.collect.Lists;
import com.weixin.platform.domain.Owner;
import com.weixin.platform.domain.Product;
import com.weixin.platform.domain.state.ResponseEnum;
import com.weixin.platform.domain.state.ProductStateEnum;
import com.weixin.platform.wxtools.DateUtil;
import com.weixin.platform.wxtools.PictureUtil;
import com.weixin.platform.wxtools.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by ruanzf on 2015/4/21.
 */
public class ProductDao {
    private final static Logger logger = LoggerFactory.getLogger(ProductDao.class);

    private NamedParameterJdbcTemplate readTemplate = DbOperator.getReadJdbc();
    private NamedParameterJdbcTemplate writeTemplate = DbOperator.getWriteJdbc();

    protected String tableName = "product";

    public Integer save(Product product) {
        String sql = "insert into " +
                tableName +
                " (name, owner, describe, status, type," +
                " photo, school, item, createtime, nick, phone) values" +
                " (:name, :owner, :describe, :status, :type," +
                " :photo, :school, :item, :createtime, :nick, :phone) RETURNING id";
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(product);
        Integer id = writeTemplate.queryForObject(sql, parameterSource, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt(1);
            }
        });
        return id;
    }

    public List<Product> load() {
        String sql = "select id, " +
                " photo, createtime" +
                " from "
                + tableName +
                " where id > 433" +
                " and id != 443" +
                " order by id desc";

        Map<String, Object> maps = new HashMap<String, Object>();
        List<Product> list = readTemplate.query(sql, maps, new ProductHandle());
        return list;
    }

    public Product getById(int id) {
        String sql = "select id, name, owner," +
                " describe, photo, school," +
                " item, createtime, status, type" +
                " from "
                + tableName +
                " where id = :id";

        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("id", id);
        List<Product> list = readTemplate.query(sql, maps, new ProductHandle());
        if(list.isEmpty()) {
            return null;
        }else {
            return list.get(0);
        }
    }

    private class ProductHandle implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int i) throws SQLException {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setCreatetime(rs.getLong("createtime"));//, "yyyy-MM-dd HH:mm"));

            String photo = rs.getString("photo");
            product.setPhoto(photo);
            return product;
        }
    }

    public ResponseEnum soldOut(int id, String name) {
        String sql = "update product set status = :status where id = :id and owner = :owner";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", ProductStateEnum.SOLDOUT.getCode());
        params.put("id", id);
        params.put("owner", name);
        Integer result = writeTemplate.update(sql, params);
        if(result > 0) {
            return ResponseEnum.SOLD_OUT;
        }
        Product product = getById(id);
        if(product == null) {
            return ResponseEnum.NOGOODS;
        }else if(!product.getOwner().equals(name)) {
            return ResponseEnum.NOT_YOURS;
        }else {
            return ResponseEnum.ERROR;
        }
    }

    public List<Owner> getByProductId(List<Integer> ids) {
        String query = "";
        Map<String, Object> maps = new HashMap<String, Object>();
        if(ids.size() == 1) {
            query = "p.id = :id";
            maps.put("id", ids.get(0));
        }else {
            query = "p.id in(:id)";
            maps.put("id", ids);
        }

        //3 = ProductStateEnum.OK
        String sql = "select p.nick, p.phone, p.type, p.id from product as p" +
                " where p.status = 3 and " + query;

        List<Owner> owners = readTemplate.query(sql, maps, new RowMapper<Owner>() {
            @Override
            public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
                Owner owner = new Owner();
                owner.setId(rs.getInt("id"));
                owner.setType(rs.getInt("type"));
                owner.setPhone(rs.getString("phone"));
                owner.setNick(rs.getString("nick"));
                return owner;
            }
        });
        return owners;
    }

    public void setPicture(int id, String photo) {
        String sql = "update product set photo = :photo where id = :id";
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("id", id);
        maps.put("photo", photo);
        writeTemplate.update(sql, maps);
    }

    public void savaProductItem(int productId, String categorys) {
        if(categorys.equals("")) {
            return;
        }
        List<Integer> itemList = new ArrayList<Integer>();
        String[] typeArray = categorys.split("/");

        int size = 3;
        int type = 0;

        if(typeArray.length < size) {
            size = typeArray.length;
        }

        try {
            for(int i=0; i < size; i++) {
                String typeStr = typeArray[i];
                type = Integer.parseInt(typeStr);
                saveItem(productId, type);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveItem(int productId, int item) {
        String sql = "INSERT INTO category (product_id, school, item) VALUES (:productId, :school, :item)";
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("productId", productId);
        maps.put("school", -1);
        maps.put("item", item);
        writeTemplate.update(sql, maps);
    }

    public void saveItem(String products, int item) {

        String[] proArray = products.split(",");
        for(String proStr : proArray) {
            int productId = Integer.parseInt(proStr);
            saveItem(productId, item);
        }

    }

    public static void main(String[] args) {
        String products = "268,296,777,774,652,388,376,362";
        int item = 11;
        new ProductDao().saveItem(products, item);
    }

}
