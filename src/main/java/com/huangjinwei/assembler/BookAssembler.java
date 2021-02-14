package com.huangjinwei.assembler;

import com.huangjinwei.dto.admin.book.AdminBookResponse;
import com.huangjinwei.model.Book;
import org.mapstruct.Mapper;

/**
 * @author huangjinwei
 * 2021-02-01
 */
@Mapper(componentModel = "spring")
public interface BookAssembler {

    AdminBookResponse toResponse(Book book);
}
