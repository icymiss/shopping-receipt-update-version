public enum Location {
    CALIFORNIA("CA"),
    NEW_YORK("NY");

    private String shortCode;

    Location(String shortCode){
            this.shortCode = shortCode;
    }
    public String getShortCode(){
            return shortCode;
        }
}

