package com.pharma.location.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.Location;

@Repository
public interface LocationRepository  extends JpaRepository<Location, Long> {
}