package com.todo.business;

import com.todo.business.PriorityService;
import com.todo.dao.PriorityRepository;
import com.todo.dto.PriorityDto;
import com.todo.entity.Priority;
import com.todo.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepository priorityRepository;

    @Autowired
    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public List<PriorityDto> getAll() {
        List<Priority> priorities = priorityRepository.findAll();
        return priorities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PriorityDto getById(Long id) {
        Priority priority = priorityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The requested resource was not found."));
        return toDto(priority);
    }

    @Override
    public PriorityDto create(PriorityDto priorityDto) {
        Priority priority = toEntity(priorityDto);
        Priority savedPriority = priorityRepository.save(priority);
        return toDto(savedPriority);
    }

    @Override
    public PriorityDto update(Long id, PriorityDto priorityDto) {
        Priority priority = priorityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The requested resource was not found."));

        BeanUtils.copyProperties(priorityDto, priority);
        Priority updatedPriority = priorityRepository.save(priority);
        return toDto(updatedPriority);
    }

    @Override
    public void delete(Long id) {
        Priority priority = priorityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The requested resource was not found."));

        priorityRepository.delete(priority);
    }

    // Map between Priority and PriorityDto
    private PriorityDto toDto(Priority priority) {
        PriorityDto priorityDto = new PriorityDto();
        BeanUtils.copyProperties(priority, priorityDto);
        return priorityDto;
    }

    private Priority toEntity(PriorityDto priorityDto) {
        Priority priority = new Priority();
        BeanUtils.copyProperties(priorityDto, priority);
        return priority;
    }
}
