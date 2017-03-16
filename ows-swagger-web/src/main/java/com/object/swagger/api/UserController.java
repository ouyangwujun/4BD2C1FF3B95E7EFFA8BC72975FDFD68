package com.object.swagger.api;

import com.object.swagger.api.model.Result;
import com.object.swagger.api.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * 用户管理
 *
 */
@RestController
@RequestMapping("user")
@Api(value = "user", description = "用户管理", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	// 列出某个类目的所有规格
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ApiOperation(value = "获得用户列表", notes = "列表信息", httpMethod = "GET")
	public Result<User> list(
			@ApiParam(value = "分类ID", required = true) @RequestParam Long categoryId,
			@ApiParam(value = "分类ID", required = true) @RequestParam Long categoryId2,
			@ApiParam(value = "token", required = true) @RequestParam String token) {
		Result<User> result = new Result<User>();
		User user = new User();
		user.setName("zhangsan");
		user.setPassword("password");
		user.setSex(1);
		user.setToken("aastewetwewe97wewesf7w8");
		result.setData(user);
		return result;
	}
	
	@RequestMapping(value = "getUserById", method = RequestMethod.GET)
	@ApiOperation(value = "获得用户", notes = "列表信息", httpMethod = "GET")
	public User getUserById(@ApiParam(value = "user ID", required = true) @RequestParam String id){
		User user = new User();
		user.setName("lisi");
		user.setPassword("password");
		user.setSex(1);
		user.setToken("aastewetwewe97wewesf7w8");
		return user;
	}
	@RequestMapping(value = "add", method = RequestMethod.GET)
	@ApiOperation(value = "添加用户", notes = "添加用户(用于数据同步)", httpMethod = "POST")
	public Result<String> add(@RequestBody User user) {
		String u = findUser(user);
		System.out.println(u);
		return null;
	}
	@RequestMapping(value = "update", method = RequestMethod.GET)
	@ApiOperation(value = "update用户", notes = "update user", httpMethod = "POST")
	public Result<String> update(User user) {
		String u = findUser(user);
		System.out.println(u);
		return null;	
	}

	private String findUser(User user) {
		String token = user.getToken();
		return token;
	}
}