package dding.band.service;


import dding.band.config.eums.BandPosition;
import dding.band.entity.BandMember;
import dding.band.repository.BandMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class BandMemberService {

    private final BandMemberRepository bandMemberRepository;

    //  멤버 추가
    public void addMember(String bandId, String userId, BandPosition position) {
        // 중복 방지
        if (bandMemberRepository.existsByBandIdAndUserId(bandId, userId)) {
            throw new IllegalArgumentException("이미 밴드에 가입된 사용자입니다.");
        }

        BandMember member = BandMember.builder()
                .bandId(bandId)
                .userId(userId)
                .position(position)
                .joinedAt(LocalDateTime.now())
                .build();

        bandMemberRepository.save(member);
    }

    //  밴드 멤버 전체 조회
    public List<BandMember> getMembersByBand(String bandId) {
        return bandMemberRepository.findByBandId(bandId);
    }

    //  특정 유저가 속한 밴드 목록
    public List<BandMember> getBandsByUser(String userId) {
        return bandMemberRepository.findByUserId(userId);
    }

    //  밴드 탈퇴 (멤버 삭제)
    public void removeMember(Long memberId) {
        bandMemberRepository.deleteById(memberId);
    }
}