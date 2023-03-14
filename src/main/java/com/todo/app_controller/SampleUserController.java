//package com.todo.controller;
//
//
//import com.todo.annotation.Authenticator;
//import com.todo.business.SampleUserService;
//import com.todo.constants.CommonConstants;
//import com.todo.dto.ResponseDTO;
//import com.todo.dto.UserRequest;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin("*")
//@RestController
//@RequestMapping("/api/v1/user")
//public class SampleUserController {
//    @Autowired
//    private ResponseDTO responseDTO;
//    @Autowired
//    private SampleUserService userService;
//
//    @RequestMapping(method = RequestMethod.POST)
//    @Authenticator
//    public ResponseDTO createUser(@RequestBody @Valid UserRequest userRequest,
//                                  @RequestHeader(name = "X-Authorization",required = false) String accessToken) throws Exception {
//        userService.createUser(userRequest);
//        return responseDTO.ok(CommonConstants.SUCCESS_RESPONSE);
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    @Authenticator
//    public ResponseDTO getUsers(@RequestHeader(name = "X-Authorization",required = false) String accessToken) throws Exception {
//        return responseDTO.ok(HttpStatus.OK.value(), userService.getUsers(), CommonConstants.SUCCESS_RESPONSE);
//    }
//
////    @RequestMapping(value = "/paginated", method = RequestMethod.POST)
////    @Authenticator
////    public ResponseDTO getPaginatedUsers(@RequestHeader(name = "X-Authorization",required = false) String accessToken,
////                                         @RequestBody UserPaginationRequest userPaginationRequest) throws Exception {
////        return responseDTO.ok(HttpStatus.OK.value(), userService.getPaginatedUsers(userPaginationRequest), CommonConstants.SUCCESS_RESPONSE);
////    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @Authenticator
//    public ResponseDTO getUserById(
//            @PathVariable(name = "id") String id,
//            @RequestHeader(name = "X-Authorization",required = false) String accessToken
//    ) throws Exception {
//        return responseDTO.ok(HttpStatus.OK.value(), userService.getUserById(id), CommonConstants.SUCCESS_RESPONSE);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @Authenticator
//    public ResponseDTO updateUser(
//            @PathVariable(name = "id") String id,
//            @RequestHeader(name = "X-Authorization",required = false) String accessToken,
//            @RequestBody @Valid UserRequest userRequest) throws Exception {
//        userService.updateUser(id, userRequest);
//        return responseDTO.ok(CommonConstants.SUCCESS_RESPONSE);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @Authenticator
//    public ResponseDTO deleteUser(
//            @PathVariable(name = "id") String id,
//            @RequestHeader(name = "X-Authorization",required = false) String accessToken) throws Exception {
//        userService.deleteUser(id);
//        return responseDTO.ok(CommonConstants.SUCCESS_RESPONSE);
//    }
//}
