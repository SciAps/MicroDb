package com.devsmart.microdb.ubjson;

import java.io.*;


public class UBReader implements Closeable {

    private final InputStream mInputStream;

    public UBReader(InputStream in) {
        mInputStream = in;
    }

    @Override
    public void close() throws IOException {
        mInputStream.close();
    }

    private byte readControl() throws IOException {
        int value = mInputStream.read();
        if(value == -1) {
            throw new IOException("eof");
        } else {
            return (byte)value;
        }
    }



    private UBValue readInt(byte control) throws IOException {
        long value;
        switch (control) {
            case UBValue.MARKER_INT8:
                value = readControl();
                break;

            case UBValue.MARKER_UINT8:
                value = (0xFF & readControl());
                break;

            case UBValue.MARKER_INT16:
                value = (readControl() & 0xFF) << 8 | (readControl() & 0xFF);
                break;

            case UBValue.MARKER_INT32:
                value = (readControl() & 0xFF) << 24 | (readControl() & 0xFF) << 16
                        | (readControl() & 0xFF) << 8 | (readControl() & 0xFF);
                break;

            case UBValue.MARKER_INT64:
                value = (readControl() & 0xFF) << 56 | (readControl() & 0xFF) << 48
                        | (readControl() & 0xFF) << 40 | (readControl() & 0xFF) << 32
                        | (readControl() & 0xFF) << 24 | (readControl() & 0xFF) << 16
                        | (readControl() & 0xFF) << 8 | (readControl() & 0xFF);
                break;

            default:
                throw new IOException("not an int type");

        }

        return UBValueFactory.createInt(value);
    }

    private UBValue readChar() throws IOException {
        char value = (char) readControl();
        return UBValueFactory.createChar(value);
    }

    private UBValue readFloat32() throws IOException {
        int intvalue = (readControl() & 0xFF) << 24 | (readControl() & 0xFF) << 16
                | (readControl() & 0xFF) << 8 | (readControl() & 0xFF);

        float value = Float.intBitsToFloat(intvalue);
        return UBValueFactory.createFloat32(value);
    }

    private UBValue readValue(byte control) throws IOException {
        UBValue retval = null;
        switch(control) {
            case UBValue.MARKER_NULL:
                retval = UBValueFactory.createNull();
                break;

            case UBValue.MARKER_TRUE:
                retval = UBValueFactory.createBool(true);
                break;

            case UBValue.MARKER_FALSE:
                retval = UBValueFactory.createBool(false);
                break;

            case UBValue.MARKER_CHAR:
                retval = readChar();
                break;

            case UBValue.MARKER_INT8:
            case UBValue.MARKER_UINT8:
            case UBValue.MARKER_INT16:
            case UBValue.MARKER_INT32:
            case UBValue.MARKER_INT64:
                retval = readInt(control);
                break;

            case UBValue.MARKER_FLOAT32:
                retval = readFloat32();
                break;

            case UBValue.MARKER_STRING:
                break;
        }

        return retval;
    }

    public UBValue read() throws IOException {
        byte control = readControl();
        return readValue(control);
    }
}
