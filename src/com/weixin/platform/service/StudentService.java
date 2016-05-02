package com.weixin.platform.service;

import com.weixin.platform.dao.postgresql.StudentDao;
import com.weixin.platform.domain.Owner;
import com.weixin.platform.domain.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/2.
 */
public class StudentService extends BaseService{

    private StudentDao studentDao;

    public StudentService() {
        this.studentDao = new StudentDao();
    }

    public String save(Student student) {
        return studentDao.save(student);
    }

    public Student getByName(String name) {
        return studentDao.getByName(name);
    }

    public List<Owner> getByProductId(List<Integer> ids) {
        return studentDao.getByProductId(ids);
    }

    public Student updateOrSave(Student student) {
        return studentDao.updateOrSave(student);
    }

}
