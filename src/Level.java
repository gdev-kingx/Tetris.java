public enum Level {
	
	ONE(48, 1), TWO(43, 2), THREE(38, 3), FOUR(33, 4), FIVE(28, 5), SIX(23, 6), SEVEN(18, 7), EIGHT(13, 8), NINE(6, 9),
	TEN(5, 10), ELEVEN(5, 11), TWELVE(5, 12), THIRTEEN(4, 13), FOURTEEN(4, 14), FIVETEEN(4, 15), SIXTEEN(3, 16),
	SEVENTEEN(3, 17), EIGHTTEEN(3, 18), NINETEEN(2, 19), TWENTY(2, 20), TWENTYONE(2, 21), TWENTYTWO(2, 22),
	TWENTYTHREE(2, 23), TWENTYFOUR(2, 24), TWENTYFIVE(2, 25), TWENTYSIX(2, 26), TWENTYSEVEN(2, 27), TWENTYEIGHT(2, 28),
	TWENTYNINE(2, 29), THIRTY(1, 30), THIRTYONE(1, 31), THIRTYTWO(1, 32);

	public static int startlevel = 0;
	public static int currlevel = startlevel;
	private final int[] incLines = new int[4];
	private final int speed;

	Level(final int speed, final int index) {
		incLines[0] = 40 * (1 + index);
		incLines[1] = 120 * (1 + index);
		incLines[2] = 300 * (1 + index);
		incLines[3] = 1200 * (1 + index);
		this.speed = speed;
	}

	int getIncreasment(int lines) {
		return incLines[lines - 1];
	}

	public int getSpeed() {
		return speed;
	}
}
