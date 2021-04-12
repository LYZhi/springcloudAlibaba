package com.zhi.content.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author LYZhi
 * @date 2021/4/9 上午9:35
 */

public class UserCenterLogConfig {
    @Bean
    public Logger.Level logger(){
        return Logger.Level.FULL;
    }
}
