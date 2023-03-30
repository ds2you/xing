package com.lantu.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.lantu.sys.entity.User;
import com.lantu.sys.mapper.UserMapper;
import com.lantu.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author laocai
 * @since 2023-03-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
  /*  @Override
    public Map<String, Object> login(User user) {
        //根据用户名和密码查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        User longUser = this.baseMapper.selectOne(wrapper);
        //结果不为空 并且密码和传入得密码匹配 生成token  并将用户信息存入redis
        if (longUser != null  && passwordEncoder.matches(user.getPassword(), longUser.getPassword())){
            //暂时UUID 终极方案是jwt
            String key = "user:" + UUID.randomUUID();
            //存入redius
            longUser.setPassword(null);
            redisTemplate.opsForValue().set(key, longUser,30, TimeUnit.MINUTES);
            //返回数据
            Map<String ,Object> data = new HashMap<>();
            data.put("token",key);
            return  data;
        }

        return null;
    }*/

   @Override
   public Map<String, Object> login(User user) {
      //根据用户名和密码查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
       wrapper.eq(User::getUsername,user.getUsername());
        wrapper.eq(User::getPassword,user.getPassword());
        User longUser = this.baseMapper.selectOne(wrapper);
      //结果不为空 生成token  并将用户信息存入redis
       if (longUser != null){
           //暂时UUID 终极方案是jwt
           String key = "user" + UUID.randomUUID();
           //存入redius
           longUser.setPassword(null);
           redisTemplate.opsForValue().set(key, longUser,30, TimeUnit.MINUTES);
           //返回数据
           Map<String ,Object> data = new HashMap<>();
           data.put("token"  , key);
           return  data;
       }

        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        //根据toke获取用户信息
        Object obj = redisTemplate.opsForValue().get(token);
        //反序列化
        User loginUser = JSON.parseObject(JSON.toJSONString(obj), User.class);
        if (obj != null){

            Map<String , Object> data = new HashMap<>();
            data.put("name",loginUser.getUsername());
            data.put("avatar",loginUser.getAvatar());
            //角色
            List<String> roleList = this.baseMapper.getRoleNameByUserId(loginUser.getId());
            data.put("roles" , roleList);
            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
}
