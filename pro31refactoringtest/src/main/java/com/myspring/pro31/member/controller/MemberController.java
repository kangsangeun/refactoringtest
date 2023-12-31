package com.myspring.pro31.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.myspring.pro31.member.vo.MemberVO;

public interface MemberController {
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView addMember(MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
