package model;

/**
 * Created by ADMIN on 21-11-2016.
 */

public class M_locresponce {

    /**
     * validSave : {"status":"true"}
     */

    private ValidSaveBean validSave;

    public ValidSaveBean getValidSave() {
        return validSave;
    }

    public void setValidSave(ValidSaveBean validSave) {
        this.validSave = validSave;
    }

    public static class ValidSaveBean {
        /**
         * status : true
         */

        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
