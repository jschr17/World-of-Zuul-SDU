package Zuul_Framework;

/**
 *
 * @author Wilde
 */
public class Immovable implements Interactables /*extends Destructables*/{
    private String name, description, useDescription;
    private boolean destructible, flag;
    private final boolean isPickupable = false;
    private Item item;
    
    /**
     *
     * @param name is the name of the Immovable being made, e.g. "table".
     * @param description is the description of the Immovable, e.g. "this is a table. There are some notes on the table".
     * @param useDescription is the description printed when you use the object, e.g. "you open the cabinet door".
     * @param destructible is the boolean that is true if the object can be the atarget of the "break" command.
     * @param flag is a boolean that is set to true, if the immovable is locked in some way.
     */
    public Immovable(String name, String description, String useDescription, boolean destructible, boolean flag){
        this.name = name; 
        this.description = description; 
        this.useDescription = useDescription; 
        this.destructible = destructible; 
        this.flag = flag; 
    }
    
    //public method to open cabinets (amoury/ hallway/ airlock)
    public void openCabinet(){
        if (this.flag == true) {
            System.out.println("You can't open this. It's locked. ");
        }
        else {
            System.out.println(useDescription);
            /*
            Open cabinet event (knives/ weapon inside/ oxygen-bottle access)
            */
        }
    }   
    
    //public method to break the table in the armoury
    public void breakTable(){
        //check to see if table is destructible before running the rest
        if (this.destructible == true) {
            System.out.println("You break the leg off the table \n a bunch of"
                    + "notes fall on the floor.");
            //changes the description of the table.
            this.description = "There is a leg missing from the table";
            destructible = false; // since the immovable is broken, it can't eb broken more.
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
        
    public String getDescription(){
        return description;
    }
        
    public String getUseDescription(){
        return useDescription;
    }
    
    // method to unlock radio array and weapon cabinet (break the lock - use this method)
    public void unlock(){
        this.flag = false;
    }
    
    // sets any items the immovable might contain
    public void setItems(Item item){
        this.item = item;
    }
    
    public Item takeItem(){
        Item returnItem = this.item;
        this.item = null;
        return returnItem;
    }
}
