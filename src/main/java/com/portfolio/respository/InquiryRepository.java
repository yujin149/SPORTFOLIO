package com.portfolio.respository;

import com.portfolio.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByPhone(String phone);
    List<Inquiry> findByEmail(String email);


}
