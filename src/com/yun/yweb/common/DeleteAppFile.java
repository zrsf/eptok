package com.yun.yweb.common;

import java.io.File;

public class DeleteAppFile {
	
	//删除文件夹及里面的文件
	public static void toDeleteFolder(File file) {
		File[] subFiles = file.listFiles();
		if(subFiles != null && subFiles.length > 0) {
			for(File f : subFiles) {
				if(f.isFile()) {
					f.delete();
				}else if(f.isDirectory()) {
					toDeleteFolder(f);
				}else {
					f.delete();
				}
			}
		}
		file.delete();
	}
	
	//删除文件夹下最后更新时间大于等于1天的文件夹
	public static void toDeleteUselessFolder(File file) {
		long curTime = System.currentTimeMillis();
		File[] subFiles = file.listFiles();
		if(subFiles != null && subFiles.length > 0) {
			for(File f : subFiles) {
				if(f.isDirectory()) {
					long modified = file.lastModified();
					long uselessTime = (curTime - modified)/1000/3600;
					if(uselessTime >= 24) {
						toDeleteFolder(f);
					}
				}
			}
		}
	}
}
