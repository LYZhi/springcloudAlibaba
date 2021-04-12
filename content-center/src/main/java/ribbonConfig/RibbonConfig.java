package ribbonConfig;

import com.netflix.loadbalancer.IRule;
import com.zhi.content.config.NacosWeightRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LYZhi
 * @date 2021/4/9 上午9:38
 */
@Configuration
public class RibbonConfig {
    @Bean
    public IRule ribbonRule(){
        return new NacosWeightRule();
    }

//    @Bean
//    public IPing Ping(){
//        return new PingUrl();
//    }

}
