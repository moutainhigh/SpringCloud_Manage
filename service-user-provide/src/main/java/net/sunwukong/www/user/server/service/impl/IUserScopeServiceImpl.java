package net.sunwukong.www.user.server.service.impl;

import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.base.util.DataBaseTool;
import net.sunwukong.www.user.bean.UserScope;
import net.sunwukong.www.user.server.dao.usercenter.write.UserScopeMapperWrite;
import net.sunwukong.www.user.server.service.IUserScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明:用户服务范围服务接口实现
 *
 * @author Mick
 * @CreateDate 2018/7/3 15:08
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class IUserScopeServiceImpl implements IUserScopeService {

    @Autowired
    UserScopeMapperWrite userScopeMapperWrite;

    @Override
    public ResponseData addUserScope(UserScope userScope) {
        ResponseData responseData = new ResponseData();
        userScope.setId(DataBaseTool.createId());
        int i = userScopeMapperWrite.insert(userScope);
        if (i<=0){
            throw new GrilException("添加用户服务范围失败");
        }
        return responseData;
    }
}
