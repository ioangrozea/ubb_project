package com.internshipfinder.demo.business.service;

import com.internshipfinder.demo.business.dto.PositionDTO;
import com.internshipfinder.demo.business.dto.PositionDetailsDTO;
import com.internshipfinder.demo.persistence.entity.Company;
import com.internshipfinder.demo.persistence.entity.Position;
import com.internshipfinder.demo.persistence.repository.CompanyRepository;
import com.internshipfinder.demo.persistence.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public List<PositionDTO> getAllPositions() {
        List<Position> positions = this.positionRepository.getAllByCompanyAcceptedByAdminOrderByCreatedAtDesc(true);
        return StreamSupport
                .stream(positions.spliterator(), false)
                .map(c -> this.modelMapper.map(c, PositionDTO.class))
                .collect(Collectors.toList());
    }

    public List<PositionDTO> getAllCompanyPositions(Long companyId) {
        List<Position> positions = this.positionRepository.getAllByCompanyIdOrderByCreatedAtDesc(companyId);
        return StreamSupport
                .stream(positions.spliterator(), false)
                .map(c -> this.modelMapper.map(c, PositionDTO.class))
                .collect(Collectors.toList());
    }


    public PositionDetailsDTO getPositionById(Long positionId) throws Exception {
        return modelMapper.map(
                this.positionRepository.findById(positionId).orElseThrow(Exception::new),
                PositionDetailsDTO.class);
    }

    public PositionDTO createPosition(PositionDTO positionDTO) throws Exception {
        Company company = this.companyRepository.findById(positionDTO.getCompanyId()).orElseThrow(Exception::new);

        if (company.isAcceptedByAdmin()) {
        positionDTO.setCreatedAt(LocalDate.now());
        return this.modelMapper.map(
                this.positionRepository.save(this.modelMapper.map(positionDTO, Position.class)),
                PositionDTO.class);
        } else {
            throw new Exception();
        }
    }

    public PositionDTO updatePosition(Long id, PositionDTO positionDTO) {
        positionDTO.setId(id);
        positionDTO.setCreatedAt(LocalDate.now());
        return this.modelMapper.map(
                this.positionRepository.save(modelMapper.map(positionDTO, Position.class)),
                PositionDTO.class);

    }

    public void deletePosition(Long positionId) {
        this.positionRepository.deleteById(positionId);
    }
}
