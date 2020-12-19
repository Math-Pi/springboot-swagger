# SpringBoot学习笔记：Swagger

## 一、从pom.xml引入Maven依赖

> 注意：不要引入太新依赖，之前遇到坑，引入3.0.0版本打开不了swagger-ui.html页面

```xml
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>2.8.0</version>
</dependency>
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
	<version>2.8.0</version>
</dependency>
```

## 二、配置Config类

- **@Configuration：标注该类为配置类**
- **@EnableSwagger2：开启Swagger2**

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestfulApiDocs() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cjm.swagger.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger学习")
                .description("Swagger Test")
                .termsOfServiceUrl("https://blog.csdn.net")
                .contact(new Contact("chenjiaming", "https://www.baidu.com", "wscjm@outlook.com"))
                .version("1.0.0")
                .build();
    }
}
```

## 三、Controller类

- **@Api(tags = "Test")：标注在类上，用于描述类作用**
- **@ApiOperation("测试方法")：标注在方法上，用于描述方法作用**
- **@ApiImplicitParams：含一或多个@ApiImplicitParam，描述参数信息**

```java
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
```

> 注意： 如果tags 值设置为中文, 那么 下面的方法名点击将不能被展开,改成英文之后正常。value 值是否为中文不影响。

| 注解                                  | 说明                                                        |
| ------------------------------------- | ----------------------------------------------------------- |
| @Api                                  | 说明类的作用                                                |
| @ApiOperation                         | 方法的说明                                                  |
| @ApiImplicitParams、@ApiImplicitParam | 方法的参数的说明；@ApiImplicitParams 用于指定单个参数的说明 |
| @ApiParam                             | 用于方法参数，字段说明                                      |
| @ApiModel                             | 用在 JavaBean 类上，说明 JavaBean 的 用途                   |
| @ApiModelProperty                     | 用在 JavaBean 类的属性上面，说明此属性的的含义              |
| @ApiResponses、@ApiResponse           | 用于方法返回值的说明 ；@ApiResponses 用于指定单个参数的说明 |
| @ApiIgnore                            | 用于类或者方法上，可以不被swagger显示在页面上               |



## 四、测试

打开浏览器，地址栏输入http://localhost:8080/swagger-ui.html，页面如下，表示配置成功。

<img src="https://img-blog.csdnimg.cn/20201219123334246.png" alt="image-20201219120524869" style="zoom:80%;" />

