package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.Region;

import java.util.List;
import java.util.Optional;

public interface IRegionRepository {
    Optional<List<Region>> getAllRegions();
    Optional<Region> getRegionById(Integer id);
    Region saveRegion(Region region);
    void removeRegion(Integer id);
}
