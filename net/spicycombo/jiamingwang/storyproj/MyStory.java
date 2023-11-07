package net.spicycombo.jiamingwang.storyproj;

public class MyStory extends Story {    
    protected String name() {
        return "MyStory";
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
    
    protected Scene getScene(String id) {
        switch (id) {
            case "":
                return null;
            default:
                return null;
        }
    }
}
