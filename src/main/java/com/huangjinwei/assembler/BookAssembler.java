package com.huangjinwei.assembler;

import com.huangjinwei.dto.admin.book.AdminBookResponse;
import com.huangjinwei.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author huangjinwei
 * 2021-02-01
 */
@Mapper(componentModel = "spring")
public interface BookAssembler {

    @Mapping(target = "categoryName", ignore = true)
    AdminBookResponse toResponse(Book book);
}
