package com.myself.designpatterns.abstratfactory;

/**
 * 用户接口类
 *
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-17 上午10:12:45
 */
public interface UserService {
    /**
     * 新增用户
     *
     * @param user
     * @author daniel
     * @time 2016-6-17 上午10:13:01
     */
    public void insertUser(User user);

    /**
     * 获得用户方法
     *
     * @param id
     * @return
     * @author daniel
     * @time 2016-6-17 上午10:13:51
     */
    public User getUser(int id);
}
