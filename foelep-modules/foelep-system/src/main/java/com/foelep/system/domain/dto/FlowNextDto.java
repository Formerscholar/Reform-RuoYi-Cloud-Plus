package com.foelep.system.domain.dto;

import com.foelep.system.api.domain.SysRole;
import com.foelep.system.api.domain.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 动态人员、组
 *
 * @author Xuan xuan
 * @date 2021/4/17 22:59
 */
@Data
public class FlowNextDto implements Serializable {

    private String type;

    private String vars;

    private List<SysUser> userList;

    private List<SysRole> roleList;
}
