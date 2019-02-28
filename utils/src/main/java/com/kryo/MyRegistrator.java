package com.ac.kryo;

import com.esotericsoftware.kryo.Kryo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.serializer.KryoRegistrator;

/**
 * Created by aochong on 2018-10-22.
 */
public class MyRegistrator implements KryoRegistrator {

    @Override
    public void registerClasses(Kryo arg0) {
        arg0.register(ConsumerRecord.class);
    }

}
