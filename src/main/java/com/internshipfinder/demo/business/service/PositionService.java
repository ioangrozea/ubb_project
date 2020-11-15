package com.internshipfinder.demo.business.service;

import com.internshipfinder.demo.business.dto.PositionDTO;
import com.internshipfinder.demo.persistence.entity.Position;
import com.internshipfinder.demo.persistence.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper;

    public Set<PositionDTO> getAllPositions() {
        Iterable<Position> positions = this.positionRepository.findAll();
        return StreamSupport
                .stream(positions.spliterator(), false)
                .map(c -> this.modelMapper.map(c, PositionDTO.class))
                .collect(Collectors.toSet());
    }

    public PositionDTO getPositionById(Long positionId) throws Exception {
        return modelMapper.map(
                this.positionRepository.findById(positionId).orElseThrow(Exception::new),
                PositionDTO.class);
    }

    public PositionDTO createPosition(PositionDTO positionDTO) {
        return this.modelMapper.map(
                this.positionRepository.save(this.modelMapper.map(positionDTO, Position.class)),
                PositionDTO.class);
    }

    public PositionDTO updatePosition(Long id, PositionDTO positionDTO) throws Exception {
        Position positionOptional = this.positionRepository.findById(id).orElseThrow(Exception::new);

        positionDTO.setId(id);
        return  this.modelMapper.map(
                this.positionRepository.save(modelMapper.map(positionDTO, Position.class)),
                PositionDTO.class);

    }

    public void deletePosition(Long positionId) {
        this.positionRepository.deleteById(positionId);
    }
}
