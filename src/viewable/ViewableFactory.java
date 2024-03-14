package viewable;

import main.Agent;

public class ViewableFactory {

    public Viewable getViewable(Agent agent) {
        
        if ("Circle".equals(agent.getViewableType())) {
            return new ViewableCircle(agent);
        }
        // viewableType might be null or not match one of the above
        return new ViewableCircle(agent);
    }

}