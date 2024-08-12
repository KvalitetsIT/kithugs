package dk.kvalitetsit.hello.dao;

import dk.kvalitetsit.hello.dao.entity.HelloEntity;

import java.util.List;

public interface HelloDao {
    void insert(HelloEntity helloEntity);

    List<HelloEntity> findAll();
}