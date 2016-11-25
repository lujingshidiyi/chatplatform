package com.contactserver.user.mgr;

import com.contactserver.user.UserEntity;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户信息管理
 * Created by admin on 16/9/20.
 */
public class UserMgr {
    //缓存用户实体的Map集
    final Map<Integer, UserEntity> userEntityMap = new HashMap<>( );

    //是否存在此用户,如果不存在则创建
    public UserEntity getOrCreateUserEntity( int userId ) {
        for ( Map.Entry<Integer, UserEntity> userEntityEntry : userEntityMap.entrySet( ) ) {
            if ( userEntityEntry.getKey( ) == userId ) {
                return userEntityEntry.getValue( );
            }
        }
        return initUserEntity( userId );
    }

    //初始化用户实体
    private UserEntity initUserEntity( int userId ) {
        UserEntity userEntity = new UserEntity( );
        userEntity.setUserId( userId );
        return userEntity;
    }

}
