package com.Luojia.controller.user;

import com.Luojia.constant.StatusConstant;
import com.Luojia.entity.Dish;
import com.Luojia.result.Result;
import com.Luojia.service.DishService;
import com.Luojia.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
@Api(tags = "C端-菜品浏览接口")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<DishVO>> list(Long categoryId) {
        //查询数据库前先查redis缓存
        //约定key的规则：dish_分类Id
        String key = "dish_" + categoryId;

        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);

        if(list != null && list.size() > 0){
            return Result.success(list);
        }
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);//查询起售中的菜品

        list = dishService.listWithFlavor(dish);

        //运行到这里说明redis里没有当前查询的数据
        redisTemplate.opsForValue().set(key,list);

        return Result.success(list);
    }

}
