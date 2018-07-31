/*package com.param.adt.common;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.param.master.doctor.dto.DoctorRegistarionDto;
import com.param.master.globle.dto.EmployeeMasterDto;
import com.param.master.patient.dto.PatientMasterDto;

@Service
public class ProfilePicUploadUtility implements IAWSCredentials{
	
	private MultipartFile multipartFile;
	private T object;
	
	public ProfilePicUploadUtility(MultipartFile multipartFile,T object){
		this.multipartFile = multipartFile;
		this.object = object;
	}
	
	public ProfilePicUploadUtility uploadPatientProfile = new ProfilePicUploadUtility(multipartFile, object){
		public void run(){
			
		}
	};
	
	public String uploadPatientProfile(MultipartFile multipartFile,PatientMasterDto patientMasterDto){
		try{
			if(multipartFile != null && multipartFile.getSize() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(multipartFile.getContentType());
				//write a file on s3 server
				s3Client.putObject(PATIENT_BUCKET, patientMasterDto.getMrnNumber(), multipartFile.getInputStream(), metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(PATIENT_BUCKET, patientMasterDto.getMrnNumber(), CannedAccessControlList.PublicRead);
				//get url of file			
				String url = s3Client.getResourceUrl(PATIENT_BUCKET, patientMasterDto.getMrnNumber());
				return url;
			}
		}catch(AmazonServiceException ae){
			System.out.println("Error Message:    " + ae.getMessage());
		    System.out.println("HTTP Status Code: " + ae.getStatusCode());
		    System.out.println("AWS Error Code:   " + ae.getErrorCode());
		    System.out.println("Error Type:       " + ae.getErrorType());
		    System.out.println("Request ID:       " + ae.getRequestId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String uploadDoctorProfile(MultipartFile multipartFile,DoctorRegistarionDto doctorRegistarionDto){
		try{
			if(multipartFile != null && multipartFile.getSize() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(multipartFile.getContentType());
				//write a file on s3 server
				s3Client.putObject(DOCTOR_BUCKET, doctorRegistarionDto.getDoctorNumber(), multipartFile.getInputStream(), metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(DOCTOR_BUCKET, doctorRegistarionDto.getDoctorNumber(), CannedAccessControlList.PublicRead);
				//get url of file			
				String url = s3Client.getResourceUrl(DOCTOR_BUCKET, doctorRegistarionDto.getDoctorNumber());
				return url;
			}
		}catch(AmazonServiceException ae){
			System.out.println("Error Message:    " + ae.getMessage() + "\nHTTP Status Code: " + ae.getStatusCode()+"\nAWS Error Code:   " + ae.getErrorCode()+"\nError Type:       " + ae.getErrorType()+"Request ID:       " + ae.getRequestId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String uploadEmployeeProfile(MultipartFile multipartFile,EmployeeMasterDto employeeMasterDto){
		try{
			if(multipartFile != null && multipartFile.getSize() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(multipartFile.getContentType());
				//write a file on s3 server
				s3Client.putObject(EMPLOYEE_BUCKET, employeeMasterDto.getEmployeeNumber(), multipartFile.getInputStream(), metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(EMPLOYEE_BUCKET, employeeMasterDto.getEmployeeNumber(), CannedAccessControlList.PublicRead);
				//get url of file			
				String url = s3Client.getResourceUrl(EMPLOYEE_BUCKET, employeeMasterDto.getEmployeeNumber());
				return url;
			}
		}catch(AmazonServiceException ae){
			System.out.println("Error Message:    " + ae.getMessage());
		    System.out.println("HTTP Status Code: " + ae.getStatusCode());
		    System.out.println("AWS Error Code:   " + ae.getErrorCode());
		    System.out.println("Error Type:       " + ae.getErrorType());
		    System.out.println("Request ID:       " + ae.getRequestId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String uploadVigorePatientProfile(MultipartFile multipartFile,PatientMasterDto patientMasterDto){
		try{
			if(multipartFile != null && multipartFile.getSize() > 0){
				AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
				AmazonS3Client s3Client = new AmazonS3Client(awsCredentials);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(multipartFile.getContentType());
				//write a file on s3 server
				s3Client.putObject(VIGORE_PATIENT_BUCKET, patientMasterDto.getMrnNumber(), multipartFile.getInputStream(), metadata);
				//Setting Public Permissions to file
				s3Client.setObjectAcl(VIGORE_PATIENT_BUCKET, patientMasterDto.getMrnNumber(), CannedAccessControlList.PublicRead);
				//get url of file			
				String url = s3Client.getResourceUrl(VIGORE_PATIENT_BUCKET, patientMasterDto.getMrnNumber());
				return url;
			}
		}catch(AmazonServiceException ae){
			System.out.println("Error Message:    " + ae.getMessage());
		    System.out.println("HTTP Status Code: " + ae.getStatusCode());
		    System.out.println("AWS Error Code:   " + ae.getErrorCode());
		    System.out.println("Error Type:       " + ae.getErrorType());
		    System.out.println("Request ID:       " + ae.getRequestId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
*/