package pl.coderslab.motoroute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.motoroute.entity.Region;
import pl.coderslab.motoroute.entity.Type;
import pl.coderslab.motoroute.repository.RegionRepository;
import pl.coderslab.motoroute.repository.TypeRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public void add(Type type) {
        typeRepository.save(type);
    }

    public Type findById(long id) {
        return typeRepository.findById(id).orElse(null);
    }

    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    public void update(Type type) {
        typeRepository.save(type);
    }

    public void deleteById(long id) {
        typeRepository.deleteById(id);
    }


}
