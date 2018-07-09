package com.hansintelligent.rrrmvpframework.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * BaseCheckedBean
 *
 * @Description: 增加isChecked属性，用于购物车等批量操作的bean
 * Created by wangfu on 2018/6/28.
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BaseCheckedBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    protected boolean isChecked;//是否选中



}
