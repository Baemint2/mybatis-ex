package com.gdu.prj.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gdu.prj.dto.StudentDto;

public class StudentDaoImpl implements StudentDao {
  
  private SqlSessionFactory factory = null;
  
  private static StudentDao studentdao = new StudentDaoImpl();
  private StudentDaoImpl() {
    try {
      String resource = "com/gdu/prj/config/mybatis-config.xml";
      InputStream in = Resources.getResourceAsStream(resource);
      factory = new SqlSessionFactoryBuilder().build(in);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static StudentDao getInstance() {
    return studentdao;
  }

  @Override
  public int insertStudent(StudentDto student) {
    SqlSession sqlSession = factory.openSession(false);
    int insertCount = sqlSession.insert("com.gdu.prj.dao.student_t.insertStudent", student);
    if(insertCount == 1) {
      sqlSession.commit();
    }
    sqlSession.close();
    return insertCount;
  }

  @Override
  public int updateStudent(StudentDto student) {
    SqlSession sqlSession = factory.openSession(false);
    int updateCount = sqlSession.update("com.gdu.prj.dao.student_t.updateStudent", student);
    if(updateCount == 1) {
      sqlSession.commit();
    }
    sqlSession.close();
    return updateCount;
  }

  @Override
  public int deleteStudent(int student_no) {
    SqlSession sqlSession = factory.openSession();
    int deleteCount = sqlSession.delete("com.gdu.prj.dao.student_t.deleteStudent", student_no);
    if(deleteCount == 1) {
      sqlSession.commit();
    }
    sqlSession.close();
    return deleteCount;
  }

  @Override
  public List<StudentDto> selectStudentList(Map<String, Object> params) {
    SqlSession sqlSession = factory.openSession();
    List<StudentDto> studentList = sqlSession.selectList("com.gdu.prj.dao.student_t.selectStudentList", params);
    sqlSession.close();
    return studentList;
  }

  @Override
  public int getStudentCount() {
    SqlSession sqlSession = factory.openSession();
    int total = sqlSession.selectOne("com.gdu.prj.dao.student_t.getStudentCount");
    sqlSession.close();
    return total;
  }

  @Override
  public StudentDto selectStudentByNo(int student_no) {
    SqlSession sqlSession = factory.openSession();
    StudentDto student = sqlSession.selectOne("com.gdu.prj.dao.student_t.selectStudentByNo", student_no);
    sqlSession.close();
    return student;
  }

}
