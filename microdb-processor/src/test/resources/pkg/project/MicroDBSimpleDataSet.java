package pkg.project;

import com.devsmart.microdb.DefaultChangeListener;
import com.devsmart.microdb.Driver;
import com.devsmart.microdb.Emitter;
import com.devsmart.microdb.MapFunction;
import com.devsmart.microdb.MicroDB;
import com.devsmart.microdb.SimpleDBModel_pxy;
import com.devsmart.microdb.Utils;
import com.devsmart.ubjson.UBValue;
import com.devsmart.ubjson.UBValueFactory;
import java.io.IOException;

public class MicroDBSimpleDataSet extends SimpleDataSet {

    private final MicroDB mDb;

    public MicroDBSimpleDataSet(MicroDB db) {
        mDb = db;
    }

    @Override
    public void install(MicroDB db) throws IOException {
        super.install(db);

        db.addIndex("SimpleDBModel.myString", new MapFunction<String>() {
            @Override
            public void map(UBValue value, Emitter<String> emitter) {
                if(Utils.isValidObject(value, SimpleDBModel_pxy.TYPE)) {
                    UBValue v = value.asObject().get("myString");
                    if(v != null && v.isString()) {
                        emitter.emit(v.asString());
                    }
                }
            }
        });

        db.addChangeListener(new DefaultChangeListener() {
            @Override
            public void onBeforeInsert(Driver driver, UBValue value) {
                if(Utils.isValidObject(value, SimpleDBModel_pxy.TYPE)) {
                    final long longValue = driver.incrementLongField("varSimpleDBModel.myLong");
                    value.asObject().put("myLong", UBValueFactory.createInt(longValue));
                }

            }

        });

        db.addIndex("SimpleDBModel.indexLong", new MapFunction<Long>() {
            @Override
            public void map(UBValue value, Emitter<Long> emitter) {
                if(Utils.isValidObject(value, SimpleDBModel_pxy.TYPE)) {
                    long v = value.asObject().get("indexLong").asLong();
                    emitter.emit(v);
                }
            }
        });

    }

    public Iterable<SimpleDBModel> querySimpleDBModelBymyString(String min, boolean minInclusive, String max, boolean maxInclusive) throws IOException {
        return mDb.queryIndex("SimpleDBModel.myString", SimpleDBModel.class, min, minInclusive, max, maxInclusive);
    }

    public Iterable<SimpleDBModel> querySimpleDBModelByindexLong(Long min, boolean minInclusive, Long max, boolean maxInclusive) throws IOException {
        return mDb.queryIndex("SimpleDBModel.indexLong", SimpleDBModel.class, min, minInclusive, max, maxInclusive);
    }

}