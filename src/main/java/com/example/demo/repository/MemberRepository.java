package com.example.demo.repository;

import com.example.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByKey(Long key);
    Optional<Member> findById(String id);
    Optional<Member> findByEmail(String email);

    Optional<Member> findByName(String name);

    List<Member> findAll();

}
