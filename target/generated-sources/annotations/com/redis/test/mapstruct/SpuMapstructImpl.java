package com.redis.test.mapstruct;

import com.redis.test.entity.Spu;
import com.redis.test.req.StoreSpuReq;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-16T16:59:02+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_192 (Oracle Corporation)"
)
@Component
public class SpuMapstructImpl implements SpuMapstruct {

    @Override
    public List<StoreSpuReq> spuListToSpuReqList(List<Spu> spuList) {
        if ( spuList == null ) {
            return null;
        }

        List<StoreSpuReq> list = new ArrayList<StoreSpuReq>( spuList.size() );
        for ( Spu spu : spuList ) {
            list.add( spuToStoreSpuReq( spu ) );
        }

        return list;
    }

    protected StoreSpuReq spuToStoreSpuReq(Spu spu) {
        if ( spu == null ) {
            return null;
        }

        StoreSpuReq storeSpuReq = new StoreSpuReq();

        storeSpuReq.setId( spu.getId() );
        storeSpuReq.setSpuName( spu.getSpuName() );
        storeSpuReq.setStoreId( spu.getStoreId() );

        return storeSpuReq;
    }
}
