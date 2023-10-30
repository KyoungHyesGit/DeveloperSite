package com.dogsole.developersite.adviceBoard.repository;

import com.dogsole.developersite.adviceBoard.entity.AdviceBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface AdviceBoardRepository extends JpaRepository<AdviceBoard, Long> {
    Page<AdviceBoard> findByTitleContaining(String searchKeyword, Pageable pageable);
    Page<AdviceBoard> findByWriterContaining(String writer, Pageable pageable);


    public Page<AdviceBoard> findAllByOrderByRegDateAsc(Pageable pageable);

    public Page<AdviceBoard> findAllByOrderByRegDateDesc(Pageable pageable);

    public Page<AdviceBoard> findAll(Pageable pageable);

    public Page<AdviceBoard> findAllByOrderByViewsDesc(Pageable pageable);

    public Page<AdviceBoard> findAllByOrderByViewsAsc(Pageable pageable);
    Page<AdviceBoard> findByCategory(String category, Pageable pageable);
    public Page<AdviceBoard> findByCategoryAndTitleContaining(String category, String searchKeyword, Pageable pageable);

}
