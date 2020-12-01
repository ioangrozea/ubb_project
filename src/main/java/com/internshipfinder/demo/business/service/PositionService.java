package com.internshipfinder.demo.business.service;

import com.internshipfinder.demo.business.dto.PositionDTO;
import com.internshipfinder.demo.business.dto.PositionDetailsDTO;
import com.internshipfinder.demo.persistence.entity.Position;
import com.internshipfinder.demo.persistence.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Set<PositionDTO> getAllCompanyPositions(Long companyId) {
        Iterable<Position> positions = this.positionRepository.getAllByCompanyId(companyId);
        return StreamSupport
                .stream(positions.spliterator(), false)
                .map(c -> this.modelMapper.map(c, PositionDTO.class))
                .collect(Collectors.toSet());
    }


    public PositionDetailsDTO getPositionById(Long positionId) throws Exception {
        return modelMapper.map(
                this.positionRepository.findById(positionId).orElseThrow(Exception::new),
                PositionDetailsDTO.class);
    }

    public PositionDTO createPosition(PositionDTO positionDTO) {
        positionDTO.setCreatedAt(LocalDate.now());
        return this.modelMapper.map(
                this.positionRepository.save(this.modelMapper.map(positionDTO, Position.class)),
                PositionDTO.class);
    }

    public PositionDTO updatePosition(Long id, PositionDTO positionDTO) throws Exception {
        positionDTO.setId(id);
        return  this.modelMapper.map(
                this.positionRepository.save(modelMapper.map(positionDTO, Position.class)),
                PositionDTO.class);

    }

    public void deletePosition(Long positionId) {
        this.positionRepository.deleteById(positionId);
    }
}
