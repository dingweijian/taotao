package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TreeNode;
import com.taotao.service.ItemCatService;

/* 商品分类管理
 * <p>Title: ItemCatController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @version 1.0
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<TreeNode> getItemCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<TreeNode> list;
		try {
			list = itemCatService.getItemCatList(parentId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
