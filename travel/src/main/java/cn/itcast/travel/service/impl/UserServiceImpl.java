package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {

        //根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        if(u != null){
            //用户名存在，注册失败
            return false;
        }
        //设置激活码
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");


        userDao.save(user);
        //保存用户信息

        String content = "<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>点击激活黑马旅游网</a>" ;
        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        return true;
    }

    @Override
    public boolean active(String code) {
        //根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if(user != null){
            userDao.updateStatus(user);
            return true;
        }



        return false;
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
