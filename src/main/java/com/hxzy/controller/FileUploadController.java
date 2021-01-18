//package com.hxzy.controller;
//
//import com.hxzy.vo.Result;
//import com.hxzy.vo.ResultCode;
//import org.csource.common.MyException;
//import org.csource.common.NameValuePair;
//import org.csource.fastdfs.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.UUID;
//
///**
// * 通用文件上传和下载管理
// */
//@Controller
//@RequestMapping(value = "/api/file")
//public class FileUploadController {
//
//    /**
//     * fastdfs文件服务器地址
//     */
//    @Value("${fastdfs.server}")
//    private String fastdfs_server;
//
//    /**
//     * 根据id去数据库查询相应的值
//     * @return
//     */
//    @GetMapping(value = "/download")
//    public ResponseEntity<byte[]> download() throws IOException, MyException {
//
//        //读文件，返回 byte[]  字节流
//        //加载fastdfs.properties文件
//        ClientGlobal.init("fastdfs.properties");
//        //创建客户端
//        TrackerClient tracker = new TrackerClient();
//        //得到主服务器
//        TrackerServer trackerServer = tracker.getConnection();
//        //得到存储服务器
//        StorageServer storageServer = tracker.getStoreStorage(trackerServer);
//        //得到客户端
//        StorageClient1 client = new StorageClient1(trackerServer, storageServer);
//        //文件下载
//        String fileId="group1/M00/00/00/Cs4ADl9gIxeAOUY0AACdhMNWz8M313.png";
//        byte[] body =client.download_file1(fileId);
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attchement;filename=" + UUID.randomUUID().toString()+".png");
//        HttpStatus statusCode = HttpStatus.OK;
//        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
//        return entity;
//    }
//
//
//    /**
//     * 文件上传
//     * @return
//     * @throws IOException
//     * @throws MyException
//     */
//    @ResponseBody
//    @PostMapping(value = "/upload")
//    public Result fileUpload(MultipartFile attach) throws IOException, MyException {
//        ClientGlobal.init("fastdfs.properties");
//
//        //创建客户端
//        TrackerClient tracker = new TrackerClient();
//        //得到主服务器
//        TrackerServer trackerServer = tracker.getConnection();
//        //得到存储服务器
//        StorageServer storageServer = tracker.getStoreStorage(trackerServer);
//        //得到客户端
//        StorageClient1 client = new StorageClient1(trackerServer, storageServer);
//
//        //上传文件
//        String fileOrignName= attach.getOriginalFilename();
//        //设定文件参数(文件名，文件类型，文件大小，文件备注，等等，你所需的特性,开发自己加的)
//        NameValuePair[] metaList = new NameValuePair[2];
//        metaList[0] = new NameValuePair("fileName", fileOrignName);
//        metaList[1] = new NameValuePair("fileSize", attach.getSize()+"");
//
//        //文件后缀名
//        String extName= fileOrignName.substring(fileOrignName.lastIndexOf(".")+1);
//
//        String fileId = client.upload_file1(attach.getBytes(), extName, metaList);
//
//        Result result= Result.createResult_Data(ResultCode.OK, fastdfs_server+fileId);
//        return result;
//    }
//
//
//    public static void main(String[] args) throws IOException, MyException {
//
//        ClientGlobal.init("fastdfs.properties");
//
//        //创建客户端
//        TrackerClient tracker = new TrackerClient();
//        //得到主服务器
//        TrackerServer trackerServer = tracker.getConnection();
//        //得到存储服务器
//        StorageServer storageServer = tracker.getStoreStorage(trackerServer);
//        //得到客户端
//        StorageClient1 client = new StorageClient1(trackerServer, storageServer);
//
//        //上传文件
//        //设定文件参数(文件名，文件类型，文件大小，文件备注，等等，你所需的特性,开发自己加的)
////        NameValuePair[] metaList = new NameValuePair[2];
////        metaList[0] = new NameValuePair("fileName", "spring.png");
////        metaList[1] = new NameValuePair("fileSize", "自定义");
////
////        String fileId = client.upload_file1("C:\\spring.png", null, metaList);
////        System.out.println("upload success. file id is: " + fileId);
//
//
//        //下载
//        String fileId="group1/M00/00/00/Cs4ADl9gIxeAOUY0AACdhMNWz8M313.png";
//        byte[] result =client.download_file1(fileId);
//        System.out.println(" 文件下载结果is: " + result.length);
//         //定义一个输出流(另存为 d:\\hxzy.png)
//        FileOutputStream   outputStream=new FileOutputStream(new File("d:\\hxzy.png"));
//        outputStream.write(result,0,result.length);
//        outputStream.close();
//        System.out.println("文件另存成功d:\\hxzy.png");
//
//    }
//}
