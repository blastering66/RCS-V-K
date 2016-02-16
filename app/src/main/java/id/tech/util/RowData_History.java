package id.tech.util;

/**
 * Created by RebelCreative-A1 on 15/12/2015.
 */
public class RowData_History  {
    public String stock_qty_warehouse,stock_qty_display,stock_qty_unit,nama_toko,region_toko,stock_name;

    public RowData_History(String stock_qty_warehouse, String stock_qty_display, String stock_qty_unit, String nama_toko, String region_toko, String stock_name) {
        this.stock_qty_warehouse = stock_qty_warehouse;
        this.stock_qty_display = stock_qty_display;
        this.stock_qty_unit = stock_qty_unit;
        this.nama_toko = nama_toko;
        this.region_toko = region_toko;
        this.stock_name = stock_name;
    }
}
