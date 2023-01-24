package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 作为实体类的积累
 */
@Data
public class BaseEntity implements Serializable{
   private String createdUser;
   private Date createdTime;
   private String modifiedUser;
   private Date  modifiedTime;

//   @Override
//   public boolean equals(Object o) {
//      if (this == o) return true;
//      if (!(o instanceof BaseEntity)) return false;
//      BaseEntity that = (BaseEntity) o;
//      return Objects.equals(createdUser, that.createdUser) &&
//              Objects.equals(createdTime, that.createdTime) &&
//              Objects.equals(modifiedUser, that.modifiedUser) &&
//              Objects.equals(modifiedTime, that.modifiedTime);
//   }
//
//   @Override
//   public int hashCode() {
//
//      return Objects.hash(createdUser, createdTime, modifiedUser, modifiedTime);
//   }
//
//   @Override
//   public String toString() {
//      return "BaseEntity{" +
//              "createdUser='" + createdUser + '\'' +
//              ", createdTime=" + createdTime +
//              ", modifiedUser='" + modifiedUser + '\'' +
//              ", modifiedTime=" + modifiedTime +
//              '}';
//   }
}
