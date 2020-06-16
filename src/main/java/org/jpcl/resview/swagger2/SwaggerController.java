package org.jpcl.resview.swagger2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/swagger")
@Api(tags="swagger测试controller")
public class SwaggerController {

    @ApiOperation(value = "打招呼", notes = "测试方法")
    @ApiImplicitParam(name="name", value = "姓名")
    @RequestMapping("/sayhi")
    public String sayHi(String name) {
        return "Hello" + name;
    }
}
