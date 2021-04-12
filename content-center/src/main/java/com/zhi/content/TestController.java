package com.zhi.content;

import com.zhi.content.dao.ShareMapper;
import com.zhi.content.dao.entity.Share;
import com.zhi.content.dao.entity.dto.user.UserDTO;
import com.zhi.content.feign.TestBaiduFeign;
import com.zhi.content.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author LYZhi
 * @date 2021/4/8 上午11:43
 */
@RestController
public class TestController {
    @Autowired
    private UserFeign userFeign;

    @Resource
    private ShareMapper shareMapper;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/test")
    public List<Share> testInter(){
        Share share = new Share();
        share.setCreateTime(new Date());
        share.setUpdateTime(new Date());
        share.setAuthor("zhi");
        share.setBuyCount(1);
        share.setTitle("xxxxx");
        share.setCover("xxxxxxxx");

        this.shareMapper.insertSelective(share);
        List<Share> shares = shareMapper.selectAll();
        return shares;
    }

    @GetMapping("/test2")
    public List<ServiceInstance> getInstances(){
        discoveryClient.getServices();
        return discoveryClient.getInstances("user-center");
    }

    @GetMapping("/test3")
    public List<String> getServices(){

        return discoveryClient.getServices();
    }

    @GetMapping("/get")
    public UserDTO query(UserDTO userDTO){
        return userFeign.query(userDTO);
    }

    @Autowired
    private TestBaiduFeign testBaiduFeign;
    @GetMapping("baidu")
    public String baiduindex(){
        return testBaiduFeign.index();
    }
}
