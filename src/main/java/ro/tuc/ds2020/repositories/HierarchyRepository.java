package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Employee;
import ro.tuc.ds2020.entities.Hierarchy;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HierarchyRepository extends JpaRepository<Hierarchy, UUID> {
    List<Hierarchy> findByBossId(UUID bossId);
    List<Hierarchy> findBySubordinateId(UUID bossId);
}
