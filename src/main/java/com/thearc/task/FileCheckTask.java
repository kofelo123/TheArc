package com.thearc.task;

import com.thearc.domain.BoardAttachVO;
import com.thearc.domain.ConfigProfile;
import com.thearc.mapper.BoardMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 허정원
 * since 2018-10-15
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자                수정내용
 *  -----------    --------    ---------------------------
 *   2018-10-15
 *
 * </pre>
 */


@Log4j
@Component
public class FileCheckTask {

    @Autowired
    ConfigProfile configProfile;

    @Setter(onMethod_ = { @Autowired})
    private BoardMapper mapper;

    private String getFolderYesterDay(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();

        // 어제날짜 (ex 오늘이 16일이면 -> Mon Oct 15 11:58:55 KST 2018)
        cal.add(Calendar.DATE, -1);

        String str = sdf.format(cal.getTime());//2018-10-15

        return str.replace("-", File.separator); // 2018\10\15

    }

    @Scheduled(cron="0 0 2 * * *") //매일 새벽 2시에 동작
    public void checkFiles()throws Exception{

        log.info("File Check Task run...........");
        log.warn(new Date());

        //파일리스트 in DB
        List<BoardAttachVO> fileList = mapper.getOldFiles();

        //썸네일이 아닌 파일
        List<Path> fileListPaths = fileList.stream()
                                    .map(vo -> Paths.get(configProfile.getUploadPath()
                                                            ,vo.getFullName())).collect(Collectors.toList());

        //이미지파일- 썸네일파일
//        fileList.stream().filter(vo -> vo.isFileType() == true)
            fileList.stream()
                .map(vo -> Paths.get(configProfile.getUploadPath(), vo.getFullName().substring(0,11).replace("/",File.separator)
                        , "s_" + vo.getFullName())).collect(Collectors.toList())
                                    .forEach(p -> fileListPaths.add(p));


            log.warn("=================================");

            fileListPaths.forEach(p -> log.warn(p)); //DB에 등록된파일

            //어제날짜경로의 업로드된 모든파일(DB등록 안된것까지모두)
            File targetDir = Paths.get(configProfile.getUploadPath(),getFolderYesterDay()).toFile();

            File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false); //모든파일 - DB에 있는 파일 = 지워야할 파일

            log.warn("------------------");
            for(File file : removeFiles ){
                log.warn(file.getAbsolutePath());
                file.delete();
            }




    }



}
