package com.myspring.pro31.member.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.pro31.member.service.MemberService;
import com.myspring.pro31.member.vo.MemberVO;

@Controller(value = "memberController")
public class MemberControllerImpl implements MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
	
	@Autowired // Spring의 의존성 주입 기능에 의해 자동 연결되도록 생성자,필드,setter 메서드 또는 구성 메소드를 표시한다.
	private MemberService memberService;

//	public void setMemberService(MemberServiceImpl memberService) {
//		this.memberService = memberService;
//	}
	
	@Autowired
	private MemberVO MemberVO;
	

//	@RequestMapping(value = "/member/listMembers.do", method = RequestMethod.GET)
	@RequestMapping(value = {"/","/main.do"}, method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		
		logger.info("info 레벨 : viewName= " + viewName);
//		logger.debug("debug 레벨 : viewName= " + viewName);
		
		List<MemberVO> membersList = memberService.listMembers();
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}
	
	@RequestMapping(value = "/member/memberForm.do")
	public String form(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "memberForm";
	}

	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/",1), fileName.length());
		}
		
		return fileName;
	}

	@Override
	@RequestMapping(value ="/member/addMember.do",method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int result = 0;
		result = memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}





}
