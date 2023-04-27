package com.example.lorem.dto;

import com.example.lorem.entity.MemberEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberAddress;
    private String memberPhone;
//    private Date memberBirth;

    //Entity -> DTO로 변환하는 메서드
    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberAddress(memberEntity.getMemberAddress());
        memberDTO.setMemberPhone(memberEntity.getMemberPhone());
//        memberDTO.setMemberBirth(memberEntity.getMemberBirth());

        return memberDTO;
    }


}
