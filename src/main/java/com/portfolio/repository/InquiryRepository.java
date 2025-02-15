package com.portfolio.repository;

import com.portfolio.entity.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByPhone(String phone);
    List<Inquiry> findByEmail(String email);

    //페이징 및 리스트 가져오기 (등록일 기준 내림차순)
    Page<Inquiry> findAllByOrderByRegTimeDesc(Pageable pageable);
}
