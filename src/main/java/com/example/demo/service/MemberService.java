package com.example.demo.service;

import com.example.demo.dto.JoinRequest;
//import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
//import com.example.demo.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    public static MemberEntity getLoginUser;
    private final MemberRepository memberRepository;
//    private final BCryptPasswordEncoder encoder;



    /**
     * 회원가입
     */
//    public String save (MemberDTO memberDTO) {
//        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
//        Optional<MemberEntity> existingMember = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
//        if (existingMember.isPresent()) {
//            return "이미 존재하는 회원입니다.";
//        }
//        memberRepository.save(memberEntity);
//        return null;
//    }
    /**
     * Email 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkMemberEmailDuplicate(String MemberEmail) {
        return memberRepository.existsByMemberEmail(MemberEmail);
    }

    /**
     * 회원가입 기능
     * 화면에서 JoinRequest(loginId, password, nickname)을 입력받아 User로 변환 후 저장
     * 비밀번호를 암호화해서 저장
     * loginId, nickname 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void join(JoinRequest req) {
        MemberEntity memberEntity = req.toMemberEntity(req.getMemberPassword());
        memberRepository.save(memberEntity);
    }
    /**
     *  로그인 기능`
     *  화면에서 LoginRequest(loginId, password)을 입력받아 loginId와 password가 일치하면 User return
     *  loginId가 존재하지 않거나 password가 일치하지 않으면 null return
     */
    public MemberEntity login(LoginRequest req){
        Optional<MemberEntity> optionalMemberEntity = MemberRepository.findByMemberEmail(req.getLoginId());

        if(optionalMemberEntity.isEmpty()){
            return null;
        }
        MemberEntity MemberEntity = optionalMemberEntity.get();

        if(!MemberEntity.getMemberPassword().equals(req.getPassword())){
            return null;
        };
        return MemberEntity;
    }
    /**
     * userId(Long)를 입력받아 User을 return 해주는 기능
     * 인증, 인가 시 사용
     * userId가 null이거나(로그인 X) userId로 찾아온 User가 없으면 null return
     * userId로 찾아온 User가 존재하면 User return
     */
    public MemberEntity getLoginUserByMno(Long Mno) {
        Optional<MemberEntity> optionalUser = MemberRepository.findByMno(Mno);
        if (optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    /**
     * loginId(String)를 입력받아 User을 return 해주는 기능
     * 인증, 인가 시 사용
     * loginId가 null이거나(로그인 X) userId로 찾아온 User가 없으면 null return
     * loginId로 찾아온 User가 존재하면 User return
     */
    public MemberEntity getLoginUserByLoginId(String MemberEmail) {
        if(MemberEmail == null) return null;

        Optional<MemberEntity> optionalUser = MemberRepository.findByMemberEmail(MemberEmail);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }


//    public MemberDTO login (MemberDTO memberDTO) {
//        Optional<MemberEntity> byEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
//            if (byEmail.isPresent()) {
//                // 조회 결과가 있는 경우
//                MemberEntity memberEntity = byEmail.get();
//                if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
//                    // 비밀번호 일치
//                    // entity -> dto 변환 후 리턴
//                    MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
//                    return dto;
//                } else {
//                    // 비밀번호 불일치
//                    return null;
//                }
//            }
//            else {
//                return null;
//            }
//    }


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




