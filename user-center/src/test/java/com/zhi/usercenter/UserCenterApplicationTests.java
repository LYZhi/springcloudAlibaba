package com.zhi.usercenter;

import com.alibaba.nacos.common.http.param.MediaType;
import com.zhi.usercenter.controller.UserController;
import com.zhi.usercenter.dao.entity.User;
import com.zhi.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.xml.ws.soap.Addressing;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@SpringBootTest
public class UserCenterApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        // (1)构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    public void testHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/hello").accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assertions.assertEquals(1, 1);
    }


    @ParameterizedTest
    @ValueSource(ints = {2, 3, 1})
    public void testFindById(Integer id) throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/users/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)//发送数据的格式
        )
                .andDo(MockMvcResultHandlers.print())
                .andReturn();//打印出请求和相应的内容

//                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
//        System.out.println("-----返回的json = " + responseString);


    }

    @Test
    public void testFind() throws Exception {

        String responseString = mvc.perform(
                MockMvcRequestBuilders.get("/users/test5")
                        .contentType(MediaType.APPLICATION_JSON)//发送数据的格式
        )
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);


    }
}
