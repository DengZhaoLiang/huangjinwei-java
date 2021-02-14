package com.huangjinwei.assembler;

import com.huangjinwei.dto.api.order.BookResponse;
import com.huangjinwei.model.OrderBook;
import org.mapstruct.Mapper;

/**
 * @author huangjinwei
 * 2021-02-12
 */
@Mapper(componentModel = "spring")
public interface OrderBookAssembler {

    BookResponse toBooks(OrderBook orderBook);
}
