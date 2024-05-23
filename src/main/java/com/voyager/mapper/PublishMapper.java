package com.voyager.mapper;

import com.voyager.domain.pojo.Publish;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PublishMapper {

    /**
     * 根据职位ID查询发布信息
     *
     * @param jobId 职位ID
     * @return 对应的Publish对象列表
     */
    @Select("SELECT * FROM Publish WHERE job_id = #{jobId}")
    List<Publish> findByJobId(int jobId);

    /**
     * 根据发布ID查询发布信息
     *
     * @param publishId 发布ID
     * @return 对应的Publish对象
     */
    @Select("SELECT * FROM Publish WHERE publish_id = #{publishId}")
    Publish findByPublishId(int publishId);

    /**
     * 插入新的发布信息
     *
     * @param publish Publish对象，包含需要插入的数据
     * @return 插入操作影响的行数
     */
    @Insert("INSERT INTO Publish (publish_time, valid_time, job_id) VALUES (#{publishTime}, #{validTime}, #{jobId})")
    int insert(Publish publish);

    /**
     * 更新发布信息的有效时间
     *
     * @param publish Publish对象，包含需要更新的数据
     * @return 更新操作影响的行数
     */
    @Update("UPDATE Publish SET valid_time = #{validTime} WHERE publish_id = #{publishId}")
    int update(Publish publish);

    /**
     * 根据发布ID删除发布信息
     *
     * @param publishId 发布ID
     * @return 删除操作影响的行数
     */
    @Delete("DELETE FROM Publish WHERE publish_id = #{publishId}")
    int deleteByPublishId(int publishId);
}
