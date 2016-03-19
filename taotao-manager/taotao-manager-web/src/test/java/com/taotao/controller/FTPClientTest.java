package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPClientTest {

	//@Test
	public void testFtp() throws Exception {
		//1、连接ftp服务器
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.25.133", 21);
		//2、登录ftp服务器
		ftpClient.login("ftpuser", "ftpuser");
		//3、读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("D:\\Documents\\Pictures\\images\\2010062119283578.jpg"));
		//4、上传文件
		//1）指定上传目录
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//2）指定文件类型
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		//第一个参数：文件在远程服务器的名称
		//第二个参数：文件流
		ftpClient.storeFile("hello.jpg", inputStream);
		//5、退出登录
		ftpClient.logout();
	}

}
