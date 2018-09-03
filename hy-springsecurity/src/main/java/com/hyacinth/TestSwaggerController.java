/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018/8/25 11:01
 * 审核人员: 
 * 相关文档: 
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.hyacinth;

import com.hyacinth.module.base.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Api(tags = "测试接口模块", value = "/test1")
@RestController
@RequestMapping("/test")
public class TestSwaggerController {

    /**
     @ApiOperation：用在请求的方法上，说明方法的用途、作用
     value="说明方法的用途、作用"
     notes="方法的备注说明"
     @ApiImplicitParams：用在请求的方法上，表示一组参数说明
     @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
     name：参数名
     value：参数的汉字说明、解释
     required：参数是否必须传
     paramType：参数放在哪个地方
     · header --> 请求参数的获取：@RequestHeader
     · query --> 请求参数的获取：@RequestParam
     · path（用于restful接口）--> 请求参数的获取：@PathVariable
     · body（不常用）
     · form（不常用）
     dataType：参数类型，默认String，其它值dataType="Integer"
     defaultValue：参数的默认值

     @ApiResponses：用在请求的方法上，表示一组响应
     @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
     code：数字，例如400
     message：信息，例如"请求参数没填好"
     response：抛出异常的类

     @ApiModel：用于响应类上，表示一个返回响应数据的信息
     （这种一般用在post创建的时候，使用@RequestBody这样的场景，
     请求参数无法使用@ApiImplicitParam注解进行描述的时候）
     @ApiModelProperty：用在属性上，描述响应类的属性
     @Api：修饰整个类，描述Controller的作用
     @ApiOperation：描述一个类的一个方法，或者说一个接口
     @ApiParam：单个参数描述
     @ApiModel：用对象来接收参数
     @ApiProperty：用对象接收参数时，描述对象的一个字段
     @ApiResponse：HTTP响应其中1个描述
     @ApiResponses：HTTP响应整体描述
     @ApiIgnore：使用该注解忽略这个API
     @ApiError ：发生错误返回的信息
     @ApiImplicitParam：一个请求参数
     @ApiImplicitParams：多个请求参数
     */
    @ApiOperation(value="展示首页信息value", notes = "展示首页信息notes")
    @GetMapping("/show")
    public Object showInfo(){
        return "hello world";
    }

    @ApiOperation(value="添加用户信息", notes = "添加用户信息")
    @ApiImplicitParam(name="user", value="User", required = true, dataType = "User")
    @PostMapping("/addUser")
    public Object addUser(@RequestBody User user){
        return "success";
    }
}
