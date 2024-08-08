package info.jiheon.moviebooking.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SeatTest {

	@Test
	@DisplayName("좌석예약")
	void test0() {
		//given
		Seat seat = new Seat("A-01");
		UUID userId = UUID.randomUUID();
		//when
		seat.register(userId);
		//then
		assertEquals(userId, seat.getRegisterId());
	}

	@Test
	@DisplayName("좌석취소")
	void test1() {
		//given
		Seat seat = new Seat("A-01");
		UUID userId = UUID.randomUUID();
		seat.register(userId);
		seat.unregister(userId);
		assertNull(seat.getRegisterId());

	}

	@Test
	@DisplayName("한번 좌석이 예약되면, 다른 사람이 예약할 수 없다")
	void test2() {
		Seat seat = new Seat("A-01");
		UUID userId1 = UUID.randomUUID();

		seat.register(userId1);

		UUID userId2 = UUID.randomUUID();
		assertThatThrownBy(() -> seat.register(userId2))
			.isInstanceOf(IllegalStateException.class);

	}

	@Test
	@DisplayName("좌석은 예약된 사람만 취소할 수 있다")
	void test3() {
		Seat seat = new Seat("A-01");
		UUID userId1 = UUID.randomUUID();
		seat.register(userId1);

		UUID userId2 = UUID.randomUUID();
		assertThatThrownBy(() -> seat.unregister(userId2))
			.isInstanceOf(IllegalStateException.class);

	}
}