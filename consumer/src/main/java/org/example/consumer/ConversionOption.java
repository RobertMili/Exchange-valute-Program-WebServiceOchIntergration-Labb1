package org.example.consumer;

public enum ConversionOption {
    EXIT(0),
    CONVERT_TO_SEK(1),
    CONVERT_TO_DOLLAR(2),
    CONVERT_TO_HRK(3);

    private final int choice;

    ConversionOption(int choice) {
        this.choice = choice;
    }

    public static ConversionOption getByChoice(int choice) {
        for ( ConversionOption option : values()) {
            if(option.choice == choice) {
                return option;
            }
        }
        return null;
    }
}
