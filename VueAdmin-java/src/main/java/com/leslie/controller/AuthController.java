package com.leslie.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import com.leslie.common.lang.Const;
import com.leslie.common.lang.Result;
import com.leslie.entity.SysUser;
import com.leslie.service.SysUserService;
import com.leslie.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;

/**
 * @author Leslie
 * @create 2021/7/13 14:46
 */
@RestController
public class AuthController extends BaseController{

    @Autowired
    Producer producer;

    @GetMapping("/captcha")
    public Result captcha() throws IOException{
        String key = UUID.randomUUID().toString();
        String code = producer.createText();

//         为了测试
//        key = "aaaaa";
//        code = "11111";
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 图片写进流
        ImageIO.write(image, "jpg", outputStream);

        BASE64Encoder  encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";

        String base64Img = str + encoder.encode(outputStream.toByteArray());

        redisUtil.hset(Const.CAPTCHA_KEY, key, code, 120);

        return Result.succ(
                MapUtil.builder()
                        .put("token", key)
                        .put("captchaImg", base64Img)
                        .build()

        );
    }


    /**
     * 获取用户信息接口
     * @param principal
     * @return
     */
    @GetMapping("/sys/userInfo")
    public Result userInfo(Principal principal) {

        SysUser sysUser = sysUserService.getByUsername(principal.getName());

        return Result.succ(MapUtil.builder()
                .put("id", sysUser.getId())
                .put("username", sysUser.getUsername())
                .put("avatar", sysUser.getAvatar())
                .put("created", sysUser.getCreated())
                .map()
        );
    }

}
