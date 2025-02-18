package com.portfolio.service;

import com.portfolio.constant.Role;
import com.portfolio.entity.Member;
import com.portfolio.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(memberId)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return User.builder()
            .username(member.getMemberId())
            .password(member.getPassword())
            .roles(member.getMemberStatus().toString())
            .build();
    }

    @PostConstruct
    public void init() {
        // 초기 관리자 계정 생성
        if (!memberRepository.existsByMemberId("admin")) {
            Member admin = new Member();
            admin.setMemberId("admin");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setMemberStatus(Role.ADMIN);
            memberRepository.save(admin);
        }
    }
}
