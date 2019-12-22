package com.xupt.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xupt.hero.Hero;
import com.xupt.hero.HeroDAO;

public class HeroUpdateServlet extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Hero hero=new Hero();
		hero.setName(request.getParameter("name"));
		hero.setHp(Float.parseFloat(request.getParameter("hp")));
		hero.setDamage(Integer.parseInt(request.getParameter("damage")));
		hero.setId(Integer.parseInt(request.getParameter("id")));
		new HeroDAO().updateHero(hero);
		response.sendRedirect("listHero");
	}
}
