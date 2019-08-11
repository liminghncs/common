package com.tanghuzi.common.utils;

import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片处理工具类
 * @version 1.0
 * @author: Liming
 * @date: 2019/2/9 23:44
 **/
public class ImageUtil {
    /**
     * 按指尺寸等比例缩放图片
     * @param imageFile 原图对象
     * @param newPath 缩略图保存路径名称
     * @param size 缩略尺寸
     * @throws IOException
     */
    public static void zoomImageScale(File imageFile, String newPath,int size) throws IOException {
        if(!imageFile.canRead())
            return;
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        if (null == bufferedImage)
            return;
        //获取原图的宽度和高度
        int originalWidth = bufferedImage.getWidth();
        int originalHeight = bufferedImage.getHeight();

        int newWidth=0;
        int newHeight=0;
        //原图尺寸小于缩略图的情况
        if(originalHeight<size && originalWidth<size)
        {
            newWidth=originalWidth;
            newHeight=originalHeight;
        }
        //以宽度为基准等比例缩放图片
        else if(originalWidth>originalHeight)
        {
            newWidth=size;
            double scale = (double)originalWidth / (double)newWidth;    // 缩放的比例
            newHeight =  (int)(originalHeight / scale);
        }
        //以高度为基准等比例缩放图片
        else{
            newHeight=size;
            double scale = (double)originalHeight / (double)newHeight;    // 缩放的比例
            newWidth =  (int)(originalWidth / scale);
        }
        //生成缩略图
        zoomImageUtils(imageFile, newPath, bufferedImage, newWidth, newHeight);
    }
    private static void zoomImageUtils(File imageFile, String newPath, BufferedImage bufferedImage, int width, int height)
            throws IOException{
        String suffix = StringUtils.substringAfterLast(imageFile.getName(), ".");

        // 处理 png 背景变黑的问题
        if(suffix != null && (suffix.trim().toLowerCase().endsWith("png") || suffix.trim().toLowerCase().endsWith("gif"))){
            BufferedImage to= new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = to.createGraphics();
            to = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            g2d.dispose();

            g2d = to.createGraphics();
            Image from = bufferedImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();

            ImageIO.write(to, suffix, new File(newPath));
        }else{
            // 高质量压缩，其实对清晰度而言没有太多的帮助
//            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//            tag.getGraphics().drawImage(bufferedImage, 0, 0, width, height, null);
//
//            FileOutputStream out = new FileOutputStream(newPath);    // 将图片写入 newPath
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
//            jep.setQuality(1f, true);    //压缩质量, 1 是最高值
//            encoder.encode(tag, jep);
//            out.close();
            BufferedImage newImage = new BufferedImage(width, height, bufferedImage.getType());
            Graphics g = newImage.getGraphics();
            g.drawImage(bufferedImage, 0, 0, width, height, null);
            g.dispose();
            ImageIO.write(newImage, suffix, new File(newPath));
        }
    }

}
