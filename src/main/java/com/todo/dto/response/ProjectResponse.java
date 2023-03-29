package com.todo.dto.response;

import com.todo.entity.Project;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String status;
}
