package com.huangjinwei.assembler;

import com.huangjinwei.dto.admin.user.AdminUserResponse;
import com.huangjinwei.model.User;
import org.mapstruct.Mapper;

/**
 * @author huangjinwei
 * 2021-01-31
 */
@Mapper(componentModel = "spring")
public interface UserAssembler {

    AdminUserResponse toResponse(User user);
}
