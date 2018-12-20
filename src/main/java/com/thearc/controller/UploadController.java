package com.thearc.controller;

import com.thearc.domain.ConfigProfile;
import com.thearc.util.MediaUtils;
import com.thearc.util.UploadFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Controller
public class UploadController {

  /*@Resource(name = "uploadPath")
  private String uploadPath;
*/

  @Autowired
  private ConfigProfile profile;

  @GetMapping("/uploadForm")
  public void uploadForm() {
  }

  @PostMapping("/uploadForm")
  public String uploadForm(MultipartFile file, Model model) throws Exception {

    log.info("originalName: " + file.getOriginalFilename());
    log.info("size: " + file.getSize());
    log.info("contentType: " + file.getContentType());

    String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());

    model.addAttribute("savedName", savedName);

    return "uploadResult";
  }

  @GetMapping("/uploadAjax")
  public void uploadAjax() {
	  
  }

  private String uploadFile(String originalName, byte[] fileData) throws Exception {

    UUID uid = UUID.randomUUID();

    String savedName = uid.toString() + "_" + originalName;

    File target = new File(profile.getUploadPath(), savedName);

    FileCopyUtils.copy(fileData, target);

    return savedName;

  }
  
  @ResponseBody
  @RequestMapping(value ="/uploadAjax", method=RequestMethod.POST, 
                  produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> uploadAjax(MultipartFile file)throws Exception{
    
    log.info("originalName: " + file.getOriginalFilename());
    
   
    return 
      new ResponseEntity<>(
          UploadFileUtils.uploadFile(profile.getUploadPath(),
                file.getOriginalFilename(), 
                file.getBytes()), 
          HttpStatus.CREATED);
  }
  
  
  @ResponseBody
  @RequestMapping("/displayFile")
  public ResponseEntity<byte[]>  displayFile(String fileName)throws Exception{
    
    InputStream in = null; 
    ResponseEntity<byte[]> entity = null;
    
    log.info("FILE NAME: " + fileName);
    
    try{
      
      String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
      
      MediaType mType = MediaUtils.getMediaType(formatName);
      
      HttpHeaders headers = new HttpHeaders();
      
      in = new FileInputStream(profile.getUploadPath()+fileName);
      
      if(mType != null){
        headers.setContentType(mType);
      }else{
        
        fileName = fileName.substring(fileName.indexOf("_")+1);       
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Content-Disposition", "attachment; filename=\""+ 
          new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
      }

        entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
          headers, 
          HttpStatus.CREATED);
    }catch(Exception e){
      e.printStackTrace();
      entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
    }finally{
      in.close();
    }
      return entity;    
  }
    
  @ResponseBody
  @PostMapping("/deleteFile")
  public ResponseEntity<String> deleteFile(String fileName){
    
    log.info("delete file: "+ fileName);
    
    String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
    
    MediaType mType = MediaUtils.getMediaType(formatName);
    
    if(mType != null){      
      
      String front = fileName.substring(0,12);
      String end = fileName.substring(14);
      new File(profile.getUploadPath()+ (front+end).replace('/', File.separatorChar)).delete();
    }
    
    new File(profile.getUploadPath()+ fileName.replace('/', File.separatorChar)).delete();
    
    
    return new ResponseEntity<String>("deleted", HttpStatus.OK);
  }  
  
  @ResponseBody
  @PostMapping("/deleteFiles")
  public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[] files){
    
    log.info("delete all files: "+ Arrays.toString(files));
    
    if(files == null || files.length == 0) {
      return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }
    return new ResponseEntity<String>("deleted", HttpStatus.OK);
  }  

}
