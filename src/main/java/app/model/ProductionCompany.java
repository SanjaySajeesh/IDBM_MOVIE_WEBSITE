package app.model;





public class ProductionCompany {
    private String name;
    private int id;

   
    public ProductionCompany(String n, String id) {
        name = n;
        this.id = Integer.parseInt(id);
    }

    public String getName() {
        return name;
    }
    public int getID(){
        return id;
    }
    
}
