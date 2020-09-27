package com.spring.CreditConsommation.controller;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.CreditConsommation.model.ImageModel;
import com.spring.CreditConsommation.model.Ocrinfo1;
import com.spring.CreditConsommation.model.Ocrinfo2;
import com.spring.CreditConsommation.repository.ImageRepository;
import com.spring.CreditConsommation.repository.OcrInfo1Repository;
import com.spring.CreditConsommation.repository.OcrInfo2Repository;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;




@RestController
@CrossOrigin(origins = {"http://192.168.1.4:4200" , "http://localhost:4200" , "http://127.0.0.1:4200" , "http://192.168.210.113:4200"})
@RequestMapping(path = "/image")
public class ImageController {
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	OcrInfo1Repository info1Repository;
	@Autowired
	OcrInfo2Repository info2Repository;
	
	@PostMapping("/upload")
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getBytes()));
		imageRepository.save(img);
		return ResponseEntity.status(HttpStatus.OK);
	}
	
	@PostMapping("/uploadocr")
	public ResponseEntity<Ocrinfo1> uplaodImageOcr(@RequestParam("imageFile") MultipartFile file) throws IOException {
		ArrayList<ImageModel> images = new ArrayList<ImageModel>();
		images = imageRepository.findByName(file.getOriginalFilename());
		final ImageModel retrievedImage = images.get(0);
		try {
			ITesseract tesseract = new Tesseract();
			ByteArrayInputStream bis = new ByteArrayInputStream(decompressBytes(retrievedImage.getPicByte()));
			tesseract.setLanguage("fra");
			String str1=tesseract.doOCR(ImageIO.read(bis));
			System.out.println("data: "+ str1);
			String []myarr=str1.split("\n");
			String cin="";
			String nom="";
			String prenom="";
			String date="";
			String lieu="";
			
			if(myarr[1].replaceAll("[^1-9]", "")!="")
				cin=myarr[1].replaceAll("[^1-9]", "");
			
			System.out.println(cin);
			
			if((myarr[2].split(":"))[1] != "")
				nom=(myarr[2].split(":"))[1];

			System.out.println(nom);
			
			if((myarr[3].split(":"))[1]!="")
				prenom=(myarr[3].split(":"))[1];

			System.out.println(prenom);
			
			if((myarr[4].split(":"))[2]!="")
				date=(myarr[4].split(":"))[2];

			System.out.println(date);
			
			if((myarr[5].split(":"))[1]!="")
				lieu=(myarr[5].split(":"))[1];

			System.out.println(lieu);
			
			String datelieu = date + ", " + lieu;
			System.out.println(datelieu);
			Ocrinfo1 _ocrinfo = info1Repository.save(new Ocrinfo1(nom,prenom,cin,datelieu));
		    return new ResponseEntity<>(_ocrinfo, HttpStatus.CREATED);
			}catch (TesseractException e) {
				System.out.println("Exception "+ e.getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@GetMapping("/ocrinfo1")
	public ResponseEntity<List<Ocrinfo1>> getAllCredits(@RequestParam(required = false) String nom) {
		try {
		    List<Ocrinfo1> infos = new ArrayList<Ocrinfo1>();

		    if (nom == null)
		    	info1Repository.findAll().forEach(infos::add);
		    else
		    	info1Repository.findByNomContaining(nom).forEach(infos::add);

		    if (infos.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }

		    return new ResponseEntity<>(infos, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	    
	}
	
	@PostMapping("/uploadocr2")
	public ResponseEntity<Ocrinfo2> uplaodImageOcr2(@RequestParam("imageFile") MultipartFile file) throws IOException {
		ArrayList<ImageModel> images2 = new ArrayList<ImageModel>();
		images2 = imageRepository.findByName(file.getOriginalFilename());
		final ImageModel retrievedImage = images2.get(0);
		try {
			ITesseract tesseract = new Tesseract();
			ByteArrayInputStream bis = new ByteArrayInputStream(decompressBytes(retrievedImage.getPicByte()));
			tesseract.setLanguage("fra");
			String str2=tesseract.doOCR(ImageIO.read(bis));
			System.out.println("data: "+ str2);
			String []myarr=str2.split("\n");
			String adresse="";
			String code="";
			String ville="";		
			
			if((myarr[2].split(":"))[1]!="")
				adresse=(myarr[2].split(":"))[1];
			
			System.out.println(adresse);
			
			if((myarr[3].split(" "))[1]!="")
				code=(myarr[3].split(" "))[1];

			System.out.println(code);
			
			if((myarr[3].split(" "))[2]!="")
				ville=(myarr[3].split(" "))[2];

			System.out.println(ville);
			

			Ocrinfo2 _ocrinfo = info2Repository.save(new Ocrinfo2(adresse,code,ville));
		    return new ResponseEntity<>(_ocrinfo, HttpStatus.CREATED);
			}catch (TesseractException e) {
				System.out.println("Exception "+ e.getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@GetMapping("/ocrinfo2")
	public ResponseEntity<List<Ocrinfo2>> getAllCredits2(@RequestParam(required = false) String adresse) {
		try {
		    List<Ocrinfo2> infos = new ArrayList<Ocrinfo2>();

		    if (adresse == null)
		    	info2Repository.findAll().forEach(infos::add);
		    else
		    	info2Repository.findByAdresseContaining(adresse).forEach(infos::add);

		    if (infos.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }

		    return new ResponseEntity<>(infos, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	    
	}
	
	@GetMapping(path = { "/get/{imageName}" })
	public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
		ArrayList<ImageModel> images = new ArrayList<ImageModel>();
		images = imageRepository.findByName(imageName);
		final ImageModel retrievedImage = images.get(0);
		ImageModel img = new ImageModel(retrievedImage.getName(), retrievedImage.getType(),
				decompressBytes(retrievedImage.getPicByte()));
		return img;
	}
	
	@GetMapping(path = { "/get1/{imageName}" })
	public ImageModel getImage2(@PathVariable("imageName") String imageName1) throws IOException {
		ArrayList<ImageModel> images1 = new ArrayList<ImageModel>();
		images1 = imageRepository.findByName(imageName1);
		final ImageModel retrievedImage1 = images1.get(0);
		ImageModel img1 = new ImageModel(retrievedImage1.getName(), retrievedImage1.getType(),
				decompressBytes(retrievedImage1.getPicByte()));
		return img1;
	}
	
	
	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}
	
	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
}
