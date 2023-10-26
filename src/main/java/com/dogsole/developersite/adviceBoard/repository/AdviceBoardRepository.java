package com.dogsole.developersite.adviceBoard.repository;

import com.dogsole.developersite.adviceBoard.entity.AdviceBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface AdviceBoardRepository extends JpaRepository<AdviceBoard, Long> {
    Page<AdviceBoard> findByTitleContaining(String searchKeyword, Pageable pageable);
}
