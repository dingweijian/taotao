package com.taotao.service;

import java.util.List;

import com.taotao.pojo.EUTreeNode;
import com.taotao.pojo.TaotaoResult;

public interface ContentCategoryService {

	List<EUTreeNode> getCategoryList(long parentId);
	TaotaoResult insertContentCategory(long parentId, String name);
	void deleteContentCategory(Long parentId, Long id);
	
}
