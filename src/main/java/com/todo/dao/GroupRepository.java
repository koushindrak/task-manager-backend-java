package com.todo.dao;

import com.todo.entity.Group;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAllByOwnerId(Long ownerId);

    Optional<Group> findAllByIdAndOwnerId(Long gId, Long ownerId);


    @Query("SELECT g,u FROM Group g JOIN FETCH g.users u WHERE g.id = :groupId AND g.ownerId = :ownerId")
    Optional<Group> findGroupByIdAndOwnerIdWithUsers(@Param("groupId") Long groupId, @Param("ownerId") Long ownerId);


    void deleteGroupByIdAndOwnerId(Long group_id, Long ownerId);

    @Modifying
    @Query(value = "insert into groups_users (group_id,user_id) VALUES (:groupId,:userId)", nativeQuery = true)
    @Transactional
    void addUserToGroup(@Param("groupId") Long gid, @Param("userId") Long uId);

    @Modifying
    @Query(value = "DELETE FROM groups_users  where group_id =:groupId and user_id =:userId", nativeQuery = true)
    @Transactional
    void removeUserFromGroup(@Param("groupId") Long gid, @Param("userId") Long mid);

    List<Group> findGroupsByOwnerIdOrUsers_Id(Long ownerId, Long userId);

}
