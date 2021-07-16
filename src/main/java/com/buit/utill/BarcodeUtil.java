package com.buit.utill;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import cn.hutool.core.codec.Base64Encoder;

public class BarcodeUtil {

	public static String CHARACTER_SET = "utf-8";
	
	public static String FORMAT_JPG = "jpg";
	
	public static String FORMAT_PNG = "png";
	
	/**
	 * 生成条码
	 * @Title: createBarcode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param tm
	 * @param @param width
	 * @param @param height
	 * @param @param hints
	 * @param @return
	 * @param @throws WriterException
	 * @param @throws IOException    设定文件
	 * @return String    返回类型
	 * @author 龚方舟
	 * @throws
	 */
	public static String createBarcode(String tm, int width, int height, 
			Map<EncodeHintType,Object> config ) throws WriterException, IOException {
		BitMatrix bitMatrix;		
		bitMatrix = new MultiFormatWriter().encode(tm, BarcodeFormat.CODE_128,width,height,config);
		BufferedImage image  = MatrixToImageWriter.toBufferedImage(bitMatrix);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.flush();
		outputStream.flush();
        ImageIO.write(image, FORMAT_JPG, outputStream);
		return Base64Utils.base64Encode(outputStream.toByteArray());
	}
}
