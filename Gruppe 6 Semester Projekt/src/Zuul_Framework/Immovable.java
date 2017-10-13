package Zuul_Framework;

/**
 *
 * @author Wilde
 */
public class Immovable extends Destructables{
    private String name, description, useDescription;
    private boolean destructible;

    public Immovable(String name, String description, String useDescription, boolean destructible){
        this.name = name;
        this.description = description;
        this.useDescription = useDescription;
        this.destructible = destructible;
        
         
    }
    
    //public method to open cabinets (amoury/ hallway)
    public void openCabinet(){
        
    }   
    
    //public method to break the table in the armoury
    public void breakTable(){
        //check to see if table is destructible before running the rest
        if (this.destructible == true) {
            System.out.println("You break the leg off the table \n a bunch of"
                    + "notes fall on the floor.");
            //changes the useDescription of the table.
            this.useDescription = "There is a leg missing from the table";
        /*    
        **event when breaking table
        **remove notes from table
        **pickup table leg
        */               
        }
        else
            System.out.println("You can't break this.");
        
    }

        
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
