package com.Luojia.controller.user;

import com.Luojia.constant.JwtClaimsConstant;
import com.Luojia.dto.UserLoginDTO;
import com.Luojia.entity.User;
import com.Luojia.properties.JwtProperties;
import com.Luojia.result.Result;
import com.Luojia.service.UserService;
import com.Luojia.utils.JwtUtil;
import com.Luojia.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/user")
@Api(tags = "C端用户相关接口")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JwtProperties jwtProperties;
    /**
     * 微信用户登录
     * @param userLoginDTO
     * @return
     */

    @PostMapping("/login")
    @ApiOperation("微信登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("微信用户登录：{}",userLoginDTO.getCode());

        User user = userService.wxLogin(userLoginDTO);

        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(),claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();
        return Result.success(userLoginVO);

    }
}
