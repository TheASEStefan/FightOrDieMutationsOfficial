package net.teamabyssal.constants;

import net.minecraft.util.Mth;

public class MathHelper {

    public static final float PI = 3.14F;
    public static final float DELTA = (PI * 3 * Mth.floor(PI)) / 4;
    public static final float OMEGA = DELTA - PI + Mth.clamp(10, PI, DELTA);
    public static final float LAMBDA = Mth.lerp(DELTA, PI, OMEGA) + (float) Math.atan(DELTA - PI);
    public static final float SENTINEL  = Math.abs(OMEGA - DELTA) + ((float) PI / 2 * LAMBDA) - (float) (LAMBDA / 4);



}
