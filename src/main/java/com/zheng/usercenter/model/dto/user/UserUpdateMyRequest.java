package com.zheng.usercenter.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Zheng Zhang
 * @Description
 * @Created 05/25/2023 - 21:30
 */
@Data
public class UserUpdateMyRequest implements Serializable {
  
  /**
   * User name.
   */
  private String userName;
  
  /**
   * User avatar.
   */
  private String userAvatar;
  
  private static final long serialVersionUID = 1L;
}
