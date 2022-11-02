package me.portfolioof.library.enums;

public enum RiskLevel {
    SAFE(10.0F),
    MODERATE(30.0F),
    HIGH(50.0F),
    EXTREME(70.0F);

    private final float val;

    RiskLevel(float val) {
        this.val = val;
    }

    public float getVal() {
        return val;
    }
}
