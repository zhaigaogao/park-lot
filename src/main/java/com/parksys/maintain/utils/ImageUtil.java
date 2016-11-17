package com.parksys.maintain.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.log4j.Logger;

import com.alibaba.druid.support.logging.Log;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtil {
	// static {
	// Slf4jUtil.buildSlf4jUtil().loadSlf4j();
	// }
	private static Logger log = Logger.getLogger("ImageUtil");
	
	private static String DEFAULT_THUMB_PREVFIX = "th";
	private static String DEFAULT_CUT_PREVFIX = "cut_";
	private static Boolean DEFAULT_FORCE = false;

	/**
	 * <p>
	 * Title: cutImage
	 * </p>
	 * <p>
	 * Description: 根据原图与裁切size截取局部图片
	 * </p>
	 * 
	 * @param srcImg
	 *            源图片
	 * @param output
	 *            图片输出流
	 * @param rect
	 *            需要截取部分的坐标和大小
	 */
	public void cutImage(File srcImg, OutputStream output,
			java.awt.Rectangle rect) {
		if (srcImg.exists()) {
			java.io.FileInputStream fis = null;
			ImageInputStream iis = null;
			try {
				fis = new FileInputStream(srcImg);
				// ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG,
				// JPEG, WBMP, GIF, gif]
				String types = Arrays.toString(ImageIO.getReaderFormatNames())
						.replace("]", ",");
				String suffix = null;
				// 获取图片后缀
				if (srcImg.getName().indexOf(".") > -1) {
					suffix = srcImg.getName().substring(
							srcImg.getName().lastIndexOf(".") + 1);
				}// 类型和图片后缀全部小写，然后判断后缀是否合法
				if (suffix == null
						|| types.toLowerCase().indexOf(
								suffix.toLowerCase() + ",") < 0) {
					return;
				}
				// 将FileInputStream 转换为ImageInputStream
				iis = ImageIO.createImageInputStream(fis);
				// 根据图片类型获取该种类型的ImageReader
				ImageReader reader = ImageIO.getImageReadersBySuffix(suffix)
						.next();
				reader.setInput(iis, true);
				ImageReadParam param = reader.getDefaultReadParam();
				param.setSourceRegion(rect);
				BufferedImage bi = reader.read(0, param);
				ImageIO.write(bi, suffix, output);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null)
						fis.close();
					if (iis != null)
						iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
		}
	}

	public void cutImage(File srcImg, OutputStream output, int x, int y,
			int width, int height) {
		cutImage(srcImg, output, new java.awt.Rectangle(x, y, width, height));
	}

	public void cutImage(File srcImg, String destImgPath,
			java.awt.Rectangle rect) {
		File destImg = new File(destImgPath);
		if (destImg.exists()) {
			String p = destImg.getPath();
			try {
				if (!destImg.isDirectory())
					p = destImg.getParent();
				if (!p.endsWith(File.separator))
					p = p + File.separator;
				cutImage(srcImg,
						new java.io.FileOutputStream(p + DEFAULT_CUT_PREVFIX
								+ "_" + new java.util.Date().getTime() + "_"
								+ srcImg.getName()), rect);
			} catch (FileNotFoundException e) {
			}
		}
	}

	public void cutImage(File srcImg, String destImg, int x, int y, int width,
			int height) {
		cutImage(srcImg, destImg, new java.awt.Rectangle(x, y, width, height));
	}

	public void cutImage(String srcImg, String destImg, int x, int y,
			int width, int height) {
		cutImage(new File(srcImg), destImg, new java.awt.Rectangle(x, y, width,
				height));
	}

	/**
	 * <p>
	 * Title: thumbnailImage
	 * </p>
	 * <p>
	 * Description: 根据图片路径生成缩略图
	 * </p>
	 * 
	 * @param imagePath
	 *            原图片路径
	 * @param w
	 *            缩略图宽
	 * @param h
	 *            缩略图高
	 * @param prevfix
	 *            生成缩略图的前缀
	 * @param force
	 *            是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
	 */
	public static void thumbnailImage(File srcImg, OutputStream output, int w,
			int h, String prevfix, boolean force) {
		if (srcImg.exists()) {
			try {
				// ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG,
				// JPEG, WBMP, GIF, gif]
				String types = Arrays.toString(ImageIO.getReaderFormatNames())
						.replace("]", ",");
				String suffix = null;
				// 获取图片后缀
				if (srcImg.getName().indexOf(".") > -1) {
					suffix = srcImg.getName().substring(
							srcImg.getName().lastIndexOf(".") + 1);
				}// 类型和图片后缀全部小写，然后判断后缀是否合法
				if (suffix == null
						|| types.toLowerCase().indexOf(
								suffix.toLowerCase() + ",") < 0) {
					return;
				}
				Image img = ImageIO.read(srcImg);
				// 根据原图与要求的缩略图比例，找到最合适的缩略图比例
				if (!force) {
					int width = img.getWidth(null);
					int height = img.getHeight(null);
					if (((width * 1.0) / w) > 1 || ((height * 1.0) / h) > 1) {
						if ((width * 1.0) / w < (height * 1.0) / h) {
							if (width > w) {
								h = Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(height * w
												/ (width * 1.0)));
							}
						} else {
							if (height > h) {
								w = Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(width * h
												/ (height * 1.0)));
							}
						}
					} else {
						w = width;
						h = height;
					}
				}

				BufferedImage bi = new BufferedImage(w, h,
						BufferedImage.TYPE_INT_RGB);
				Graphics g = bi.getGraphics();
				g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
				g.dispose();
				// 将图片保存在原目录并加上前缀
				ImageIO.write(bi, suffix, output);
				output.close();
			} catch (IOException e) {
			}
		}
	}

	public static void thumbnailImage(File srcImg, int w, int h,
			String prevfix, boolean force) {
		String p = srcImg.getAbsolutePath();
		try {
			if (!srcImg.isDirectory())
				p = srcImg.getParent();
			if (!p.endsWith(File.separator))
				p = p + File.separator;
			thumbnailImage(srcImg, new java.io.FileOutputStream(p + prevfix
					+ srcImg.getName()), w, h, prevfix, force);
		} catch (FileNotFoundException e) {
		}
	}

	public static void thumbnailImage(String imagePath, int w, int h,
			String prevfix, boolean force) {
		File srcImg = new File(imagePath);
		thumbnailImage(srcImg, w, h, prevfix, force);
	}

	public static void thumbnailImage(String imagePath, int w, int h,
			boolean force) {
		thumbnailImage(imagePath, w, h, DEFAULT_THUMB_PREVFIX, DEFAULT_FORCE);
	}

	public static void thumbnailImage(String imagePath, int w, int h) {
		thumbnailImage(imagePath, w, h, DEFAULT_FORCE);
	}

	public static void main(String[] args) throws IOException {
		imageCompress("E:\\APP\\", "333.png", "444.png", 0.5f, 0.5f, 2448, 3264);
	}

	/**
	 * 描述：
	 * 
	 * @param path
	 *            需要压缩的图片路径
	 * @param fileName
	 *            要压缩的图片名称
	 * @param toFileName
	 *            压缩后的图片名称
	 * @param scale
	 *            压缩比例 不能大于1,默认0.5
	 * @param quality
	 *            压缩品质介于0.1~1.0之间
	 * @param width
	 *            压缩后的图片的宽度
	 * @param height
	 *            压缩后的图片的高度 返回值：void
	 * @throws IOException 
	 */
	public static void imageCompress(String path, String fileName, String toFileName,
			float scale, float quality, int width, int height) throws IOException {
		 // 原图路径 原图名称 目标路径 压缩比率0.5 0.75 原图宽度 原图高度
			long start = System.currentTimeMillis();
			Image image = javax.imageio.ImageIO.read(new File(path + fileName));
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);
			// if (scale > 0.5)
			scale = 1.0f;// 默认压缩比为0.5，压缩比越大，对内存要去越高，可能导致内存溢出
			// 按比例计算出来的压缩比
			 float realscale = getRatio(imageWidth, imageHeight, width,
			 height);
			 float finalScale = Math.min(scale, realscale);// 取压缩比最小的进行压缩
			 imageWidth = (int) (finalScale * imageWidth);
			 imageHeight = (int) (finalScale * imageHeight);

			image = image.getScaledInstance(imageWidth, imageHeight,
					Image.SCALE_AREA_AVERAGING);
			// Make a BufferedImage from the Image.
			BufferedImage mBufferedImage = new BufferedImage(imageWidth,
					imageHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = mBufferedImage.createGraphics();

			g2.drawImage(image, 0, 0, imageWidth, imageHeight, Color.white,
					null);
			g2.dispose();

			float[] kernelData2 = { -0.125f, -0.125f, -0.125f, -0.125f, 2,
					-0.125f, -0.125f, -0.125f, -0.125f };
			Kernel kernel = new Kernel(3, 3, kernelData2);
			ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
			mBufferedImage = cOp.filter(mBufferedImage, null);

			FileOutputStream out = new FileOutputStream(path + toFileName);
			System.out.println(path + toFileName);
			// JPEGEncodeParam param =
			// encoder.getDefaultJPEGEncodeParam(bufferedImage);
			// param.setQuality(0.9f, true);
			// encoder.setJPEGEncodeParam(param);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder
					.getDefaultJPEGEncodeParam(mBufferedImage);
			param.setQuality(quality, true);// 默认0.75
			encoder.setJPEGEncodeParam(param);
			encoder.encode(mBufferedImage);
			out.close();
			long end = System.currentTimeMillis();
			System.out.println("图片：" + fileName + "，压缩时间：" + (end - start)
					+ "ms");
	}

	public static void imageCompress(String path, String fileName,
			String toFileName, float scale, int width, int height) throws IOException {
		imageCompress(path, fileName, toFileName, scale, 0.75f, width, height);
	}

	private static float getRatio(int width, int height, int maxWidth,
			int maxHeight) {// 获得压缩比率的方法
		float Ratio = 1.0f;
		float widthRatio;
		float heightRatio;
		widthRatio = (float) maxWidth / width;
		heightRatio = (float) maxHeight / height;
		if (widthRatio < 1.0 || heightRatio < 1.0) {
			Ratio = widthRatio <= heightRatio ? widthRatio : heightRatio;
		}
		return Ratio;
	}

	public static byte[] convertImage2Type(String imageFile, String imageType)
			throws Exception {// 图片格式转换
		File inputFile = new File(imageFile);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		BufferedImage input = ImageIO.read(inputFile);
		ImageIO.write(input, imageType, output);
		return output.toByteArray();
	}

	public static void convertImage2TypePng(String imageFile, String imageType)
			throws Exception {// 图片格式转换
		File inputFile = new File(imageFile);
		int suffixIndex = imageFile.lastIndexOf(".");
		String suffix = imageFile.substring(suffixIndex + 1);
		if (!"png".equals(suffix)) {// 如果原图片的不是PNG格式的图片
			String fileName = imageFile.substring(0, suffixIndex + 1) + "png";
			File output = new File(fileName);
			BufferedImage input = ImageIO.read(inputFile);
			ImageIO.write(input, imageType, output);
			// 转换后删除原文件
			if (inputFile.exists())
				inputFile.delete();
		}
	}
}
