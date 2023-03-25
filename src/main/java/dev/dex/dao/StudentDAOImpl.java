package dev.dex.dao;

import dev.dex.entity.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Repository
public class StudentDAOImpl implements StudentDAO {
    private EntityManager entityManager;

    // Dependency Injection via Constructor Injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        // retrieve/read from database using the primary key
        // in this example, retrieve Student with primary key: 1
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
//        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE firstName='Daffy' OR lastName='Doe'", Student.class);
//        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE email LIKE '%mit%'", Student.class);
//        TypedQuery<Student> query = entityManager.createQuery("FROM Student ORDER BY lastName", Student.class);
        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);
        query.setParameter("theData", lastName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public int updateAll() {
        int rowsUpdated = entityManager.createQuery("UPDATE Student SET lastName='Tester'").executeUpdate();
        return rowsUpdated;
    }

    @Override
    @Transactional
    public void delete(int id) {
        Student student = entityManager.find(Student.class, id);
        if (student == null) return;

        // delete the student
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public void deleteByLastName(String lastName) {
        Query query = entityManager.createQuery("DELETE FROM Student WHERE lastName=:theData");
        query.setParameter("theData", lastName);
        int rowsDeleted = query.executeUpdate();
    }

    @Override
    @Transactional
    public int deleteAll() {
        int rowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return rowsDeleted;
    }
}
