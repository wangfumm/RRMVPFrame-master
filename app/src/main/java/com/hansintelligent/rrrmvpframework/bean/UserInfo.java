package com.hansintelligent.rrrmvpframework.bean;

import com.hansintelligent.rrrmvpframework.base.BaseCheckedBean;

import java.io.Serializable;

import lombok.Data;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * UserInfo
 * Created by wangfu on 2018/5/15.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseCheckedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Mobile;
    private String Sex;
    private String Avatar;
    private String Status;
    private String NickName;


}
