package com.aat.Library_Management_System.services;

import com.aat.Library_Management_System.dto.MemberDTO;
import com.aat.Library_Management_System.entities.Member;
import com.aat.Library_Management_System.mappers.MemberMapper;
import com.aat.Library_Management_System.repository.MemberRepository;
import com.aat.Library_Management_System.services.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServices implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberDTO createMember(MemberDTO dto) {
        Member member = MemberMapper.toEntity(dto);
        return MemberMapper.toDTO(memberRepository.save(member));
    }

    @Override
    public MemberDTO updateMember(Long id, MemberDTO dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));

        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setMembershipType(dto.getMembershipType());
        member.setMembershipDate(dto.getMembershipDate());

        return MemberMapper.toDTO(memberRepository.save(member));
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        return memberRepository.findById(id)
                .map(MemberMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(MemberMapper::toDTO)
                .collect(Collectors.toList());
    }
}
