package io.admin.common.utils.tree.drop;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import io.admin.common.antd.DropEvent;
import io.admin.common.antd.TreeNodeItem;
import io.admin.common.utils.tree.TreeTool;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

// 树拖拽排序
public class TreeDropTool {



    // 计算拖拽排序
    public static  DropResult onDrop(DropEvent e, List<TreeNodeItem> tree) {
        int dropPosition = e.getDropPosition();


        Map<String, TreeNodeItem> keyMap = TreeTool.treeToMap(tree);

        DropResult result = new DropResult();

        TreeNodeItem dragNode = keyMap.get(e.getDragKey());
        TreeNodeItem dropNode = keyMap.get(e.getDropKey());
        Assert.notNull(dragNode,"拖拽的节点不存在");
        Assert.notNull(dropNode,"放置的节点不存在");


        result.parentKey = e.isDropToGap() ? dropNode.getParentKey() : dropNode.getKey();
        TreeNodeItem parentNode = keyMap.get(result.getParentKey());
        Assert.notNull(parentNode,"父节点不存在");

        // 获得兄弟节点
        List<TreeNodeItem> children = parentNode.getChildren();
        if(CollUtil.isEmpty(children) || children.size() == 1){
            return result;
        }

        for (TreeNodeItem child : children) {
            result.sortedKeys.add(child.getKey());
        }

        // 交换位置
        int swapPos = dropPosition;
        if (dropPosition == -1) { // 最前
            swapPos = 0;
        } else if (dropPosition == children.size()) { // 最后
            swapPos = children.size() - 1;
        }

        ListUtil.swapTo(result.sortedKeys, e.getDragKey(), swapPos);


        return result;
    }



}
