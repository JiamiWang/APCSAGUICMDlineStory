package net.spicycombo.jiamingwang.storyproj;

public class MyStory extends Story {    
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
        if (attempt != null) {
            curScene = attempt;
            UpdateInterfaces();
            return true;
        }
        return false;
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
            case "END1" -> new Scene("END1", "It ended.");
            case "END2" -> new Scene("END2", "It also ended");
            case "1" -> new Scene("1", "Welcome!", "END1|You're done!", "END2|You are done, but this is second");
            default -> null;
        };
    }
}
