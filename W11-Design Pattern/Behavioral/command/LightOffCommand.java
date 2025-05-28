public class LightOffCommand implements Command{
    private  Light light;

    LightOffCommand (Light light){
        this.light = light;
    }

    public  void execute (){
        light.off();
    }
}
