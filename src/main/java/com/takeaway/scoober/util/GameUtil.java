package com.takeaway.scoober.util;

import java.util.Arrays;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.takeaway.scoober.domain.Player;

public class GameUtil {

	public static int[] allowedArray = new int[] { -1, 0, 1 };

	/**
	 * When a player starts, it intercepts a random (whole) number and sends it to the
	 * second player as an approach of starting the game. The receiving player can
	 * now always choose between adding one of {-1, 0, 1} to get to a number that is
	 * divisible by 3. Divide it by three. The resulting whole number is then sent
	 * back to the original sender.
	 * 
	 * @param number
	 * @param playerName
	 * @return Player object
	 */
	public static Player play(int number, String playerName) {

		Assert.isTrue(2 <= number, "Number should be greater than or equal to 2");
		Assert.isTrue(!StringUtils.isEmpty(playerName), "Player Name should not be null");

		Integer addedNumber = Arrays.stream(allowedArray).boxed().filter(value -> ((number + value) % 3 == 0))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid number - " + number));

		Integer resultingNumber = (number + addedNumber) / 3;

		return Player.builder().name(playerName).addedNumber(addedNumber).resultingNumber(resultingNumber).build();

	}

}
