package adss_group_k.inventory.BusinessLayer;

import java.util.List;

public class byCategoryReport extends Report {

    private List<Product> byCategoryPro;
    private String catName;

    public byCategoryReport(String name, String report_producer, List<Product> byCategoryPro, String catName) {
        super(name,report_producer);
        this.byCategoryPro=byCategoryPro;
        this.catName=catName;
    }

    public List<Product> getByCategoryPro() { return byCategoryPro; }
    public String toString(){
        String s= super.toString()+ "The products in "+ catName+" category are:"+"\n";
        for (Product p:byCategoryPro) {
            s=s+p.getName()+"\n";
        }
        return s;
    }
}
