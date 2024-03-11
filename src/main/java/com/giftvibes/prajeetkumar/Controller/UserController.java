package com.giftvibes.prajeetkumar.Controller;

import static com.giftvibes.prajeetkumar.utils.MyConstant.GET;
import static com.giftvibes.prajeetkumar.utils.MyConstant.USER;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giftvibes.prajeetkumar.Service.UserService;
import com.giftvibes.prajeetkumar.dto.response.MessageResponse;
import com.giftvibes.prajeetkumar.dto.response.UserFetchResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping(USER)
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@RequiredArgsConstructor
@Tag(name="User")
public class UserController {

    private final UserService userService;

    @GetMapping(GET)
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<?> getAll() {
        try{
            userService.getAll();
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new MessageResponse(), HttpStatus.EXPECTATION_FAILED);

        }
    }

    @GetMapping(GET+"/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        UserFetchResponse response = new UserFetchResponse();
        try{
            response = userService.getById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e){
            var responseMessage = MessageResponse.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }
        // catch(Exception e){
        //     return new ResponseEntity<>(new MessageResponse(), HttpStatus.EXPECTATION_FAILED);
        // }
    }

   
}