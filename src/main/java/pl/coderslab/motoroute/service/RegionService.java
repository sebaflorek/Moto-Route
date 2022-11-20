package pl.coderslab.motoroute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.motoroute.entity.Region;
import pl.coderslab.motoroute.repository.RegionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    public void add(Region region) {
        regionRepository.save(region);
    }

    public Region findById(long id) {
        return regionRepository.findById(id).orElse(null);
    }

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public void update(Region region) {
        regionRepository.save(region);
    }

    public void deleteById(long id) {
        regionRepository.deleteById(id);
    }


}
