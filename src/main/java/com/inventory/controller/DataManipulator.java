package com.inventory.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

@Controller
public class DataManipulator {

        @PostMapping("/data")
        public ResponseEntity<?> getExcelData(@ModelAttribute(value = "file") MultipartFile file, @RequestParam(value = "choice",defaultValue = "") String choice ) throws IOException {

            String cellResponse = " ";

            if (file != null && !file.isEmpty()) {

                DataFormatter formatter = new DataFormatter();
                InputStream is = file.getInputStream();
                XSSFWorkbook workbook = new XSSFWorkbook(is);
                XSSFSheet readSheet = workbook.getSheetAt(0);

                if(choice.equalsIgnoreCase("R")){

                    Iterator<Row> readIterator = readSheet.iterator();
                    while (readIterator.hasNext()) {
                        Row row = readIterator.next();

                        if(row.getRowNum() == 0){
                            continue;
                        }

                        for (int i = 0; i <= 3; i++) {
                            Cell cell = row.getCell(i);

                            if (cell != null && cell.getCellTypeEnum() != CellType.BLANK
                                    && !formatter.formatCellValue(cell).trim().isEmpty()) {

                                String data = formatter.formatCellValue(cell);
                                cellResponse += data.concat(" ");
                            }
                        }
                        cellResponse += "\n ";
                    }
                }

                else if(choice.equalsIgnoreCase("W")) {

                    if(workbook.getNumberOfSheets()  == 1){
//                        FileOutputStream fos = new FileOutputStream();
                        XSSFSheet sheet2 = workbook.createSheet("sheet2");

                    }
                    XSSFSheet writeSheet = workbook.getSheetAt(1);

                    Iterator<Row> readIterator = readSheet.iterator();
                    readIterator.next();

                    int writingRow = 0;

                    if(writeSheet.getLastRowNum() == 0){
                        Row row = writeSheet.createRow(writingRow);
                      row.createCell(0).setCellValue("slno");
                      row.createCell(1).setCellValue("name");
                      row.createCell(2).setCellValue("department");
                      row.createCell(3).setCellValue("position");
                    }

                    writingRow = writeSheet.getLastRowNum();

                    while(readIterator.hasNext()){
                        Row readRow = readIterator.next();
                        Row writeRow = writeSheet.createRow(++writingRow);
                        for(int i = 0 ;i < 4 ; i++){

                            Cell readCell = readRow.getCell(i);
                            Cell writeCell = writeRow.createCell(i);
                            try{
                                if(readCell != null && readCell.getCellType() != CellType.BLANK) {
                                    if(readCell.getCellType() == CellType.NUMERIC) {
                                        writeCell.setCellValue(readCell.getNumericCellValue());
                                    } else if(readCell.getCellType() == CellType.STRING){
                                        writeCell.setCellValue(readCell.getStringCellValue());
                                    }
                                }
                            }
                            catch (Exception exception){
                                System.err.println(exception.getMessage());
                            }
                        }
                    }
                }else{
                    return new ResponseEntity<>("Have You not Given Choice ?",HttpStatus.BAD_GATEWAY);
                }

                return ResponseEntity.ok(cellResponse +" \n Total Rows  "+(readSheet.getLastRowNum()));

            } else {
                return new ResponseEntity<>("No File Given To Extract Data", HttpStatus.BAD_REQUEST);
            }

        }
}
