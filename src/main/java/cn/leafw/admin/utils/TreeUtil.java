package cn.leafw.admin.utils;

import cn.leafw.admin.model.vo.PermissionTreeVO;

import java.util.ArrayList;
import java.util.List;

/**
 * TreeUtil
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2020/1/6
 */
public class TreeUtil {

    /**
     * 递归构建树
     * @param treeNodes
     * @return
     */
    public static List<PermissionTreeVO> buildByRecursive(List<PermissionTreeVO> treeNodes) {
        List<PermissionTreeVO> trees = new ArrayList<PermissionTreeVO>();
        for (PermissionTreeVO treeNode : treeNodes) {
            if (new Long(0L).equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归寻找子节点
     * @param treeNode
     * @param treeNodes
     * @return
     */
    public static PermissionTreeVO findChildren(PermissionTreeVO treeNode,List<PermissionTreeVO> treeNodes) {
        treeNode.setChildPermission(new ArrayList<PermissionTreeVO>());
        for (PermissionTreeVO it : treeNodes) {
            if(treeNode.getPermissionId().equals(it.getParentId())) {
                if (treeNode.getChildPermission() == null) {
                    treeNode.setChildPermission(new ArrayList<PermissionTreeVO>());
                }
                treeNode.getChildPermission().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

}

