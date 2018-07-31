package com.param.adt.common;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

import com.param.adt.master.global.dto.UserMasterDto;

import in.param.exception.ApplicationException;



public class CommonUtils {
	
	private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final Random random = new Random();
	private static final String NUMBERS = "0123456789";
	
	public static String saveImage(MultipartFile file, String folderPath, String newImgName) throws ApplicationException{
		try{
			InputStream inputStream = file.getInputStream();

			File entidadImagePath = new File(folderPath);
			if(!entidadImagePath.exists()){
				entidadImagePath.mkdirs();
			}

			File newFile = new File(entidadImagePath+ File.separator + newImgName);
			
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			OutputStream outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
		} catch (IOException e) {
			throw new ApplicationException();
		} 
		return newImgName;
   }
	
	public static MultipartFile getMultipartFileFromText(String content){
		try{
			MultipartFile multiPartFile = new MultipartFile() {
				@Override
				public void transferTo(File dest) throws IOException, IllegalStateException {
					
				}
				
				@Override
				public boolean isEmpty() {
					return false;
				}
				
				@Override
				public long getSize() {
					return content.getBytes().length;
				}
				
				@Override
				public String getOriginalFilename() {
					return "";
				}
				
				@Override
				public String getName() {
					return "";
				}
				
				@Override
				public InputStream getInputStream() throws IOException {
					return new ByteArrayInputStream(content.getBytes());
				}
				
				@Override
				public String getContentType() {
					return "text/html";
				}
				
				@Override
				public byte[] getBytes() throws IOException {
					return content.getBytes();
				}
			};
			return multiPartFile;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String generateRandomString(){
		try{
			StringBuilder salt = new StringBuilder();
	        while (salt.length() < 9) { // length of the random string.
	            int index = (int) (random.nextFloat() * CHARS.length());
	            salt.append(CHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static UserMasterDto generateUserNameAndPassword(UserMasterDto userMasterDto){
		try{
			/*Random r = new Random();
			int low = 0,high = 99;
			int result = r.nextInt(high-low) + low;*/
			
			//generate username
			String username = userMasterDto.getFirstName().trim().substring(0, 2).toLowerCase() + userMasterDto.getLastName().trim().toLowerCase();
			username = username + ADTCommonDateUtils.getStringDate(ADTCommonDateUtils.getDate(userMasterDto.getDateOfBirth(), "dd-M-yyyy"), "yyyy");
			
			//generate password
			String password = generateRandomString();
			userMasterDto.setUserName(username);
			userMasterDto.setPassword(password);
			userMasterDto.setLoginName(username);
			return userMasterDto;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String generatePaymentVoucherNo(Long id){
		try{
			StringBuilder salt = new StringBuilder();
	        while (salt.length() < 5) { // length of the random string.
	            int index = (int) (random.nextFloat() * NUMBERS.length());
	            salt.append(NUMBERS.charAt(index));
	        }
	        String saltStr = salt.toString().concat(id.toString());
	        return saltStr;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(generatePaymentVoucherNo((long)521));
		
	}
}