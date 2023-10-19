public class MyStory extends Story {
    
    
    
    public String name() {
        return "MyStory";
    }
    
    protected boolean ValidateOption(String option) {
        return false;
    }
    
    public String getSceneText(String id) {
        return "";
    }
}
