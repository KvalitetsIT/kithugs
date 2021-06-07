package dk.kvalitetsit.kithugs.dao;

import dk.kvalitetsit.kithugs.dao.entity.HelloEntity;

import java.util.List;

public interface HelloDao {
    void insert(HelloEntity helloEntity);

    List<HelloEntity> findAll();
}