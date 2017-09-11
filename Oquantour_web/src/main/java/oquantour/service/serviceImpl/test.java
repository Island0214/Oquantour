package oquantour.service.serviceImpl;

import oquantour.data.dao.*;
import oquantour.data.datagetter.BasicInfoGetter;
import oquantour.data.datagetter.HistoryInfoGetter;
import oquantour.data.datahelper.ProcessDataHelper;
import oquantour.exception.FormativeNotExistsException;
import oquantour.exception.StockLessThanOneHundredException;
import oquantour.po.PlateRealTimePO;
import oquantour.po.StockPO;
import oquantour.po.UserPO;
import oquantour.util.tools.BasicIndices;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;

/**
 * Created by island on 5/3/17.
 */


@Transactional(readOnly = false)
public class test {
    static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    static UserDao userDao;
    static StockDao stockDao;
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {

//        CombinationDao combinationDao = (CombinationDao)ctx.getBean("combinationDao");
//        combinationDao.getStockCombination("qky", "价值股集中营");
        StockDao stockDao = (StockDao) ctx.getBean("stockDao");
//
//        try {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(new java.util.Date());
//            calendar.add(Calendar.DAY_OF_MONTH, -1);
//            Date date = new Date(calendar.getTime().getTime());
////            BasicDao basicDao = (BasicDao) ctx.getBean("basicDao");
////            basicDao.addTopList(date);
//            calendar.add(Calendar.YEAR, -1);
//            Date sdate = new Date(calendar.getTime().getTime());
//            List<String> list = stockDao.getAllStockCodeAndName();
//            List<String> stockIDs = new ArrayList<>();
//            for (int i = 0; i < list.size(); i++) {
//                stockIDs.add(list.get(i).split(";")[0]);
//            }
//            stockDao.filterStock(sdate, date, stockIDs, 5, true, true, true, false);
//        } catch (FormativeNotExistsException e1) {
//            e1.printStackTrace();
//        } catch (StockLessThanOneHundredException e2) {
//            e2.printStackTrace();
//        }

//        Calendar calendar = Calendar.getInstance();
//        Date date = new Date(calendar.getTime().getTime());
//        calendar.add(Calendar.YEAR, -1);
//        Date sdate = new Date(calendar.getTime().getTime());
//        stockDao.searchAllStocksByDate(sdate, date);
//        stockDao.getAllStockInfos();

        for(int i = -1; i <= -1; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new java.util.Date());
            calendar.add(Calendar.DAY_OF_MONTH, i);
            String date = simpleDateFormat.format(calendar.getTime());
            stockDao.updateDailyStockInfo(date);
        }


//

//        System.out.println(date);
//
//        System.out.println(basicDao.getTopListInfo().size());
//        PlateDao plateDao = (PlateDao)ctx.getBean("plateDao");
//        plateDao.updateRealTimePlateInfo();


//        ProcessDataHelper processDataHelper = new ProcessDataHelper();
//        processDataHelper.addStockNameInfo();
//        HistoryInfoGetter historyInfoGetter = new HistoryInfoGetter();
//        historyInfoGetter.addStockInfo();
//        BasicInfoGetter basicInfoGetter = new BasicInfoGetter();
//        basicInfoGetter.getBasicInfo();

    }

    public static void addStockInfo() {
        int start = 2500;
        int end = 2566;

        List<Thread> threadList = new ArrayList<>();
        List<Runnable> runnableList = new ArrayList<>();

        int length = 20;
        int count = 0;
        String[] ids = {"1", "10", "11", "12", "14", "16", "17", "18", "19", "100", "150", "151", "153", "155", "157", "158", "159", "1696", "1896",
                "2", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
                "30", "31", "32", "33", "34", "35", "36", "37", "39", "301", "338"};
        for (int i = 300121; i <= 300187; i = i + 1) {
//            if(end - i < 20)
//                length = end - i;
//            for(int j = i; j < i + length; j++) {
            String path = "/Users/island/IdeaProjects/Oquantour/datasource/stocks/3/" + i + ".txt";
            //String path = "/Users/island/IdeaProjects/Oquantour/datasource/stocks/" + ids[i].charAt(0) + "/" + ids[i] + ".txt";
            count++;

                /*
                Runnable run = () -> {
                    addStockInfo(path);
                };
                runnableList.add(run);
                Thread thread = new Thread(run);
                threadList.add(thread);*/
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ProcessDataHelper processDataHelper = new ProcessDataHelper();
                    processDataHelper.addStockInfo(path);
                }
            });
            threadList.add(thread);
        }
//            for(int j = length; j < 20; j++){
//                count++;
//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                    }
//                });
//                threadList.add(thread);
//            }

            /*
            threadList.get(0).start();
            threadList.get(1).start();
            threadList.get(2).start();
            threadList.get(3).start();
            threadList.get(4).start();
            threadList.get(5).start();
            threadList.get(6).start();
            threadList.get(7).start();
            threadList.get(8).start();
            threadList.get(9).start();
            threadList.get(10).start();
            threadList.get(11).start();
            threadList.get(12).start();
            threadList.get(13).start();
            threadList.get(14).start();
            threadList.get(15).start();
            threadList.get(16).start();
            threadList.get(17).start();
            threadList.get(18).start();
            threadList.get(19).start();

            threadList.clear();
            */
//        }
        for (int i = 0; i < threadList.size(); i++) {
            threadList.get(i).start();
        }

    }

    public static void searchStock() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        StockDao stockDao = (StockDao) ctx.getBean("stockDao");
        try {
            Date startDate = new Date(simpleDateFormat.parse("2005-02-01").getTime());
            Date endDate = new Date(simpleDateFormat.parse("2006-01-31").getTime());
            List<StockPO> stockPOS = stockDao.searchStock("1", startDate, endDate);
            System.out.println(stockPOS.size());

            for (int i = 0; i < stockPOS.size(); i++) {
                System.out.println(simpleDateFormat.format(new java.util.Date(stockPOS.get(i).getDateValue().getTime())));

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void searchAllStock() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        StockDao stockDao = (StockDao) ctx.getBean("stockDao");
        try {
            Date startDate = new Date(simpleDateFormat.parse("2005-02-01").getTime());
            Date endDate = new Date(simpleDateFormat.parse("2006-01-31").getTime());
            Map<String, List<StockPO>> map = stockDao.searchAllStocksByDate(startDate, endDate);
            System.out.println(map.size());

            Iterator<String> iter = map.keySet().iterator();
            String key;

            while (iter.hasNext()) {

                key = iter.next();

                System.out.println(map.get(key).get(0).getStockId());

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void filter() {
        Map<BasicIndices, double[]> map = new HashMap<>();
        double[] d = {-10, 10};
        map.put(BasicIndices.adratio, d);
        map.put(BasicIndices.arturnover, d);
        map.put(BasicIndices.pe, d);

        stockDao.filterStock(map);
    }

}
