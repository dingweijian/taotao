package com.taotao.rest.service;

import com.taotao.pojo.TaotaoResult;

public interface RedisService {

	TaotaoResult syncContent(long contentCid);
}
