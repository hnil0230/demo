package com.example.demo.repository;

import com.example.demo.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    Optional<MemberEntity> findByMemberEmail(String MemberEmail);

    //MemberEntity save(MemberEntity memberEntity);
    /*Optional<MemberEntity> findByMno(Long Mno);
    Optional<MemberEntity> findByMId(String Mid);
    Optional<MemberEntity> findByMEmail(String Memail);

    Optional<MemberEntity> findByMName(String Mname);

    List<MemberEntity> findAll();*/

}
