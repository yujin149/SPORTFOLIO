package com.portfolio.service;

import com.portfolio.dto.InquiryDto;
import com.portfolio.entity.Inquiry;
import com.portfolio.respository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InquiryService {
    @Autowired
    private InquiryRepository inquiryRepository;

    public void saveInquiry(InquiryDto inquiryDto) {
        Inquiry inquiry = new Inquiry();
        inquiry.setCompany(inquiryDto.getCompany());
        inquiry.setName(inquiryDto.getName());
        inquiry.setEmail(inquiryDto.getEmail());
        inquiry.setPhone(inquiryDto.getPhone());
        inquiry.setTitle(inquiryDto.getTitle());
        inquiry.setContent(inquiryDto.getContent());
        
        inquiryRepository.save(inquiry);
    }
    
    //페이징 처리 및 리스트 가져오기
    public Page<Inquiry> getInquiriesPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return inquiryRepository.findAllByOrderByRegTimeDesc(pageable);
    }

    //문의 상세 조회
    public Inquiry getInquiryById(Long id) {
        return inquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("문의를 찾을 수 없습니다."));
    }
}
