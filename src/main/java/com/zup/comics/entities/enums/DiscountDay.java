package com.zup.comics.entities.enums;

public enum DiscountDay {

	MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

	private int code;

	private DiscountDay(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static DiscountDay valueOf(int code) {
		for (DiscountDay value : DiscountDay.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid DiscountDay code");
	}
}
