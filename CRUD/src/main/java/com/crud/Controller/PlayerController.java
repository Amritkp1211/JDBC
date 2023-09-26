package com.crud.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.crud.Repository.PlayerRepository;
import com.crud.entity.Players;

@RestController
public class PlayerController {

	@Autowired
	private PlayerRepository playerRepository;
	
	@PostMapping("/players")
	public ResponseEntity<Players> insertPlayer(@RequestBody Players players) {
		   int i = playerRepository.insertplayers(players);
		   if(i!=0) {
			   return ResponseEntity.status(HttpStatus.CREATED).build() ;
		   }
		   else {
			   return new ResponseEntity("player not inserted",HttpStatus.INTERNAL_SERVER_ERROR);
		   }
		
	}
	@GetMapping("/players")
	public ResponseEntity<List<Players>> getAllplayers(){
		List<Players> list = playerRepository.getAllplayers();
		if(list.isEmpty()) {
			return new ResponseEntity("list is empty",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity("list fetch successfully",HttpStatus.OK).ok(list);
	}
	@GetMapping("/players/{id}")
	public ResponseEntity<Players> getPlayerByID(@PathVariable("id") int id) throws PlayerNotFoundException {
		  Players players=null;
		try {
			  players=playerRepository.getPlayerById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PlayerNotFoundException();
		}
		return ResponseEntity.status(HttpStatus.OK).body(players);
	}
	
	@PutMapping("/players")
	public ResponseEntity<Players> updatePlayer(@RequestBody Players players) {
		
		int i = playerRepository.updatePlayers(players);
		 if(i!=0) {
			 return new ResponseEntity("player deleted successfully",HttpStatus.OK);
		 }
		 return new ResponseEntity("player deleted successfully",HttpStatus.OK);
	}
	
	
	@DeleteMapping("/players/{id}")
	public ResponseEntity<Players> deletePlayer(@PathVariable("id") int id) throws PlayerNotFoundException{

		 boolean i=playerRepository.deletePlayer(id);
		
		 if(i==true) {
			 return new ResponseEntity("player deleted successfully",HttpStatus.OK);
		 }
		 else {
		    return new ResponseEntity("players not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
    	   }
	}
	
}
