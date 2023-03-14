package com.todo.business;

import com.todo.dto.PriorityDto;

import java.util.List;

public interface PriorityService {

    List<PriorityDto> getAll();

    PriorityDto getById(Long id);

    PriorityDto create(PriorityDto priorityDto);

    PriorityDto update(Long id, PriorityDto priorityDto);

    void delete(Long id);
}
