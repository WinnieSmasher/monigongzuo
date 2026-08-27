package red.mlz.study.entity;

import java.math.BigInteger;

public class Student {

    private BigInteger id;
    private String name;
    private Integer gender;
    private Integer updateTime;
    private Integer createTime;
    private Integer isDeleted;

    public BigInteger getId() { return id; }
    public void setId(BigInteger id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getGender() { return gender; }
    public void setGender(Integer gender) { this.gender = gender; }

    public Integer getUpdateTime() { return updateTime; }
    public void setUpdateTime(Integer updateTime) { this.updateTime = updateTime; }

    public Integer getCreateTime() { return createTime; }
    public void setCreateTime(Integer createTime) { this.createTime = createTime; }

    public Integer getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }

}
