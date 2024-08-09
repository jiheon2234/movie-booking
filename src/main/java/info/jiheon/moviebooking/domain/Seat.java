package info.jiheon.moviebooking.domain;

import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Seat {

	/* 좌석번호(생성시 부여) */
	private final String seatName;

	/* 예약한 유저 아이디*/
	private UUID bookedUserId;

	/**
	 * @return 예약가능여부
	 */
	public boolean canBook() {
		return Objects.isNull(bookedUserId);
	}

	/**
	 * @param userId 예약하는 유저의 UUID
	 * @throws IllegalStateException 이미 예약된 좌석을 예약할 경우
	 */
	public void book(@NonNull UUID userId) {
		if (!canBook()) {
			throw new IllegalStateException("%s번 좌석은 이미 예약됨".formatted(seatName));
		}
		this.bookedUserId = userId;
	}

	/**
	 * 예약취소
	 *
	 * @param userId 예약을 취소하는 유저의 UUID
	 * @throws IllegalStateException 좌석이 예약되지 않은 상태인 경우 또는 예약한 유저가 아닌 경우
	 */
	public void cancelBooking(@NonNull UUID userId) {
		if (canBook()) {
			throw new IllegalStateException("%s번 좌석은 예약되지 않은 상태입니다".formatted(seatName));
		}
		if (!bookedUserId.equals(userId)) {
			throw new IllegalStateException("%s번 좌석은 %s만 취소가능".formatted(seatName, bookedUserId));
		}
		this.bookedUserId = null;
	}

	/**
	 * @param rowI rowIdx
	 * @param colI colIdx
	 */
	public Seat(int rowI, int colI) {
		this.seatName = String.format("%c-%02d", 'A' + rowI, colI + 1);
	}

}
