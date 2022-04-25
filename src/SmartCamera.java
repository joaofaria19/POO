public class SmartCamera extends SmartDevice{
    private double resolution;
    private int fileSize;

    public SmartCamera(){
        super();
        this.fileSize=0;
        this.resolution=0.0;
    }

    // Construtor opcional para apenas construir a SmartCamera com o seu respetivo id;
    public SmartCamera(String id){
        super(id);
        this.fileSize=0;
        this.resolution=0.0;
    }

    public SmartCamera(String id,double resolution,int fileSize){
        super(id);
        this.fileSize=fileSize;
        this.resolution=resolution;
    }

    public SmartCamera(SmartCamera sc){
        super(sc);
        this.fileSize=sc.getFileSize();
        this.resolution=sc.getResolution();
    }

    public double getResolution() {
        return resolution;
    }

    public void setResolution(double resolution) {
        this.resolution = resolution;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public boolean equals(Object o){
        if(o==this)return true;
        if(o==null || o.getClass()!= this.getClass()) return false;
        if(!super.equals(o)) return false;
        SmartCamera sc = (SmartCamera) o;
        return (this.resolution==sc.getResolution() && this.fileSize == sc.getFileSize() );
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("SmartCamera:{ ")
                .append(super.toString())
                .append(" File size: ")
                .append(this.fileSize)
                .append(" Resolution: ")
                .append(this.resolution)
                .append(" }\n");
        return sb.toString();
    }

    public SmartDevice clone(){
        return new SmartCamera(this);
    }

    @Override
    public double consumoDispositivo() {
        return this.resolution * this.fileSize;
    }


}
