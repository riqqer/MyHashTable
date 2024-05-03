public class MyTestingClass {
    private String temp;

    MyTestingClass(String temp){
        this.temp = temp;
    }

    public int hashCode(){
        int h = 53;
        for(int i=0; i<temp.length(); i++){
            h = h*53 + temp.charAt(i);
        }
        return h;
    }
}
