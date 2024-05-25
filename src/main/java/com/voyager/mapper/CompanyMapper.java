package com.voyager.mapper;

import com.github.pagehelper.Page;
import com.voyager.domain.dto.CompanyPageQueryDTO;
import com.voyager.domain.pojo.Company;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CompanyMapper {

    /**
     * 插入新的公司记录
     * @param company Company对象，包含需要插入的数据
     * @return 插入操作影响的行数
     */
    @Insert("INSERT INTO company (company_id, company_name, company_intro, contact_name, contact_phone, email, address, website) " +
            "VALUES (null, #{companyName}, #{companyIntro}, #{contactName}, #{contactPhone}, #{email}, #{address}, #{website})")
    int insertCompany(Company company);

    /**
     * 根据公司名称删除公司记录
     * @param companyName 公司名称
     * @return 删除操作影响的行数
     */
    @Delete("DELETE FROM company WHERE company_name = #{companyName}")
    int deleteCompanyByName(String companyName);

    /**
     * 根据公司名称查询公司
     * @param companyName 公司名称
     * @return 对应的Company对象
     */
    @Select("SELECT * FROM company WHERE company_name = #{companyName}")
    Company findCompanyByName(String companyName);

    /**
     * 更新公司的联系人信息
     * @param companyName 公司名称
     * @param contactName 联系人姓名
     * @param contactPhone 联系人电话
     * @return 更新操作影响的行数
     */
    @Update("UPDATE company SET contact_name = #{contactName}, contact_phone = #{contactPhone} WHERE company_name = #{companyName}")
    int updateCompanyContact(@Param("companyName") String companyName, @Param("contactName") String contactName, @Param("contactPhone") String contactPhone);

    /**
     * 更新公司的网址
     * @param companyName 公司名称
     * @param website 公司网址
     * @return 更新操作影响的行数
     */
    @Update("UPDATE company SET website = #{website} WHERE company_name = #{companyName}")
    int updateCompanyWebsite(@Param("companyName") String companyName, @Param("website") String website);

    /**
     * 根据条件查询公司
     * @param companyPageQueryDTO 查询条件
     * @return {@link Page }<{@link Company }>
     */
    Page<Company> selectByCriteria(CompanyPageQueryDTO companyPageQueryDTO);
}
