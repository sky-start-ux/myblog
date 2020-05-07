package com.example.blog.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUploadUtils {

    private final static String AVATAR_PATH_PREFIX = "static/images/avatar";
    private final static String QQPaymentCode_PATH_PREFIX = "static/images/QQPaymentCode";
    private final static String weChatPaymentCode_PATH_PREFIX = "static/images/weChatPaymentCode";
    private final static String weChatQrCode_PATH_PREFIX = "static/images/weChatQrCode";

    private static File getImgDirFile(int flag){
        String fileDirPath = "";
        switch (flag){
            case 1:
                // 构建上传文件的存放 “文件夹” 路径
                fileDirPath = "src/main/resources/" + AVATAR_PATH_PREFIX;
                break;
            case 2:
                fileDirPath = "src/main/resources/" + QQPaymentCode_PATH_PREFIX;
                break;
            case 3:
                fileDirPath = "src/main/resources/" + weChatPaymentCode_PATH_PREFIX;
                break;
            case 4:
                fileDirPath = "src/main/resources/" + weChatQrCode_PATH_PREFIX;
                break;
        }
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }

    private static String Upload(MultipartFile file,String username,int flag){
        String filename = file.getOriginalFilename();
        filename = username + '-' + filename;
        File fileDir = FileUploadUtils.getImgDirFile(flag);
        String path = fileDir.getAbsolutePath() + File.separator + filename;
        try {
            File newFile = new File(path);
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static String getPath(MultipartFile avatar,String nickname,int flag){
        String filePath = FileUploadUtils.Upload(avatar, nickname, flag);
        filePath = filePath.substring(filePath.indexOf("images"));
        filePath = filePath.replace("\\","/");
        filePath = "/" + filePath;
        return filePath;
    }

}
