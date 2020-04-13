package com.zhongjianbaoapi.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GenerateAvatarImage {

    public static void main(String[] args) throws IOException {

        // 来源图
        BufferedImage bi1 = ImageIO.read(new File("D:\\uploadFiles\\APP\\346895\\headPortrait\\1564131124730.jpg"));
        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
        BufferedImage image = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());

        Graphics2D g2 = image.createGraphics();
        image = g2.getDeviceConfiguration().createCompatibleImage(bi1.getWidth(), bi1.getHeight(),
                Transparency.TRANSLUCENT);
        g2 = image.createGraphics();

        // 将背景设置为透明。如果注释该段代码，默认背景为白色.也可通过g2.setPaint(paint) 设置背景色
        g2.setComposite(AlphaComposite.Clear);
        g2.fill(new Rectangle(image.getWidth(), image.getHeight()));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2.drawImage(bi1, 0, 0, null);
        g2.dispose();

        try {
            // 输出图地址
            ImageIO.write(image, "PNG", new File("D:\\uploadFiles\\APP\\346895\\headPortrait\\1564131124730.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
