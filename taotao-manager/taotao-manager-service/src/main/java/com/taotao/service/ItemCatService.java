package com.taotao.service;

import java.util.List;

import com.taotao.pojo.TreeNode;

public interface ItemCatService {

	List<TreeNode> getItemCatList(long parentId) throws Exception;

}
