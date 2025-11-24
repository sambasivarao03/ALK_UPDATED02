package com.alk.repository;

import com.alk.model.VoterData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoterDataRepository extends JpaRepository<VoterData, Long> {

    List<VoterData> findByPii_PiiPk(String piiPk);
}
