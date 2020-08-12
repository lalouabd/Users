package com.bandg.users.models;

import com.bandg.users.exceptions.parsing.IllegalCellException;
import com.bandg.users.exceptions.parsing.IllegalFileException;
import com.bandg.users.exceptions.parsing.IllegalRowException;
import javafx.scene.input.DataFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.util.Lists;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.*;

@Repository
public class StaffParser {
    private final  List<String> cells= Lists.list("matricule"
            ,"date_naissance"
            ,"lieu_naissance"
            ,"code_sexe"
            ,"CIN"
            ,"sit_fam"
            ,"nationalite"
            ,"date_embauche"
            ,"grade"
            ,"fonction"
            ,"Poste"
            ,"categorie"
            ,"echelon"
            ,"Ent_affect"
            ,"section_analytique"
            ,"regime_retraite"
            ,"Affil_RECORE"
            ,"date_dern_promo"
    );

    public List<Object> parseFile(byte[] bytes) throws Exception {
        List<Staff> accepted = new ArrayList<>();
        JSONArray rejected = new JSONArray();
        try {
            Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(bytes));
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            Row row = rows.next();
            checkSheet(row);
            if (!rows.hasNext())
                throw new IllegalFileException("invalid file  : file has no data");
            while (rows.hasNext()) {
                Row row1 = rows.next();
                Optional<Staff> staff = handleRow(row1);
                if (staff.isPresent()) {
                    accepted.add(staff.get());
                } else {
                 JSONObject jso = new JSONObject();
                 jso.put("rejected",row1.getCell(0).getNumericCellValue());
                    rejected.put(jso);
                }
            }

        }catch(Exception e)
        {
            throw e;
        }
        return Lists.list(accepted, rejected);
    }

    private Optional<Staff> handleRow(Row row) {
        Optional<Staff> stf = Optional.ofNullable(null);
        DataFormatter dataFormatter = new DataFormatter() ;
        if (row.getPhysicalNumberOfCells() != cells.size())
            {
                Staff staff = null;
                try {
                    String dob = dataFormatter.formatCellValue(row.getCell(1));
                   System.out.println(dob);
//                    if (dob.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{2}"))
//                    {
                        String []a = dob.trim().split("/");
                        StringBuilder sb = new StringBuilder();

                        if (a.length == 3)
                        {
                            if (a[2].length() == 2)
                            sb.append("19" + a[2].trim()+"-");
                            else
                                sb.append( a[2].trim()+"-");
                            sb.append((a[0].length() == 2 ? a[0] : "0"+ a[0]) + "-");

                            sb.append((a[1].length() == 2 ? a[1] : "0"+ a[1]));

                            dob = sb.toString();
                        }
                        else
                            dob = "1800-12-02";

                        System.out.println(dob.trim().replace("/"," "));
              //     }
                  //  else
                    //    dob = "1800-12-02";
                    staff = new Staff(
                            Integer.parseInt(
                                    dataFormatter.formatCellValue(
                                            row.getCell(0))),
                    java.sql.Date
                                    .valueOf(
                                            dob
                                    ),
                            dataFormatter.
                                    formatCellValue(row.getCell(2)),
                            dataFormatter.
                                    formatCellValue(row.getCell(3)),
                            dataFormatter.
                                    formatCellValue(row.getCell(4)),
                            dataFormatter.
                                    formatCellValue(row.getCell(5)),
                            dataFormatter.
                                    formatCellValue(row.getCell(6)),
                            dataFormatter.
                                    formatCellValue(row.getCell(7)),
                            dataFormatter.
                                    formatCellValue(row.getCell(8)),
                            dataFormatter.
                                    formatCellValue(row.getCell(9)),
                            dataFormatter.
                                    formatCellValue(row.getCell(10)),
                            dataFormatter.
                                    formatCellValue(row.getCell(11)),
                            Integer.parseInt(
                                    dataFormatter.
                                            formatCellValue(row.getCell(12))),
                            dataFormatter.
                                    formatCellValue(row.getCell(13)),
                            dataFormatter.
                                    formatCellValue(row.getCell(14)),
                            dataFormatter.
                                    formatCellValue(row.getCell(15)),
                            Integer.parseInt(dataFormatter.
                                    formatCellValue(row.getCell(16))),
                            dataFormatter.
                                    formatCellValue(row.getCell(17))
                    );
                }catch (Exception e)
                {
                   e.printStackTrace();
                }
                stf = Optional.ofNullable(staff);
            }
        return stf;
    }

    private void checkSheet(Row row) throws IllegalFileException {

            if(row != null)
            {
                Iterator<Cell> fCell = row.cellIterator();
                cells.forEach(c->{
                    try {
                        String name = fCell.next().getStringCellValue();
                        if (!name.equals(c))
                            throw new IllegalCellException("incorrect cell name : " + name + " must be :" + c);
                    }catch (NoSuchElementException e) {
                        throw new IllegalRowException("number of cells not valid missing cell : " + c);
                    }
                });
                if (fCell.hasNext())
                    throw new IllegalFileException("file has more cells than usual");
            }else
                throw new IllegalFileException("illegal File");
    }
}
