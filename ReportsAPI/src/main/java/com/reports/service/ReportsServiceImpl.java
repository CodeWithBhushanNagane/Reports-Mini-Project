package com.reports.service;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.reports.dao.EligibilityDetailsDao;
import com.reports.entity.EligibilityDetails;
import com.reports.requestdto.SearchRequest;
import com.reports.responsedto.SearchResponse;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportsServiceImpl implements ReportsService {

	@Autowired
	EligibilityDetailsDao eligibilityDetailsDao;

//	@Override
//	public List<String> getAllUniquePlanNames() {
//		List<EligibilityDetails> eligibilityDetailsList = eligibilityDetailsDao.findAll();
//		Set<String> planNames = new HashSet<>();
//		eligibilityDetailsList.forEach(eligibilityDetails->{
//			planNames.add(eligibilityDetails.getPlanName());
//		});
//		return new ArrayList<>(planNames);
//	}

	@Override
	public List<String> getAllUniquePlanNames() {
		return eligibilityDetailsDao.findPlanNames();
	}

//	@Override
//	public List<String> getAllUniquePlanStatuses() {
//		List<EligibilityDetails> eligibilityDetailsList = eligibilityDetailsDao.findAll();
//		Set<String> planStatuses = new HashSet<>();
//		eligibilityDetailsList.forEach(eligibilityDetails->{
//			planStatuses.add(eligibilityDetails.getPlanStatus());
//		});
//		return new ArrayList<>(planStatuses);
//	}

	@Override
	public List<String> getAllUniquePlanStatuses() {
		return eligibilityDetailsDao.findPlanStatuses();
	}

	@Override
	public List<SearchResponse> getAllEligibilityDetails(SearchRequest searchRequest) {
		EligibilityDetails eligibilityDetails = new EligibilityDetails();
		String planName = searchRequest.getPlanName();
		if (planName != null && !planName.equals("")) {
			eligibilityDetails.setPlanName(planName);
		}
		String planStatus = searchRequest.getPlanStatus();
		if (planStatus != null && !planStatus.equals("")) {
			eligibilityDetails.setPlanStatus(planStatus);
		}
		LocalDate planStartDate = searchRequest.getPlanStartDate();
		if (planStartDate != null) {
			eligibilityDetails.setPlanStartDate(planStartDate);
		}
		LocalDate planEndDate = searchRequest.getPlanEndDate();
		if (planEndDate != null) {
			eligibilityDetails.setPlanEndDate(planEndDate);
		}

		Example<EligibilityDetails> example = Example.of(eligibilityDetails);
		List<SearchResponse> searchResponseList = new ArrayList<>();
		List<EligibilityDetails> eligibilityDetailsList = eligibilityDetailsDao.findAll(example);
		eligibilityDetailsList.forEach(eliDetails -> {
			SearchResponse searchResponse = new SearchResponse();
			BeanUtils.copyProperties(eliDetails, searchResponse);
			searchResponseList.add(searchResponse);
		});
		return searchResponseList;
	}

	@Override
	public void generateExcel(HttpServletResponse httpServletResponse) throws IOException {
		List<EligibilityDetails> entities = eligibilityDetailsDao.findAll();
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet();
		HSSFRow headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Email");
		headerRow.createCell(2).setCellValue("Mobile");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("SSN");

		int i = 1;
		for(EligibilityDetails entity : entities) {
			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getName());
			dataRow.createCell(1).setCellValue(entity.getEmail());
			dataRow.createCell(2).setCellValue(entity.getMobileNo());
			dataRow.createCell(3).setCellValue(entity.getGender());
			dataRow.createCell(4).setCellValue(entity.getSsn());
			i++;
		}
		
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		workBook.write(outputStream);
		outputStream.close();
		workBook.close();
	}

	@Override
	public void generatePdf(HttpServletResponse httpServletResponse) throws Exception {
		List<EligibilityDetails> entities = eligibilityDetailsDao.findAll();
		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, httpServletResponse.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph paragraph = new Paragraph("Search Report", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 3.0f, 3.5f, 3.0f, 3.0f, 3.0f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		cell.setPadding(5);

		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Mobile", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("SSN", font));
		table.addCell(cell);
		
//		cell.setBackgroundColor(Color.BLUE);
//		Font dataFont = FontFactory.getFont(FontFactory.HELVETICA);
//		dataFont.setColor(Color.WHITE);
		entities.forEach(entity->{
//			cell.setPhrase(new Phrase(entity.getName(), dataFont));
//			table.addCell(cell);
//			cell.setPhrase(new Phrase(entity.getEmail(), dataFont));
//			table.addCell(cell);
//			cell.setPhrase(new Phrase(entity.getMobileNo().toString(), dataFont));
//			table.addCell(cell);
//			cell.setPhrase(new Phrase(entity.getGender().toString(), dataFont));
//			table.addCell(cell);
//			cell.setPhrase(new Phrase(entity.getSsn().toString(), dataFont));
//			table.addCell(cell);
			
			table.addCell(entity.getName());
			table.addCell(entity.getEmail());
			table.addCell(entity.getMobileNo().toString());
			table.addCell(entity.getGender().toString());
			table.addCell(entity.getSsn().toString());
		});
		document.add(table);
		document.close();
	}

}
