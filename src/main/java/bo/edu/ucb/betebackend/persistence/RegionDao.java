package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.Region;
import bo.edu.ucb.betebackend.domain.repository.IRegionRepository;
import bo.edu.ucb.betebackend.persistence.dao.RegionRepository;
import bo.edu.ucb.betebackend.persistence.entity.RegionEntity;
import bo.edu.ucb.betebackend.persistence.mapper.RegionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegionDao implements  IRegionRepository {
    final private RegionRepository regionRepository;
    final private RegionMapper regionMapper;

    public RegionDao(RegionRepository regionRepository, RegionMapper regionMapper) {
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    @Override
    public Optional<List<Region>> getAllRegions() {
        List<RegionEntity> regionEntities = regionRepository.findAll();
        return Optional.ofNullable(regionMapper.toRegionList(regionEntities));
    }

    @Override
    public Optional<Region> getRegionById(Integer id) {
        return regionRepository.findById(id).map(regionMapper::toRegion);
    }

    @Override
    public Region saveRegion(Region region) {
        return regionMapper.toRegion(regionRepository.save(regionMapper.toRegionEntity(region)));
    }

    @Override
    public void removeRegion(Integer id) {
        regionRepository.deleteById(id);
    }
}
