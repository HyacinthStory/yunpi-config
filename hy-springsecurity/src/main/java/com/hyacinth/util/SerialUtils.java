/********************************************
 * 功能说明: 序列号工具类
 * 模块名称: 通用工具模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2017年5月23日 下午2:34:24
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.hyacinth.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 序列号工具类
 * @author zhangfb
 * @version 1.0.0.1
 */
public class SerialUtils {
	
	private static final int MIN_ID = 1;
	private static final int MAX_ID = 999;
	
	private static final int DEF_LEN = 36;
	private static final char[] SEED_STR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final Random random = new Random();
	private static final SimpleDateFormat fileSdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static int id = 1;
	
	public static void main(String[] args) {
		for (int i = 0; i<3; i++) {
			String code = getCode();
			System.out.println(code);
		}
	}
	
	public synchronized static int getId() {
		if (id > MAX_ID) {
			id = MIN_ID;
		}
		return id++;
	}
	
	public static String getTimeId() {
		long sysTime = System.currentTimeMillis();
		return String.format("%d%03d", sysTime, getId());
	}

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

	/** 
     * 获得时间+6位随机编码
     * @return String
     */
    public static String getCode() {
    	String sysTime = DateUtils.getDateString(new Date(), "yyMMddHHmmss");
		return String.format("%s%s", sysTime, getCaptcha());
    }
    
	/** 
     * 获得6位随机验证码
     * @return String
     */
    public static String getCaptcha() {
    	char code[]= new char[6];
    	for (int i = 0; i < 6; i++) {
    		code[i] = SEED_STR[random.nextInt(10)];
    	}
        return new String(code);
    }
	
	/** 
     * 获得32位随机字符串
     * @return String
     */ 
    public static String getRandomId(){
    	char code[]= new char[32];
    	for (int i = 0; i < 32; i++) {
    		code[i] = SEED_STR[random.nextInt(DEF_LEN)];
    	}
        return new String(code);
    }

    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        return millis + String.format("%03d", end3);
    }

    /**
     * 文件名生成
     */
    public static String genFileName() {
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        return fileSdf.format(new Date()) + String.format("%03d", end3);
    }
}
