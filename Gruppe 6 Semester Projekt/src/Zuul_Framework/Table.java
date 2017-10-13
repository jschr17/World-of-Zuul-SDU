package Zuul_Framework;

/**
 *
 * @author Wilde
 */
public class Table extends Destructables{
    private String name, description, useDescription;
    private boolean destructible;

    public Table(String name, String description, String useDescription, boolean destructible){
        this.name = name;
        this.description = description;
        this.useDescription = useDescription;
        this.destructible = destructible;
        
         
    }
        
    //public method break table
        //event when breaking table
        //print "you broke the table"
        //remove notes from table
        //pickup table leg
        //change useDescription
        
    public String getName(){
        return name;
    }
        
    @Override /*Why does netbeans say this needs to be here?*/
    public String getDescription(){
        return description;
    }
        
    public String getUseDescription(){
        return useDescription;
    }
}
