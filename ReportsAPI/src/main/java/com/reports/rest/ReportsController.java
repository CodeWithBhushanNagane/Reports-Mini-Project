package com.reports.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reports.requestdto.SearchRequest;
import com.reports.responsedto.SearchResponse;
import com.reports.service.ReportsService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportsController {

	@Autowired
	ReportsService reportsService;

	@GetMapping("/planNames")
	public ResponseEntity<List<String>> getPlanNames() {
		return new ResponseEntity<>(reportsService.getAllUniquePlanNames(), HttpStatus.OK);
	}

	@GetMapping("/planStatuses")
	public ResponseEntity<List<String>> getPlanStatuses() {
		return new ResponseEntity<List<String>>(reportsService.getAllUniquePlanStatuses(), HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request) {
		return new ResponseEntity<List<SearchResponse>>(reportsService.getAllEligibilityDetails(request),
				HttpStatus.OK);
	}

	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.xls";
		
		response.setHeader(headerKey, headerValue);
		reportsService.generateExcel(response);

	}

	@GetMapping("/pdf")
	public void pdfReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.pdf";
		
		response.setHeader(headerKey, headerValue);
		reportsService.generatePdf(response);
	}
}
