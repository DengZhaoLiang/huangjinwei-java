package com.huangjinwei.assembler;

import com.huangjinwei.dto.admin.AdminResponse;
import com.huangjinwei.model.Admin;
import org.mapstruct.Mapper;

/**
 * @author huangjinwei
 * 2021-01-30
 */
@Mapper(componentModel = "spring")
public interface AdminAssembler {

    AdminResponse toResponse(Admin admin);
}
