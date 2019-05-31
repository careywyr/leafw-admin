package cn.leafw.admin.web;

import cn.leafw.admin.service.api.UserService;
import cn.leafw.admin.service.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeafwAdminApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void userTest(){
        UserDto userDto = new UserDto();
        userDto.setUserName("admin");
        userDto.setPassword("admin");
        UserDto userDto1 = userService.loginByUserNameAndPwd(userDto);
        System.out.println(null==userDto1);

    }

}
