package com.ceshiren;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PngTest {

    @Test
    @DisplayName("allure注解的方式添加图片")
    public void testAddPngAn() throws IOException {
        byte[] contents = Files.newInputStream(Paths.get("image1.png")).readAllBytes();
        attachPngFile(contents, "allure图片");
    }

    @Attachment(value = "{pngName}", type = "image/png", fileExtension = "png")
    private byte[] attachPngFile(byte[] contents, String pngName) {
        System.out.println("图片name:" + pngName);
        return contents;
    }

    @Test
    @DisplayName("allure方法的方式添加图片")
    public void testAddPngMethod() throws IOException {
        Allure.addAttachment("截图", "image/png", Files.newInputStream(Paths.get("image1.png")), "png");
    }
}
