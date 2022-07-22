package com.example.demo.controller;

import com.example.demo.util.RedisUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 36569
 * @Date 2022-07-13 10:04
 * @Version 1.0
 */
@RestController
@RequestMapping("/qrCode")
@Slf4j
@Tag(name = "RedisTestController", description = "redis测试数据接口")
public class RedisTestController {

    private final RedisUtil redisUtil;

    @Autowired
    public RedisTestController(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Operation(summary = "向redis中批量添加数据",
            description = "向redis中批量添加数据",
            parameters = {
                    @Parameter(name = "startKey", description = "起始key"),
                    @Parameter(name = "endKey", description = "结束key")
            },
            responses = {
                    @ApiResponse(description = "返回记录",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class)))}
    )
    @PostMapping("/addVal")
    public String addVal(int startKey, int endKey){
        long s = System.currentTimeMillis();
        String testVal = "{\"licence_type\":1,\"in_time\":1656366583,\"licence_color\":0,\"car_number\":\"川A8F12M\",\"c_type\":\"临停车\",\"park_id\":30221,\"in_pass\":\"camera_76359F87A717ADDF62A41225\",\"id\":1656401018538884,\"order_id\":\"order_76359F87A717ADDF62A41225_1656366583\"}";
        int rows = 0;
        String key;
        //188428
        for (int i = startKey; i <= endKey; i++) {
            key = i + "";
            boolean set = redisUtil.set(key, testVal);
            if(set){
                rows++;
            }
            System.out.println("key: " + key + ", 新增结果: " + set + ", 当前成功添加数据条数: " + rows);
        }
        return ("最终成功添加数据条数: " + rows + ", 耗时 "+(System.currentTimeMillis()-s)+"ms");
    }

}
