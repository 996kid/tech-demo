package test;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ImageMerger {

    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input.add("D:/996kid/4b7e6420-f71e-4325-8dd7-dc24f1e481aa_1.png");
        input.add("D:/996kid/4b7e6420-f71e-4325-8dd7-dc24f1e481aa_2.png");
        input.add("D:/996kid/4b7e6420-f71e-4325-8dd7-dc24f1e481aa_3.png");
        input.add("D:/996kid/4b7e6420-f71e-4325-8dd7-dc24f1e481aa_4.png");
        merge(input, "D:/996kid/merge.png");
    }


    public static void merge(List<String> input, String outputFilePath) {
        try {
            List<File> files = new ArrayList<>();
            for (String fileString : input) {
                files.add(new File(fileString));
            }
            // 读取所有图片到内存
            List<BufferedImage> images = new ArrayList<>();
            int width = 0;
            int totalHeight = 0;
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                images.add(image);
                if (width == 0) {
                    width = image.getWidth();
                }
                totalHeight += image.getHeight();
            }

            // 创建合并后的图片
            BufferedImage mergedImage = new BufferedImage(width, totalHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = mergedImage.createGraphics();

            // 将每张图片绘制到最终图片中
            int currentHeight = 0;
            for (BufferedImage image : images) {
                g2d.drawImage(image, 0, currentHeight, null);
                currentHeight += image.getHeight();
            }
            g2d.dispose();

            // 保存合并后的图片
            ImageIO.write(mergedImage, "PNG", new File(outputFilePath));
            System.out.println("mergedImage.width: "+mergedImage.getWidth() +", height: " + mergedImage.getHeight());
            log.info("合并后的图片已保存至: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
