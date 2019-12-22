package com.xupt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xupt.hero.Hero;
import com.xupt.hero.HeroDAO;

public class HeroEditServlet extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		int id=Integer.parseInt(request.getParameter("id"));
		Hero hero=new HeroDAO().getHero(id);
		StringBuffer sb=new StringBuffer();
		sb.append("<!DOCTYPE html>");
		sb.append("<form action='updateHero' method='post'>");
		sb.append("名字：<input type='text' name='name' value='%s'><br>");
		sb.append("血量：<input type='text' name='hp' value='%f'><br>");
		sb.append("攻击力：<input type='text' name='damage' value='%d'><br>");
		sb.append("<input type='hidden' name='id' value='%d'>");
		sb.append("<input type='submit' value='更新'>");
		sb.append("</form>");
		String html=String.format(sb.toString(),hero.getName(),hero.getHp(),hero.getDamage(),hero.getId());
		response.getWriter().write(html);
	}
}
