package com.dogsole.developersite.jobPost.repository;

import com.dogsole.developersite.jobPost.entity.JobPostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPostEntity, Long> {
    // 전체공고목록
    Page<JobPostEntity> findAll(Pageable pageable);

    Page<JobPostEntity> findByVenderEntity_VenderId(Long venderId, Pageable pageable);

    JobPostEntity findByTempId(Long tempId);

    //전체검색
    @Query("SELECT j FROM JobPostEntity j " +
            "JOIN vender v ON j.id = v.venderId " +
            "WHERE j.title LIKE %:keyword% OR v.venderName LIKE %:keyword%")
    Page<JobPostEntity> findByTitleContaining(@Param("keyword") String keyword, Pageable pageable);

    // 등록순으로 공고 목록 조회
    @Query("SELECT j FROM JobPostEntity j " +
            "JOIN vender v ON j.id = v.venderId " +
            "WHERE j.title LIKE %:keyword% OR v.venderName LIKE %:keyword% order by j.createDt asc")
    Page<JobPostEntity> findAllByOrderByCreateDtAsc(@Param("keyword") String keyword,Pageable pageable);

    // 마감일순으로 공고 목록 조회
    @Query("SELECT j FROM JobPostEntity j " +
            "JOIN vender v ON j.id = v.venderId " +
            "WHERE j.title LIKE %:keyword% OR v.venderName LIKE %:keyword% order by j.endTime asc")
    Page<JobPostEntity> findByEndTimeSearch(@Param("keyword") String keyword,Pageable pageable);


}
