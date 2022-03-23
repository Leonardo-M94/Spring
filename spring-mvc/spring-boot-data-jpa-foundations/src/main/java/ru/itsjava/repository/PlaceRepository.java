package ru.itsjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsjava.domain.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
