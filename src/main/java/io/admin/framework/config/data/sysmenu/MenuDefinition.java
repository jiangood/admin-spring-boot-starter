package io.admin.framework.config.data.sysmenu;

import io.admin.common.dto.antd.AntDesignIcon;
import lombok.Data;

import java.util.List;

@Data
public class MenuDefinition {

    private String id;

    private String pid;


    // 名称
    private String name;


    // 图标
    private AntDesignIcon icon;

    // 路由
    private String path;


    // 排序
    private Integer seq;


    /**
     * 在 点击tab时，刷新页面
     */
    private boolean refreshOnTabClick;


    /**
     * 子节点（表中不存在，用于构造树）
     */
    private List<MenuDefinition> children;


    /**
     * 权限表，
     */
    private List<MenuPermission> perms;

    /**
     * 菜单的消息数量接口
     */
    private String messageCountUrl;

    /**
     * 是否禁用菜单，默认false
     */
    private boolean disabled;
}
