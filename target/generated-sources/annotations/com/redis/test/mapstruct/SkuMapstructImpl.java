package com.redis.test.mapstruct;

import com.redis.test.entity.Sku;
import com.redis.test.req.StoreSkuReq;
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
public class SkuMapstructImpl implements SkuMapstruct {

    @Override
    public List<StoreSkuReq> skuListToSkuReqList(List<Sku> skuList) {
        if ( skuList == null ) {
            return null;
        }

        List<StoreSkuReq> list = new ArrayList<StoreSkuReq>( skuList.size() );
        for ( Sku sku : skuList ) {
            list.add( skuToStoreSkuReq( sku ) );
        }

        return list;
    }

    protected StoreSkuReq skuToStoreSkuReq(Sku sku) {
        if ( sku == null ) {
            return null;
        }

        StoreSkuReq storeSkuReq = new StoreSkuReq();

        storeSkuReq.setId( sku.getId() );
        storeSkuReq.setSkuName( sku.getSkuName() );
        storeSkuReq.setSpuId( sku.getSpuId() );
        storeSkuReq.setPrice( sku.getPrice() );

        return storeSkuReq;
    }
}
