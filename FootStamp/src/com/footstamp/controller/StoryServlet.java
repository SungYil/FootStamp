package com.footstamp.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.footstamp.bean.DetailStorySaveBean;
import com.footstamp.bean.DetailStoryViewBean;
import com.footstamp.bean.LocationResultBean;
import com.footstamp.bean.TagResultBean;
import com.footstamp.service.ReviewService;
import com.footstamp.service.StoryService;
import com.oreilly.servlet.MultipartRequest;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * Servlet implementation class StoryServlet
 */
@Controller
public class StoryServlet extends HttpServlet {
   @Autowired
   private StoryService storyService; 
   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      String key = request.getParameter("key");
      if("tagPage".equals(key)){//�±� �˻�ȭ�� ���°�
         String tagName=request.getParameter("tagName");
         //������ �±׸����� ��ȸ�ϴ� ������ �־���Ѵ�.(��ȸ��� ������ �ʿ��ϴ�)
         int tagCnt=100240;
         request.setAttribute("tagName", tagName);
         request.setAttribute("tagCnt",tagCnt);
          
         TagResultBean bean=new TagResultBean("23.jpg","tagResult","13","13");//������ beanList�����Ѵ�. �˻������!!
         request.setAttribute("tagResult", bean);
         RequestDispatcher view=request.getRequestDispatcher("resources/views/tagSearch.jsp");
         view.forward(request, response);
      }
      else if("locationPage".equals(key)){//��ġ�˻� ���
         String locationName=request.getParameter("locationName");
         //������ �±׸����� ��ȸ�ϴ� ������ �־���Ѵ�.(��ȸ��� ������ �ʿ��ϴ�)
         System.out.println("PageMove-location page�� �̵� ��..��ġ�� : "+locationName);
         LocationResultBean bean=new LocationResultBean("23.13","134","23.jpg","9","7","locationResult");//������ beanList�����Ѵ�. �˻� �����!
         request.setAttribute("location", bean);
         
         request.setAttribute("locationName",locationName);
         
         //�˻� ��� ���丮�������� �����;��ϴ� �κ�
         // �˻� ��� -> 
         
         RequestDispatcher view=request.getRequestDispatcher("resources/views/mapSearch.jsp");
         view.forward(request, response);
      }
      DetailStory����
      else if("detailStory".equals(key)){
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         //���ǿ��� ID��ȯ
         HttpSession session=request.getSession();
         String user_id = (String)session.getAttribute("user_id");
         //�̹��� ID��ȯ
         String imgId = request.getParameter("imgId");
         System.out.println("StoryServlet-�󼼺��� Ŭ�� �̹��� : "+imgId);
         String[] tempArr = DynamicIdBinder.imgIdDisassemble(imgId);
         System.out.println("StoryServlet-�󼼺��� Ŭ�� �̹��� ��� : "+Arrays.toString(tempArr));
         //id,��,��,��,��,��,��
         String [] storyIdList = new String[]{tempArr[2],tempArr[3],tempArr[4],tempArr[5],tempArr[6],tempArr[7],tempArr[8]};
      
         //StoryService ��ü ���� ��ȯ
         StoryService service = StoryService.getInstance();
         String tempStoryId =  DynamicIdBinder.bulletinIdAssemble("true", user_id, storyIdList);
         String[]result = service.searchStoryService(user_id, tempStoryId);
         //ReviewService ��ü ���� ��ȯ
         ReviewService reviewService = ReviewService.getInstance();
         String[] reviewCnt = reviewService.getReviewsCount(tempStoryId);
         //(����0,����1,�浵2,��¥3,����4,�̹������ϸ�5,����6,��������7,��������8,���丮�ĺ�����9,�ۼ��ڸ�10) 
         Random ran=new Random();         
         JSONObject res = new JSONObject();
         res.put("result","ok");

         res.put("location",result[0]);
         //��¥�� ������ �״�� �ѷ��ش�.
         res.put("date",result[3]);
         res.put("year", 2017);
         res.put("month", 2);
         res.put("day", 15);
         res.put("dayOfWeek","ȭ");
         res.put("hour", 15);
         res.put("min", 40);
         res.put("weather", result[4]);
         res.put("img",result[5]);
         res.put("content", result[6]);
         res.put("id",result[9]);//���丮 �ĺ�Ű
         res.put("writer", result[10]);
         res.put("likeCnt",reviewCnt[0]);
         res.put("reviewCnt",reviewCnt[1]);
         res.put("isLike","true");//���� ���ƿ� ��������(�̰ɷ� ���ƿ� ���� �����ؾ��Ѵ�)
         res.put("num",ran.nextInt()%100);
         
         
          * ���� �󼼺���� ���⼭ ���ص� �ȴ�.
          * JSONArray reviewList=new JSONArray();
         String[][]showReviewList = reviewService.searchByBulletinId(tempStoryId);
         res[0] = bean.getWriterId();
         res[1] = bean.getReviewId();
         res[2] = bean.getBulletinId();
         res[3] = bean.getIsLike();
         res[4] = bean.getIsStory();
         res[5] = bean.getDate();
         res[6] = bean.getContent();
         for(int i = 0 ; i< showReviewList.length;++i){
            JSONObject obj=new JSONObject();
            res.put("img","story/admin_0000_00_00.jpg");
            res.put("num",ran.nextInt()%1000);
            obj.put("id",showReviewList[0]);
            obj.put("reviewId",showReviewList[1]);//���� �ĺ�Ű
            obj.put("date", showReviewList[5]);
            obj.put("content",showReviewList[6]);
            obj.put("year", 2017);
            obj.put("month", 2);
            obj.put("day", 15);
            obj.put("hour", 7);
            obj.put("min", 30);
            reviewList.add(obj);
            
         }
         res.put("reviewList", reviewList);
         PrintWriter writer = response.getWriter();
         writer.println(res);
      }
      mapsearch����
      else if("mapSearch".equals(key)){
           PrintWriter out=response.getWriter();
            String keyword=request.getParameter("keyword");
            JSONObject result =new JSONObject();
            //���� �˻� ��� ��ȯ
            JSONObject res=new JSONObject();
            JSONArray locationList=new JSONArray();
               JSONObject obj=new JSONObject();
               obj.put("lat","37.5563989");
               obj.put("lng","126.9160863");
               locationList.add(obj);
               JSONObject obj2=new JSONObject();
               obj2.put("lat","37.5663989");
               obj2.put("lng","126.9260863");
               locationList.add(obj2);
               JSONObject obj3=new JSONObject();
               obj3.put("lat","37.5663989");
               obj3.put("lng","126.9360863");
               locationList.add(obj3);
            result.put("locationList", locationList);
            

            if(keyword!=null&&!"".equals(keyword))
               result.put("result","success");
            else
               result.put("result","fail");
            
           
            out.println(result);
      }
      searchStroyServlet����
      else if("calenderSearch".equals(key)){
         request.setCharacterEncoding("utf-8");
         HttpSession session = request.getSession();
         String id = (String)session.getAttribute("user_id");
         String s_year = request.getParameter("s_year");
         String s_mon = request.getParameter("s_mon");
         String s_day = request.getParameter("s_day");
         String s_hour = request.getParameter("s_hour");
         String s_min = request.getParameter("s_min");
         String e_year = request.getParameter("e_year");
         String e_mon = request.getParameter("e_mon");
         String e_day = request.getParameter("e_day");
         String e_hour = request.getParameter("e_hour");
         String e_min = request.getParameter("e_min");
         
         System.out.println("SearchStory-doget : ����ھ��̵� - "+id+"���۳�¥- "+s_year+"��"+s_mon+"��"+s_day+"��"+s_hour+"��"+s_min+"��"+
                                         "\n���ᳯ¥ - "+e_year+"��"+e_mon+"��"+e_day+"��"+e_hour+"��"+e_min+"��");

         int s_yearNum, s_monNum, s_dayNum, s_hourNum, s_minNum,
               e_yearNum, e_monNum, e_dayNum, e_hourNum, e_minNum;
         String result = "��¥���ۿ���";
         String[][] searchList = null;         
         JSONObject storyResult =new JSONObject();
         try {
            if(id==null||"".equals(id))
               result = "���̵����ۿ���";
            else
               result = "success";
            
             * number format����ó��
             
            s_yearNum = Integer.parseInt(s_year);
            s_monNum = Integer.parseInt(s_mon);
            s_dayNum = Integer.parseInt(s_day);
            s_hourNum = Integer.parseInt(s_hour);
            s_minNum = Integer.parseInt(s_min);
            e_yearNum = Integer.parseInt(e_year);
            e_monNum = Integer.parseInt(e_mon);
            e_dayNum = Integer.parseInt(e_day);
            e_hourNum = Integer.parseInt(e_hour);
            e_minNum = Integer.parseInt(e_min);
            //�����ϰ� ����¥�� �־ ��� �޾ƿ´�.
            StoryService storyService = StoryService.getInstance();
            searchList = storyService.searchStoriesService(id, s_year+"_"+s_mon+"_"+s_day+"_"+s_hour+"_"+s_min+"_00", e_year+"_"+e_mon+"_"+e_day+"_"+e_hour+"_"+e_min+"_59");
            //�̹������ϸ�0, ����1, ��2, ���ƿ� ����3, ���� ����4      
               JSONArray resultList=new JSONArray();
               for(int i = 0; i < searchList.length;++i){
                  JSONObject obj=new JSONObject();
                  obj.put("img",searchList[i][0]);
                  obj.put("lat",searchList[i][1]);
                  obj.put("lng",searchList[i][2]);
                  obj.put("likeCnt",searchList[i][3]);
                  obj.put("reviewCnt",searchList[i][4]);
                 resultList.add(obj);
               }
               //ã�� ��ġ���� ��� �ְ�
               storyResult.put("locationList", resultList);   
         } catch (NumberFormatException e) {
            result = "��¥���ۿ���";
         } finally {
            //��� �ְ�
            storyResult.put("result", result);
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println(storyResult);
            writer.flush();
         }
      }
   }

   *//**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    *//*
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
       String key=request.getParameter("key");
       addStory����
       if("addStory".equals(key)){
             String path = getServletContext().getRealPath("/resources/images");
            PrintWriter outImg = response.getWriter();
            outImg.println("path :"+ path);
            //�������κ��� �Ѿ�� ������ ���
            MultipartRequest multi = new MultipartRequest(request, path,"utf-8");
            HttpSession session = request.getSession();
            String id = (String)session.getAttribute("user_id");
            String year = multi.getParameter("year");
            String mon = multi.getParameter("mon");
            String day = multi.getParameter("day");
            String userChoiceDate = multi.getParameter("date");
            String lat = multi.getParameter("lat");
             String lng = multi.getParameter("lng");
             String location = multi.getParameter("location");
             String content = multi.getParameter("content");
             String hour = multi.getParameter("hour");
             String min = multi.getParameter("min");
             String weather =multi.getParameter("weather");
             String open = multi.getParameter("open");
             String share = multi.getParameter("share");
             String fileTitle=multi.getFilesystemName("img");
             System.out.println("AddStory-dopost : �������"
                   +"\n ��¥ : "+userChoiceDate
                    +"\n ���� : "+lat
                    +"\n �浵 : "+lng
                      +"\n ���� : "+location
                      +"\n ���� : "+content
                      +"\n �ð� : "+hour
                      +"\n �� : "+min 
                      +"\n ���� : "+weather
                      +"\n ��������: "+open
                      +"\n �������� : "+share
                      +"\n ���ε� ���ϸ� :"+fileTitle);
             
            StringTokenizer token=new StringTokenizer(fileTitle,".");//��������(�ر�)
            String file=null;
            
            while(token.hasMoreTokens()){//���� Ȯ���� ������
               file=token.nextToken();
            }
            //������ �̹��� ���� ����� �����.
             GregorianCalendar cal = new GregorianCalendar();
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
             //�̹��� ���ϸ� �����Ѵ�.
             String imgFilePath = DynamicIdBinder.imgIdAssemble("true", id, 
                   new String[]{Integer.toString(cal.get(Calendar.YEAR)),
                   Integer.toString(cal.get(Calendar.MONTH)+1),
                   Integer.toString(cal.get(Calendar.DATE)), 
                   Integer.toString(cal.get(Calendar.HOUR_OF_DAY)),
                   Integer.toString(cal.get(Calendar.MINUTE)),
                   Integer.toString(cal.get(Calendar.SECOND))},file
                   );
             //���丮�ĺ������� �����Ѵ�.
             String storyId = DynamicIdBinder.bulletinIdAssemble("true", id, new String[]{Integer.toString(cal.get(Calendar.YEAR)),
                   Integer.toString(cal.get(Calendar.MONTH)+1),
                   Integer.toString(cal.get(Calendar.DATE)), 
                   Integer.toString(cal.get(Calendar.HOUR_OF_DAY)),
                   Integer.toString(cal.get(Calendar.MINUTE)),
                   Integer.toString(cal.get(Calendar.SECOND))});
             //������ ��ü�� �����Ѵ�
             DetailStorySaveBean saveBean=new DetailStorySaveBean(location,
                   lat,lng,year+"_"+mon+"_"+day+"_"+hour+"_"+min+"_00",weather,imgFilePath,content
                   ,open,share,null,id,storyId);//�����ϱ� ���� ������ ���� saveBean����
             //Service��ü�� ����õ�
             StoryService storyService = StoryService.getInstance();
             //�߰����
             String result = "fail";
             if(null!=storyService.addStoryService(id, storyId, saveBean))
                result = "success";
             //�������ϸ�����
            File old=new File(path+"\\"+fileTitle);//�������ϸ� ������
            File newFile=new File(path+"\\"+id+"_"+dateFormat+"."+file.toLowerCase());//�������ϴ� ���ϸ����� ���ϻ���
            if(newFile.isFile()){//������ �� �̸����� �ִٸ� �� ����
               newFile.delete();
            }
            old.renameTo(newFile);//���� ���ϸ� ��ü   
            //��� ��ȯ
            JSONObject resultObj=new JSONObject();
            System.out.println(path+"\\"+id+"_"+dateFormat+"."+file.toLowerCase());
            //�߰��� �Ϸ� �ϱ� ���� ��ȯ
            String[][] dayList = storyService.searchDiaryService(storyId,new String[]{year,mon,day});
             JSONArray resultList=new JSONArray();
               for(int i = 0; i < dayList.length;++i){
                  JSONObject obj=new JSONObject();
                  obj.put("img",dayList[i][0]);
                  obj.put("lat",dayList[i][1]);
                  obj.put("lng",dayList[i][2]);
                  obj.put("likeCnt",dayList[i][3]);
                  obj.put("reviewCnt",dayList[i][4]);
                 resultList.add(obj);
               }
               //ã�� ��ġ���� ��� �ְ�
               resultObj.put("locationList", resultList);
               resultObj.put("result","success");
            PrintWriter writer = response.getWriter();
            writer.print(resultObj);
       }
       reviseServlet����
       else if("modifyStory".equals(key)){
            Random ran=new Random();
            String path = getServletContext().getRealPath("/resources/images");

            MultipartRequest multi = new MultipartRequest(request, path, "utf-8");
            String location = multi.getParameter("location");
            String content = multi.getParameter("content");
            String hour = multi.getParameter("hour");
            String min = multi.getParameter("min");
            String weather = multi.getParameter("weather");
            String open = multi.getParameter("open");
            String share = multi.getParameter("share");
            String fileTitle = multi.getFilesystemName("img");

            System.out.println("ReviseStory-dopost : ���޹��� ����" 
            + "\n   ��ġ : "+ location 
            + "\n   ���� : " + content 
            + "\n   �� : " + hour
            + "\n   �� : " + min 
            + "\n   ���� : " + weather 
            + "\n   �������� : " + open 
            + "\n   �������� : " + share 
            + "\n   ���� �̐a : " + fileTitle);
            
            if(fileTitle==null){//-------------------------------------���� �������ε尡 �����ʾ����� ��񿡼� ������ �����ߴ� ���ϸ��� �������� ������ �־���Ѵ�.
               fileTitle="1540.jpg";
            }
            
            StringTokenizer token=new StringTokenizer(fileTitle,".");
            String file=null;
            
            while(token.hasMoreTokens()){//���� Ȯ���� ������
               file=token.nextToken();
            }
            
            File old=new File(path+"\\"+fileTitle);//�������ϸ� ������
            File newFile=new File(path+"\\"+hour+min+"."+file.toLowerCase());//�������ϴ� ���ϸ����� ���ϻ���
            if(newFile.isFile()){//������ �� �̸����� �ִٸ� �� ����
               newFile.delete();
            }
            old.renameTo(newFile);//���� ���ϸ� ��ü
            System.out.println(path+"\\"+hour+min+"."+file.toLowerCase());
            
            
            String result = "���丮 ������ ���� ����";
            JSONObject res = new JSONObject();
            int hourNum, minNum;
            try {
               if(location==null||"".equals(location))
                  result = "��ġ ���� ����";
               else
                  result = "success";
               hourNum = Integer.parseInt(hour);
               minNum = Integer.parseInt(min);
               
                * number format��ȿ������
                
               s_yearNum = Integer.parseInt(s_year);
               s_monNum = Integer.parseInt(s_mon);
               s_dayNum = Integer.parseInt(s_day);
               s_hourNum = Integer.parseInt(s_hour);
               s_minNum = Integer.parseInt(s_min);
               e_yearNum = Integer.parseInt(e_year);
               e_monNum = Integer.parseInt(e_mon);
               e_dayNum = Integer.parseInt(e_day);
               e_hourNum = Integer.parseInt(e_hour);
               e_minNum = Integer.parseInt(e_min);
               
               //��¥ ���� ��������� DB���� �����͸� �޾ƿ��� �ȴ�.
               
               result = "success";
               
            } catch (NumberFormatException e) {
               System.out.println("ReviseStory-dopost :NumberFormatException�߻� "+hour+" "+min);
               result = "���� ��¥ �Է� ���� ����";
            } finally {
               DetailStoryViewBean modiBean=new DetailStoryViewBean(location,//������ �Խñ� Bean���� ����
                     "2017.01.08"+hour+"."+min,weather,hour+min+"."+file.toLowerCase(),
                     content,null,"id","img","idKey","like","reviewCnt","reviewContent",
                     "WriterID","writerImg","reviewDate");
               
               //res.put("modiInfo",modiBean);
               
               res.put("fileName",hour+min+"."+file.toLowerCase());
               res.put("result", result);
               res.put("num",ran.nextInt()%100);//�Ȱ��� �����̸��� �������� �̹����� ������Ʈ �����ֱ� ���ؼ� �ߺ� ���������� ���� ������.
               response.setCharacterEncoding("UTF-8");
               PrintWriter writer = response.getWriter();
               writer.println(res);
               writer.flush();
            }
       }
   }*/
}