package com.office.library.book.admin.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//서비스 클래스로 지정 --> IoC 컨테이너에 객체 생성
@Service
public class UploadFileService {
	//첨부파일 업로드 처리
	public String upload(MultipartFile file) {
		boolean result = false;
		//File 저장
		String fileOriName = file.getOriginalFilename();
		String fileExtension = //확장자 추출
				fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());
		String uploadDir = "C:\\library\\upload\\"; //파일 업로드 위치
		
		//첨부파일의 이름을 유일하게 지정하기 위한 루틴
		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replaceAll("-","");
		File saveFile = new File(uploadDir + "\\" + uniqueName + fileExtension);
		
		//지정된 폴더가 존재하지 않을 경우 폴더를 생성함
		if(!saveFile.exists()) saveFile.mkdirs();
		
		try { //파일 전송
			file.transferTo(saveFile);
			result = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(result) {
			//파일 전송 성공
			System.out.println("[UploadFileService] FILE UPLOAD SUCCESS!!");
			//폴더를 제외한 파일명만 리턴
			return uniqueName + fileExtension;
		}
		else {
			//파일 전송 실패
			System.out.println("[UploadFileService] FILE UPLOAD FALL!!");
			return null;
		}
	}
}
