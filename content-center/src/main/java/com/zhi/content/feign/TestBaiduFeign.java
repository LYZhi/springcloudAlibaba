package com.zhi.content.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author LYZhi
 * @date 2021/4/9 下午4:07
 */
@FeignClient(name = "baidu",url = "https://www.baidu.com")
public interface TestBaiduFeign {

    @GetMapping("")
    public String index();

}
