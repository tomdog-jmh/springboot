package com.jmh.springboottest.pojo;


import java.util.Date;

public class File {

  private long id;
  private String fileName;
  private String sysName;
  private String sysPath;
  private Date createTime;
  private Date modifyTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  public String getSysName() {
    return sysName;
  }

  public void setSysName(String sysName) {
    this.sysName = sysName;
  }


  public String getSysPath() {
    return sysPath;
  }

  public void setSysPath(String sysPath) {
    this.sysPath = sysPath;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public Date getModifyTime() {
    return modifyTime;
  }

  public void setModifyTime(Date modifyTime) {
    this.modifyTime = modifyTime;
  }

}
