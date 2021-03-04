package com.kong.dao;

import com.kong.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User getUser();
}
