package net.spicycombo.jiamingwang.storyproj;

public class MyStory extends Story { 
    private boolean ended;
    
    protected String name() {
        return "MyStory";
    }

    public void start() {
        if (started) return;

        curScene = getScene("1"); // anything, .-.
        started = true;
    }

    protected boolean ValidateOption(String option) {
        Scene attempt = getScene(option);
        if (attempt != null && curScene.getOptions().containsKey(option)) {
            curScene = attempt;
            UpdateInterfaces();
            return true;
        }
        return false;
    }

    protected boolean getIsEnded() {
        return ended;
    }
    
    protected boolean isEndScene(Scene s) {
        String id = s.getSceneId(); // too much to type out
        boolean ended;
        if (s.getSceneId().equals("9")) {
            boolean dead = 0.2 <= Math.random();
            
            if (dead) s.setScene(s.getScene()
                .replace("{0}", "explosive bomb")
                .replace("{1}", " You died as of result."));
            else s.setScene(s.getScene()
                .replace("{0}", "a few gold coins"
                .replace("{1}", "")));
            ended = dead;
        } else ended = id.equals("5") || id.equals("13") || id.equals("14") || id.equals("16");
        
        return ended;
    }

    // new syntax was recommended by the IDE (not using BlueJ). I'm however used to
    /*
    switch (item) {
        case ...:
            return ...; // or do something and break;
        default ...:
            return ...; // or do something and break;
    }

    */

    protected Scene getScene(String id) {
        return switch (id) {
            case "1" -> new Scene(
                "1",
                "You woke up, and you are in a forest. There are trees everywhere, and a lot of vegetation is formed around you. You see three moons in the sky, and a path to take. What would you like to do? ",
                "2|Sit down on the floor",
                "3|Take the path"
                );
            case "2" -> new Scene(
                "2",
                "You sit at the area for a while, time passes by, nothing happens.",
                "3|Take the path",
                "4|Keep sitting down"
                );
            case "3" -> new Scene(
                "3",
                "You started walking down the path. As time passes by, you see more cool trees, and you find a red X mark. What would you like to do?",
                "7|Examine the area",
                "6|Start digging at the X mark",
                "8|Keep walking down the path"
                );
            case "4" -> new Scene(
                "4",
                "4. You kept sitting down. As time goes on, it starts raining.",
                "5|Keep sitting down",
                "3|Take the path"
                );
            case "5" -> new Scene(
                "5",
                "The rain stopped, but your knees started hurting. A mysterious white cube start following you, and you couldn't escape it, so it killed you.",
                "1|Restart"
                );
            case "6" -> new Scene(
                "6",
                "You start digging at the red X mark, and you find a crate.",
                "9|Open the crate",
                "8|Don't open the crate"
                );
            case "7" -> new Scene(
                "7",
                "You examine the area. It's nothing special, just plain old dirt and a red X.",
                "3|Return to previous scene");
            case "8" -> new Scene(
                "8",
                "8. You find yourself at the end of a mountain, and inside there is a mine shaft.",
                "10|Enter the mineshaft",
                "11|Climb over the mountain"
                );
            case "9" -> new Scene(
                "9",
                "You open the crate, and you find a {0}.{1}",
                "8|Walk down the path"
                );
            case "10" -> new Scene(
                "10",
                "You enter the mineshaft, and find some diamonds in ore. You collected it and kept going.",
                "12|Continue"
                );
            case "11" -> new Scene(
                "11",
                "You arrived at the top of the mountain. Wow, what an amazing scenery that you see in front of you. It's just the beautiful sky, lakes, and waterfall.",
                "14|Jump down to the lake",
                "15|Walk down to the other side"
                );
            case "12" -> new Scene(
                "12",
                "You find a mysterious orb, and a hole with lots of light.",
                "13|Interact with the orb",
                "11|Go to the hole"
                );
            case "13" -> new Scene(
                "13",
                "You touch the orb, and suddenly got teleport back to your house. It is late at night, you open the door, and don't know if this is a dream. Well, either way, you can't wait to tell your friends about what just happened.",
                "1|Restart"
                );
            case "14" -> new Scene(
                "14",
                "14. This isn't Minecraft, because you got fall damage in the water. Plus, the lake was deep, and you couldn't swim. You drowned and died.",
                "1|Restart"
                );
            case "15" -> new Scene(
                "15",
                "You got to the other side of the mountain. You see a path walk like the path on the other side of the mountain.",
                "16|Walk down the path"
                );
            case "16" -> new Scene(
                "16",
                "You see an old man with a wooden house. He told you that he has lived here for a while, and he has a plane to take you back to the mainland. You received a small badge from him for memorabilia, and you thanked him as you left the plane.",
                "1|Restart");
            default -> null;
        };
    }
}
