package nz.co.cucumber.tstzrangetest;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityWithRangeRepository extends JpaRepository<EntityWithRange, UUID> {

    public EntityWithRange findByName(String name);
}
