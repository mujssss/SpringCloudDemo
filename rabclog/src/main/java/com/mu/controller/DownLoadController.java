package com.mu.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@Controller
@RequestMapping("/file")
public class DownLoadController {

    @RequestMapping("/down")
    public String downPage(){
        return "down";
    }


    /**
     *
     * @param request
     * @param realPath   文件完整路径
     * @param userAgent
     * @param filename     下载的文件名
     * @param inline      预览  下载 标识位
     * @return
     */
    @RequestMapping("/download")
    public ResponseEntity<byte[]> downlaodFile(HttpServletRequest request, @RequestParam("path") String realPath
            , @RequestHeader("user-agent") String userAgent, @RequestParam("filename") String filename
            , @RequestParam(required = false,defaultValue = "false") boolean inline ) {
        // 根路径加上传参数的路径构成文件路径地址
        File file = new File(realPath);
        // 构建响应
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok();
        bodyBuilder.contentLength(file.length());
        // 二进制数据流


        bodyBuilder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        // 文件名编码
        try {
            String encodeFileName = URLEncoder.encode(filename, "UTF-8");
            // IE
            if (userAgent.indexOf("MSIE")>0){
                bodyBuilder.header("Content-Disposition","attachment;filename="+encodeFileName);
            }else {
                // 其他浏览器
                if (inline){
                    // 在浏览器中打开
                    URL url = new URL("file:///" + file);
                    bodyBuilder.header("Content-Type",url.openConnection().getContentType());
                    bodyBuilder.header("Content-Disposition","inline;filename*=UTF-8''"+encodeFileName);
                }else {
                    // 直接下载
                    bodyBuilder.header("Content-Disposition","attachment;filename*=UTF-8''"+encodeFileName);
                }

            }
            // 下载成功返回二进制流
            return bodyBuilder.body(FileUtils.readFileToByteArray(file));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        // 下载失败直接返回错误的请求
        return (ResponseEntity<byte[]>) ResponseEntity.badRequest();

    }
}
