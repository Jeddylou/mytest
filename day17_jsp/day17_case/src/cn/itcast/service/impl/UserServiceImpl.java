package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }


    public User login(User user){
        return dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] uids) {
        if(uids != null && uids.length > 0)
        {
            for (String uid : uids) {
                dao.delete(Integer.parseInt(uid));
            }
        }

    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        int totalCount = dao.findTotalCount(condition);

        int totalPage = totalCount % rows == 0?totalCount / rows : totalCount/rows + 1;

        if(currentPage > totalPage)
        {
            currentPage = totalPage;
        }
        int start = (currentPage - 1) * rows;
        System.out.println(currentPage);

        //创建空的
        PageBean<User> pb = new PageBean<User>();



        pb.setTotalCount(totalCount);

        //计算开始记录的索引


        List<User> list = dao.findByPage(start, rows, condition);
        pb.setList(list);


        pb.setTotalpage(totalPage);

        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        return pb;
    }
}
