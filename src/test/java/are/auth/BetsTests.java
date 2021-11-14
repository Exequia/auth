package are.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.common.collect.Iterables;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import are.auth.entities.bets.Bet;
import are.auth.entities.bets.BetProfile;
import are.auth.repositories.bets.IBetsRepository;

@SpringBootTest
class BetsTests {

	@Autowired
	private IBetsRepository betsRepository;

	private String name ="1234567890asdf";

	@Test
	void contextLoads() {
	}

	@Test
	void saveNewBet_shouldBeValid() {
		Bet bet = new Bet();
		bet.setName(name);
		bet.setModel("{}");

		BetProfile profile = new BetProfile();
		profile.setId(1L);

		bet.setProfile(profile);
		Bet newBet = this.betsRepository.save(bet);
		assertEquals(bet.getName(), newBet.getName());
		assertNotNull(newBet.getId());
	}

	@Test
	void deleteBet_shouldBeValid() {
		Iterable<Bet> bets = this.betsRepository.findAll();
		int size = Iterables.size(bets);
		this.betsRepository.delete(Iterables.getLast(bets));
        assertEquals(size - 1, this.betsRepository.count());
	}
}
