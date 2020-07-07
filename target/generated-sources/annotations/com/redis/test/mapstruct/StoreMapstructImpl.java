package com.redis.test.mapstruct;

import com.redis.test.entity.Store;
import com.redis.test.req.StoreReq;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-16T16:59:02+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_192 (Oracle Corporation)"
)
@Component
public class StoreMapstructImpl implements StoreMapstruct {

    @Override
    public StoreReq storeToStoreReq(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreReq storeReq = new StoreReq();

        storeReq.setId( store.getId() );
        storeReq.setStoreName( store.getStoreName() );
        storeReq.setLat( store.getLat() );
        storeReq.setLon( store.getLon() );

        return storeReq;
    }
}
