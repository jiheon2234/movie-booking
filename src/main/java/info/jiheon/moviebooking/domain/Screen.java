package info.jiheon.moviebooking.domain;

import java.util.List;
import java.util.UUID;

import lombok.NonNull;

/**
 * 상영관
 */
public interface Screen {

	/**
	 * 예약 가능한 좌석 반환
	 *
	 * @return 예약가능여부 2차원 배열
	 */
	boolean[][] getBookingStatus();

	/**
	 * 예약 메서드
	 *
	 * @param userId   예약자 ID
	 * @param row      row idx
	 * @param colStart 예약할 가장 왼쪽 좌석
	 * @param cnt      예약할 좌석 수
	 * @return 예약된 좌석 이름들 ex)["A-01"]
	 */
	List<String> book(@NonNull UUID userId, int row, int colStart, int cnt);

	/**
	 * 예약취소 메서드
	 *
	 * @param userId 유저아이디
	 */
	void cancelBooking(@NonNull UUID userId);

}
