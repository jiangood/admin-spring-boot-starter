// 数据结构 - tree

// 为方便起见，默认 3个字段 id， pid， children


import {ArrUtil} from "./arr";


function treeToList(tree, level = 1, result = []) {
    for (const node of tree) {
        const newNode = {...node, level: level};
        result.push(newNode);

        if (node.children && node.children.length > 0) {
            treeToList(node.children, level + 1, result);
        }
    }

    return result;
}


export const TreeUtil = {
    treeToList,

    /**
     *
     * @param tree
     * @param level -1所有
     */
    findKeysByLevel(tree, level) {
        const list = this.treeToList(tree)
        if (level === -1) {
            return list.filter(t => t.level === level).map(t => t.id)
        }
        return list.filter(t => t.level === level).map(t => t.id)
    },

    /**
     * 将数组转换为树
     * @param list
     * @param idKey
     * @param pidKey
     * @returns {*[]}
     */
    buildTree(list, idKey = 'id', pidKey = 'pid') {
        const map = {};
        for (let i = 0; i < list.length; i++) {
            const node = list[i];
            let id = node[idKey];
            map[id] = node;
        }

        const root = [];
        for (let i = 0; i < list.length; i++) {
            const node = list[i];
            let pid = node[pidKey];
            let parent = map[pid]
            if (parent) {
                if (parent.children == null) {
                    parent.children = []
                }
                parent.children.push(node)
            } else {
                root.push(node)
            }
        }

        return root;
    },


    // 遍历树节点
    walk(tree, callback) {
        // 遍历当前层级的节点
        for (let i = 0; i < tree.length; i++) {
            const node = tree[i];
            callback(node); // 执行回调函数

            // 遍历子节点
            if (node.children && node.children.length > 0) {
                this.walk(node.children, callback);
            }
        }
    },

    findByKey(key, list, keyName = "id") {
        for (let item of list) {
            if (item[keyName] === key) {
                return item;
            }
            if (item.children && item.children.length) {
                const rs = this.findByKey(key, item.children, keyName)
                if (rs) {
                    return rs;
                }
            }
        }
    },


    findByKeyList(treeData, keyList) {
        const itemList = [];
        this.traverseTree(treeData, (item) => {
            const key = item.key || item.id;

            if (ArrUtil.contains(keyList, key)) {
                itemList.push(item);
            }
        })

        return itemList;
    },
// id, pid
    getSimpleList(treeNodeList) {
        const buffer = [];

        if (treeNodeList != null) {
            treeNodeList.forEach((t) => {
                buffer.push(t);
                TreeUtil.getChild(t, buffer);
            });
        }
        return buffer;
    }

    , getChild(treeNode, buffer) {
        if (treeNode.children != null && treeNode.children.length > 0) {
            treeNode.children.forEach((c) => {
                buffer.push(c);
                TreeUtil.getChild(c, buffer);
            });
        }
        return buffer;
    }

    , getKeyList(tree, value) {
        const list = TreeUtil.getSimpleList(tree);
        debugger

        const map = {};
        list.forEach((t) => {
            map[t.key || t.id] = t;
        });

        const t = map[value];
        if (t == null) {
            return [];
        }

        const keys = [t.key || t.id];
        let parent = map[t.parentKey || t.pid];
        while (parent != null) {
            keys.push(parent.key || t.id);
            parent = map[parent.parentKey || t.pid];
        }

        return keys.reverse();
    },


}














