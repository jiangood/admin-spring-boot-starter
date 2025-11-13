package io.admin.common.antd;

// https://ant.design/components/menu-cn#itemtype

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItem {
    Boolean danger; //	展示错误状态样式	boolean	false
    Boolean disabled; //	是否禁用	boolean	false
    String extra; //	额外节点	ReactNode	-	5.21.0
    AntDesignIcon icon; //	菜单图标	ReactNode	-
    String key; //item 的唯一标志	string	-
    String label; //	菜单项标题	ReactNode	-
    String title; //    设置收缩时展示的悬浮标题 string


    List<MenuItem> children ;


    // 额外字段，用于组装树
    @JsonIgnore
    String parentKey;




    String path;

    @Override
    public MenuItem clone() {
        try {
            MenuItem clone = (MenuItem) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
