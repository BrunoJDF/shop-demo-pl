package com.demo.shop.persistence.mapper;

import com.demo.shop.domain.dto.PurchaseDto;
import com.demo.shop.persistence.entity.PurchaseOrder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = PurchaseItemMapper.class)
public interface PurchaseMapper {
    /** Los atributos de la izquierda SOURCE, son los atributos desde donde se va realizar el mapeo hacia el TARGET
     * **/
    @Mappings({
            @Mapping(source = "id", target = "purchaseId"),
            @Mapping(source = "idClient", target = "clientId"),
            @Mapping(source = "status", target = "active"),
            @Mapping(source = "productList", target = "items")
    })
    PurchaseDto toPurchaseDto(PurchaseOrder purchaseOrder);
    List<PurchaseDto> toPurchaseListDto(List<PurchaseOrder> purchaseOrderList);

    /** Se debe colocar todos los campos a mapear en caso no se coloque se debe ignorar
     * */
    @Mappings({
            @Mapping(target = "client", ignore = true)
    })
    @InheritInverseConfiguration
    PurchaseOrder toPurchaseOrder(PurchaseDto purchaseDto);
}
