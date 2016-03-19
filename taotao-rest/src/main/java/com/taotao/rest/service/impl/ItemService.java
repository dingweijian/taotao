package com.taotao.rest.service.impl;

import com.taotao.pojo.TaotaoResult;

public interface ItemService {
	TaotaoResult getItemBaseInfo(long itemId);
	TaotaoResult getItemDesc(long itemId);
	TaotaoResult getItemParam(long itemId);
	
}
