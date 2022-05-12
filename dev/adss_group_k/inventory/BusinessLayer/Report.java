package adss_group_k.inventory.BusinessLayer;

import java.util.Date;
public class Report {

    enum ReportType{Missing,Expired,Surpluses,Defective,bySupplier,byProduct,byCategory}

    static protected Integer id=0;
    protected String name;
    protected Date date;
    protected String report_producer;

    public Report(String name, String report_producer) {
        this.name=name;
        this.id=id;
        id++;
        date=new Date();
        this.report_producer=report_producer;
    }
    public Integer getId() { return id; }

    public String getName() { return name; }

    public Date getDate() { return date; }

    public String getReport_producer() { return report_producer; }
    public String toString(){
        return "Id: "+id+"\n"+ "name: "+name+"\n"+ "date: "+date+"\n"+ "report_producer: "+report_producer+"\n";
    }
}
