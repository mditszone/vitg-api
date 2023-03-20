package com.vitg.repository;

import com.vitg.entity.Enquires;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnquiresRepository extends JpaRepository<Enquires, Integer> {
}
