package com.portfolio.service;

import com.portfolio.dto.InquiryDto;
import com.portfolio.entity.Inquiry;
import com.portfolio.respository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    //문의하기 데이터 가져오기
    public List<Inquiry> getInquiry() {
        return inquiryRepository.findAll();
    }
}
