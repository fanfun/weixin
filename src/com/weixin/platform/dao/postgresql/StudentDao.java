package com.weixin.platform.dao.postgresql;

import com.weixin.platform.domain.Owner;
import com.weixin.platform.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruanzf on 2015/4/21.
 */
public class StudentDao {
    private final static Logger logger = LoggerFactory.getLogger(StudentDao.class);

    private NamedParameterJdbcTemplate readTemplate = DbOperator.getReadJdbc();
    private NamedParameterJdbcTemplate writeTemplate = DbOperator.getWriteJdbc();

    public String save(Student student) {
        String sql = "insert into student" +
                " (name, nick, school, phone, time, visit)" +
                " values (:name, :nick, :school, :phone, :time, :visit) RETURNING name";
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(student);
        String name = writeTemplate.queryForObject(sql, parameterSource, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString(1);
            }
        });
        return name;
    }

    public Student delete(String name) {
        String sql = "DELETE FROM student WHERE name = :name RETURNING name, nick, school, phone, time, visit";
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("name", name);
        List<Student> students = writeTemplate.query(sql, maps, new StudentHandle());
        if(!students.isEmpty()) {
            return students.get(0);
        }else {
            return null;
        }
    }

    public Student updateOrSave(Student student) {
        Student old = delete(student.getName());
        if(old == null) {
            old = student;
        }
        old.setNick(student.getNick());
        old.setPhone(student.getPhone());
        old.setSchool(student.getSchool());
        save(student);
        return old;
    }
    public Student getByName(String name) {
        String sql = "select name, nick, school, phone, time, visit from student where name = :name";
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("name", name);
        List<Student> student = readTemplate.query(sql, maps, new StudentHandle());
        if(!student.isEmpty()) {
            return student.get(0);
        }
        return null;
    }

    private class StudentHandle implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student result = new Student();
            result.setVisit(resultSet.getInt("visit"));
            result.setSchool(resultSet.getInt("school"));
            result.setName(resultSet.getString("name"));
            result.setTime(resultSet.getString("time"));
            result.setPhone(resultSet.getString("phone"));
            result.setNick(resultSet.getString("nick"));
            return result;
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
        String sql = "select s.nick, s.phone, p.id from student as s, product as p" +
                " where p.owner = s.name and p.status = 3 and " + query;

        List<Owner> owners = readTemplate.query(sql, maps, new RowMapper<Owner>() {
            @Override
            public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
                Owner owner = new Owner();
                owner.setId(rs.getInt("id"));
                owner.setPhone(rs.getString("phone"));
                owner.setNick(rs.getString("nick"));
                return owner;
            }
        });
        return owners;
    }



    public static void main(String[] args) {
        StudentDao dao = new StudentDao();
        Student student = new Student();
        student.setName("aaa");
        dao.updateOrSave(student);

    }
}
