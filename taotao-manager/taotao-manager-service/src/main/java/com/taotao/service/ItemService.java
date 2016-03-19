package com.taotao.service;

import com.taotao.pojo.EUDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(long itemId);
	
	public EUDataGridResult getItemList(int page, int rows) ;
	
	public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception;

}
