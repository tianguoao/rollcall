package com.tga.rollcall.dao;

import com.tga.rollcall.entity.Student;
import com.tga.rollcall.entity.StudentExample;
import org.springframework.stereotype.Repository;

/**
 * StudentMapper继承基类
 */
@Repository
public interface StudentMapper extends MyBatisBaseDao<Student, Long, StudentExample> {
}