//package com.todo.business;
//
//import com.todo.dao.UserRepository;
//import com.todo.entity.User;
//import com.todo.exceptions.ValidationException;
//import com.todo.utils.CommonUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//
//import static com.todo.constants.ApplicationConstants.ADMIN_ROLE_ID;
//
//@Service
//public class SampleUserService {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public void createUser(UserRequest userRequest) {
//        System.out.println("userRequest--" + userRequest);
//
//        if (userRequest.getRoleId().equals(ADMIN_ROLE_ID)) {
//            if (!CommonUtils.getRoleIdOfLoggedInUser().equals(ADMIN_ROLE_ID))
//                throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "You do not have access to create another admin");
//        }
//
//        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
//        User user = new User();
//        BeanUtils.copyProperties(userRequest,user);
//        user.setId(CommonUtils.generateUUID());
////        Role role = (Role) userRepository.readById(Role.class, userRequest.getRoleId());
////        user.setUserRole(role);
//        userRepository.save(user);
//    }
//
//    public Object getUsers() {
//        System.out.println("GettingUsers");
//        return userRepository.findAll();
//    }
//
//    public UserResponse getUserById(String id) {
//        User user = userRepository.findById(id).get();
//        if(Objects.isNull(user)){
//            throw new ValidationException(HttpStatus.BAD_REQUEST.value(),"User doesn't exist " + "with" + " id - "+id);
//        }
//        UserResponse userResponse = new UserResponse();
//        BeanUtils.copyProperties(user,userResponse);
//        return userResponse;
//    }
//
//    public void updateUser(String id, UserRequest userRequest) {
//        User user = userRepository.findById(id).get();
//        BeanUtils.copyProperties(userRequest,user);
////        Role role = (Role) userRepository.readById(Role.class, userRequest.getRoleId());
////        User.updateFromUser(updateUser, user);
////        updateUser.setUserRole(role);
//        userRepository.save(user);
//    }
//
//    public void deleteUser(String id) {
//        if (CommonUtils.getUserIdOfLoggedInUser().equals(id))
//            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "You cannot delete yourself");
//
//        userRepository.deleteById(id);
//    }
//
////    public Object getPaginatedUsers(UserPaginationRequest userPaginationRequest) {
////        List response = new ArrayList();
////        if (isSearchEnabled(userPaginationRequest)) {
////            SearchRequest searchRequest = new SearchRequest();
////            searchRequest.setTable(User.class);
////            searchRequest.setMax(userPaginationRequest.getMax());
////            searchRequest.setOffset(userPaginationRequest.getOffset());
////
////            HashMap<String, Object> queryParam = new HashMap<>();
////            queryParam.put("firstName", userPaginationRequest.getFirstName());
////            queryParam.put("lastName", userPaginationRequest.getLastName());
////            searchRequest.setQueryParams(queryParam);
////
////            response = userRepository.searchWithFilter(searchRequest);
////        }
////
////        return response;
////    }
//
////    private boolean isSearchEnabled(UserPaginationRequest userPaginationRequest) {
////        return Objects.nonNull(userPaginationRequest.getFirstName()) || Objects.nonNull(userPaginationRequest.getLastName());
////    }
//}
