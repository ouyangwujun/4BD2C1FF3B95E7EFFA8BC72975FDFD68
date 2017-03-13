package com.object.swagger.api;

import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import com.wordnik.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by PUZE81 on 2017/3/13.
 */
@Controller
@Api(value = "user", description = "用户管理", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("user")
public class UserController {

    @ResponseBody
    @ApiOperation(value = "获得用户列表", notes = "列表信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(){
        return new String("{name:'123'}");
    };

}
