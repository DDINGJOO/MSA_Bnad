package dding.band.service;

import dding.band.dto.request.BandRecruitPositionRequest;
import dding.band.dto.response.BandRecruitPositionResponse;
import dding.band.entity.BandRecruitPosition;
import dding.band.repository.BandRecruitPositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BandRecruitPositionService {

    private final BandRecruitPositionRepository positionRepository;

    public void addRecruitPosition(BandRecruitPositionRequest request) {
        BandRecruitPosition position = BandRecruitPosition.builder()
                .bandId(request.getBandId())
                .position(request.getPosition())
                .isClosed(false)
                .description(request.getDescription())
                .build();
        positionRepository.save(position);
    }

    public List<BandRecruitPositionResponse> getPositions(String bandId) {
        return positionRepository.findByBandId(bandId).stream()
                .map(pos -> BandRecruitPositionResponse.builder()
                        .id(pos.getId())
                        .position(pos.getPosition())
                        .isClosed(pos.isClosed())
                        .description(pos.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    public void closePosition(Long positionId) {
        BandRecruitPosition position = positionRepository.findById(positionId)
                .orElseThrow(() -> new IllegalArgumentException("포지션을 찾을 수 없습니다."));
        position.setClosed(true);
        positionRepository.save(position);
    }
}