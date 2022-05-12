package adss_group_k.inventory.ServiceLayer.Objects;

import adss_group_k.inventory.BusinessLayer.DiscountPair;

import java.time.LocalDateTime;
import java.util.List;

public class ProductItem {
    public int getId() {
        return id;
    }

    private int id;
    private String store;
    private String location;
    private String supplier;
    private LocalDateTime expirationDate;
    private List<adss_group_k.inventory.ServiceLayer.Objects.DiscountPair> man_discount;
    private List<adss_group_k.inventory.ServiceLayer.Objects.DiscountPair> cus_discount;
    private boolean is_defect;
    private String defect_reporter;

    public ProductItem(adss_group_k.inventory.BusinessLayer.ProductItem PItem) {
        id=PItem.getId();
        store=PItem.getStore();
        location= PItem.getLocation();
        supplier= PItem.getSupplier();
        expirationDate= PItem.getExpirationDate();
        is_defect= PItem.isIs_defect();
        defect_reporter= PItem.getDefect_reporter();
        List<DiscountPair> BusinessCus_discount = PItem.getCus_discount();
        for (DiscountPair dp: BusinessCus_discount) {
            cus_discount.add(new adss_group_k.inventory.ServiceLayer.Objects.DiscountPair(dp));
        }
        List<DiscountPair> BusinessMan_discount = PItem.getMan_discount();
        for (DiscountPair dp: BusinessMan_discount) {
            man_discount.add(new adss_group_k.inventory.ServiceLayer.Objects.DiscountPair(dp));
        }
    }
}
