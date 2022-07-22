package com.example.demo;

import com.example.demo.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = RedisTestApplication.class)
@RunWith(value = SpringRunner.class)
class RedisTestApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void add(){
        boolean b = redisUtil.set("test_key", "test_val");
        System.out.println("向redis中添加元素结果: " + b);
    }

    @Test
    void batchSetVal(){
        long s = System.currentTimeMillis();
        String testVal = "{\"licence_type\":1,\"in_time\":1656366583,\"licence_color\":0,\"car_number\":\"川A8F12M\",\"c_type\":\"临停车\",\"park_id\":30221,\"in_pass\":\"camera_76359F87A717ADDF62A41225\",\"id\":1656401018538884,\"order_id\":\"order_76359F87A717ADDF62A41225_1656366583\"}";
        int rows = 0;
        String key;
        //188428
        for (int i = 1; i <= 3000000; i++) {
            key = i + "";
            boolean set = redisUtil.set(key, testVal);
            if(set){
                rows++;
            }
            System.out.println("key: " + key + ", 新增结果: " + set + ", 当前成功添加数据条数: " + rows);
        }
        System.out.println("最终成功添加数据条数: " + rows + ", 耗时 "+(System.currentTimeMillis()-s)+"ms");
    }

    @Test
    void getVal(){
        String name = redisUtil.getString("name");
        System.out.println("name = " + name);
    }

}
