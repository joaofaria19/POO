public class SmartCamera extends SmartDevice{
    private double resolution;
    private int fileSize;
    private double consumoDiario;

    public SmartCamera(){
        super();
        this.fileSize=0;
        this.resolution=0.0;
        this.consumoDiario=0.0;
    }

    // Construtor opcional para apenas construir a SmartCamera com o seu respetivo id;
    public SmartCamera(String id){
        super(id);
        this.fileSize=0;
        this.resolution=0.0;
        this.consumoDiario=0.0;

    }

    public SmartCamera(String id,double resolution,int fileSize,double consumoDiario){
        super(id);
        this.fileSize=fileSize;
        this.resolution=resolution;
        this.consumoDiario=consumoDiario;
    }

    public SmartCamera(SmartCamera sc){
        super(sc);
        this.fileSize=sc.getFileSize();
        this.resolution=sc.getResolution();
        this.consumoDiario=sc.getConsumoDiario();
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

    public double getConsumoDiario() {
        return this.consumoDiario;
    }

    public void setConsumoDiario(double consumoDiario) {
        this.consumoDiario = consumoDiario;
    }


    public boolean equals(Object o){
        if(o==this)return true;
        if(o==null || o.getClass()!= this.getClass()) return false;
        if(!super.equals(o)) return false;
        SmartCamera sc = (SmartCamera) o;
        return (this.resolution==sc.getResolution() && this.fileSize == sc.getFileSize() && this.consumoDiario==sc.getConsumoDiario());
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(" » SmartCamera:{ ")
                .append(super.toString())
                .append(" File size: ")
                .append(this.fileSize)
                .append(" Resolution: ")
                .append(this.resolution)
                .append(" Consumo Diario: ")
                .append(this.consumoDiario)
                .append(" }\n");
        return sb.toString();
    }

    public SmartDevice clone(){
        return new SmartCamera(this);
    }

    @Override
    public double consumoEnergetico() {
        return this.resolution * this.fileSize*this.consumoDiario;
    }


}
