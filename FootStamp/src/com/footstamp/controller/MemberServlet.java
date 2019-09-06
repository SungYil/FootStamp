package com.footstamp.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.footstamp.bean.MemberBean;
import com.footstamp.service.MemberService;

@Controller
public class MemberServlet {

   @Autowired
   private MemberService memberService;

   @RequestMapping(value = "/login.do", method = RequestMethod.POST)
   public ModelAndView login(HttpServletRequest request,
         HttpServletResponse response,
         @RequestParam(required = false) String id,
         @RequestParam(required = false) String pwd) {
      // 전달 데이터 확인
      System.out.println("로그인 요청");
      System.out.println(id + "/" + pwd);

      String msg = null;

      // 아이디 조건 8~16자리 시작은 숫자 불가능
      String id_check = "[^0-9가-힣ㄱ-ㅎㅏ-ㅣ]{1}[^가-힣ㄱ-ㅎㅏ-ㅣ]{7,15}";
      // 비밀번호 조건 한글빼고 8~16글자
      String pwd_check = "[^가-힣ㄱ-ㅎㅏ-ㅣ]{8,16}";
      int res = memberService.checkPassword(id, pwd);
      if ("".equals(id)) {
         // request.setAttribute("error", "아아디를 입력하세요.");
         msg = "3";
      } else if ("".equals(pwd)) {
         // request.setAttribute("error", "비밀번호를 입력하세요.");
         msg = "4";
      } else if (!Pattern.matches(id_check, id)) {
         // request.setAttribute("error",
         // "아이디는 8~16글자 가능합니다(한글제외,숫자로 시작 불가능).");
         msg = "5";
      } else if (!Pattern.matches(pwd_check, pwd)) {
         // request.setAttribute("error", "비밀번호는 8~16글자 가능합니다(한글 제외).");
         msg = "6";
      }
      // 아아디 없는 경우와 비밀번호 맞지 않는 경우 추가해야한다.
      else if (res == 2) {// 아이디가 없는 경우
         msg = "1";
      } else if (res == 0) {// 비밀번호가 틀린경우
         msg = "2";
      } else {// 로그인 성공
         // 해당하는 고객 찾아온다.
         MemberBean member = memberService.searchMemberService(id);
         // 세션에 아이디,비밀번호 저장
         HttpSession session = request.getSession();
         session.setAttribute("member", member);
         response.addCookie(new Cookie("id", member.getId()));
         response.addCookie(new Cookie("pwd", member.getPwd()));
         msg = "0";
      }

      ModelAndView mnv = new ModelAndView();
      mnv.addObject("msg", msg);
      mnv.setViewName("forward:/resources/views/loginByMobile.jsp");
      return mnv;
   }

   @RequestMapping(value = "/logining.do")
   public ModelAndView loging(HttpServletRequest request, HttpSession session) {
      System.out.println("로그인 유지 요청");
      Cookie[] cookies1 = request.getCookies();
      System.out.println("쿠키-----------------------------------------------------------");
      if(cookies1!=null)
      for(Cookie co:cookies1){
         System.out.println(co.getName()+"/"+co.getValue());
      }
      // 세션 유지되고 있는건지 확인한다.
      MemberBean member = (MemberBean) session.getAttribute("member");
      System.out.println("로그인 되있는 사람 : " + member);
      ModelAndView mnv = new ModelAndView();
      mnv.setViewName("forward:/resources/views/logining.jsp");
      if (member != null) {// 로그인 되있는 경우
         mnv.addObject("result", 0);
         return mnv;
      } else {// 여기서 아이디와 비밀번호 확인한다.세션은 만료되어도 아이디와 비밀번호가 있으면 로그인 유지
         Cookie[] cookies = request.getCookies();
         String id = null;
         String pwd = null;
         System.out.println("쿠키-----------------------------------------------------------");
         if(cookies!=null)
         for(Cookie co:cookies){
            System.out.println(co.getName()+"/"+co.getValue());
         }
         if(cookies!=null)
         for (Cookie co : cookies) {
            if ("id".equals(co.getName())) {// 쿠키 key에 id잇을 경우
               id = co.getValue();
            }
            if ("pwd".equals(co.getName())) {
               pwd = co.getValue();
            }
         }
         // 아이디와 비번 모두 구한 경우
         if (id != null && pwd != null) {
            member = memberService.searchMemberService(id);
            // 아이디,비밀번호 존재하고 일치할 경우 로그인 유지
            if (pwd.equals(member.getPwd())) {
               // 해당 세션에 추가한다.
               session.setAttribute("member", member);
               mnv.addObject("result", 0);
               return mnv;
            }
         }
         mnv.addObject("result", 1);
      }
      return mnv;
   }
   
   @RequestMapping(value="/signup.do", method=RequestMethod.POST)
   //커맨드 객체 사용하기 ,커맨드 객체는 일반 객체 말하는거다
   public ModelAndView singup(HttpServletRequest request,HttpServletResponse response,MemberBean member) throws IOException {
      System.out.println("회원가입");   
      response.setCharacterEncoding("utf-8");
      System.out.println(member);
      //유효성 검증하는 부분필요
      int result=-1;//아이디 등록 요청 결과코드
      String id=member.getId();
      String name=member.getName();
      String pwd=member.getPwd();
      String call=member.getCall();
      // "[^0-9가-힣ㄱ-ㅎㅏ-ㅣ]{1}[^가-힣ㄱ-ㅎㅏ-ㅣ]{7,15}";
      String id_check = "[^0-9가-힣ㄱ-ㅎㅏ-ㅣ][^가-힣ㄱ-ㅎㅏ-ㅣ]{7,15}";// 아이디
      String pwd_check = "[^가-힣ㄱ-ㅎㅏ-ㅣ]{8,16}";// 비밀번호
      String name_check = "[가-힣]{1,20}";// 이름 
      String call_check = "[^. \\-\\(\\)0-9]+";// 전화번호인지 이메일인지 구분하기 위해서
      String phone_check = "^([0-9]{3}|([0-9]){3})[-. ]?([0-9]{3,4})[-. ]?([0-9]{4}$)";// 전화번호
      String email_check="^([a-zA-Z0-9]{1,40})@[a-zA-Z0-9]{2,20}.[a-zA-Z0-9]{2,10}(.[a-zA-Z0-9]{2,10})?";   
      member.setProfileImg("default_myimage.png");//이미지 빼고 다받고 이미지만 디폴트로 설정
      String msg=null;
      // 아이디 확인(중복 체크는 서버에서)
      if ("".equals(id.trim())) {
         //"아아디를 입력하세요"
         msg="1";
      } else if (!Pattern.matches(id_check,id)) {
         //"아이디는 8~16글자 가능합니다(한글제외,숫자로 시작 불가능)."
         msg="2";
      }
      else if ("".equals(pwd.trim())) {
         //"비밀번호를 입력하세요"
         msg="3";
      } 
      else if (!Pattern.matches(pwd_check, pwd)) {
         //"비밀번호는 8~16글자 가능합니다(한글 제외)."
         msg="4";
      }
      // 이름 확인
      else if ("".equals(name.trim())) {
         //"이름을 입력하세요."
         msg="5";
      } 
      else if (!Pattern.matches(name_check, name)) {
         //"이름은 한글 20글자까지 가능합니다."
         msg="6";
      }
      else if ("".equals(call)) {
         //"이메일이나 연락처를 입력하세요."
         msg="7";
      }
      else{
         result=memberService.addMemberService(member);
      }
      // 이메일
      /*else if (Pattern.matches(call_check, call)) {
         if (!Pattern.matches(email_check, call)) {
            //"올바른 이메일 주소를 입력해주세요."
            msg="8";
         }
         else{
            result=memberService.addMemberService(member);
         }
      }
      // 연락처
      else if (!Pattern.matches(call_check, call)) {
         if (!Pattern.matches(phone_check, call_check)) {
            //"연락처가 정확하지 않습니다."
            msg="9";
         }
         else{
            result=memberService.addMemberService(member);
         }
      }*/
      if(result==0){
         //"이미 존재하는 아이디입니다"
         msg="10";
      }
      else if(result==1){
         //"회원가입 성공했습니다"
         msg="0";
      }
      System.out.println("결과 코드 값은?! : "+msg);
      ModelAndView mnv=new ModelAndView();
      mnv.addObject("msg", msg);
      mnv.setViewName("/resources/views/signupByMobile");
      return mnv;
   }
   
}