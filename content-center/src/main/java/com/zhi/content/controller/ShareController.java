package com.zhi.content.controller;

import com.zhi.content.dao.entity.dto.content.ShareDTO;
import com.zhi.content.service.ShareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LYZhi
 * @date 2021/4/8 下午2:38
 */
@RestController
@RequestMapping("/share")
@Slf4j
public class ShareController {

    @Resource
    private ShareService shareService;

    @GetMapping("/{id}")
    public ShareDTO findById(@PathVariable Integer id){
        return shareService.findById(id);
    }
}
