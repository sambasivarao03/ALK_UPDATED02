package com.alk.repository;

import com.alk.model.AadhaarData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AadhaarDataRepository extends JpaRepository<AadhaarData, Long> {

    List<AadhaarData> findByPii_PiiPk(String piiPk);
}
