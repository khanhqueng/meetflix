package com.khanhisdev.payment_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String itemid;
    private String itename;
    private int itemprice;
    private int itemquantity;
}
