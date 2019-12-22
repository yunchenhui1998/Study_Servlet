package com.xupt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xupt.hero.Hero;
import com.xupt.hero.HeroDAO;

public class HeroListServlet extends HttpServlet{
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		List<Hero> heros=new HeroDAO().listHero();
		StringBuffer sb=new StringBuffer();
		sb.append("<table align='center' border='1' cellspacing='0'>\r\n");
		sb.append("<tr><td>id</td><td>name</td><td>hp</td><td>damage</td><td>edit</td><td>delete</td></tr>\r\n");
		String trFormat="<tr><td>%d</td><td>%s</td><td>%f</td><td>%d</td><td><a href='editHero?id=%d'>edit</a></td><td><a href='deleteHero?id=%d'>delete</a></td></tr>\r\n";
		for(Hero h:heros) {
			String tr=String.format(trFormat, h.getId(),h.getName(),h.getHp(),h.getDamage(),h.getId(),h.getId());
			sb.append(tr);
		}
		sb.append("</table>");
		response.getWriter().write(sb.toString());
	}
}
