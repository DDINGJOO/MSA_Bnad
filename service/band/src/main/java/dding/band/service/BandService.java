package dding.band.service;


import dding.band.config.eums.BandPosition;
import dding.band.dto.request.BandCreateRequest;
import dding.band.dto.request.BandMemberJoinRequest;
import dding.band.dto.response.BandMemberResponse;
import dding.band.dto.response.BandResponse;
import dding.band.entity.Band;
import dding.band.entity.BandMember;
import dding.band.entity.BandPositionSlot;
import dding.band.repository.BandMemberRepository;
import dding.band.repository.BandPositionSlotRepository;
import dding.band.repository.BandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BandService {
    private  final BandRepository bandRepository;
    private final BandMemberRepository bandMemberRepository;
    private final BandPositionSlotRepository positionSlotRepository;

    @Transactional
    public void createBand(BandCreateRequest request) {
        Band band = BandCreateRequest.toBandEntity(request);
        List<BandPositionSlot> positionSlots = request.getPositions().stream()
                .map(positionCountRequest -> BandPositionSlot.builder()
                        .band(band)
                        .position(positionCountRequest.getPosition())
                        .requiredCount(positionCountRequest.getCount())
                        .isCloses(false)
                        .build())
                .toList();
        BandMember bandMember = BandMember.builder()
                .band(band)
                .confirmedAt(LocalDateTime.now())
                .userId(band.getLeaderId())
                .joinedAt(LocalDateTime.now())
                .description("LEADER")
                .position(BandPosition.ETC)
                .build();


        bandRepository.save(band);
        bandMemberRepository.save(bandMember);

        positionSlotRepository.saveAll(positionSlots);
    }


    public BandResponse getBandDetail(String bandId) {
        Band band = getBand(bandId);
        return BandResponse.from(band);
    }

    public void deleteBand(String bandId) {
        Band band = bandRepository.findById(bandId).orElseThrow(() -> new RuntimeException("Band not found"));
        bandMemberRepository.deleteAllByBand(band);
        bandRepository.deleteAllByBandId(bandId);
        bandRepository.delete(band);
    }


    public Page<BandMemberResponse> getBandMembers(String bandId, Pageable pageable) {
        Band band = getBand(bandId);
        return getBandMembers(band,pageable);
    }

    public Page<BandMemberResponse> getNotConfirmedBandMembers(String bandId, Pageable pageable) {
        Band band = getBand(bandId);
        return getNotConfirmedBandMembers(band, pageable);
    }





    //밴드 떠나기
    public boolean leaveBand(String bandId, String userId) {
        Band band = getBand(bandId);
        BandMember bandMember = getBandMember(bandId, userId);
        bandMemberRepository.delete(bandMember);
        return true;
    }



    private Page<BandMemberResponse> getBandMembers(Band band, Pageable pageable) {
        Page<BandMember> bandMembers = bandMemberRepository.findAllByBandAndIsConfirmed(band, true, pageable);
        List<BandMemberResponse> bandMemberResponses = bandMembers.getContent().stream()
                .map(BandMemberResponse::from)
                .toList();
        return new PageImpl<>(bandMemberResponses, pageable, bandMembers.getTotalElements());


    }

    //지원자 리스트
    private Page<BandMemberResponse> getNotConfirmedBandMembers(Band band, Pageable pageable) {
        Page<BandMember> bandMembers = bandMemberRepository.findAllByBandAndIsConfirmed(band, false, pageable);
        List<BandMemberResponse> bandMemberResponses = bandMembers.getContent().stream()
                .map(BandMemberResponse::from)
                .toList();
        return new PageImpl<>(bandMemberResponses, pageable, bandMembers.getTotalElements());

    }

    public void confirmed(String leaderId , String userId)
    {
        Band band = bandRepository.findByLeaderId(leaderId).orElseThrow(
                () -> new RuntimeException("Not BandLeader")
        );
        BandMember bandMember = bandMemberRepository.findByBandAndUserId(band, userId);
        bandMember.setIsConfirmed(true);
        bandMember.setConfirmedAt(LocalDateTime.now());
        bandMemberRepository.save(bandMember);

        //밴드 모집인원에 해당 포자션의 카운트를 -1 함
        BandPositionSlot bandPositionSlot = positionSlotRepository.findByBandAndPosition(band, bandMember.getPosition());
        bandPositionSlot.setRequiredCount(bandPositionSlot.getRequiredCount() - 1);
        positionSlotRepository.save(bandPositionSlot);
        //밴드 모집인원에 해당 포지션의 카운트가 0이 되면 모집 마감
        if(bandPositionSlot.getRequiredCount() == 0)
        {
            bandPositionSlot.setCloses(true);
            positionSlotRepository.save(bandPositionSlot);
        }

    }



    public Page<BandResponse> getBandList(Pageable pageable) {
        Page<Band> bandPage = bandRepository.findAll(pageable);
        List<BandResponse> bandResponses = bandPage.getContent().stream()
                .map(BandResponse::from)
                .toList();
        return new PageImpl<>(bandResponses, pageable, bandPage.getTotalElements());
    }

    public Page<BandResponse> getMyBandList(String userId, Pageable pageable) {
        List<Band> bands = getBandByUserId(userId);
        List<BandResponse> bandResponses = bands.stream()
                .map(BandResponse::from)
                .toList();
        return new PageImpl<>(bandResponses, pageable, bandResponses.size());
    }




    public boolean reject(String leaderId , String userId)
    {
        Band band = bandRepository.findByLeaderId(leaderId).orElseThrow(
                () -> new RuntimeException("Not BandLeader")
        );
        BandMember bandMember = bandMemberRepository.findByBandAndUserId(band, userId);
        bandMemberRepository.deleteByUserIdAndBand(bandMember.getUserId(), band);
        return true;
    }

    private BandMember getBandMember(String bandId, String userId) {
        Band band = bandRepository.findById(bandId).orElseThrow(() -> new RuntimeException("Band not found"));
        return bandMemberRepository.findByBandAndUserId(band, userId);

    }
    private Band getBand(String bandId) {
        return bandRepository.findById(bandId).orElseThrow(() -> new RuntimeException("Band not found"));
    }

    private Band getBandByLeader(String leaderId)
    {
        return bandRepository.findByLeaderId(leaderId).orElseThrow(
                () -> new RuntimeException("Not found ")
        );
    }



    @Transactional
    public ResponseEntity<?> requestBandMember(BandMemberJoinRequest req) {

        bandMemberRepository.save(req.toEntity(bandRepository));
        return ResponseEntity.ok("Band member request sent successfully");
    }






    private List<Band> getBandByUserId(String userId) {
        return bandRepository.findAllByMembers_UserId(userId);
    }
    private Integer BandMemberCount(String bandId){
        Band band = bandRepository.findById(bandId).orElseThrow(() -> new RuntimeException("Band not found"));
        return band.getMembers().size();
    }
}
