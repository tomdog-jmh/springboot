package com.jmh.springboottest.mapper;

import com.jmh.springboottest.pojo.PersonalMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * name : jmh
 * time : 2021/1/22 16:25
 */
public interface ListMapper {

    /**
     * 获得列表
     * @param status
     * @return
     */
    List<PersonalMessage> getList(@Param("status") String status);

    /**
     * 根据id删除
     * @param idList
     * @return
     */
    int deleteById(@Param("idList") List<Integer> idList);

    /**
     * 根据id修改
     * @param id
     * @param type
     * @return
     */
    int updateById(@Param("id")int id, @Param("type")String type);

    /**
     *
     * @param list
     */
    void exportInto(@Param("list") List<PersonalMessage> list);

    /**
     * 数据插入列表
     * @param list
     * @return
     */
    List<PersonalMessage> getListById(@Param("list") List<Integer> list);

    /**
     * 修改为以选择
     * @param idList
     * @param name
     */
    void updateByIdList(@Param("idList")List<Integer> idList,@Param("name") String name);
}
