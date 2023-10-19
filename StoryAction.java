
/**
 * Represents a story action
 *
 * @author Jiaming Wang
 * @version 1, 19 Oct 2023
 */

public class StoryAction
{
    private String id, content, option1, option2;
    
    public String getId() { return id; }
    public String getContent() { return content; }
    public String getOption1() { return option1; }
    public String getOption2() { return option2; }
    
    public StoryAction(String id, String content, String option1, String option2) {
        this.id = id; this.content = content; this.option1 = option1; this.option2 = option2;
    }
}
