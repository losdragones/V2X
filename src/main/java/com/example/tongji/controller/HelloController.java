package com.example.tongji.controller;

import com.example.tongji.dto.UserDTO;
import com.example.tongji.vo.UserVO;
import com.example.tongji.service.UserService;
import com.example.tongji.utils.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 示例Controller
 *
 */
@RestController
@RequestMapping("/tongji")
@Slf4j
public class HelloController {

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseResult<Integer> saveUser(@RequestBody UserVO userVO) {
		return ResponseResult.success(userService.saveUser(userVO));
	}

	@PostMapping("/query")
	public ResponseResult<UserDTO> getUser(@RequestBody UserVO userVO) {
		return ResponseResult.success(userService.queryUser(userVO));

	}

	@PostMapping("/del")
	public ResponseResult<Integer> saveUser(Integer id) {
		return ResponseResult.success(userService.deleteById(id));

	}

	@RequestMapping("/vueTest")
	@CrossOrigin
	public ResponseResult<Integer> vue(){
		return ResponseResult.success(13);
	}

}
