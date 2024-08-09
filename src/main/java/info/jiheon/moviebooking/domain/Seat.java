package info.jiheon.moviebooking.domain;

import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Seat {

	/* 좌석번호(생성시 부여) */
	private final String SEAT_NAME;

	/* 예약한 유저 아이디*/
	private UUID registerId;

	/**
	 * @return 예약가능여부
	 */
	public boolean canBook() {
		return Objects.isNull(registerId);
	}

	/**
	 * @param userId 예약하는 유저의 UUID
	 * @throws IllegalStateException 이미 예약된 좌석을 예약할 경우
	 */
	public void book(@NonNull UUID userId) {
		if (!canBook()) {
			throw new IllegalStateException("%s번 좌석은 이미 예약됨".formatted(SEAT_NAME));
		}
		this.registerId = userId;
	}

	/**
	 * 예약취소
	 *
	 * @param userId 예약을 취소하는 유저의 UUID
	 * @throws IllegalStateException 좌석이 예약되지 않은 상태인 경우 또는 예약한 유저가 아닌 경우
	 */
	public void cancelBook(@NonNull UUID userId) {
		if (canBook()) {
			throw new IllegalStateException("%s번 좌석은 예약되지 않은 상태입니다".formatted(SEAT_NAME));
		}
		if (!registerId.equals(userId)) {
			throw new IllegalStateException("%s번 좌석은 %s만 취소가능".formatted(SEAT_NAME, registerId));
		}
		this.registerId = null;
	}

	/**
	 * 유일한 생성자
	 *
	 * @param seatName 생성시 자동으로 부여될 좌석번호 EX) A-01
	 */
	public Seat(@NonNull String seatName) {
		this.SEAT_NAME = seatName;
	}

}
