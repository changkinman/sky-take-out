package com.Luojia.service.impl;

import com.Luojia.dto.GoodsSalesDTO;
import com.Luojia.entity.Orders;
import com.Luojia.mapper.OrderMapper;
import com.Luojia.mapper.UserMapper;
import com.Luojia.service.ReportService;
import com.Luojia.vo.OrderReportVO;
import com.Luojia.vo.SalesTop10ReportVO;
import com.Luojia.vo.TurnoverReportVO;
import com.Luojia.vo.UserReportVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;
    /**
     * 统计指定时间区间内的营业额
     * @param begin
     * @param end
     * @return
     */
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end){
        //当前集合用于存放begin到end范围内每天的日期
        List<LocalDate> dateList = new ArrayList<>();

        dateList.add(begin);

        while(!begin.equals(end)){
            //日期计算
            begin = begin.plusDays(1);
            dateList.add(begin);
        }
        //存放每天营业额
        List<Double> turnoverList = new ArrayList<>();

        for(LocalDate date : dateList){
            //查询date日期对应的营业额数据，营业额是指状态为已完成的订单金额合计
            LocalDateTime beginTime = LocalDateTime.of(date,LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date,LocalTime.MAX);

            //select sum(amount) from orders where order_time > ? and order_time < ? and status = 5
            Map map = new HashMap();
            map.put("begin",beginTime);
            map.put("end",endTime);
            map.put("status", Orders.COMPLETED);
            Double turnover = orderMapper.sumByMap(map);
            turnover = turnover == null ? 0.0 : turnover;
            turnoverList.add(turnover);
        }
        //封装返回结果，构建VO中的dateList,turnoverList
        return TurnoverReportVO
                .builder()
                .dateList(StringUtils.join(dateList,","))
                .turnoverList(StringUtils.join(turnoverList,","))
                .build();
    }

    /**
     * 统计指定时间区间内的用户数据
     * @param begin
     * @param end
     * @return
     */

    public UserReportVO getUserStatistics(LocalDate begin, LocalDate end){
        //存放begin到end之间每天对应的日期
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);

        while(!begin.equals(end)){
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        //存放每天新增用户数量及总用户数量
        List<Integer> newUserList = new ArrayList<>();
        List<Integer> totalUserList = new ArrayList<>();

        for(LocalDate date : dateList){
            LocalDateTime beginTime = LocalDateTime.of(date,LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date,LocalTime.MAX);

            Map map = new HashMap();
            map.put("end",endTime);

            //总用户数量
            Integer totalUser = userMapper.countByMap(map);

            map.put("begin",beginTime);

            //新增用户数量
            Integer newUser = userMapper.countByMap(map);

            totalUserList.add(totalUser);
            newUserList.add(newUser);
        }

        //构建结果数据，dateList，totalUserList,newUserList
        return UserReportVO
                .builder()
                .dateList(StringUtils.join(dateList,","))
                .totalUserList(StringUtils.join(totalUserList,","))
                .newUserList(StringUtils.join(newUserList,","))
                .build();
    }

    /**
     * 统计指定时间区间内的订单数据
     * @param begin
     * @param end
     * @return
     */
    public OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end) {
        //存放begin到end之间每天对应的日期
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);

        while(!begin.equals(end)){
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        List<Integer> orderCountList = new ArrayList<>();
        List<Integer> validOrderCountList = new ArrayList<>();
        //遍历dateList集合，查询每天有效订单数和订单总数
        for (LocalDate localDate : dateList) {
            //查询每天订单总数
            LocalDateTime beginTime = LocalDateTime.of(localDate,LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(localDate,LocalTime.MAX);

            Integer orderCount = getOrderCount(beginTime,endTime,null);
            //查询每天有效订单数
            Integer validOrderCount = getOrderCount(beginTime,endTime,Orders.COMPLETED);

            orderCountList.add(orderCount);
            validOrderCountList.add(validOrderCount);
        }

        //计算时间区间内的订单总数量
        Integer totalOrderCount = orderCountList.stream().reduce(Integer::sum).get();
        //计算时间区间内的有效订单数
        Integer validOrderCount = validOrderCountList.stream().reduce(Integer::sum).get();

        //计算订单完成率
        Double orderCompletionRate = 0.0;
        if(totalOrderCount !=0) {
            //计算订单完成率
            orderCompletionRate = validOrderCount.doubleValue() / totalOrderCount;
        }

        return  OrderReportVO
                .builder()
                .dateList(StringUtils.join(dateList,","))
                .orderCountList(StringUtils.join(orderCountList,","))
                .validOrderCountList(StringUtils.join(validOrderCountList,","))
                .totalOrderCount(totalOrderCount)
                .validOrderCount(validOrderCount)
                .orderCompletionRate(orderCompletionRate)
                .build();
    }

    /**
     * 根据条件统计订单数量
     * @param begin
     * @param end
     * @param status
     * @return
     */
    private Integer getOrderCount(LocalDateTime begin, LocalDateTime end,Integer status) {
        Map map = new HashMap();
        map.put("begin",begin);
        map.put("end",end);
        map.put("status",status);

       return orderMapper.countByMap(map);
    }

    /**
     * 统计指定时间区间内销量排名前十
     * @param begin
     * @param end
     * @return
     */
    public SalesTop10ReportVO getSalesTop10(LocalDate begin, LocalDate end) {
        LocalDateTime beginTime = LocalDateTime.of(begin,LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(end,LocalTime.MAX);

        List<GoodsSalesDTO> salesTop10 = orderMapper.getSalesTop10(beginTime,endTime);
        List<String> names = salesTop10.stream().map(GoodsSalesDTO::getName).collect(Collectors.toList());
        String nameList = StringUtils.join(names,",");

        List<Integer> numbers = salesTop10.stream().map(GoodsSalesDTO::getNumber).collect(Collectors.toList());
        String numberList = StringUtils.join(numbers,",");

        //封装返回结果数据
        return SalesTop10ReportVO
                .builder()
                .nameList(nameList)
                .numberList(numberList)
                .build();
    }

    @Autowired
    private com.Luojia.service.WorkspaceService workspaceService;

    /**
     * 导出运营数据报表
     * @param response
     */
    @Override
    public void exportBusinessData(javax.servlet.http.HttpServletResponse response) {
        LocalDate begin = LocalDate.now().minusDays(30);
        LocalDate end = LocalDate.now().minusDays(1);

        // 1. 查询30天概览数据
        LocalDateTime beginTime = LocalDateTime.of(begin, LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(end, LocalTime.MAX);
        com.Luojia.vo.BusinessDataVO businessData = workspaceService.getBusinessData(beginTime, endTime);

        // 2. 动态生成 Excel
        try (org.apache.poi.xssf.usermodel.XSSFWorkbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook();
             javax.servlet.ServletOutputStream out = response.getOutputStream()) {

            org.apache.poi.xssf.usermodel.XSSFSheet sheet = workbook.createSheet("运营数据报表");

            // 列宽
            sheet.setColumnWidth(0, 256 * 18);
            sheet.setColumnWidth(1, 256 * 15);
            sheet.setColumnWidth(2, 256 * 15);
            sheet.setColumnWidth(3, 256 * 15);
            sheet.setColumnWidth(4, 256 * 15);
            sheet.setColumnWidth(5, 256 * 15);

            // 样式
            org.apache.poi.xssf.usermodel.XSSFCellStyle titleStyle = workbook.createCellStyle();
            org.apache.poi.xssf.usermodel.XSSFFont titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);

            org.apache.poi.xssf.usermodel.XSSFCellStyle headerStyle = workbook.createCellStyle();
            org.apache.poi.xssf.usermodel.XSSFFont headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(org.apache.poi.ss.usermodel.IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            headerStyle.setBorderTop(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            headerStyle.setBorderLeft(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            headerStyle.setBorderRight(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            headerStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);

            org.apache.poi.xssf.usermodel.XSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setBorderBottom(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            cellStyle.setBorderTop(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            cellStyle.setBorderLeft(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            cellStyle.setBorderRight(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            cellStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);

            // 标题
            org.apache.poi.xssf.usermodel.XSSFRow row0 = sheet.createRow(0);
            row0.setHeightInPoints(30);
            org.apache.poi.xssf.usermodel.XSSFCell cell0 = row0.createCell(0);
            cell0.setCellValue("运营数据报表");
            cell0.setCellStyle(titleStyle);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 5));

            // 时间范围
            org.apache.poi.xssf.usermodel.XSSFRow row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue("时间区间：" + begin + " 至 " + end);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, 1, 0, 5));

            // 概览头部
            org.apache.poi.xssf.usermodel.XSSFRow row3 = sheet.createRow(3);
            row3.setHeightInPoints(20);
            org.apache.poi.xssf.usermodel.XSSFCell cA = row3.createCell(0); 
            cA.setCellValue("指标统计 (近30天)"); 
            cA.setCellStyle(headerStyle);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(3, 3, 0, 5));

            org.apache.poi.xssf.usermodel.XSSFRow row4 = sheet.createRow(4);
            String[] metricHeaders = {"营业额", "订单完成率", "新增用户数", "有效订单数", "平均客单价", "统计天数"};
            for (int i = 0; i < 6; i++) {
                org.apache.poi.xssf.usermodel.XSSFCell cell = row4.createCell(i);
                cell.setCellValue(metricHeaders[i]);
                cell.setCellStyle(headerStyle);
            }

            // 概览数据
            org.apache.poi.xssf.usermodel.XSSFRow row5 = sheet.createRow(5);
            row5.createCell(0).setCellValue(businessData.getTurnover() != null ? businessData.getTurnover() : 0.0);
            row5.createCell(1).setCellValue(businessData.getOrderCompletionRate() != null ? businessData.getOrderCompletionRate() : 0.0);
            row5.createCell(2).setCellValue(businessData.getNewUsers() != null ? businessData.getNewUsers() : 0);
            row5.createCell(3).setCellValue(businessData.getValidOrderCount() != null ? businessData.getValidOrderCount() : 0);
            row5.createCell(4).setCellValue(businessData.getUnitPrice() != null ? businessData.getUnitPrice() : 0.0);
            row5.createCell(5).setCellValue(30);
            for (int i = 0; i < 6; i++) {
                row5.getCell(i).setCellStyle(cellStyle);
            }

            // 每日明细标题
            org.apache.poi.xssf.usermodel.XSSFRow row7 = sheet.createRow(7);
            org.apache.poi.xssf.usermodel.XSSFCell cB = row7.createCell(0);
            cB.setCellValue("每日明细数据");
            cB.setCellStyle(headerStyle);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(7, 7, 0, 5));

            org.apache.poi.xssf.usermodel.XSSFRow row8 = sheet.createRow(8);
            String[] detailHeaders = {"日期", "营业额", "订单完成率", "新增用户数", "有效订单数", "平均客单价"};
            for (int i = 0; i < 6; i++) {
                org.apache.poi.xssf.usermodel.XSSFCell cell = row8.createCell(i);
                cell.setCellValue(detailHeaders[i]);
                cell.setCellStyle(headerStyle);
            }

            // 写入每日数据
            int startRow = 9;
            for (int i = 0; i < 30; i++) {
                LocalDate date = begin.plusDays(i);
                LocalDateTime dailyBegin = LocalDateTime.of(date, LocalTime.MIN);
                LocalDateTime dailyEnd = LocalDateTime.of(date, LocalTime.MAX);
                com.Luojia.vo.BusinessDataVO dailyData = workspaceService.getBusinessData(dailyBegin, dailyEnd);

                org.apache.poi.xssf.usermodel.XSSFRow row = sheet.createRow(startRow + i);
                row.createCell(0).setCellValue(date.toString());
                row.createCell(1).setCellValue(dailyData.getTurnover() != null ? dailyData.getTurnover() : 0.0);
                row.createCell(2).setCellValue(dailyData.getOrderCompletionRate() != null ? dailyData.getOrderCompletionRate() : 0.0);
                row.createCell(3).setCellValue(dailyData.getNewUsers() != null ? dailyData.getNewUsers() : 0);
                row.createCell(4).setCellValue(dailyData.getValidOrderCount() != null ? dailyData.getValidOrderCount() : 0);
                row.createCell(5).setCellValue(dailyData.getUnitPrice() != null ? dailyData.getUnitPrice() : 0.0);

                for (int j = 0; j < 6; j++) {
                    row.getCell(j).setCellStyle(cellStyle);
                }
            }

            // 输出
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=OperationalReport.xlsx");
            workbook.write(out);

        } catch (Exception e) {
            log.error("导出运营数据报表异常", e);
        }
    }
}
