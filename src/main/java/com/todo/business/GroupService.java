package com.todo.business;

import com.todo.a_utils.GroupUtils;
import com.todo.dao.GroupRepository;
import com.todo.dto.GroupRequest;
import com.todo.dto.GroupResponse;
import com.todo.entity.Group;
import com.todo.entity.User;
import com.todo.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<GroupResponse> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups.stream()
                .map(group -> new GroupResponse(group))
                .collect(Collectors.toList());
    }

    public GroupResponse getGroupById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(101,"Group not found with id " + id));
        return new GroupResponse(group);
    }

    public GroupResponse createGroup(GroupRequest groupRequest) {
        Group group = GroupUtils.toGroup(groupRequest);
        group = groupRepository.save(group);
        return new GroupResponse(group);
    }

    public GroupResponse updateGroup(Long id, GroupRequest updateRequest) {
        Group group = GroupUtils.toGroup(updateRequest);
        group.setId(id);
        group = groupRepository.save(group);
        return new GroupResponse(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public void addMemberToGroup(Long groupId, Long memberId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id " + groupId));

        User member = new User();
        member.setId(memberId);

//        group.getMembers().add(member);
        groupRepository.save(group);
    }

    public void removeMemberFromGroup(Long groupId, Long memberId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id " + groupId));

        User member = new User();
        member.setId(memberId);

//        group.getMembers().remove(member);
        groupRepository.save(group);
    }

}
