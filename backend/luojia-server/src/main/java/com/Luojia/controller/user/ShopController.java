package com.Luojia.controller.user;

import com.Luojia.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController("userShopController")
@Slf4j
@RequestMapping("/user/shop")
@Api(tags = "用户端店铺相关接口")
public class ShopController {

    public static final String key = "SHOP_STATUS";
    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 获取店铺状态
     * @return
     */
    @ApiOperation("获取店铺状态")
    @GetMapping("/status")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(key);
        log.info("获取到店铺的营业状态为：{}",status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }

    /**
     * 获取商户信息
     * @return
     */
    @ApiOperation("获取商户信息")
    @GetMapping("/getMerchantInfo")
    public Result<Map<String, Object>> getMerchantInfo(){
        log.info("获取商户信息");
        Map<String, Object> map = new HashMap<>();
        map.put("phone", "13800138000");
        return Result.success(map);
    }
}
