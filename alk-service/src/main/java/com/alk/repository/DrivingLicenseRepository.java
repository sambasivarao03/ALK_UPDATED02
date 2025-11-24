package com.alk.repository;

import com.alk.model.DrivingLicense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrivingLicenseRepository extends JpaRepository<DrivingLicense, Long> {

    List<DrivingLicense> findByPii_PiiPk(String piiPk);
}
