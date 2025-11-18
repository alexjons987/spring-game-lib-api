package se.alexjons.gamelib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.alexjons.gamelib.entity.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
