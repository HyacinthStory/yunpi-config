/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018/8/25 10:55
 * 审核人员: 
 * 相关文档: 
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.hyacinth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.hyacinth"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-api文档")
                .description("swagger接口教程")
                //服务条款网址
                .termsOfServiceUrl("https://blog.csdn.net/ysk_xh_521")
                .version("1.0")
                .contact(new Contact("zhangfb", "http://ysk521.cn", "1208539256@qq.com"))
                .build();
    }
}
