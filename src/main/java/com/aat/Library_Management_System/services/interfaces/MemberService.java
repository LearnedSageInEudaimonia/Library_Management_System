package com.aat.Library_Management_System.services.interfaces;

import com.aat.Library_Management_System.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    MemberDTO createMember(MemberDTO dto);
    MemberDTO updateMember(Long id, MemberDTO dto);
    void deleteMember(Long id);
    MemberDTO getMemberById(Long id);
    List<MemberDTO> getAllMembers();
}
