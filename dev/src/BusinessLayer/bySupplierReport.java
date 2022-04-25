package BusinessLayer;

import java.util.List;

public class bySupplierReport extends Report {

    private List<ProductItem> bySupplierPro;
    String suppName;

    public bySupplierReport(String name, Integer id, String report_producer, List<ProductItem> bySupplierPro, String suppName) {
        super(name, id, report_producer);
        this.bySupplierPro = bySupplierPro;
        this.suppName=suppName;
    }

    public List<ProductItem> getBySupplierPro() { return bySupplierPro; }
    public String toString(){
        String s= super.toString()+ "The Items that "+ suppName+" is provides are:"+"\n";
        for (ProductItem p:bySupplierPro) {
            s=s+p.getId()+"\n";
        }
        return s;
    }
}