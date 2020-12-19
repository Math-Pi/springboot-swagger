package com.cjm.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author CJM
 * @Date 2020/12/19  11:20
 */
@RestController
@Api(tags = "Test")
public class TestController {

    @ApiOperation("测试方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "name",value = "姓名")})
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(String name){
        return "Hello "+name;
    }
}
