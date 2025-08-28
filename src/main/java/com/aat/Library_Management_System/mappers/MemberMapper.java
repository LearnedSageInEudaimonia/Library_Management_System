package com.aat.Library_Management_System.mappers;

import com.aat.Library_Management_System.dto.MemberDTO;
import com.aat.Library_Management_System.entities.Member;

public class MemberMapper {

    public static MemberDTO toDTO(Member member) {
        if (member == null) return null;
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .membershipType(member.getMembershipType())
                .membershipDate(member.getMembershipDate())
                .build();
    }

    public static Member toEntity(MemberDTO dto) {
        if (dto == null) return null;
        return Member.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .membershipType(dto.getMembershipType())
                .membershipDate(dto.getMembershipDate())
                .build();
    }
}
