package com.net.toooen.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangShaoFeng
 * @Description: 操作文件工具
 * @date 2016年3月7日
 */
public class FileUtil {
	private static final Logger Log = LoggerFactory.getLogger(FileUtil.class);

	private static File file;

	/**
	 * 判断文件或目录是否存在
	 * 
	 * @param dirName
	 * @return Boolean
	 */
	public static Boolean isDir(String dirName) {
		file = new File(dirName);
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 创建目录-不存在就创建
	 * 
	 * @param dirName
	 * @return void
	 */
	public static void createDirectory(String dirName) {
		file = new File(dirName);
		try {
			if (!FileUtil.isDir(dirName))
				file.mkdirs();

		} catch (Exception e) {
			Log.error("[文件操作]  创建目录错误:目录名：" + dirName + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 */
	public static void deleteFile(String sPath) {
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 */
	public static void deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (dirFile.exists() && dirFile.isDirectory()) {
			// 删除文件夹下的所有文件(包括子目录)
			File[] files = dirFile.listFiles();
			for (int i = 0; i < files.length; i++) {
				// 删除子文件
				if (files[i].isFile()) {
					deleteFile(files[i].getAbsolutePath());
				} // 删除子目录
				else {
					deleteDirectory(files[i].getAbsolutePath());
				}
			}
			// 删除当前目录
			dirFile.delete();
		}
	}

	/**
	 * 追加方式写
	 * 
	 * @param fileName
	 * @param context
	 * @return void
	 */
	public static void writerFileForAppend(String fileName, String context) {
		OutputStream os;
		try {
			os = new FileOutputStream(fileName, true);
			// 创建字符缓冲流
			BufferedOutputStream bout = new BufferedOutputStream(os);

			byte[] buff = context.getBytes();

			bout.write(buff);
			bout.flush();
			bout.close();

		} catch (FileNotFoundException e) {
			Log.error("[文件操作]  文件没找到:文件名" + fileName + e.getMessage());
		} catch (IOException e) {
			Log.error("[文件操作]  文件追加写入出错:文件名" + fileName + e.getMessage());
		}
	}

	/**
	 * 读取文件内容
	 * 
	 * @param fileName
	 *            文件名
	 * @throws IOException
	 * @return String 文件内容
	 */
	public static String readFile(String fileName) throws IOException {
		StringBuilder stb = new StringBuilder();
		InputStream is = new FileInputStream(fileName);
		// 定义一个字节类型数组，所有的文件都是字节形式存在的
		byte[] buff = new byte[200];// 每次从文件中读取200个字节
		// 每次读取的实际字节长度
		int length = 0;

		// is.read()方法：从buff缓中区的第0个位开始读取字节，每次最多读取200，
		// 方法会返回一个实际读取的长度，用length接收，当值为-1时，表示所有的字节全部读取完毕
		while ((length = is.read(buff, 0, 200)) != -1) {
			// 把字节以平台的默认编码方式转为字符，从buff的第0个位开始转换，实际要转换的长度是length
			stb.append(new String(buff, 0, length));
		}
		// 关闭流
		is.close();
		return stb.toString();

	}

	/**
	 * 获取当前目录下所有的文件名
	 * 
	 * @param dirName
	 *            路径名
	 * @param postfix
	 *            文件后缀名
	 * @return 目录下所有文件名String[]
	 */
	public static String[] getCurrFile(String dirName, String postfix) {

		File dirFile = new File(dirName);
		String[] fileNames = dirFile.list();
		List<String> list = new ArrayList<String>();
		// 文件过滤，只需要txt文件,防止非指令文件读取
		for (String name : fileNames) {
			// 文件名过滤
			if (name.endsWith(postfix)) {
				// System.out.println(name); // 只打印该目录下的.xml文件
				list.add(dirName + "/" + name);
			}
		}

		if (fileNames.length == 0)
			return null;
		// 把泛型转成字符数组
		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	/**
	 * 复制文件
	 * 
	 * @param inFile
	 *            源文件
	 * @param outFile
	 *            目标文件
	 * @return
	 * @throws Exception
	 */
	public static Boolean copyFile(String inFile, String outFile) {
		Boolean falg = true;
		// 源文件装载
		File in = new File(inFile);
		try {
			// 输出目标文件装载
			File out = new File(outFile);
			// 套上输入文件流--源文件流
			FileInputStream fin = new FileInputStream(in);
			// 套上输出文件流---目标流
			FileOutputStream fout = new FileOutputStream(out);
			// 一次性读取的最大字节数
			int length = 2097152;// 2m内存
			byte[] buffer = new byte[length];
			while (true) {
				// 读入内存
				int ins = fin.read(buffer);
				// 值为-1表示读到文件尾
				if (ins == -1) {
					fin.close();
					fout.flush();
					fout.close();
					break;

				} else
					// 把buffer中的字节内容写入（输出）到目标文件
					fout.write(buffer, 0, ins);
			}
		} catch (Exception e) {
			Log.error("[文件操作]文件复制出错" + e.getMessage());
			falg = false;
		}
		return falg;
	}

	/**
	 * @Title: fileUpload
	 * @Description: 文件上传方法
	 * @param request
	 * @param picFile
	 * @return 文件路径
	 * @throws
	 */
	public static String fileUpload(MultipartFile file, String folderPath) {
		// 获取文件名
		String fileName = "/" + getYMDHMSS() + "-" + file.getOriginalFilename();
		String fileUploadPath = Constants.FILE_UPLOAD_PATH + folderPath;
		// 创建目录
		createDirectory(fileUploadPath);
		boolean flag = true;
		OutputStream bos = null;
		InputStream stream = null;
		try {
			stream = file.getInputStream();
			bos = new FileOutputStream(fileUploadPath + fileName);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = stream.read(buffer, 0, 1024)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bos) {
					bos.close();
				}
				if (null != stream) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (flag) {
			return folderPath + fileName;
		} else {
			return null;
		}
	}

	/**
	 * 取当前年月日
	 * 
	 * @return
	 */
	public static String getYMDHMSS() {
		return nowDateForStr("yyyyMMddHHmmss");
	}

	public static String nowDateForStr(String format) {
		Date date = new Date();
		return dateForString(date, format);
	}

	/**
	 * 将日期转为指定格式的字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return
	 * @author yuanchong
	 * @version falvm
	 * @date Dec 9, 2009
	 * @return String
	 */
	public static String dateForString(Date date, String format) {
		String dateTime = "";

		if (date == null) {
			return dateTime;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(format);

		dateTime = formatter.format(date);

		return dateTime;
	}

	public static Date stringForDate(String date, String format)
			throws ParseException {
		Date dateTime = null;

		if (date == null) {
			return dateTime;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(format);

		dateTime = formatter.parse(date);

		return dateTime;
	}

	/** get yyyy_MM_dd now() */
	public static String getDateNow() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}

	public static String getFormmatTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	public static int getWorkDayBetween(String date1, String date2)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date begDate = sdf.parse(date1);
		Date endDate = sdf.parse(date2);
		// 总天数
		int days = (int) ((endDate.getTime() - begDate.getTime()) / (24 * 60 * 60 * 1000)) + 1;
		// 总周数，
		int weeks = days / 7;
		int rs = 0;
		// 整数周
		if (days % 7 == 0) {
			rs = days - 2 * weeks;
		} else {
			Calendar begCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			begCalendar.setTime(begDate);
			endCalendar.setTime(endDate);
			// 周日为1，周六为7
			int beg = begCalendar.get(Calendar.DAY_OF_WEEK);
			int end = endCalendar.get(Calendar.DAY_OF_WEEK);
			if (beg > end) {
				rs = days - 2 * (weeks + 1);
			} else if (beg < end) {
				if (end == 7) {
					rs = days - 2 * weeks - 1;
				} else {
					rs = days - 2 * weeks;
				}
			} else {
				if (beg == 1 || beg == 7) {
					rs = days - 2 * weeks - 1;
				} else {
					rs = days - 2 * weeks;
				}
			}
		}
		return rs;
	}

	/*
	 * 获取两个日期之间的所有日期，格式：xx月xx日
	 */
	public static List<String> getDayBetween(Calendar startDay, Calendar endDay) {
		@SuppressWarnings("unused")
		DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
		if (startDay.compareTo(endDay) > 0) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		Calendar currentPrintDay = startDay;
		while (currentPrintDay.compareTo(endDay) <= 0) {
			DateFormat FORMATTER1 = new SimpleDateFormat("MM月dd日");
			list.add(FORMATTER1.format(currentPrintDay.getTime()));
			currentPrintDay.add(Calendar.DATE, 1);
		}
		return list;

	}
}
