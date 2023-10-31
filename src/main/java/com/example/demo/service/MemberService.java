package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
//import com.example.demo.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public String save (MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        Optional<MemberEntity> existingMember = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (existingMember.isPresent()) {
            return "이미 존재하는 회원입니다.";
        }
        memberRepository.save(memberEntity);
        return null;
    }

    public MemberDTO login (MemberDTO memberDTO) {
        Optional<MemberEntity> byEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
            if (byEmail.isPresent()) {
                // 조회 결과가 있는 경우
                MemberEntity memberEntity = byEmail.get();
                if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                    // 비밀번호 일치
                    // entity -> dto 변환 후 리턴
                    MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                    return dto;
                } else {
                    // 비밀번호 불일치
                    return null;
                }
            }
            else {
                return null;
            }
    }


/*
    //전체 회원 조회
    public List<MemberEntity> findMembers() {
        return memberRepository.findAll();
    }
*/

   /* public Optional <MemberEntity> findOne(Long Mno){
        return memberRepository.findByMno(Mno);
    }*/
}




