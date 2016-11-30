package thelibrarians.sulibraryapp;

/**
 * Created by Andy on 11/25/2016.
 */
public class RoomDetail{
    String name,description,directions, section;
    int icon, room_id, group_id, capacity;

    public RoomDetail(String name, int room_id, int group_id, String description, int capacity, String directions){
        setName(name);
        setRoomID(room_id);
        setGroupID(group_id);
        setDescription(description);
        setCapacity(capacity);
        setDirections(directions);
        section = StudyRoomReserveFragment.sections[0];
        if (capacity <= 4) {
            setIcon(R.drawable.group_study_small);
        }
        else if (capacity <= 8) {
            setIcon(R.drawable.group_study_medium);
        }
        else{
            setIcon(R.drawable.group_study_large);
        }

    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setIcon(int res){
        icon = res;
    }

    public int getIcon(){
        return icon;
    }

    public void setRoomID(int room_id){
        this.room_id = room_id;
    }

    public int getRoomID(){
        return room_id;
    }

    public void setGroupID(int group_id){
        this.group_id = group_id;
    }

    public int getGroupID(){
        return group_id;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setDirections(String directions){
        this.directions = directions;
    }

    public String getDirections(){
        return directions;
    }

    public void setSection(String section){
        this.section = section;
    }

    public String getSection(){
        return section;
    }
}