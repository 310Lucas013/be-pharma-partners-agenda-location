package com.pharma.location.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.stream.Location;

public interface LocationRepository  extends JpaRepository<Location, Long> {
}