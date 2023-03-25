package dev.dex.dao;

import dev.dex.entity.*;

import java.util.*;

public interface StudentDAO {
    void save(Student student);

    Student findById(int id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(Student student);

    int updateAll();

    void delete(int id);

    void deleteByLastName(String lastName);

    int deleteAll();
}
