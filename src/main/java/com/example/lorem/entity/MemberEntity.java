package com.example.lorem.entity;

import com.example.lorem.dto.MemberDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 50) //unique 제약조건 추가(중복X)
    private  String memberEmail;

    @Column(length = 20, nullable = false)
    private String memberPassword;

    @Column(length = 10, nullable = false)
    private String memberName;

    @Column(length = 50, nullable = false)
    private String memberAddress;

    @Column(length = 11, nullable = false)
    private String memberPhone;

//    @Column
//    private Date memberBirth;

    //DTO 객체 -> Entity로 변환시켜주는 메서드 생성
    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberAddress(memberDTO.getMemberAddress());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
//        memberEntity.setMemberBirth(memberDTO.getMemberBirth());

        return memberEntity;
    }
    //id포함
    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberAddress(memberDTO.getMemberAddress());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
//        memberEntity.setMemberBirth(memberDTO.getMemberBirth());

        return memberEntity;
    }
}