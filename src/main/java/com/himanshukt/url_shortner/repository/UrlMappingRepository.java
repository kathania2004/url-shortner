package com.himanshukt.url_shortner.repository;

import com.himanshukt.url_shortner.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlMappingRepository extends JpaRepository<UrlMapping,Long> {

    Optional<UrlMapping> findByshortCode(String shortCode);
}
