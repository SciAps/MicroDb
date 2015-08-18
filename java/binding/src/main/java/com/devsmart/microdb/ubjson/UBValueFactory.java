package com.devsmart.microdb.ubjson;


public class UBValueFactory {

    private static final UBNull VALUE_NULL = new UBNull();
    private static final UBBool VALUE_TRUE = new UBBool(true);
    private static final UBBool VALUE_FALSE = new UBBool(false);

    public static UBNull createNull() {
        return VALUE_NULL;
    }

    public static UBChar createChar(char value) {
        return new UBChar(value);
    }

    public static UBBool createBool(boolean value) {
        if(value) {
            return VALUE_TRUE;
        } else {
            return VALUE_FALSE;
        }
    }

    private static boolean inRange(long value, long min, long max) {
        return value >= min && value <= max;
    }

    public static UBValue createInt(long value) {
        if(inRange(value, 0, 255)) {
            return new UBUInt8(value);
        } else if(inRange(value, -128, 127)) {
            return new UBInt8(value);
        } else if(inRange(value, -32768, 32767)) {
            return new UBInt16(value);
        } else if(inRange(value, -2147483648, 2147483647)) {
            return new UBInt32(value);
        } else {
            return new UBInt64(value);
        }
    }

    public static UBFloat32 createFloat32(float value) {
        return new UBFloat32(value);
    }

    public static UBFloat64 createFloat64(double value) {
        return new UBFloat64(value);
    }

    public static UBString createString(byte[] string) {
        return new UBString(string);
    }

    public static UBString createString(String string) {
        return createString(string.getBytes(UBString.UTF_8));
    }

    public static UBInt8Array createArray(byte[] value) {
        return new UBInt8Array(value);
    }

    public static UBFloat32Array createArray(float[] value) {
        return new UBFloat32Array(value);
    }

    public static UBFloat64Array createArray(double[] value) {
        return new UBFloat64Array(value);
    }

    public static UBArray createArray(UBValue[] value) {
        return new UBArray(value);
    }
}
