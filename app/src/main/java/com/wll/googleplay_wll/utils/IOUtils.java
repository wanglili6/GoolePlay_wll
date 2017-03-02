package com.wll.googleplay_wll.utils;

import com.socks.library.KLog;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {
	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				KLog.e(e);
			}
		}
		return true;
	}
}
