package ando.vc;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import net.sourceforge.tess4j.util.LoadLibs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 自动识别数字类型验证码
 *
 * @author ChangBao
 * @date 2020年10月26日 15:48:36
 */
public class IdentifyVC {

    private static final String DEST = "tessdata";
    public static final String vc_4043 = "src/main/resources/img/vc_4043.png";
    public static final String vc_8505 = "src/main/resources/img/vc_8505.png";

    public static void main(String[] args) throws Exception {
        getStringValueFromImage();
    }

    private static void getStringValueFromImage() throws Exception {
        File imageFile = new File(vc_8505); // vc_4043
        BufferedImage image = ImageIO.read(imageFile);
        image = convertImage(image);

        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        //In case you don't have your own tessdata, let it also be extracted for you
        File tessDataFolder = LoadLibs.extractTessResources(DEST);
        //Set the tessdata path
        instance.setDatapath(tessDataFolder.getAbsolutePath());

        try {
            String result = instance.doOCR(image);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * 提高识别度 - 对图片进行放大处理
     */
    public static BufferedImage convertImage(BufferedImage image) throws Exception {
        //1.按指定宽高创建一个图像副本
        //image = ImageHelper.getSubImage(image, 0, 0, image.getWidth(), image.getHeight());
        //2.图像转换成灰度的简单方法 - 黑白处理
        image = ImageHelper.convertImageToGrayscale(image);
        //图像缩放 - 放大n倍图像
        image = ImageHelper.getScaledInstance(image, image.getWidth() * 4, image.getHeight() * 4);
        return image;
    }


}
