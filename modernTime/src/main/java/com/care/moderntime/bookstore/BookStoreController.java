package com.care.moderntime.bookstore;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.moderntime.bookstore.dto.BookStoreDTO;
import com.care.moderntime.bookstore.service.BookStoreService;

@Controller
public class BookStoreController {
	@Autowired BookStoreService bss;
	
	@RequestMapping("bookstore")
	public String bookstore() {
		return "bookstore/bookStoreIndex";
	}
	
	@RequestMapping("bookStoreView")
	public String bookStoreview(String id, HttpSession session, Model model) {
		model.addAttribute("bookstoreview",bss.bookstoreview(id));
		return "bookstore/bookStoreView";
	}
		
		
	@RequestMapping("sell")
	public String sell() {
		return "bookstore/sell";
	}
	
//	@ResponseBody
//	@GetMapping("findList")
//	public HashMap<String, ArrayList<String>> findList(@RequestParam(required=false)String keyword) {
//		System.out.println(keyword);
//		HashMap<String, ArrayList<String>> data = bss.findList(keyword);
//		return data;
//	}
	
	@ResponseBody
	@PostMapping("bookSell")
	public String bookSell(BookStoreDTO dto) {
		BookStoreDTO tmp = bss.bookSell(dto);
		bss.insertPicture(tmp);
		return "성공";
	}
	
	@ResponseBody
	@PostMapping("bookSellList")
	public String bookSellList() {
		String data = bss.bookSellList();
		return data;
	}
	
	@ResponseBody
	@PostMapping("bookSellListSearch")
	public String bookSellListSearch(@RequestParam(required=false)String keyword) {
		String data = bss.bookSellListSearch(keyword);
//		System.out.println("BookStoreController : " + data);
		return data;
	}
	
	@ResponseBody
	@PostMapping("bookSellListMy")
	public String bookSellListMy(@RequestParam(required=false)String id) {
		String data = bss.bookSellListMy(id);
		System.out.println("BookStoreController : " + data);
		return data;
	}
	@RequestMapping("myBook")
	public String myBook() {
		return "bookstore/myBook";
	}
	
	@RequestMapping("messagebox")
	public String messagebox() {
		return "bookstore/messageBox";
	}
	
}