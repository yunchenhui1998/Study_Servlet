package com.xupt.hero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HeroDAO {
	public HeroDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
	}
	public int getTotal() {
		int total=0;
		String sql="select count(*) from hero";
		try (Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(sql)){
			ResultSet rs=statement.executeQuery();
			if(rs.next()) {
				total=rs.getInt(1);
			}
			System.out.println("total:"+total);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	public void addHero(Hero h) {
		String sql="insert into hero values(null,?,?,?)";
		try (Connection connection=getConnection();PreparedStatement statement=connection.prepareStatement(sql);){
			statement.setString(1,h.getName());
			statement.setFloat(2, h.getHp());
			statement.setInt(3,h.getDamage());
			statement.execute();
			ResultSet rs=statement.getGeneratedKeys();
			if(rs.next()) {
				int id=rs.getInt(1);
				h.setId(id);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void updateHero(Hero h) {
		String sql="update hero set name=?,hp=?,damage=? where id=?";
		try(Connection c=getConnection();PreparedStatement statement=c.prepareStatement(sql)){
			statement.setString(1, h.getName());
			statement.setFloat(2, h.getHp());
			statement.setInt(3, h.getDamage());
			statement.setInt(4,h.getId());
			statement.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteHero(int id) {
		String sql="delete from hero where id=?";
		try(Connection c=getConnection();PreparedStatement statement=c.prepareStatement(sql)){
			statement.setInt(1, id);
			statement.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public Hero getHero(int id) {
		Hero h=null;
		String sql="select * from hero where id=?";
		try(Connection c=getConnection();PreparedStatement ps=c.prepareStatement(sql)){
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				h=new Hero();
				h.setId(id);
				h.setName(rs.getString(2));
				h.setHp(rs.getFloat(3));
				h.setDamage(rs.getInt(4));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return h;
	}
	public List<Hero> listHero(int start,int count){
		List<Hero> list=new ArrayList<>();
		String sql="select * from hero limit ?,?";
		try(Connection c=getConnection();PreparedStatement ps=c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Hero h=new Hero();
				h.setId(rs.getInt(1));
				h.setName(rs.getString(2));
				h.setHp(rs.getFloat(3));
				h.setDamage(rs.getInt(4));
				list.add(h);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Hero> listHero(){
		return listHero(0,Short.MAX_VALUE);
	}
}
