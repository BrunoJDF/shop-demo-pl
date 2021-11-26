package com.demo.shop.persistence.mapper;

import com.demo.shop.domain.dto.PurchaseItemDto;
import com.demo.shop.persistence.entity.PurchaseProduct;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {
    @Mappings({
            @Mapping(source = "id.idProduct", target = "productId"),
            // @Mapping(source = "quantity", target = "quantity"), si el nombre de los atributos son iguales no es necesario mapearlo
            // @Mapping(source = "total", target = "total"),
            @Mapping(source = "status", target = "active"),
    })
    PurchaseItemDto toPurchaseItemDto(PurchaseProduct purchaseProduct);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "purchaseOrder", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "id.idPurchase", ignore = true),
    })
    PurchaseProduct toPurchaseProduct(PurchaseItemDto purchaseItemDto);
}
