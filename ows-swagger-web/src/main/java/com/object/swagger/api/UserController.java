package com.object.swagger.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * 用户管理
 *
 */
@Api(value = "user", description = "用户管理", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping("user")
public class UserController {

	// 列出某个类目的所有规格
	@ApiOperation(value = "获得用户列表", notes = "列表信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
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
	@ResponseBody
	@ApiOperation(value = "根据id获得用户信息", notes = "用户信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserById(
			@ApiParam(value = "user ID", required = true) @RequestParam String id
			){
		User user = new User();
		user.setName("lisi");
		user.setPassword("password");
		user.setSex(1);
		user.setToken("aastewetwewe97wewesf7w8");
		return user;
	}

	@ApiOperation(value = "添加用户", notes = "添加用户(用于数据同步)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	// @RequestBody只能有1个
	// 使用了@RequestBody，不能在拦截器中，获得流中的数据，再json转换，拦截器中，也不清楚数据的类型，无法转换成java对象
	// 只能手动调用方法
	public Result<String> add(@RequestBody User user) {
		String u = findUser(user);
		System.out.println(u);
		return null;
	}

	@ApiOperation(value = "update用户", notes = "update user", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.GET)
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