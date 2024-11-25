package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新建赛事对象 newevent
 * 
 * @author maomao
 * @date 2024-11-11
 */
public class Newevent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String schoolName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String address;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String city;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String province;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String postalCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String phoneNumber;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String email;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String establishedYear;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSchoolName(String schoolName) 
    {
        this.schoolName = schoolName;
    }

    public String getSchoolName() 
    {
        return schoolName;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setPostalCode(String postalCode) 
    {
        this.postalCode = postalCode;
    }

    public String getPostalCode() 
    {
        return postalCode;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setEstablishedYear(String establishedYear) 
    {
        this.establishedYear = establishedYear;
    }

    public String getEstablishedYear() 
    {
        return establishedYear;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("schoolName", getSchoolName())
            .append("address", getAddress())
            .append("city", getCity())
            .append("province", getProvince())
            .append("postalCode", getPostalCode())
            .append("phoneNumber", getPhoneNumber())
            .append("email", getEmail())
            .append("establishedYear", getEstablishedYear())
            .toString();
    }
}
