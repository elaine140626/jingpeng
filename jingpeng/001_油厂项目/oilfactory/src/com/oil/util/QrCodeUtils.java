package com.oil.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QrCodeUtils {


	public static void qrCodeShow(HttpServletRequest request,HttpServletResponse response,String contents) throws Exception {
		try {
		    Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		    try {
		            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, 200, 200,hints); // 1、读取文件转换为字节数组
//		            ByteArrayOutputStream out = new ByteArrayOutputStream();
		            BufferedImage image = toBufferedImage(bitMatrix);
		            //转换成png格式的IO流
		            ImageIO.write(image, "png", response.getOutputStream());
//		            byte[] bytes = out.toByteArray();
//		            // 2、将字节数组转为二进制
//		            BASE64Encoder encoder = new BASE64Encoder();
//		            binary = encoder.encodeBuffer(bytes).trim();
		        } catch (WriterException e) { // TODO Auto-generated catch block
		            e.printStackTrace();
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * image流数据处理
	 *
	 * @author ianly
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
	    int width = matrix.getWidth();
	    int height = matrix.getHeight();
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    for (int x = 0; x < width; x++) {
	        for (int y = 0; y < height; y++) {
	            image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
	        }
	    }
	    return image;
	}
	
	
	
}
