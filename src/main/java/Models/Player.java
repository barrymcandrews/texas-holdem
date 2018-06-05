package Models;


import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private String name;
    private Role role;
    private int wallet;
    private String[] AiNames = new String[] {"Bob", "Linda", "Tina", "Gene", "Louise", "Jimmy Jr.", "Teddy", "AndyenOllie"};
    private ArrayList<String> AiNamesList = new ArrayList<>();

    public Player(){this.wallet = 1000; AiNamesList.addAll(Arrays.asList(AiNames));}

    public Player(String name, Role role){
        this.name = name;
        this.role = role;
        this.wallet = 1000;

        AiNamesList.addAll(Arrays.asList(AiNames));
    }


    public void resetAiNames(String name){
        for(int i = 0; i < AiNamesList.size(); i++){
            if(AiNamesList.get(i).equals(name)){
                AiNamesList.remove(name);
            }
        }
    }

    public enum Role {
        PLAYER, DEALER
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String[] getAiNames() {
        return AiNames;
    }


    public ArrayList<String> getAiNamesList() {
        return AiNamesList;
    }


}
