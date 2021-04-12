package com.zhi.content.feign;

import com.zhi.content.dao.entity.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author LYZhi
 * @date 2021/4/9 下午2:35
 */
@FeignClient(name = "user-center")
public interface UserFeign {
    @GetMapping("/users/{id}")
    UserDTO finfById(@PathVariable Integer id);

    @GetMapping("/users/test5")
    UserDTO query(@SpringQueryMap UserDTO userDTO);

}
