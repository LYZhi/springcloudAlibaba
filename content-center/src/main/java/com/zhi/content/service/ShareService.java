package com.zhi.content.service;

import com.zhi.content.dao.ShareMapper;
import com.zhi.content.dao.entity.Share;
import com.zhi.content.dao.entity.dto.content.ShareDTO;
import com.zhi.content.dao.entity.dto.user.UserDTO;
import com.zhi.content.feign.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LYZhi
 * @date 2021/4/8 下午2:22
 */
@Service
@Slf4j
public class ShareService {

    @Resource
    private ShareMapper shareMapper;
    @Autowired
    private UserFeign userFeign;
//    @Resource
//    private RestTemplate restTemplate;
//    @Resource
//    private DiscoveryClient discoveryClient;

    public ShareDTO findById(Integer id) {
        Share share = shareMapper.selectByPrimaryKey(id);
        Integer userId = share.getUserId();

//        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
//        List<String> URLS = instances.stream()
//                .map(instance -> instance.getUri().toString() + "/users/{id}")
//                .collect(Collectors.toList());
//        int i = ThreadLocalRandom.current().nextInt(URLS.size());
//        String url = URLS.get(i);
//        log.info("请求目标地址：" + URLS.get(i)+" "+i);
        UserDTO userDTO = userFeign.finfById(userId);

//        UserDTO userDTO = restTemplate.getForObject("http://user-center/users/{userId}",
//                UserDTO.class, userId);

        ShareDTO shareDTO = new ShareDTO();
        BeanUtils.copyProperties(share, shareDTO);
        shareDTO.setWxNickname(userDTO.getWxNickname());
        return shareDTO;
    }
}
