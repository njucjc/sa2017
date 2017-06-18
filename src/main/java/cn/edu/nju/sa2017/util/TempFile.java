package cn.edu.nju.sa2017.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public final class TempFile {
    public static java.io.File createTempFile(MultipartFile file) {
        try {
            java.io.File f = new File("./target/classes/" + file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(file.getBytes());
            fos.close();
            return f;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getUniqueSignature(String t) {
        return t + '-' + UUID.randomUUID().toString() + '-' + new Date().getTime();
    }

}
