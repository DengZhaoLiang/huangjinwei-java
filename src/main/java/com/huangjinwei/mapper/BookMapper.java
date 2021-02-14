package com.huangjinwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangjinwei.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author huangjinwei
 * 2021-02-01
 */
@Mapper
@Repository
public interface BookMapper extends BaseMapper<Book> {

    /**
     * 减少库存
     */
    void reduceInventoriesById(@Param("id") Long id, @Param("inventories") Integer inventories);
}
