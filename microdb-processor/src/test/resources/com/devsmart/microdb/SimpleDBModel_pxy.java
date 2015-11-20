package com.devsmart.microdb;

import com.devsmart.ubjson.UBArray;
import com.devsmart.ubjson.UBObject;
import com.devsmart.ubjson.UBString;
import com.devsmart.ubjson.UBValue;
import com.devsmart.ubjson.UBValueFactory;
import java.util.UUID;

import pkg.project.MyDatum;
import pkg.project.SimpleDBModel;


public final class SimpleDBModel_pxy extends SimpleDBModel {

    public static final UBString TYPE = UBValueFactory.createString("SimpleDBModel");

    @Override
    public void writeUBObject(UBObject data) {
        super.writeUBObject(data);
        data.put("type", TYPE);
        data.put("myString", UBValueFactory.createStringOrNull(getMyString()));
        data.put("myBool", UBValueFactory.createBool(getMyBool()));
        data.put("myByte", UBValueFactory.createInt(getMyByte()));
        data.put("myShort", UBValueFactory.createInt(getMyShort()));
        data.put("myInt", UBValueFactory.createInt(getMyInt()));
        data.put("myLong", UBValueFactory.createInt(getMyLong()));

        {
            SimpleDBModel inst = getInternal();
            if(inst == null) {
                data.put("internal", UBValueFactory.createNull());
            } else {
                UBObject obj = UBValueFactory.createObject();
                inst.writeUBObject(obj);
                data.put("internal", obj);
            }
        }
        data.put("link", link.getId());
        data.put("myFloatArray", UBValueFactory.createArrayOrNull(getMyFloatArray()));
        data.put("myDoubleArray", UBValueFactory.createArrayOrNull(getMyDoubleArray()));
        data.put("addresses", Utils.toUBArray(getAddresses()));
        data.put("genericValue", UBValueFactory.createValueOrNull(getGenericValue()));

        {
            MyDatum datum = getMyDatum();
            if(datum == null) {
                data.put("myDatum", UBValueFactory.createNull());
            } else {
                data.put("myDatum", datum.toUBValue());
            }
        }

    }

    @Override
    public void init(UUID id, UBObject obj, MicroDB db) {
        super.init(id, obj, db);
        if (obj.containsKey("myString")) {
            super.setMyString(obj.get("myString").asString());
        }
        if (obj.containsKey("myBool")) {
            super.setMyBool(obj.get("myBool").asBool());
        }
        if (obj.containsKey("myByte")) {
            super.setMyByte(obj.get("myByte").asByte());
        }
        if (obj.containsKey("myShort")) {
            super.setMyShort(obj.get("myShort").asShort());
        }
        if (obj.containsKey("myInt")) {
            super.setMyInt(obj.get("myInt").asInt());
        }
        if (obj.containsKey("myLong")) {
            super.setMyLong(obj.get("myLong").asLong());
        }
        if (obj.containsKey("internal")) {
            UBValue value = obj.get("internal");
            if(value.isNull()) {
                super.setInternal(null);
            } else {
                SimpleDBModel_pxy tmp = new SimpleDBModel_pxy();
                tmp.init(null, value.asObject(), db);
                super.setInternal(tmp);
            }
        }
        link = new Link<SimpleDBModel>(obj.get("link"), db, SimpleDBModel_pxy.class);
        if (obj.containsKey("myFloatArray")) {
            super.setMyFloatArray(obj.get("myFloatArray").asFloat32Array());
        }
        if (obj.containsKey("myDoubleArray")) {
            super.setMyDoubleArray(obj.get("myDoubleArray").asFloat64Array());
        }
        if (obj.containsKey("addresses")) {
            UBArray input = obj.get("addresses").asArray();
            final int size = input.size();
            SimpleDBModel_pxy[] output = new SimpleDBModel_pxy[size];
            for (int i = 0; i < size; i++) {
                SimpleDBModel_pxy tmp = new SimpleDBModel_pxy();
                tmp.init(null, input.get(i).asObject(), db);
                output[i] = tmp;
            }
            super.setAddresses(output);
        }
        if (obj.containsKey("genericValue")) {
            super.setGenericValue((UBValue)obj.get("genericValue"));
        }
        if (obj.containsKey("myDatum")) {
            MyDatum datum;
            UBValue value = obj.get("myDatum");
            if(value.isNull()) {
                datum = null;
            } else {
                datum = new MyDatum();
                datum.fromUBValue(value);
            }
            super.setMyDatum(datum);
        }
    }


    @Override
    public void setMyString(String value) {
        super.setMyString(value);
        mDirty = true;
    }

    @Override
    public void setMyBool(boolean value) {
        super.setMyBool(value);
        mDirty = true;
    }

    @Override
    public void setMyByte(byte value) {
        super.setMyByte(value);
        mDirty = true;
    }

    @Override
    public void setMyShort(short value) {
        super.setMyShort(value);
        mDirty = true;
    }

    @Override
    public void setMyInt(int value) {
        super.setMyInt(value);
        mDirty = true;
    }

    @Override
    public void setMyLong(long value) {
        super.setMyLong(value);
        mDirty = true;
    }

    @Override
    public void setInternal(SimpleDBModel value) {
        super.setInternal(value);
        mDirty = true;
    }

    @Override
    public void setMyFloatArray(float[] value) {
        super.setMyFloatArray(value);
        mDirty = true;
    }

    @Override
    public void setMyDoubleArray(double[] value) {
        super.setMyDoubleArray(value);
        mDirty = true;
    }

    @Override
    public void setAddresses(SimpleDBModel[] value) {
        super.setAddresses(value);
        mDirty = true;
    }

    @Override
    public void setGenericValue(UBValue value) {
        super.setGenericValue(value);
        mDirty = true;
    }

    @Override
    public void setMyDatum(MyDatum value) {
        super.setMyDatum(value);
        mDirty = true;
    }
}