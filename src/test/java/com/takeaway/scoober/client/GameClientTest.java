package com.takeaway.scoober.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.takeaway.scoober.domain.Player;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
args = {"--playerName=krishna","--port=9000","--nextPlayerName=shijin","--nextPlayerAPIUrl=localhost:8081/api/game"} )
@DisplayName("Game Client Test")
class GameClientTest {
	
	@Autowired
	private GameClient gameClient;
	
	@Value("${nextPlayer.name}")
	private String nextPlayerName;

	@Test
	@DisplayName("Test play with valid Number")
	void testPlay() {
		// Given , When
		Player player = gameClient.play(56);

		// Then
		assertEquals(player.getResultingNumber(), 19);
		assertEquals(player.getAddedNumber(), 1);
		assertEquals(player.getName(), nextPlayerName);
	}

}
