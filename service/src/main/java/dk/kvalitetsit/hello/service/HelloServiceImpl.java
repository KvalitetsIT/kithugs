package dk.kvalitetsit.hello.service;

import dk.kvalitetsit.hello.dao.HelloDao;
import dk.kvalitetsit.hello.dao.entity.HelloEntity;
import dk.kvalitetsit.hello.service.model.HelloServiceInput;
import dk.kvalitetsit.hello.service.model.HelloServiceOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import java.time.ZonedDateTime;

@Service
public class HelloServiceImpl implements HelloService {
    private final HelloDao helloDao;

    public HelloServiceImpl(HelloDao helloDao) {
        this.helloDao = helloDao;
    }   

    @Override
    public List<HelloServiceOutput> helloServiceGetAll() {
        var dbEntries = helloDao.findAll();
        var result = dbEntries.stream()
            .map(dbEntry -> new HelloServiceOutput(dbEntry.name(), ZonedDateTime.now()))
            .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<HelloServiceOutput> helloServiceGetByName(HelloServiceInput input) {
        var name = input.name();
        var dbEntries = helloDao.findByName(name);
        var result = dbEntries.stream()
            .map(dbEntry -> new HelloServiceOutput(dbEntry.name(), ZonedDateTime.now()))
            .collect(Collectors.toList());
        return result;
    }


    @Override
    public HelloServiceOutput helloServicePost(HelloServiceInput input) {
        var helloEntity = HelloEntity.createInstance(input.name());
        helloDao.insert(helloEntity);

        return new HelloServiceOutput(helloEntity.name(), ZonedDateTime.now());
    }
}
