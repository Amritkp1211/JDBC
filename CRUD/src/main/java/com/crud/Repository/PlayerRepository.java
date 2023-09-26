package com.crud.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crud.Controller.PlayerNotFoundException;
import com.crud.entity.Players;

@Repository
public class PlayerRepository {
   
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertplayers(Players players) {
		
		
		   return this.jdbcTemplate.update("insert into players(name,city) values(?,?)",players.getName(),players.getCity());
         
	}
	
	public List<Players> getAllplayers(){
		 List<Players> list = this.jdbcTemplate.query("select * from players",new BeanPropertyRowMapper<Players>(Players.class));
	          return list;
	}
	public Players getPlayerById(int id) throws PlayerNotFoundException {
		Players players=null;
		try {
			players = this.jdbcTemplate.queryForObject("select * from players where id =?"
					,new Object[]{id},new BeanPropertyRowMapper<Players>(Players.class));
			
		} catch (PlayerNotFoundException e) {
		            e.printStackTrace();
		            throw new PlayerNotFoundException();
		}	
         return players;
	}
	
	public int updatePlayers(Players players) {
			
		 return jdbcTemplate.update("update players set name=?,city=? where id=?"
				    ,players.getName(),players.getCity(),players.getId());	
		 
		 
	}
	public boolean deletePlayer(int id) throws PlayerNotFoundException{
		int update = jdbcTemplate.update("delete from players where id=?",id);
		if(update!=0) {
			return true;
		}
		else {
			throw new PlayerNotFoundException();
		}
	}
	
	}
   

