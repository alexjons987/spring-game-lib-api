package se.alexjons.gamelib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.alexjons.gamelib.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
