package com.alk.repository;

import com.alk.model.PanData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PanDataRepository extends JpaRepository<PanData, Long> {

    List<PanData> findByPii_PiiPk(String piiPk);
}
